package iopiotrsukiennik.whowhen.backend.api.outer;

import iopiotrsukiennik.whowhen.backend.api.IRequestIdentifierBound;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Piotr
 * Date: 11.11.12
 * Time: 17:15
 * To change this template use File | Settings | File Templates.
 */
public class GetTimelineRequest extends IRequestIdentifierBound implements Serializable {
    public GetTimelineRequest(String requestIdentifier) {
       super(requestIdentifier);
    }
}
