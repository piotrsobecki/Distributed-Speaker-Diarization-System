package pl.piotrsukiennik.whowhen.backend.api.outer;

import java.io.Serializable;

/**
 * @author Piotr Sukiennik
 */
public class BackendResponse implements Serializable {

    private String requestIdentifier;

    public BackendResponse( String requestIdentifier ) {
        this.requestIdentifier = requestIdentifier;

    }

    public String getRequestIdentifier() {
        return requestIdentifier;
    }
}
