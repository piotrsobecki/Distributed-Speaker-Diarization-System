package iopiotrsukiennik.whowhen.backend.api.inner.convertion;

import iopiotrsukiennik.whowhen.backend.api.inner.InnerBackendService;

/**
 * Created with IntelliJ IDEA.
 * User: Piotr
 * Date: 08.11.12
 * Time: 23:51
 * To change this template use File | Settings | File Templates.
 */
public interface ConvertionService extends InnerBackendService {
    void handle(ConvertionRequest convertionRequest);
    String[] getAcceptableFormats();
}
