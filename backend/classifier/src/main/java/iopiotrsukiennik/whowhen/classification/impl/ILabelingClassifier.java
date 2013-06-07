package iopiotrsukiennik.whowhen.classification.impl;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Piotr
 * Date: 04.11.12
 * Time: 12:13
 * To change this template use File | Settings | File Templates.
 */
public interface ILabelingClassifier {
    LabelingClassificationResult getClassification(double[] vector);
    LabelingClassificationResult   getClassification(double[] vector,String... labels);
    LabelingClassificationResult[] getClassificationDistribution(double[] vector);
    void train(List<double[]> data);
    public String getLabel();
}

