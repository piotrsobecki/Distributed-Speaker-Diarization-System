package pl.piotrsukiennik.whowhen.backend.api.inner.classification;

import pl.piotrsukiennik.whowhen.backend.api.inner.InnerBackendService;

/**
 * @author Piotr Sukiennik
 */
public interface ClassificationService extends InnerBackendService {
    void handle( ClassificationRequest classificationRequest );
}
