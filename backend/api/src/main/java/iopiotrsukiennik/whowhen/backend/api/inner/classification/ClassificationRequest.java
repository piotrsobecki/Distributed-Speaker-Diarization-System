package iopiotrsukiennik.whowhen.backend.api.inner.classification;

import iopiotrsukiennik.whowhen.backend.api.IRequestIdentifierBound;

import java.io.Serializable;
import java.util.List;

/**
 * @author Piotr Sukiennik
 */
public class ClassificationRequest extends IRequestIdentifierBound implements Serializable {
    private List<double[]> featuresData;

    private Integer speakersCount;

    public ClassificationRequest( String requestIdentifier, List<double[]> featuresData, Integer speakersCount ) {
        super( requestIdentifier );
        this.featuresData = featuresData;
        this.speakersCount = speakersCount;
    }

    public Integer getSpeakersCount() {
        return speakersCount;
    }

    public List<double[]> getFeaturesData() {
        return featuresData;
    }

    @Override
    public String toString() {
        return "ClassificationRequest{" +
         "featuresData=" + featuresData +
         "} " + super.toString();
    }
}
