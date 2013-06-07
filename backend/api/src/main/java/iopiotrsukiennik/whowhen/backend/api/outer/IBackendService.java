package iopiotrsukiennik.whowhen.backend.api.outer;

import iopiotrsukiennik.whowhen.backend.api.inner.classification.ClassificationResponse;
import iopiotrsukiennik.whowhen.backend.api.inner.convertion.ConvertionResponse;
import iopiotrsukiennik.whowhen.backend.api.inner.processing.ProcessingResponse;
import iopiotrsukiennik.whowhen.backend.api.inner.splitter.SplitterResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Piotr
 * Date: 11.11.12
 * Time: 16:22
 * To change this template use File | Settings | File Templates.
 */
public interface IBackendService {
    BackendResponse handle(BackendRequest backendRequest);
    GetProgressResponse getProgress(GetProgressRequest getProgressRequest);
    GetTimelineResponse handle(GetTimelineRequest getLabelledIntervalsRequest);
    String[] getAcceptableFormats();
    void notify(ClassificationResponse classificationResponse);
    void notify(ConvertionResponse convertionResponse);
    void notify(ProcessingResponse processingResponse);
    void notify(SplitterResponse splitterResponse);
}
