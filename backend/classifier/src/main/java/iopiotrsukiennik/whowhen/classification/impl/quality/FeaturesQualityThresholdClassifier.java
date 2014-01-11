package iopiotrsukiennik.whowhen.classification.impl.quality;


import iopiotrsukiennik.whowhen.classification.impl.AbstractBinaryClassifier;
import iopiotrsukiennik.whowhen.classification.impl.LabelingClassificationResult;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Arrays;
import java.util.List;

/**
 * @author Piotr Sukiennik
 */
@JsonAutoDetect
public abstract class FeaturesQualityThresholdClassifier extends AbstractBinaryClassifier implements java.io.Serializable {

    @JsonProperty
    private Double requiredQuality = 0.0;

    @JsonProperty
    private String[] requiredLabels = null;

    @JsonProperty
    protected Integer featuresFrom;

    @JsonProperty
    protected Integer featuresTo;

    FeaturesQualityThresholdClassifier() {
        super();
    }

    public void train( List<double[]> data ) {
        for ( double[] dArr : data ) {
            setRequiredQuality( Math.max( requiredQuality, evaluateQuality( Arrays.copyOfRange( dArr, featuresFrom, featuresTo ) ) ) );
        }
    }

    public LabelingClassificationResult getClassification( double[] vector ) {
        if ( evaluateQuality( Arrays.copyOfRange( vector, featuresFrom, featuresTo ) ) <= getRequiredQuality() ) {
            return new LabelingClassificationResult( label, 1 );
        }
        else {
            return new LabelingClassificationResult( "NOT_" + label, 1 );
        }
    }

    @Override
    public LabelingClassificationResult getClassification( double[] vector, String... labels ) {
        if ( requiredLabels != null ) {
            for ( String label : labels ) {
                if ( Arrays.binarySearch( requiredLabels, label ) > 0 ) {
                    return getClassification( vector );
                }
            }
        }
        else {
            return getClassification( vector );
        }
        return null;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
         "requiredQuality=" + requiredQuality +
         ", label='" + label + '\'' +
         '}';
    }

    public double getRequiredQuality() {
        return this.requiredQuality;
    }

    public void setRequiredQuality( double requiredQuality ) {
        this.requiredQuality = requiredQuality;
    }

    public String[] getRequiredLabels() {
        return requiredLabels;
    }

    public void setRequiredLabels( String[] requiredLabels ) {
        this.requiredLabels = requiredLabels;
        if ( requiredLabels != null && requiredLabels.length > 0 ) {
            Arrays.sort( requiredLabels );
        }
    }

    public Integer getFeaturesFrom() {
        return featuresFrom;
    }

    public void setFeaturesFrom( Integer featuresFrom ) {
        this.featuresFrom = featuresFrom;
    }

    public Integer getFeaturesTo() {
        return featuresTo;
    }

    public void setFeaturesTo( Integer featuresTo ) {
        this.featuresTo = featuresTo;
    }

    public abstract double evaluateQuality( double[] arr );
}
