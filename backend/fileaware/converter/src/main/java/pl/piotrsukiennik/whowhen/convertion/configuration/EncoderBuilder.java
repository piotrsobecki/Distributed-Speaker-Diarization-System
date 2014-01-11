package pl.piotrsukiennik.whowhen.convertion.configuration;

import it.sauronsoftware.jave.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.File;

/**
 * @author Piotr Sukiennik
 */

@Configuration
public class EncoderBuilder {

    @Value( "#{converterProperties['Converter.codec']}" )
    private String codec;

    @Value( "#{new Integer(audioProperties['AudioFormat.sampleRate'])}" )
    private Integer samplingRate;

    @Value( "#{new Integer(audioProperties['AudioFormat.channels'])}" )
    private Integer channels;

    @Value( "#{new Integer(converterProperties['Converter.volume'])}" )
    private Integer volume;

    @Value( "#{new Integer(audioProperties['AudioFormat.sampleSizeInBits'])}" )
    private Integer sampleSize;

    @Value( "#{converterProperties['Converter.format']}" )
    private String format;

    @Value( "#{new Boolean(audioProperties['AudioFormat.signed'])}" )
    private boolean signed;

    @Value( "#{new Boolean(audioProperties['AudioFormat.bigEndian'])}" )
    private boolean bigEndian;

    private String[] acceptableFormats;

    @PostConstruct
    public void init() {
        try {
            acceptableFormats = encoder().getSupportedDecodingFormats();
        }
        catch ( EncoderException ee ) {
            throw new RuntimeException( ee );
        }
    }

    public Runnable buildEncoder( File sourceFile, File targetFile, EncoderProgressListener encoderProgressListener ) {
        return buildEncoder( sourceFile, targetFile, null, null, encoderProgressListener );
    }

    public Runnable buildEncoder( final File sourceFile, final File targetFile, Float offset, Float duration, final EncoderProgressListener encoderProgressListener ) {
        final EncodingAttributes encodingAttributes = getEncodingAttributes();
        encodingAttributes.setOffset( offset );
        encodingAttributes.setDuration( duration );
        encodingAttributes.setAudioAttributes( audioAttributes() );
        return new Runnable() {
            public void run() {
                try {
                    encoder().encode( sourceFile, targetFile, encodingAttributes, encoderProgressListener, true );
                }
                catch ( InputFormatException ife ) {
                    ife.printStackTrace();
                }
                catch ( EncoderException ee ) {
                    ee.printStackTrace();
                }
            }
        };
    }

    @Bean
    public EncodingAttributes getEncodingAttributes() {
        EncodingAttributes encodingAttributes = new EncodingAttributes();
        encodingAttributes.setAudioAttributes( audioAttributes() );
        encodingAttributes.setFormat( format );
        return encodingAttributes;
    }

    @Bean
    public Encoder encoder() {
        return new Encoder( ffmpegLocator() );
    }


    @Bean( name = "whoWhenFFMPegLocator" )
    public FFMPEGLocator ffmpegLocator() {
        return new DefaultFFMPEGLocator();
    }


    @Bean( name = "whoWhenAudioAttributes" )
    public AudioAttributes audioAttributes() {
        AudioAttributes audioAttributes = new AudioAttributes();
        audioAttributes.setCodec( codec );
        audioAttributes.setBitRate( getBitRate() );
        audioAttributes.setSamplingRate( samplingRate );
        audioAttributes.setChannels( channels );
        audioAttributes.setVolume( volume );
        return audioAttributes;
    }

    public Integer getBitRate() {
        return sampleSize * samplingRate;
    }

    public String getCodec() {
        return codec;
    }

    public void setCodec( String codec ) {
        this.codec = codec;
    }

    public Integer getSamplingRate() {
        return samplingRate;
    }

    public void setSamplingRate( Integer samplingRate ) {
        this.samplingRate = samplingRate;
    }

    public Integer getChannels() {
        return channels;
    }

    public void setChannels( Integer channels ) {
        this.channels = channels;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume( Integer volume ) {
        this.volume = volume;
    }

    public Integer getSampleSize() {
        return sampleSize;
    }

    public void setSampleSize( Integer sampleSize ) {
        this.sampleSize = sampleSize;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat( String format ) {
        this.format = format;
    }

    public boolean isSigned() {
        return signed;
    }

    public void setSigned( boolean signed ) {
        this.signed = signed;
    }

    public boolean isBigEndian() {
        return bigEndian;
    }

    public void setBigEndian( boolean bigEndian ) {
        this.bigEndian = bigEndian;
    }

    public String[] getAcceptableFormats() {
        return acceptableFormats;
    }

    public void setAcceptableFormats( String[] acceptableFormats ) {
        this.acceptableFormats = acceptableFormats;
    }
}
