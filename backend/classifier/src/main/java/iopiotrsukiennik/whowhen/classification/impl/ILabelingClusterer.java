package iopiotrsukiennik.whowhen.classification.impl;

import java.util.List;

/**
 * @author Piotr Sukiennik
 */
public interface ILabelingClusterer extends Cloneable {
    LabelingClassificationResult getClassification( double[] vector );

    LabelingClassificationResult getClassification( double[] vector, String... labels );

    LabelingClassificationResult[] getClassificationDistribution( double[] vector );

    void setExpectedNumberClusters( int numberClusters );

    void train( List<double[]> data );

    public String getRequiredLabel();

    public ILabelingClusterer clone();
}

