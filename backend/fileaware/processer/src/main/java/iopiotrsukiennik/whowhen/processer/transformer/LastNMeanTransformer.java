package iopiotrsukiennik.whowhen.processer.transformer;


import iopiotrsukiennik.whowhen.shared.util.math.MeanCalculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Piotr
 * Date: 25.10.12
 * Time: 23:25
 * To change this template use File | Settings | File Templates.
 */
public class LastNMeanTransformer extends AbstractFeatureVectorsTransformer {

    private MeanCalculator.MeanMethod meanMethod = MeanCalculator.MeanMethod.ARITHMETIC;
    private int howManyForMean;
    private boolean doRepeatData=false;

    public LastNMeanTransformer(){

    }

    public LastNMeanTransformer(int howManyForMean) {
        this(howManyForMean, MeanCalculator.MeanMethod.ARITHMETIC);
    }

    public LastNMeanTransformer(int howManyForMean, MeanCalculator.MeanMethod meanMethod) {
        this.howManyForMean = howManyForMean;
        this.meanMethod = meanMethod;

    }


    public synchronized List<double[]> transform(List<double[]> input) {

        List<double[]> buffer = new ArrayList<double[]>();
        for (int i=0; i<howManyForMean;i++){
            buffer.add(new double[getColumnsTo()-getColumnsFrom()]);
        }
        int buffered=0;
        List<double[]> outputList = new LinkedList<double[]>();
        for(double[] inArr: input) {
            buffer.set(buffered%howManyForMean, Arrays.copyOfRange(inArr,getColumnsFrom(),getColumnsTo()));
            buffered++;
            if (doRepeatData){
                outputList.add(MeanCalculator.calculateMean(buffer,meanMethod));
            } else if(buffered>=howManyForMean){
                outputList.add(MeanCalculator.calculateMean(buffer,meanMethod));
                buffered=0;
            }
        }
        return outputList;
    }


    public MeanCalculator.MeanMethod getMeanMethod() {
        return meanMethod;
    }

    public void setMeanMethod(MeanCalculator.MeanMethod meanMethod) {
        this.meanMethod = meanMethod;
    }

    public boolean isDoRepeatData() {
        return doRepeatData;
    }

    public void setDoRepeatData(boolean doRepeatData) {
        this.doRepeatData = doRepeatData;
    }

    public int getHowManyForMean() {
        return howManyForMean;
    }

    public void setHowManyForMean(int howManyForMean) {
        this.howManyForMean = howManyForMean;
    }
}
