package iopiotrsukiennik.whowhen.convertion.configuration;

import it.sauronsoftware.jave.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created with IntelliJ IDEA.
 * User: Piotr
 * Date: 20.10.12
 * Time: 22:16
 * To change this template use File | Settings | File Templates.
 */

@Configuration
public class EncoderBuilder {

    //@Value("#{systemProperties['FFMPEG_HOME']}")
    private String FFMPEG_HOME="/ffmpeg";
    @Value("#{converterProperties['Converter.codec']}")
    private String codec;
    private Integer samplingRate;
    private Integer channels;
    private Integer volume;
    private Integer sampleSize;

    @Value("#{converterProperties['Converter.format']}")
    private String format;

    private boolean signed;
    private boolean bigEndian;

    private String[] acceptableFormats;
    @PostConstruct
    public void init() {
        try{
            acceptableFormats = encoder().getSupportedDecodingFormats();
        }   catch (EncoderException ee){
            throw new RuntimeException(ee);
        }
    }

    public String[] getAcceptableFormats() {
        return acceptableFormats;
    }

    public void setAcceptableFormats(String[] acceptableFormats) {
        this.acceptableFormats = acceptableFormats;
    }

    public Runnable buildEncoder(File sourceFile, File targetFile, EncoderProgressListener encoderProgressListener){
        return buildEncoder(sourceFile, targetFile, null, null, encoderProgressListener);
    }

    public Runnable buildEncoder(final File sourceFile, final File targetFile, Float offset, Float duration, final EncoderProgressListener encoderProgressListener){
        final EncodingAttributes encodingAttributes = getEncodingAttributes();
        encodingAttributes.setOffset(offset);
        encodingAttributes.setDuration(duration);
        encodingAttributes.setAudioAttributes(audioAttributes());
        return new Runnable() {
            public void run() {
                try{
                    encoder().encode(sourceFile, targetFile, encodingAttributes, encoderProgressListener,true);
                } catch (InputFormatException ife){
                    ife.printStackTrace();
                    //  encoderProgressListener.message("Wrong input format");
                } catch (EncoderException ee){
                    ee.printStackTrace();
                    //  encoderProgressListener.message("Encoder Exception occured");
                }
            }
        };
    }

    @Bean
    public EncodingAttributes getEncodingAttributes(){
        EncodingAttributes encodingAttributes = new EncodingAttributes();
        encodingAttributes.setAudioAttributes(audioAttributes());
        encodingAttributes.setFormat(format);
        return encodingAttributes;
    }

    @Bean
    public Encoder encoder(){
        return new Encoder(ffmpegLocator());
    }


    @Bean(name = "whoWhenFFMPegLocator")
    public FFMPEGLocator ffmpegLocator(){
        return new FFMPEGLocator() {
            private String ffmpegPath = null;
            {
                System.out.println(new File("test").getAbsolutePath());
                String os = System.getProperty("os.name").toLowerCase();
                boolean isWindows = os.contains("windows");
                String path  = FFMPEG_HOME+"/ffmpeg"+ (isWindows?".exe":"");
                File ffmpeg = new File(path);
                ffmpegPath=ffmpeg.getAbsolutePath();

                try{
                    Process p = Runtime.getRuntime().exec(ffmpeg.getAbsolutePath()+" -version");
                    String in=null;
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    while((in=bufferedReader.readLine())!=null){
                        System.out.println(in);
                    }
                }    catch (IOException i){
                    throw new RuntimeException(i);
                }
            }

            @Override
            protected String getFFMPEGExecutablePath() {
                return ffmpegPath;  //To change body of implemented methods use File | Settings | File Templates.
            }
        };
    }

    public String getCodec() {
        return codec;
    }

    public Integer getSamplingRate() {
        return samplingRate;
    }

    public Integer getChannels() {
        return channels;
    }

    public Integer getVolume() {
        return volume;
    }

    public Integer getSampleSize() {
        return sampleSize;
    }

    public String getFormat() {
        return format;
    }

    @Bean(name = "whoWhenAudioAttributes")
    public AudioAttributes audioAttributes(){
        AudioAttributes audioAttributes = new AudioAttributes();
        audioAttributes.setCodec(codec);
        audioAttributes.setBitRate(getBitRate());
        audioAttributes.setSamplingRate(samplingRate);
        audioAttributes.setChannels(channels);
        audioAttributes.setVolume(volume);
        return audioAttributes;
    }

    public Integer getBitRate() {
       return sampleSize*samplingRate;
    }
    @Value("#{audioProperties['AudioFormat.sampleRate']}")
    public void setSamplingRate(String samplingRate) {
        this.samplingRate = Integer.valueOf(samplingRate);
    }

    @Value("#{audioProperties['AudioFormat.sampleSizeInBits']}")
    public void setSampleSize(String sampleSize) {
        this.sampleSize = Integer.valueOf(sampleSize);
    }
    @Value("#{audioProperties['AudioFormat.channels']}")
    public void setChannels(String channels) {
        this.channels = Integer.valueOf(channels);
    }
    @Value("#{converterProperties['Converter.volume']}")
    public void setVolume(String volume) {
        this.volume = Integer.valueOf(volume);
    }


    public boolean isSigned() {
        return signed;
    }

    @Value("#{audioProperties['AudioFormat.signed']}")
    public void setSigned(String signed) {
        this.signed = Boolean.parseBoolean(signed);
    }

    public boolean isBigEndian() {
        return bigEndian;
    }
    @Value("#{audioProperties['AudioFormat.bigEndian']}")
    public void setBigEndian(String bigEndian) {
        this.bigEndian =  Boolean.parseBoolean(bigEndian);
    }
}
