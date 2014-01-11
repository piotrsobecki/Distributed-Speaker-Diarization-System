package pl.piotrsukiennik.whowhen.processer.transformer;


import java.util.List;

/**
 * @author Piotr Sukiennik
 */
public class DoNothingTransformer implements IFeatureVectorsTransformer {
    public List<double[]> transform( List<double[]> input ) {
        return input;
    }
}
