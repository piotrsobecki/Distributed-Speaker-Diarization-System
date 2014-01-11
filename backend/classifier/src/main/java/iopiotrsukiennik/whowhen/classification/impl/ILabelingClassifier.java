package iopiotrsukiennik.whowhen.classification.impl;

import java.util.List;

/**
 * @author Piotr Sukiennik
 */
public interface ILabelingClassifier {
    LabelingClassificationResult getClassification( double[] vector );

    LabelingClassificationResult getClassification( double[] vector, String... labels );

    LabelingClassificationResult[] getClassificationDistribution( double[] vector );

    void train( List<double[]> data );

    public String getLabel();
}

