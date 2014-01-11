package pl.piotrsukiennik.whowhen.processer.transformer;

import java.util.List;

/**
 * @author Piotr Sukiennik
 */
public class TransformerChain implements IFeatureVectorsTransformer {
    private IFeatureVectorsTransformer[] featureVectorsTransformers;

    public TransformerChain( IFeatureVectorsTransformer[] featureVectorsTransformers ) {
        this.featureVectorsTransformers = featureVectorsTransformers;
    }

    @Override
    public List<double[]> transform( List<double[]> features ) {

        if ( featureVectorsTransformers == null || featureVectorsTransformers.length == 0 ) {
            return features;
        }
        else {
            List<double[]> featuresTransformed = features;
            for ( IFeatureVectorsTransformer featureVectorsTransformer : featureVectorsTransformers ) {
                featuresTransformed = featureVectorsTransformer.transform( featuresTransformed );
            }
            return featuresTransformed;
        }

    }
}
