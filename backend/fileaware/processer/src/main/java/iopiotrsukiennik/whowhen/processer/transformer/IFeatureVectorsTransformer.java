package iopiotrsukiennik.whowhen.processer.transformer;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Piotr
 * Date: 11.11.12
 * Time: 14:41
 * To change this template use File | Settings | File Templates.
 */
public interface IFeatureVectorsTransformer {
    List<double[]> transform(List<double[]> features);
}
