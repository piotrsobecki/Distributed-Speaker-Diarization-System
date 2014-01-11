package iopiotrsukiennik.whowhen.processer.transformer;

import iopiotrsukiennik.whowhen.shared.util.math.MeanCalculator;
import org.apache.commons.lang.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Piotr Sukiennik
 */
public class MeanTransformer implements IFeatureVectorsTransformer {


    @Override
    public List<double[]> transform( List<double[]> features ) {
        List<double[]> output = new ArrayList<double[]>( features.size() );
        for ( double[] featureArr : features ) {
            output.add( ArrayUtils.add( featureArr, MeanCalculator.calculateMean( featureArr, meanMethod ) ) );
        }
        return output;
    }

    private MeanCalculator.MeanMethod meanMethod;

    public MeanCalculator.MeanMethod getMeanMethod() {
        return meanMethod;
    }

    public void setMeanMethod( MeanCalculator.MeanMethod meanMethod ) {
        this.meanMethod = meanMethod;
    }
}
