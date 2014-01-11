package iopiotrsukiennik.whowhen.backend.api.inner.util;

import javax.sound.sampled.AudioFormat;

/**
 * @author Piotr Sukiennik
 */
public class AudioInfoUtil {
    public static AudioFormat convert( AudioInfo audioInfo ) {
        return new AudioFormat( audioInfo.getSampleRate(), audioInfo.getSampleSizeInBits(), audioInfo.getChannels(), audioInfo.isSigned(), audioInfo.isBigEndian() );
    }
}
