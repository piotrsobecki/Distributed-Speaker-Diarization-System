package iopiotrsukiennik.whowhen.backend.api.inner.processing;

import iopiotrsukiennik.whowhen.backend.api.inner.InnerBackendService;

/**
 * Created with IntelliJ IDEA.
 * User: Piotr
 * Date: 08.11.12
 * Time: 23:26
 * To change this template use File | Settings | File Templates.
 */
public interface ProcessingService extends InnerBackendService {
    void handle(ProcessingRequest processingRequest);
}
