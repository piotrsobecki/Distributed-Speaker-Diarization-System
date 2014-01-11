package pl.piotrsukiennik.whowhen.backend.api.inner.splitter;

import pl.piotrsukiennik.whowhen.backend.api.inner.InnerBackendService;

/**
 * @author Piotr Sukiennik
 */
public interface SplitterService extends InnerBackendService {
    void handle( SplitterRequest splitterRequest );
}
