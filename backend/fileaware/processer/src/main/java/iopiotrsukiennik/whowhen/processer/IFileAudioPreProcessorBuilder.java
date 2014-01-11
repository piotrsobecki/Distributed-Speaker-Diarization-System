package iopiotrsukiennik.whowhen.processer;

import comirva.audio.util.AudioPreProcessor;

import java.io.File;

/**
 * @author Piotr Sukiennik
 */
public interface IFileAudioPreProcessorBuilder {
    AudioPreProcessor buildAudioPreProcessor( File file );
}
