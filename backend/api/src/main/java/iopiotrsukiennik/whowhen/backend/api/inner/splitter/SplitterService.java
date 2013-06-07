package iopiotrsukiennik.whowhen.backend.api.inner.splitter;

import iopiotrsukiennik.whowhen.backend.api.inner.InnerBackendService;

/**
 * Created with IntelliJ IDEA.
 * User: Piotr
 * Date: 08.11.12
 * Time: 23:26
 * To change this template use File | Settings | File Templates.
 */
public interface SplitterService extends InnerBackendService {
    void handle(SplitterRequest splitterRequest);
}
