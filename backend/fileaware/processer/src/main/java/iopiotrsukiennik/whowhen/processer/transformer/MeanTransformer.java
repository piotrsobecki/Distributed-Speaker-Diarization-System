package iopiotrsukiennik.whowhen.processer.transformer;

import iopiotrsukiennik.whowhen.shared.util.math.MeanCalculator;
import org.apache.commons.lang.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Piotr
 * Date: 10.12.12
 * Time: 21:29
 * To change this template use File | Settings | File Templates.
 */
public class MeanTransformer implements IFeatureVectorsTransformer {




    @Override
    public List<double[]> transform(List<double[]> features) {
        List<double[]> output = new ArrayList<double[]>(features.size());
        for (double[] featureArr: features){
            output.add(ArrayUtils.add(featureArr,MeanCalculator.calculateMean(featureArr,meanMethod)));
        }
        return output;
    }
    private MeanCalculator.MeanMethod meanMethod;
    public MeanCalculator.MeanMethod getMeanMethod() {
        return meanMethod;
    }

    public void setMeanMethod(MeanCalculator.MeanMethod meanMethod) {
        this.meanMethod = meanMethod;
    }
}
