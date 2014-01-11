package iopiotrsukiennik.whowhen.processer.service;

import iopiotrsukiennik.whowhen.backend.api.inner.processing.ProcessingRequest;
import iopiotrsukiennik.whowhen.backend.api.inner.processing.ProcessingResponse;
import iopiotrsukiennik.whowhen.backend.api.inner.processing.ProcessingService;
import iopiotrsukiennik.whowhen.backend.api.inner.util.AudioInfo;
import iopiotrsukiennik.whowhen.backend.api.outer.IBackendService;
import iopiotrsukiennik.whowhen.processer.mfcc.MFCCAudioFileProcessor;
import iopiotrsukiennik.whowhen.processer.transformer.IFeatureVectorsTransformer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Piotr Sukiennik
 */
@Component("processingServiceImpl")
public class ProcessingServiceImpl implements ProcessingService {

    private static ExecutorService executorService = Executors.newFixedThreadPool( 4 );

    private static Log logger = LogFactory.getLog( ProcessingServiceImpl.class );

    @Resource
    private MFCCAudioFileProcessor mfccAudioFileProcessor;

    @Resource(name = "transformers")
    private IFeatureVectorsTransformer featureVectorsTransformer;

    @Resource
    private IBackendService backendService;


    @Override
    public void handle( final ProcessingRequest processingRequest ) {
        executorService.submit( new Runnable() {
            @Override
            public void run() {
                try {
                    AudioInfo audioInfo = processingRequest.getConvertedAudioInfo();
                    File convertedAudioFile = audioInfo.getAudioFile();
                    List<double[]> mfccFeatures = mfccAudioFileProcessor.process( convertedAudioFile );
                    List<double[]> transofrmedFeatures = featureVectorsTransformer.transform( mfccFeatures );
                    ProcessingResponse processingResponse = new ProcessingResponse( processingRequest.getRequestIdentifier(), transofrmedFeatures, (double) audioInfo.getDuration() / transofrmedFeatures.size() );
                    backendService.notify( processingResponse );
                }
                catch ( Exception e ) {
                    if ( logger.isErrorEnabled() ) {
                        logger.error( e );
                    }
                }
            }
        } );
    }


}
