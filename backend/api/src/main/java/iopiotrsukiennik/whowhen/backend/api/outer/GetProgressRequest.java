package iopiotrsukiennik.whowhen.backend.api.outer;

import iopiotrsukiennik.whowhen.backend.api.IRequestIdentifierBound;

import java.io.Serializable;

/**
 * @author Piotr Sukiennik
 */
public class GetProgressRequest extends IRequestIdentifierBound implements Serializable {
    public GetProgressRequest( String requestIdentifier ) {
        super( requestIdentifier );
    }
}
