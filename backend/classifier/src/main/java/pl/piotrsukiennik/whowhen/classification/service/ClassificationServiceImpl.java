package pl.piotrsukiennik.whowhen.classification.service;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import pl.piotrsukiennik.whowhen.backend.api.inner.classification.ClassificationRequest;
import pl.piotrsukiennik.whowhen.backend.api.inner.classification.ClassificationResponse;
import pl.piotrsukiennik.whowhen.backend.api.inner.classification.ClassificationService;
import pl.piotrsukiennik.whowhen.backend.api.outer.IBackendService;
import pl.piotrsukiennik.whowhen.classification.impl.ClassifierChain;
import pl.piotrsukiennik.whowhen.classification.impl.ILabelingClassifier;
import pl.piotrsukiennik.whowhen.classification.impl.ILabelingClusterer;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Piotr Sukiennik
 */
@Component( "classificationServiceImpl" )
public class ClassificationServiceImpl implements ClassificationService {
    Log log = LogFactory.getLog( ClassificationServiceImpl.class );

    @Resource
    private IBackendService backendService;

    private static ExecutorService executorService = Executors.newFixedThreadPool( 4 );

    @Resource( name = "silenceClassifier" )
    private ILabelingClassifier silenceClassifier;

    @Resource( name = "classifierDataDirectory" )
    private File classifierDataDirectory;


    @Resource( name = "kMeansLabelingClassifier" )
    private ILabelingClusterer labelingClusterer;

    @Override
    public void handle( final ClassificationRequest classificationRequest ) {
        executorService.submit( new Runnable() {
            @Override
            public void run() {
                try {
                    ClassifierChain classifierChain = new ClassifierChain( new ILabelingClassifier[] { silenceClassifier }, labelingClusterer, classifierDataDirectory );
                    List<Map<String, List<int[]>>> labeledIndexIntervals = classifierChain.process( classificationRequest.getFeaturesData(), classificationRequest.getSpeakersCount() );
                    ClassificationResponse classificationResponse = new ClassificationResponse( classificationRequest.getRequestIdentifier(), labeledIndexIntervals );
                    backendService.notify( classificationResponse );
                }
                catch ( Exception e ) {
                    if ( log.isInfoEnabled() ) {
                        log.info( ExceptionUtils.getStackTrace( e ) );
                    }
                }

            }
        } );
    }
}
