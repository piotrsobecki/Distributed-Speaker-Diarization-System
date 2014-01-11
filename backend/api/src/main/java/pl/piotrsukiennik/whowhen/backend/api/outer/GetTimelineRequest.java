package pl.piotrsukiennik.whowhen.backend.api.outer;

import pl.piotrsukiennik.whowhen.backend.api.IRequestIdentifierBound;

import java.io.Serializable;

/**
 * @author Piotr Sukiennik
 */
public class GetTimelineRequest extends IRequestIdentifierBound implements Serializable {
    public GetTimelineRequest( String requestIdentifier ) {
        super( requestIdentifier );
    }
}
