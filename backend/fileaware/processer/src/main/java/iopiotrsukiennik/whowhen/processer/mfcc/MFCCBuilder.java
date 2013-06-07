package iopiotrsukiennik.whowhen.processer.mfcc;

import comirva.audio.util.MFCC;

/**
 * Created with IntelliJ IDEA.
 * User: Piotr
 * Date: 31.10.12
 * Time: 23:08
 * To change this template use File | Settings | File Templates.
 */
public class MFCCBuilder {
    private float sampleRate;
    private int windowSize;
    private int numberCoefficients;
    private boolean useFirstCoefficient;
    private double minFreq;
    private double maxFreq;
    private int numberFilters;

    public MFCCBuilder(float sampleRate, int windowSize, int numberCoefficients, boolean useFirstCoefficient, double minFreq, double maxFreq, int numberFilters) throws IllegalArgumentException {
        this.sampleRate = sampleRate;
        this.windowSize = windowSize;
        this.numberCoefficients = numberCoefficients;
        this.useFirstCoefficient = useFirstCoefficient;
        this.minFreq = minFreq;
        this.maxFreq = maxFreq;
        this.numberFilters = numberFilters;
    }

    public MFCC build() {
        return new MFCC(sampleRate, windowSize, numberCoefficients, useFirstCoefficient, minFreq, maxFreq, numberFilters);
    }

    public float getSampleRate() {
        return sampleRate;
    }

    public void setSampleRate(float sampleRate) {
        this.sampleRate = sampleRate;
    }

    public int getWindowSize() {
        return windowSize;
    }

    public void setWindowSize(int windowSize) {
        this.windowSize = windowSize;
    }

    public int getNumberCoefficients() {
        return numberCoefficients;
    }

    public void setNumberCoefficients(int numberCoefficients) {
        this.numberCoefficients = numberCoefficients;
    }

    public boolean isUseFirstCoefficient() {
        return useFirstCoefficient;
    }

    public void setUseFirstCoefficient(boolean useFirstCoefficient) {
        this.useFirstCoefficient = useFirstCoefficient;
    }

    public double getMinFreq() {
        return minFreq;
    }

    public void setMinFreq(double minFreq) {
        this.minFreq = minFreq;
    }

    public double getMaxFreq() {
        return maxFreq;
    }

    public void setMaxFreq(double maxFreq) {
        this.maxFreq = maxFreq;
    }

    public int getNumberFilters() {
        return numberFilters;
    }

    public void setNumberFilters(int numberFilters) {
        this.numberFilters = numberFilters;
    }
}
