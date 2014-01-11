package pl.piotrsukiennik.whowhen.backend.api.outer;

import pl.piotrsukiennik.whowhen.backend.api.inner.classification.ClassificationResponse;
import pl.piotrsukiennik.whowhen.backend.api.inner.convertion.ConvertionResponse;
import pl.piotrsukiennik.whowhen.backend.api.inner.processing.ProcessingResponse;
import pl.piotrsukiennik.whowhen.backend.api.inner.splitter.SplitterResponse;

/**
 * @author Piotr Sukiennik
 */
public interface IBackendService {
    BackendResponse handle( BackendRequest backendRequest );

    GetProgressResponse getProgress( GetProgressRequest getProgressRequest );

    GetTimelineResponse handle( GetTimelineRequest getLabelledIntervalsRequest );

    String[] getAcceptableFormats();

    void notify( ClassificationResponse classificationResponse );

    void notify( ConvertionResponse convertionResponse );

    void notify( ProcessingResponse processingResponse );

    void notify( SplitterResponse splitterResponse );
}
