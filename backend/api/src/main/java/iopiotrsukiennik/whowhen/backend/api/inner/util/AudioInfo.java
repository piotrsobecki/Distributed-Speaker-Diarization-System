package iopiotrsukiennik.whowhen.backend.api.inner.util;

import java.io.File;
import java.io.Serializable;

/**
* Created with IntelliJ IDEA.
* User: Piotr
* Date: 11.11.12
* Time: 15:47
* To change this template use File | Settings | File Templates.
*/
public class AudioInfo implements Serializable {
    private File audioFile;
    private int sampleRate;
    private int sampleSizeInBits;
    private int channels;
    private boolean signed;
    private boolean bigEndian;
    private long duration;

    public AudioInfo( File audioFile) {
        this.audioFile=audioFile;
    }

    public int getSampleRate() {
        return sampleRate;
    }

    public void setSampleRate(int sampleRate) {
        this.sampleRate = sampleRate;
    }

    public int getSampleSizeInBits() {
        return sampleSizeInBits;
    }

    public void setSampleSizeInBits(int sampleSizeInBits) {
        this.sampleSizeInBits = sampleSizeInBits;
    }

    public int getChannels() {
        return channels;
    }

    public void setChannels(int channels) {
        this.channels = channels;
    }

    public boolean isSigned() {
        return signed;
    }

    public void setSigned(boolean signed) {
        this.signed = signed;
    }

    public boolean isBigEndian() {
        return bigEndian;
    }

    public void setBigEndian(boolean bigEndian) {
        this.bigEndian = bigEndian;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public File getAudioFile() {
        return audioFile;
    }

    @Override
    public String toString() {
        return "AudioInfo{" +
                "audioFile=" + audioFile +
                ", sampleRate=" + sampleRate +
                ", sampleSizeInBits=" + sampleSizeInBits +
                ", channels=" + channels +
                ", signed=" + signed +
                ", bigEndian=" + bigEndian +
                ", duration=" + duration +
                '}';
    }
}
