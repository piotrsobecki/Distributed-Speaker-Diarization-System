package pl.piotrsukiennik.whowhen.backend.api.inner.splitter;

import pl.piotrsukiennik.whowhen.backend.api.IRequestIdentifierBound;
import pl.piotrsukiennik.whowhen.backend.api.inner.util.AudioInfo;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Piotr Sukiennik
 */
public class SplitterRequest extends IRequestIdentifierBound implements Serializable {
    private AudioInfo audioInfo;

    private List<Map<String, List<int[]>>> labeledIntervalsKey;

    private File outputDirectory;

    private Double singleIntervalLength;

    public SplitterRequest( String requestIdentifier, AudioInfo audioInfo, File outputDirectory, List<Map<String, List<int[]>>> labeledIntervalsKey, Double singleIntervalLength ) {
        super( requestIdentifier );
        this.audioInfo = audioInfo;
        this.outputDirectory = outputDirectory;
        this.labeledIntervalsKey = labeledIntervalsKey;
        this.singleIntervalLength = singleIntervalLength;
    }

    public File getOutputDirectory() {
        return outputDirectory;
    }

    public Double getSingleIntervalLength() {
        return singleIntervalLength;
    }

    public List<Map<String, List<int[]>>> getLabeledIntervalsKey() {
        return labeledIntervalsKey;
    }


    public AudioInfo getAudioInfo() {
        return audioInfo;
    }

    @Override
    public String toString() {
        return "SplitterRequest{" +
         "audioInfo=" + audioInfo +
         ", labeledIntervalsKey=" + labeledIntervalsKey +
         ", outputDirectory=" + outputDirectory +
         ", singleIntervalLength=" + singleIntervalLength +
         "} " + super.toString();
    }
}
