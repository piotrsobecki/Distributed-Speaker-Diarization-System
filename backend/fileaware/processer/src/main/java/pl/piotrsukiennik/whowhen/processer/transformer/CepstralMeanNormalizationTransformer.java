package pl.piotrsukiennik.whowhen.processer.transformer;

import pl.piotrsukiennik.whowhen.shared.util.math.MathUtil;
import pl.piotrsukiennik.whowhen.shared.util.math.MeanCalculator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Piotr Sukiennik
 */

public class CepstralMeanNormalizationTransformer implements IFeatureVectorsTransformer {

    public CepstralMeanNormalizationTransformer() {
        super();
    }

    public CepstralMeanNormalizationTransformer( MeanCalculator.MeanMethod meanMethod ) {
        super();
        this.meanMethod = meanMethod;
    }

    @Override
    public List<double[]> transform( List<double[]> features ) {
        double[] means = MeanCalculator.calculateMean( features, meanMethod );
        List<double[]> output = new ArrayList<double[]>( features.size() );
        for ( double[] dArr : features ) {
            output.add( MathUtil.sub( dArr, means ) );
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
