package iopiotrsukiennik.whowhen.backend.api.outer;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Piotr
 * Date: 11.11.12
 * Time: 16:23
 * To change this template use File | Settings | File Templates.
 */
public class BackendResponse implements Serializable {

    private String requestIdentifier;

    public BackendResponse(String requestIdentifier) {
      this.requestIdentifier=requestIdentifier;

    }

    public String getRequestIdentifier() {
        return requestIdentifier;
    }
}
