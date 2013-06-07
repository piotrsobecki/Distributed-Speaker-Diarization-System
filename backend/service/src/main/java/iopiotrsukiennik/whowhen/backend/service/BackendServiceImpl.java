package iopiotrsukiennik.whowhen.backend.service;

import iopiotrsukiennik.whowhen.backend.api.IRequestIdentifierBound;
import iopiotrsukiennik.whowhen.backend.api.inner.balancer.BalancerService;
import iopiotrsukiennik.whowhen.backend.api.inner.classification.ClassificationRequest;
import iopiotrsukiennik.whowhen.backend.api.inner.classification.ClassificationResponse;
import iopiotrsukiennik.whowhen.backend.api.inner.convertion.ConvertionRequest;
import iopiotrsukiennik.whowhen.backend.api.inner.convertion.ConvertionResponse;
import iopiotrsukiennik.whowhen.backend.api.inner.processing.ProcessingRequest;
import iopiotrsukiennik.whowhen.backend.api.inner.processing.ProcessingResponse;
import iopiotrsukiennik.whowhen.backend.api.inner.splitter.SplitterRequest;
import iopiotrsukiennik.whowhen.backend.api.inner.splitter.SplitterResponse;
import iopiotrsukiennik.whowhen.backend.api.inner.util.AudioInfo;
import iopiotrsukiennik.whowhen.backend.api.outer.*;
import iopiotrsukiennik.whowhen.shared.aop.MonitorAfter;
import iopiotrsukiennik.whowhen.shared.aop.MonitorBefore;
import iopiotrsukiennik.whowhen.shared.aop.MonitorExclude;
import iopiotrsukiennik.whowhen.shared.form.RequestData;
import iopiotrsukiennik.whowhen.shared.memcached.MemcachedClientWrapper;
import iopiotrsukiennik.whowhen.shared.util.progress.Progress;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: Piotr
 * Date: 11.11.12
 * Time: 17:12
 * To change this template use File | Settings | File Templates.
 */
@MonitorBefore
@MonitorAfter
@Component("backendServiceImpl")
public class BackendServiceImpl implements IBackendService {

    @Resource
    private MemcachedClientWrapper memcachedClientWrapper;

    @Resource
    private BalancerService balancerService;

    @Resource
    private RequestProgressMailNotifier requestCompletionManager;

    @Override
    public String[] getAcceptableFormats() {
        String[] acceptableFormats = memcachedClientWrapper.get(MemcachedKeyInterface.ACCEPTABLE_FORMATS_KEY,String[].class);
        if (acceptableFormats==null){
            acceptableFormats = balancerService.getAcceptableFormats();
            memcachedClientWrapper.put(MemcachedKeyInterface.ACCEPTABLE_FORMATS_KEY,acceptableFormats);
        }
        return acceptableFormats;
    }

    @Override
    public BackendResponse handle(BackendRequest backendRequest) {
        MemcachedKeyInterface memcachedKeyInterface = new MemcachedKeyInterface(backendRequest.getRequestIdentifier());
        memcachedClientWrapper.put(memcachedKeyInterface.requestKey(),backendRequest.getRequestData());
        updateProgress(new Progress(), 0, "CONVERTING", memcachedKeyInterface.progressKey());
        ConvertionRequest convertionRequest = new ConvertionRequest(backendRequest.getRequestIdentifier(),backendRequest.getSubmittedFile());
        balancerService.handle(convertionRequest);
        return new BackendResponse(backendRequest.getRequestIdentifier());
    }

    @Override
    public GetTimelineResponse handle(GetTimelineRequest getLabelledIntervalsRequest){
        MemcachedKeyInterface memcachedKeyInterface = new MemcachedKeyInterface(getLabelledIntervalsRequest.getRequestIdentifier());
        List<Map<String,List<double[]>>> levelledLabeledIndexIntervals = new ArrayList<Map<String, List<double[]>>>();
        List<List<String>> levelledLabels = memcachedClientWrapper.get(memcachedKeyInterface.getLabelsWithLevels(),List.class);
        for (int i=0; i<levelledLabels.size();i++){
            List<String> labels = levelledLabels.get(i);
            Map<String,List<double[]>> timelineForLevel = new LinkedHashMap<String, List<double[]>>();
            levelledLabeledIndexIntervals.add(timelineForLevel);
            for (String label: labels){
                timelineForLevel.put(label,memcachedClientWrapper.get(memcachedKeyInterface.getLabelTimeline(label),List.class));
           }
        }
        return new GetTimelineResponse(levelledLabeledIndexIntervals);
    }

    @Override
    @MonitorExclude
    public GetProgressResponse getProgress(GetProgressRequest getProgressRequest) {
        return new GetProgressResponse(getProgress((IRequestIdentifierBound)getProgressRequest));
    }

