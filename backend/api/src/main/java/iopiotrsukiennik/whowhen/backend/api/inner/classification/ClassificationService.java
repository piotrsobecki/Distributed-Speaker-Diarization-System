package iopiotrsukiennik.whowhen.backend.api.inner.classification;

import iopiotrsukiennik.whowhen.backend.api.inner.InnerBackendService;

/**
 * @author Piotr Sukiennik
 */
public interface ClassificationService extends InnerBackendService {
    void handle( ClassificationRequest classificationRequest );
}
