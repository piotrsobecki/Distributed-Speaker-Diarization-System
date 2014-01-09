package iopiotrsukiennik.whowhen.splitter;

import iopiotrsukiennik.whowhen.shared.util.AudioUtil;

import javax.sound.sampled.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Piotr
 * Date: 04.11.12
 * Time: 15:26
 * To change this template use File | Settings | File Templates.
 */
public class AudioFileSplitter {

    public Map<String,File> split(AudioFormat audioFormat,double audioLength,File audioFile,Map<String, List<double[]>> intervals,File outputDirectory,AudioFileFormat.Type outputType) {
        Map<String,File> splitAudioFiles = new HashMap<String, File>();
        byte[] audioBytes = AudioUtil.getAudioFileBytes(audioFile,audioFormat);
        for (Map.Entry<String,List<double[]>> entries: intervals.entrySet()){
            File entryOutputDirectory = new File(outputDirectory,entries.getKey());
            entryOutputDirectory.mkdirs();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            for (int i=0; i<entries.getValue().size();i++){
                double[] interval = entries.getValue().get(i);
                byte[] splitAudio= AudioUtil.splitAudioBytes(audioBytes, audioFormat.getSampleSizeInBits(), interval[0], interval[1], audioLength);
                try{
                    byteArrayOutputStream.write(splitAudio);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            File outputFile = new File(entryOutputDirectory,entries.getKey()+"."+outputType.getExtension());
            splitAudioFiles.put(entries.getKey(),outputFile);
            AudioUtil.saveAudioBytesToFile(byteArrayOutputStream.toByteArray(), audioFormat, AudioFileFormat.Type.WAVE, outputFile);
        }
        return splitAudioFiles;
    }


}
