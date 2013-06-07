package iopiotrsukiennik.whowhen.classification.impl;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Comparator;

/**
 * Created with IntelliJ IDEA.
 * User: Piotr
 * Date: 04.11.12
 * Time: 11:44
 * To change this template use File | Settings | File Templates.
 */
public class LabelingClassificationResult {
    public static final Comparator<LabelingClassificationResult> PROBABILITY_COMPARATOR = new Comparator<LabelingClassificationResult>() {
        public int compare(LabelingClassificationResult o1, LabelingClassificationResult o2) {
            return Double.compare(o1.getProbability(),o2.getProbability());
        }
    };
    @JsonProperty
    private String label;

    private double probability;

    public LabelingClassificationResult(String label, double probability) {
        this.label = label;
        this.probability = probability;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    @Override
    public String toString() {
        return "LabelingClassificationResult{" +
                "label='" + label + '\'' +
                ", probability=" + probability +
                '}';
    }
}
