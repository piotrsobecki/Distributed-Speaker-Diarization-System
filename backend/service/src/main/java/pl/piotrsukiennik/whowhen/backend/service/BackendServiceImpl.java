package pl.piotrsukiennik.whowhen.backend.service;

import org.springframework.stereotype.Component;
import pl.piotrsukiennik.whowhen.backend.api.IRequestIdentifierBound;
import pl.piotrsukiennik.whowhen.backend.api.inner.balancer.BalancerService;
import pl.piotrsukiennik.whowhen.backend.api.inner.classification.ClassificationRequest;
import pl.piotrsukiennik.whowhen.backend.api.inner.classification.ClassificationResponse;
import pl.piotrsukiennik.whowhen.backend.api.inner.convertion.ConvertionRequest;
import pl.piotrsukiennik.whowhen.backend.api.inner.convertion.ConvertionResponse;
import pl.piotrsukiennik.whowhen.backend.api.inner.processing.ProcessingRequest;
import pl.piotrsukiennik.whowhen.backend.api.inner.processing.ProcessingResponse;
import pl.piotrsukiennik.whowhen.backend.api.inner.splitter.SplitterRequest;
import pl.piotrsukiennik.whowhen.backend.api.inner.splitter.SplitterResponse;
import pl.piotrsukiennik.whowhen.backend.api.inner.util.AudioInfo;
import pl.piotrsukiennik.whowhen.backend.api.outer.*;
import pl.piotrsukiennik.whowhen.shared.event.ProgressNotifier;
import pl.piotrsukiennik.whowhen.shared.form.RequestData;
import pl.piotrsukiennik.whowhen.shared.memcached.MemcachedClientWrapper;
import pl.piotrsukiennik.whowhen.shared.util.progress.Progress;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Piotr Sukiennik
 */
@Component( "backendServiceImpl" )
public class BackendServiceImpl implements IBackendService {

    @Resource
    private MemcachedClientWrapper memcachedClientWrapper;

    @Resource
    private BalancerService balancerService;

    @Resource
    private ProgressNotifier progressNotifier;


    @Override
    public String[] getAcceptableFormats() {
        String[] acceptableFormats = memcachedClientWrapper.get( MemcachedKeyInterface.ACCEPTABLE_FORMATS_KEY, String[].class );
        if ( acceptableFormats == null ) {
            acceptableFormats = balancerService.getAcceptableFormats();
            memcachedClientWrapper.put( MemcachedKeyInterface.ACCEPTABLE_FORMATS_KEY, acceptableFormats );
        }
        return acceptableFormats;
    }

    @Override
    public BackendResponse handle( BackendRequest backendRequest ) {
        MemcachedKeyInterface memcachedKeyInterface = new MemcachedKeyInterface( backendRequest.getRequestIdentifier() );
        memcachedClientWrapper.put( memcachedKeyInterface.requestKey(), backendRequest.getRequestData() );
        updateProgress( new Progress(), 0, "CONVERTING", memcachedKeyInterface.progressKey() );
        ConvertionRequest convertionRequest = new ConvertionRequest( backendRequest.getRequestIdentifier(), backendRequest.getSubmittedFile() );
        balancerService.handle( convertionRequest );
        progressNotifier.notifyAll( 0, backendRequest.getRequestIdentifier(), backendRequest.getRequestData() );
        return new BackendResponse( backendRequest.getRequestIdentifier() );
    }

    @Override
    public GetTimelineResponse handle( GetTimelineRequest getLabelledIntervalsRequest ) {
        MemcachedKeyInterface memcachedKeyInterface = new MemcachedKeyInterface( getLabelledIntervalsRequest.getRequestIdentifier() );
        List<Map<String, List<double[]>>> levelledLabeledIndexIntervals = new ArrayList<Map<String, List<double[]>>>();
        List<List<String>> levelledLabels = memcachedClientWrapper.get( memcachedKeyInterface.getLabelsWithLevels(), List.class );
        for ( int i = 0; i < levelledLabels.size(); i++ ) {
            List<String> labels = levelledLabels.get( i );
            Map<String, List<double[]>> timelineForLevel = new LinkedHashMap<String, List<double[]>>();
            levelledLabeledIndexIntervals.add( timelineForLevel );
            for ( String label : labels ) {
                timelineForLevel.put( label, memcachedClientWrapper.get( memcachedKeyInterface.getLabelTimeline( label ), List.class ) );
            }
        }
        return new GetTimelineResponse( levelledLabeledIndexIntervals );
    }

    @Override
    public GetProgressResponse getProgress( GetProgressRequest getProgressRequest ) {
        return new GetProgressResponse( getProgress( (IRequestIdentifierBound) getProgressRequest ) );
    }

