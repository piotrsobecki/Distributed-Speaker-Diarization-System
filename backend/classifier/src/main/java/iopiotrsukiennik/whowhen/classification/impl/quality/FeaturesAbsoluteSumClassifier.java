package iopiotrsukiennik.whowhen.classification.impl.quality;


/**
 * Created with IntelliJ IDEA.
 * User: Piotr
 * Date: 06.11.12
 * Time: 23:37
 * To change this template use File | Settings | File Templates.
 */
public class FeaturesAbsoluteSumClassifier extends FeaturesQualityThresholdClassifier {
    FeaturesAbsoluteSumClassifier(){
        super();
    }




    @Override
    public double evaluateQuality(double[] arr) {
        double result = 0.;
        for (double d: arr){
             result+=Math.abs(d);
        }
        return result;
    }
}
