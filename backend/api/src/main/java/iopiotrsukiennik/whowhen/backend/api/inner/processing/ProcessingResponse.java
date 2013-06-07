package iopiotrsukiennik.whowhen.backend.api.inner.processing;

import iopiotrsukiennik.whowhen.backend.api.IRequestIdentifierBound;
import iopiotrsukiennik.whowhen.backend.api.inner.InnerBackendResponse;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: Piotr
 * Date: 08.11.12
 * Time: 23:30
 * To change this template use File | Settings | File Templates.
 */
public class ProcessingResponse extends IRequestIdentifierBound implements Serializable,InnerBackendResponse {
    private List<double[]> features;
    private Double featuresIndexLengthMillis;
    public ProcessingResponse(String requestIdentifier, List<double[]> features,Double featuresIndexLengthMillis) {
        super(requestIdentifier);
        this.features = features;
        this.featuresIndexLengthMillis=featuresIndexLengthMillis;

    }

    public List<double[]> getFeatures() {
        return features;
    }

    public Double getFeaturesIndexLengthMillis() {
        return featuresIndexLengthMillis;
    }

    @Override
    public String toString() {
        return "ProcessingResponse{" +
                "features=" + features +
                ", featuresIndexLengthMillis=" + featuresIndexLengthMillis +
                "} " + super.toString();
    }
}
