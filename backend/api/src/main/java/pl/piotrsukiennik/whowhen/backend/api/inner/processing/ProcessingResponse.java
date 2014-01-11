package pl.piotrsukiennik.whowhen.backend.api.inner.processing;

import pl.piotrsukiennik.whowhen.backend.api.IRequestIdentifierBound;
import pl.piotrsukiennik.whowhen.backend.api.inner.InnerBackendResponse;

import java.io.Serializable;
import java.util.List;

/**
 * @author Piotr Sukiennik
 */
public class ProcessingResponse extends IRequestIdentifierBound implements Serializable, InnerBackendResponse {
    private List<double[]> features;

    private Double featuresIndexLengthMillis;

    public ProcessingResponse( String requestIdentifier, List<double[]> features, Double featuresIndexLengthMillis ) {
        super( requestIdentifier );
        this.features = features;
        this.featuresIndexLengthMillis = featuresIndexLengthMillis;

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
