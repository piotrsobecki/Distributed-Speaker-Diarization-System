package iopiotrsukiennik.whowhen.processer;

import comirva.audio.util.AudioPreProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * User: Piotr
 * Date: 25.10.12
 * Time: 20:47
 * To change this template use File | Settings | File Templates.
 */

public class AudioPreProcessorBuilder implements IFileAudioPreProcessorBuilder {

    private AudioFormat audioFormat;

    public AudioPreProcessorBuilder(AudioFormat audioFormat) {

        this.audioFormat=audioFormat;
    }

    public AudioPreProcessor buildAudioPreProcessor(byte[] audioData){
        InputStream input =  new ByteArrayInputStream(audioData);
        return buildAudioPreProcessor(input,audioData.length / audioFormat.getFrameSize());
    }

    public AudioPreProcessor buildAudioPreProcessor(InputStream inputStream, long sampleFramesLenght){
        System.out.println(audioFormat);
        AudioInputStream ais =  new AudioInputStream(inputStream, audioFormat,sampleFramesLenght);
        return  new AudioPreProcessor(ais, audioFormat.getSampleRate());
    }
    public AudioPreProcessor buildAudioPreProcessor(File file){
        AudioInputStream ais = null;
        try {
            ais = AudioSystem.getAudioInputStream(file);
        } catch (IOException e) {
            System.err.println(e.toString());
        } catch (UnsupportedAudioFileException e) {
            System.err.println(e.toString());
        }

        return new AudioPreProcessor(ais, audioFormat.getSampleRate());
    }






}
