package pl.piotrsukiennik.whowhen.backend.api;

import java.io.Serializable;

/**
 * @author Piotr Sukiennik
 */
public abstract class IRequestIdentifierBound implements Serializable {
    private String requestIdentifier;

    protected IRequestIdentifierBound( String requestIdentifier ) {
        this.requestIdentifier = requestIdentifier;
    }

    public String getRequestIdentifier() {
        return requestIdentifier;
    }

    public void setRequestIdentifier( String requestIdentifier ) {
        this.requestIdentifier = requestIdentifier;
    }
}
