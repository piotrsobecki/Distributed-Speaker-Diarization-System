package iopiotrsukiennik.whowhen.processer.transformer;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Piotr
 * Date: 11.11.12
 * Time: 14:54
 * To change this template use File | Settings | File Templates.
 */
public class TransformerChain implements IFeatureVectorsTransformer {
    private IFeatureVectorsTransformer[] featureVectorsTransformers;

    public TransformerChain(IFeatureVectorsTransformer[] featureVectorsTransformers) {
        this.featureVectorsTransformers = featureVectorsTransformers;
    }
    @Override
    public List<double[]> transform(List<double[]> features) {

            if (featureVectorsTransformers==null||featureVectorsTransformers.length==0){
                return features;
            } else {
                List<double[]> featuresTransformed = features;
                for (IFeatureVectorsTransformer featureVectorsTransformer: featureVectorsTransformers){
                    featuresTransformed = featureVectorsTransformer.transform(featuresTransformed);
                }
                return featuresTransformed;
            }

    }
}
