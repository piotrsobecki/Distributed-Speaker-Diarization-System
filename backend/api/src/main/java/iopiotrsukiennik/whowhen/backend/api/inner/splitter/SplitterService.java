package iopiotrsukiennik.whowhen.backend.api.inner.splitter;

import iopiotrsukiennik.whowhen.backend.api.inner.InnerBackendService;

/**
 * @author Piotr Sukiennik
 */
public interface SplitterService extends InnerBackendService {
    void handle( SplitterRequest splitterRequest );
}
