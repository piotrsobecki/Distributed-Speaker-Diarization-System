package iopiotrsukiennik.whowhen.backend.api.outer;

import iopiotrsukiennik.whowhen.backend.api.IRequestIdentifierBound;

import java.io.Serializable;

/**
 * @author Piotr Sukiennik
 */
public class GetTimelineRequest extends IRequestIdentifierBound implements Serializable {
    public GetTimelineRequest( String requestIdentifier ) {
        super( requestIdentifier );
    }
}
