package iopiotrsukiennik.whowhen.classification.filter;

import iopiotrsukiennik.whowhen.classification.impl.LabelingClassificationResult;

/**
 * Created with IntelliJ IDEA.
 * User: Piotr
 * Date: 04.11.12
 * Time: 12:19
 * To change this template use File | Settings | File Templates.
 */
public class FilteringClassificationResult extends LabelingClassificationResult {
    private boolean filterable;

    public FilteringClassificationResult(String label, double probability, boolean filterable) {
        super(label, probability);
        this.filterable = filterable;
    }

    public FilteringClassificationResult(LabelingClassificationResult labelingClassificationResult,boolean filterable) {
        this(labelingClassificationResult.getLabel(), labelingClassificationResult.getProbability(),filterable);
    }

    public boolean isFilterable() {
        return filterable;
    }

    @Override
    public String toString() {
        return "FilteringClassificationResult{" +
                "filterable=" + filterable +
                "} " + super.toString();
    }
}
