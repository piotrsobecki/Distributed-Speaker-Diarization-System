package iopiotrsukiennik.whowhen.processer.transformer;

import iopiotrsukiennik.whowhen.shared.util.math.MeanCalculator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: Piotr
 * Date: 11.12.12
 * Time: 00:08
 * To change this template use File | Settings | File Templates.
 */
public class CepstralMeanNormalizationTransformerTest {
    @Test
    public void testTransform() throws Exception {
        for (double[] dArr : new CepstralMeanNormalizationTransformer(MeanCalculator.MeanMethod.ARITHMETIC).transform(new ArrayList<double[]>() {{
            add(new double[]{1, 2, 3});
            add(new double[]{1, 5, 3});
            add(new double[]{1, 2, 3});
        }})){
            System.out.println(Arrays.toString(dArr));
        };
    }
}
