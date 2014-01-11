package pl.piotrsukiennik.whowhen.backend.api.inner.splitter;

import pl.piotrsukiennik.whowhen.backend.api.IRequestIdentifierBound;
import pl.piotrsukiennik.whowhen.backend.api.inner.InnerBackendResponse;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Piotr Sukiennik
 */
public class SplitterResponse extends IRequestIdentifierBound implements Serializable, InnerBackendResponse {

    private List<Map<String, List<double[]>>> timelines;

    public SplitterResponse( String requestIdentifier, List<Map<String, List<double[]>>> timelines ) {
        super( requestIdentifier );
        this.timelines = timelines;
    }

    public List<Map<String, List<double[]>>> getTimelines() {
        return timelines;
    }

    @Override
    public String toString() {
        return "SplitterResponse{" +
         "timeline labels=" + timelines +
         "} " + super.toString();
    }
}
