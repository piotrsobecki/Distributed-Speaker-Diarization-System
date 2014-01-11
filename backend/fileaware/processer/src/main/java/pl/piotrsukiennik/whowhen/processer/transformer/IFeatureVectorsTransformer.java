package pl.piotrsukiennik.whowhen.processer.transformer;

import java.util.List;

/**
 * @author Piotr Sukiennik
 */
public interface IFeatureVectorsTransformer {
    List<double[]> transform( List<double[]> features );
}
