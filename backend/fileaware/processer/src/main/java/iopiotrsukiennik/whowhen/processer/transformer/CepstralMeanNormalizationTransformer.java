package iopiotrsukiennik.whowhen.processer.transformer;

import iopiotrsukiennik.whowhen.shared.util.math.MathUtil;
import iopiotrsukiennik.whowhen.shared.util.math.MeanCalculator;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Piotr
 * Date: 10.12.12
 * Time: 23:38
 * To change this template use File | Settings | File Templates.
 */

public class CepstralMeanNormalizationTransformer implements IFeatureVectorsTransformer {

    public CepstralMeanNormalizationTransformer(){
       super();
    }

    public CepstralMeanNormalizationTransformer(MeanCalculator.MeanMethod meanMethod) {
        super();
        this.meanMethod = meanMethod;
    }

    @Override
    public List<double[]> transform(List<double[]> features) {
        double[] means = MeanCalculator.calculateMean(features, meanMethod);
        List<double[]> output = new ArrayList<double[]>(features.size());
        for (double[] dArr: features){
            output.add(MathUtil.sub(dArr,means));
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
