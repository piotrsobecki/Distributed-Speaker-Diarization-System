package iopiotrsukiennik.whowhen.classification.impl;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: Piotr
 * Date: 04.11.12
 * Time: 13:20
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractBinaryClassifier implements ILabelingClassifier {
    @JsonProperty
    protected String label;

    protected AbstractBinaryClassifier(){

    }


    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public LabelingClassificationResult[] getClassificationDistribution(double[] vector) {
        LabelingClassificationResult labelingClassificationResult = getClassification(vector);
        LabelingClassificationResult labelingClassificationResultInverse = inverseResult(labelingClassificationResult);
        LabelingClassificationResult[] output= new LabelingClassificationResult[]{labelingClassificationResult,labelingClassificationResultInverse};
        Arrays.sort(output,LabelingClassificationResult.PROBABILITY_COMPARATOR);
        return output;
    }

    public LabelingClassificationResult inverseResult(LabelingClassificationResult labelingClassificationResult){
        return new LabelingClassificationResult("NOT_"+labelingClassificationResult.getLabel(),1-labelingClassificationResult.getProbability());
    }


}
