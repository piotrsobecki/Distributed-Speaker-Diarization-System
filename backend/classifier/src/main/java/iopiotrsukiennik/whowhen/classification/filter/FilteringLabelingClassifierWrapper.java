package iopiotrsukiennik.whowhen.classification.filter;

import iopiotrsukiennik.whowhen.classification.impl.ILabelingClassifier;
import iopiotrsukiennik.whowhen.classification.impl.LabelingClassificationResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Piotr
 * Date: 04.11.12
 * Time: 12:17
 * To change this template use File | Settings | File Templates.
 */
public class FilteringLabelingClassifierWrapper implements IFilteringClassifier {
    private ILabelingClassifier labelingClassifier;
    private double requiredProbability;
    private List<String> labelsToFilter;

    public FilteringLabelingClassifierWrapper(ILabelingClassifier labelingClassifier, String... labels) {
        this(labelingClassifier, labels,0);
    }
    public FilteringLabelingClassifierWrapper(ILabelingClassifier labelingClassifier,String[] labelsToFilter,  double requiredProbability) {
        this.requiredProbability=requiredProbability;
        this.labelingClassifier=labelingClassifier;
        this.labelsToFilter=new ArrayList<String>();
        if (labelsToFilter!=null){
            Collections.addAll(this.labelsToFilter,labelsToFilter);
        }
    }

    public FilteringClassificationResult filters(double[] vector) {
        LabelingClassificationResult labelingClassificationResult = labelingClassifier.getClassification(vector);
        return new FilteringClassificationResult(labelingClassificationResult,checkCondition(labelingClassificationResult));
    }

    public boolean checkCondition(LabelingClassificationResult labelingClassificationResult){
        if (labelingClassificationResult instanceof FilteringClassificationResult){
            return ((FilteringClassificationResult) labelingClassificationResult).isFilterable();
        }
       return labelsToFilter.contains(labelingClassificationResult.getLabel())&&labelingClassificationResult.getProbability()>=requiredProbability;
    }

    public double getRequiredProbability() {
        return requiredProbability;
    }

    public void setRequiredProbability(double requiredProbability) {
        this.requiredProbability = requiredProbability;
    }
}
