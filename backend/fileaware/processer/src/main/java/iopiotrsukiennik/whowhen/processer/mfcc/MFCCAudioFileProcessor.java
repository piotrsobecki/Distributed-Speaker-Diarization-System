package iopiotrsukiennik.whowhen.processer.mfcc;

import iopiotrsukiennik.whowhen.processer.IFileAudioPreProcessorBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Piotr
 * Date: 25.10.12
 * Time: 22:41
 * To change this template use File | Settings | File Templates.
 */
public class MFCCAudioFileProcessor {

    private MFCCBuilder mfccBuilder;

    private IFileAudioPreProcessorBuilder audioPreProcessorBuilder;

    public MFCCAudioFileProcessor(MFCCBuilder mfccBuilder, IFileAudioPreProcessorBuilder audioPreProcessorBuilder) {
       this.mfccBuilder=mfccBuilder;
       this.audioPreProcessorBuilder=audioPreProcessorBuilder;
    }


    public List<double[]> process(File audioFile) throws IOException{
         return mfccBuilder.build().process(audioPreProcessorBuilder.buildAudioPreProcessor(audioFile));
    }


    public IFileAudioPreProcessorBuilder getAudioPreProcessorBuilder() {
        return audioPreProcessorBuilder;
    }

    public void setAudioPreProcessorBuilder(IFileAudioPreProcessorBuilder audioPreProcessorBuilder) {
        this.audioPreProcessorBuilder = audioPreProcessorBuilder;
    }

}
