package iopiotrsukiennik.whowhen.classification.impl.quality;


import iopiotrsukiennik.whowhen.shared.util.math.MathUtil;
import iopiotrsukiennik.whowhen.shared.util.math.MeanCalculator;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Piotr
 * Date: 01.11.12
 * Time: 16:04
 * To change this template use File | Settings | File Templates.
 */
@JsonAutoDetect
public class EuclidianDistanceClassifier extends FeaturesQualityThresholdClassifier implements java.io.Serializable {

    EuclidianDistanceClassifier(){
       super();
    }



    @JsonProperty
    private double[] centroid;

    public void train(List<double[]> data) {
        centroid=MeanCalculator.calculateMean(data, MeanCalculator.MeanMethod.ARITHMETIC);
        super.train(data);
    }

    @Override
    public double evaluateQuality(double[] arr) {
        return MathUtil.euclidianDistance(centroid, arr);
    }



    @Override
    public String toString() {
        return "EuclidianDistanceClassifier{" +
                ", centroid=" + centroid +
                '}';
    }
}
