package iopiotrsukiennik.whowhen.classification.impl;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Piotr
 * Date: 04.11.12
 * Time: 12:13
 * To change this template use File | Settings | File Templates.
 */
public interface ILabelingClusterer extends Cloneable{
    LabelingClassificationResult   getClassification(double[] vector);
    LabelingClassificationResult   getClassification(double[] vector, String... labels);
    LabelingClassificationResult[] getClassificationDistribution(double[] vector);
    void setExpectedNumberClusters(int numberClusters);
    void train(List<double[]> data);
    public String getRequiredLabel();
    public ILabelingClusterer clone();
}

