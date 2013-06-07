package iopiotrsukiennik.whowhen.processer.transformer;

import iopiotrsukiennik.whowhen.shared.util.math.MeanCalculator;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Piotr
 * Date: 04.12.12
 * Time: 21:20
 * To change this template use File | Settings | File Templates.
 */
public class DeltaTransformer extends AbstractFeatureVectorsTransformer {

    private int range;
    @Override
    public List<double[]> transform(List<double[]> features) {
       List<double[]> output = new ArrayList<double[]>();
       double[][] window = new double[2*range][];
       int featuresLen = features.size();
       for (int i=0; i<featuresLen;i++){
           for (int wi=0,step=1;wi<2*range; wi++, step++){
              window[wi]=Arrays.copyOfRange(features.get(Math.min(Math.max(0,i-step),featuresLen-1)),getColumnsFrom(),getColumnsTo());
              window[++wi]=Arrays.copyOfRange(features.get(Math.min(Math.max(0,i+step),featuresLen-1)),getColumnsFrom(),getColumnsTo());
           }
           double[] featuresArray  = features.get(i);
           double[] delta = MeanCalculator.calculateMean(Arrays.asList(window), MeanCalculator.MeanMethod.ARITHMETIC);
           double[] featuresOutput = new double[featuresArray.length*2];
           System.arraycopy(featuresArray,0,featuresOutput,0,featuresArray.length);
           System.arraycopy(delta,0,featuresOutput,featuresArray.length,delta.length);
           output.add(featuresOutput);
       }
       return output;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }





     public static void main(String... args){
         DeltaTransformer deltaTransformer = new DeltaTransformer();
         deltaTransformer.setRange(2);
         for (double[] doubles: deltaTransformer.transform(new ArrayList<double[]>(){{
             add(new double[]{.1,.2,.3,.4});
             add(new double[]{.1,.2,.3,.4});
             add(new double[]{.1,.2,.3,.4});
             add(new double[]{.1,.2,.3,.4});
             add(new double[]{.1,.2,.3,.4});
             add(new double[]{.1,.2,.3,.4});
             add(new double[]{.1,.2,.3,.4});
             add(new double[]{.1,.2,.3,.4});
             add(new double[]{.1,.2,.3,.4});
         }})){
             System.out.println(Arrays.toString(doubles));
         }
     }


}
