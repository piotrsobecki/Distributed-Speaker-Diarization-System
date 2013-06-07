package iopiotrsukiennik.whowhen.backend.api;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Piotr
 * Date: 11.11.12
 * Time: 20:32
 * To change this template use File | Settings | File Templates.
 */
public abstract class IRequestIdentifierBound implements Serializable {
    private String requestIdentifier;
    protected IRequestIdentifierBound(String requestIdentifier) {
        this.requestIdentifier = requestIdentifier;
    }
    public String getRequestIdentifier() {
        return requestIdentifier;
    }
    public void setRequestIdentifier(String requestIdentifier) {
        this.requestIdentifier = requestIdentifier;
    }
}