    @MonitorExclude
    public Progress getProgress(IRequestIdentifierBound requestIdentifierBound) {
        MemcachedKeyInterface memcachedKeyInterface = new MemcachedKeyInterface(requestIdentifierBound.getRequestIdentifier());
        return memcachedClientWrapper.get(memcachedKeyInterface.progressKey(),Progress.class);
    }

    @Override
    public void notify(ConvertionResponse convertionResponse) {
        MemcachedKeyInterface memcachedKeyInterface = new MemcachedKeyInterface(convertionResponse.getRequestIdentifier());
        Progress progress = getProgress(convertionResponse);
        updateProgress(progress,25,"PROCESSING",memcachedKeyInterface.progressKey());
        memcachedClientWrapper.put(memcachedKeyInterface.convertedAudioInfoResponseKey(),convertionResponse.getConvertedAudioInfo());
        ProcessingRequest processingRequest = new ProcessingRequest(convertionResponse.getRequestIdentifier(),convertionResponse.getConvertedAudioInfo());
        balancerService.handle(processingRequest);
    }

    @Override
    public void notify(ProcessingResponse processingResponse) {
        MemcachedKeyInterface memcachedKeyInterface = new MemcachedKeyInterface(processingResponse.getRequestIdentifier());
        Progress progress = getProgress(processingResponse);
        updateProgress(progress,50,"CLASSIFYING",memcachedKeyInterface.progressKey());
        memcachedClientWrapper.put(memcachedKeyInterface.processingResponseIntervalLength(),processingResponse.getFeaturesIndexLengthMillis());
        RequestData requestData = memcachedClientWrapper.get(memcachedKeyInterface.requestKey(), RequestData.class);
        ClassificationRequest classificationRequest = new ClassificationRequest(processingResponse.getRequestIdentifier(),processingResponse.getFeatures(),requestData.getSpeakersCount());
        balancerService.handle(classificationRequest);

    }

    @Override
    public void notify(ClassificationResponse classificationResponse) {
        MemcachedKeyInterface memcachedKeyInterface = new MemcachedKeyInterface(classificationResponse.getRequestIdentifier());
        Progress progress =  getProgress(classificationResponse);
        updateProgress(progress, 75, "SPLITTING", memcachedKeyInterface.progressKey());
        memcachedClientWrapper.put(memcachedKeyInterface.classificationResponseKey(),classificationResponse);
        AudioInfo convertedAudioInfo     =    memcachedClientWrapper.get(memcachedKeyInterface.convertedAudioInfoResponseKey(),AudioInfo.class);
        Double featuresIndexIntervalLength =    memcachedClientWrapper.get(memcachedKeyInterface.processingResponseIntervalLength(),Double.class);
        SplitterRequest splitterRequest = new SplitterRequest(classificationResponse.getRequestIdentifier(),convertedAudioInfo,convertedAudioInfo.getAudioFile().getParentFile(),classificationResponse.getLabeledIntervalsKey(),featuresIndexIntervalLength);
        balancerService.handle(splitterRequest);
    }

    @Override
    public void notify(SplitterResponse splitterResponse) {
        MemcachedKeyInterface memcachedKeyInterface = new MemcachedKeyInterface(splitterResponse.getRequestIdentifier());
        List<List<String>> levelledLabels = new ArrayList<List<String>>();
        for (int i=0; i<splitterResponse.getTimelines().size();i++){
            List<String> labelsOnLevel=new ArrayList<String>();
            Map<String,List<double[]>> intervals = splitterResponse.getTimelines().get(i);
            for (Map.Entry<String,List<double[]>> intervalsEntry :intervals.entrySet()){
                labelsOnLevel.add(intervalsEntry.getKey());
                memcachedClientWrapper.put(memcachedKeyInterface.getLabelTimeline(intervalsEntry.getKey()),intervalsEntry.getValue(),1, TimeUnit.DAYS);
            }
            levelledLabels.add(labelsOnLevel);
        }
        memcachedClientWrapper.put(memcachedKeyInterface.getLabelsWithLevels(),levelledLabels);
        Progress progress = getProgress(splitterResponse);
        updateProgress(progress, 100, "DONE", memcachedKeyInterface.progressKey());
        RequestData requestData = memcachedClientWrapper.get(memcachedKeyInterface.requestKey(), RequestData.class);
        if (requestData!= null && requestData.getEmail()!=null && !requestData.getEmail().isEmpty()){
            requestCompletionManager.sendCompletionNotification(splitterResponse.getRequestIdentifier(),requestData); //Send Email
        }
    }

    public void updateProgress(Progress progress,int progressPercent, String status,String progressKey){
        progress.setProgress(progressPercent);
        progress.setStatus(status);
        memcachedClientWrapper.put(progressKey, progress,1,TimeUnit.DAYS);
    }

}
