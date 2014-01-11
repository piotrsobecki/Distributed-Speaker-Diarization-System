package iopiotrsukiennik.whowhen.backend.api.inner.processing;

import iopiotrsukiennik.whowhen.backend.api.inner.InnerBackendService;

/**
 * @author Piotr Sukiennik
 */
public interface ProcessingService extends InnerBackendService {
    void handle( ProcessingRequest processingRequest );
}
