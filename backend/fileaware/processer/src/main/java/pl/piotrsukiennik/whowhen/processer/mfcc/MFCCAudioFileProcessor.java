package pl.piotrsukiennik.whowhen.processer.mfcc;

import pl.piotrsukiennik.whowhen.processer.IFileAudioPreProcessorBuilder;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author Piotr Sukiennik
 */
public class MFCCAudioFileProcessor {

    private MFCCBuilder mfccBuilder;

    private IFileAudioPreProcessorBuilder audioPreProcessorBuilder;

    public MFCCAudioFileProcessor( MFCCBuilder mfccBuilder, IFileAudioPreProcessorBuilder audioPreProcessorBuilder ) {
        this.mfccBuilder = mfccBuilder;
        this.audioPreProcessorBuilder = audioPreProcessorBuilder;
    }


    public List<double[]> process( File audioFile ) throws IOException {
        return mfccBuilder.build().process( audioPreProcessorBuilder.buildAudioPreProcessor( audioFile ) );
    }


    public IFileAudioPreProcessorBuilder getAudioPreProcessorBuilder() {
        return audioPreProcessorBuilder;
    }

    public void setAudioPreProcessorBuilder( IFileAudioPreProcessorBuilder audioPreProcessorBuilder ) {
        this.audioPreProcessorBuilder = audioPreProcessorBuilder;
    }

}
