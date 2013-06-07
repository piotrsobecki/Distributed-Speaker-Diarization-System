package iopiotrsukiennik.whowhen.processer.transformer;


import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Piotr
 * Date: 25.10.12
 * Time: 23:14
 * To change this template use File | Settings | File Templates.
 */
public class DoNothingTransformer implements IFeatureVectorsTransformer {
    public List<double[]> transform(List<double[]> input) {
       return input;
    }
}