    public Progress getProgress( IRequestIdentifierBound requestIdentifierBound ) {
        MemcachedKeyInterface memcachedKeyInterface = new MemcachedKeyInterface( requestIdentifierBound.getRequestIdentifier() );
        return memcachedClientWrapper.get( memcachedKeyInterface.progressKey(), Progress.class );
    }

    @Override
    public void notify( ConvertionResponse convertionResponse ) {
        MemcachedKeyInterface memcachedKeyInterface = new MemcachedKeyInterface( convertionResponse.getRequestIdentifier() );
        Progress progress = getProgress( convertionResponse );
        updateProgress( progress, 25, "PROCESSING", memcachedKeyInterface.progressKey() );
        progressNotifier.notifyAll( 25, convertionResponse.getRequestIdentifier() );
        memcachedClientWrapper.put( memcachedKeyInterface.convertedAudioInfoResponseKey(), convertionResponse.getConvertedAudioInfo() );
        ProcessingRequest processingRequest = new ProcessingRequest( convertionResponse.getRequestIdentifier(), convertionResponse.getConvertedAudioInfo() );
        balancerService.handle( processingRequest );
    }

    @Override
    public void notify( ProcessingResponse processingResponse ) {
        MemcachedKeyInterface memcachedKeyInterface = new MemcachedKeyInterface( processingResponse.getRequestIdentifier() );
        Progress progress = getProgress( processingResponse );
        updateProgress( progress, 50, "CLASSIFYING", memcachedKeyInterface.progressKey() );
        progressNotifier.notifyAll( 50, processingResponse.getRequestIdentifier() );
        memcachedClientWrapper.put( memcachedKeyInterface.processingResponseIntervalLength(), processingResponse.getFeaturesIndexLengthMillis() );
        RequestData requestData = memcachedClientWrapper.get( memcachedKeyInterface.requestKey(), RequestData.class );
        ClassificationRequest classificationRequest = new ClassificationRequest( processingResponse.getRequestIdentifier(), processingResponse.getFeatures(), requestData.getSpeakersCount() );
        balancerService.handle( classificationRequest );

    }

    @Override
    public void notify( ClassificationResponse classificationResponse ) {
        MemcachedKeyInterface memcachedKeyInterface = new MemcachedKeyInterface( classificationResponse.getRequestIdentifier() );
        Progress progress = getProgress( classificationResponse );
        updateProgress( progress, 75, "SPLITTING", memcachedKeyInterface.progressKey() );
        progressNotifier.notifyAll( 75, classificationResponse.getRequestIdentifier() );
        memcachedClientWrapper.put( memcachedKeyInterface.classificationResponseKey(), classificationResponse );
        AudioInfo convertedAudioInfo = memcachedClientWrapper.get( memcachedKeyInterface.convertedAudioInfoResponseKey(), AudioInfo.class );
        Double featuresIndexIntervalLength = memcachedClientWrapper.get( memcachedKeyInterface.processingResponseIntervalLength(), Double.class );
        SplitterRequest splitterRequest = new SplitterRequest( classificationResponse.getRequestIdentifier(), convertedAudioInfo, convertedAudioInfo.getAudioFile().getParentFile(), classificationResponse.getLabeledIntervalsKey(), featuresIndexIntervalLength );
        balancerService.handle( splitterRequest );
    }

    @Override
    public void notify( SplitterResponse splitterResponse ) {
        MemcachedKeyInterface memcachedKeyInterface = new MemcachedKeyInterface( splitterResponse.getRequestIdentifier() );
        List<List<String>> levelledLabels = new ArrayList<List<String>>();
        for ( int i = 0; i < splitterResponse.getTimelines().size(); i++ ) {
            List<String> labelsOnLevel = new ArrayList<String>();
            Map<String, List<double[]>> intervals = splitterResponse.getTimelines().get( i );
            for ( Map.Entry<String, List<double[]>> intervalsEntry : intervals.entrySet() ) {
                labelsOnLevel.add( intervalsEntry.getKey() );
                memcachedClientWrapper.put( memcachedKeyInterface.getLabelTimeline( intervalsEntry.getKey() ), intervalsEntry.getValue(), 1, TimeUnit.DAYS );
            }
            levelledLabels.add( labelsOnLevel );
        }
        memcachedClientWrapper.put( memcachedKeyInterface.getLabelsWithLevels(), levelledLabels );
        Progress progress = getProgress( splitterResponse );
        updateProgress( progress, 100, "DONE", memcachedKeyInterface.progressKey() );
        RequestData requestData = memcachedClientWrapper.get( memcachedKeyInterface.requestKey(), RequestData.class );
        progressNotifier.notifyAll( 100, splitterResponse.getRequestIdentifier(), requestData );
    }

    public void updateProgress( Progress progress, int progressPercent, String status, String progressKey ) {
        progress.setProgress( progressPercent );
        progress.setStatus( status );
        memcachedClientWrapper.put( progressKey, progress, 1, TimeUnit.DAYS );
    }

}
