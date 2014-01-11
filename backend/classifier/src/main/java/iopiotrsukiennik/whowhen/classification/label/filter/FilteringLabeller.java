package iopiotrsukiennik.whowhen.classification.label.filter;


import iopiotrsukiennik.whowhen.classification.filter.FilteringClassificationResult;
import iopiotrsukiennik.whowhen.classification.filter.IFilteringClassifier;
import iopiotrsukiennik.whowhen.classification.label.IFeaturesLabeller;
import iopiotrsukiennik.whowhen.classification.label.LabelledIndexIntervals;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Piotr Sukiennik
 */
public class FilteringLabeller implements IFeaturesLabeller {


    private IFilteringClassifier filteringClassifier;

    private String name;

    public FilteringLabeller( IFilteringClassifier filteringClassifier ) {
        this.filteringClassifier = filteringClassifier;
        this.name = this.filteringClassifier.getClass().getSimpleName().replaceAll( "[a-z]*", "" ) + "filtered";
    }

    public LabelledIndexIntervals label( List<double[]> input ) {
        LabelledIndexIntervals labelledIndexIntervals = new LabelledIndexIntervals();

        List<double[]> result = new ArrayList<double[]>();
        for ( int i = 0; i < input.size(); i++ ) {
            double[] vector = input.get( i );
            FilteringClassificationResult filteringClassificationResult = filteringClassifier.filters( vector );
            if ( !filteringClassificationResult.isFilterable() ) {
                result.add( vector );
            }

            labelledIndexIntervals.applyLabel( filteringClassificationResult.getLabel(), i );
        }
        labelledIndexIntervals.setResultData( result );
        return labelledIndexIntervals;
    }

    public String getName() {
        return name;
    }


}
