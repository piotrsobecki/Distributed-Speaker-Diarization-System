package iopiotrsukiennik.whowhen.backend.api.inner.classification;

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
public class ClassificationResponse  extends IRequestIdentifierBound implements Serializable,InnerBackendResponse {
    public ClassificationResponse(String requestIdentifier, List<Map<String, List<int[]>>> labeledIntervalsKey) {
        super(requestIdentifier);
        this.labeledIntervalsKey = labeledIntervalsKey;
    }

    private List<Map<String, List<int[]>>> labeledIntervalsKey;

    public List<Map<String, List<int[]>>> getLabeledIntervalsKey() {
        return labeledIntervalsKey;
    }

    @Override
    public String toString() {
        return "ClassificationResponse{" +
                "labeledIntervalsKey=" + labeledIntervalsKey +
                "} " + super.toString();
    }
}
