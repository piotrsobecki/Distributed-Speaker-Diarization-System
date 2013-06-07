package iopiotrsukiennik.whowhen.processer;

import comirva.audio.util.AudioPreProcessor;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: Piotr
 * Date: 25.10.12
 * Time: 23:17
 * To change this template use File | Settings | File Templates.
 */
public interface IFileAudioPreProcessorBuilder {
    AudioPreProcessor buildAudioPreProcessor(File file);
}
