package iopiotrsukiennik.whowhen.classification.filter;

import iopiotrsukiennik.whowhen.classification.impl.LabelingClassificationResult;

/**
 * @author Piotr Sukiennik
 */
public class FilteringClassificationResult extends LabelingClassificationResult {
    private boolean filterable;

    public FilteringClassificationResult( String label, double probability, boolean filterable ) {
        super( label, probability );
        this.filterable = filterable;
    }

    public FilteringClassificationResult( LabelingClassificationResult labelingClassificationResult, boolean filterable ) {
        this( labelingClassificationResult.getLabel(), labelingClassificationResult.getProbability(), filterable );
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
