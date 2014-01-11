package pl.piotrsukiennik.whowhen.backend.api.inner.processing;

import pl.piotrsukiennik.whowhen.backend.api.inner.InnerBackendService;

/**
 * @author Piotr Sukiennik
 */
public interface ProcessingService extends InnerBackendService {
    void handle( ProcessingRequest processingRequest );
}
