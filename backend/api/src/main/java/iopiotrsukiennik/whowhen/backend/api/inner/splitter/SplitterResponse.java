package iopiotrsukiennik.whowhen.backend.api.inner.splitter;

import iopiotrsukiennik.whowhen.backend.api.IRequestIdentifierBound;
import iopiotrsukiennik.whowhen.backend.api.inner.InnerBackendResponse;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Piotr
 * Date: 08.11.12
 * Time: 23:30
 * To change this template use File | Settings | File Templates.
 */
public class SplitterResponse extends IRequestIdentifierBound implements Serializable,InnerBackendResponse {

    private List<Map<String, List<double[]>>> timelines;

    public SplitterResponse(String requestIdentifier,List<Map<String, List<double[]>>> timelines) {
        super(requestIdentifier);
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
