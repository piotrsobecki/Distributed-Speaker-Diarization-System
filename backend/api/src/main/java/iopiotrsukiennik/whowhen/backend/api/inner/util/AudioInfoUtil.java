package iopiotrsukiennik.whowhen.backend.api.inner.util;

import javax.sound.sampled.AudioFormat;

/**
 * Created with IntelliJ IDEA.
 * User: Piotr
 * Date: 11.11.12
 * Time: 01:15
 * To change this template use File | Settings | File Templates.
 */
public class AudioInfoUtil {
    public static AudioFormat convert(AudioInfo audioInfo){
        return new AudioFormat(audioInfo.getSampleRate(), audioInfo.getSampleSizeInBits(), audioInfo.getChannels(), audioInfo.isSigned(), audioInfo.isBigEndian());
    }
}
