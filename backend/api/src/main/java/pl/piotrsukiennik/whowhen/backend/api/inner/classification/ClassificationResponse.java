package pl.piotrsukiennik.whowhen.backend.api.inner.classification;

import pl.piotrsukiennik.whowhen.backend.api.IRequestIdentifierBound;
import pl.piotrsukiennik.whowhen.backend.api.inner.InnerBackendResponse;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Piotr Sukiennik
 */
public class ClassificationResponse extends IRequestIdentifierBound implements Serializable, InnerBackendResponse {
    public ClassificationResponse( String requestIdentifier, List<Map<String, List<int[]>>> labeledIntervalsKey ) {
        super( requestIdentifier );
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
