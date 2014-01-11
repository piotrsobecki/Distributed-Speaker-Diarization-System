package pl.piotrsukiennik.whowhen.classification.impl.quality;


/**
 * @author Piotr Sukiennik
 */
public class FeaturesAbsoluteSumClassifier extends FeaturesQualityThresholdClassifier {
    FeaturesAbsoluteSumClassifier() {
        super();
    }


    @Override
    public double evaluateQuality( double[] arr ) {
        double result = 0.;
        for ( double d : arr ) {
            result += Math.abs( d );
        }
        return result;
    }
}
