package iopiotrsukiennik.whowhen.backend.api.inner.convertion;

import iopiotrsukiennik.whowhen.backend.api.inner.InnerBackendService;

/**
 * @author Piotr Sukiennik
 */
public interface ConvertionService extends InnerBackendService {
    void handle( ConvertionRequest convertionRequest );

    String[] getAcceptableFormats();
}
