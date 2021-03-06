package pl.piotrsukiennik.whowhen.convertion.service;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.MultimediaInfo;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import pl.piotrsukiennik.whowhen.backend.api.inner.convertion.ConvertionRequest;
import pl.piotrsukiennik.whowhen.backend.api.inner.convertion.ConvertionResponse;
import pl.piotrsukiennik.whowhen.backend.api.inner.convertion.ConvertionService;
import pl.piotrsukiennik.whowhen.backend.api.inner.util.AudioInfo;
import pl.piotrsukiennik.whowhen.backend.api.outer.IBackendService;
import pl.piotrsukiennik.whowhen.convertion.ProgressObservableEncoderProgressListener;
import pl.piotrsukiennik.whowhen.convertion.configuration.EncoderBuilder;
import pl.piotrsukiennik.whowhen.shared.serializer.impl.JSONDataSerializer;
import pl.piotrsukiennik.whowhen.shared.serializer.util.SerializeOnChangeObserver;
import pl.piotrsukiennik.whowhen.shared.util.progress.Progress;
import pl.piotrsukiennik.whowhen.shared.util.progress.ProgressObservable;
import pl.piotrsukiennik.whowhen.shared.util.progress.ProgressObservableCompletionListener;

import javax.annotation.Resource;
import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Piotr Sukiennik
 */
@Component( "convertionServiceImpl" )
public class ConvertionServiceImpl implements ConvertionService {

    private static Log log = LogFactory.getLog( ConvertionServiceImpl.class );

    private ExecutorService executorService = Executors.newFixedThreadPool( 4 );

    @Resource
    private EncoderBuilder encoderBuilder;

    @Resource
    private Encoder encoder;

    @Resource
    private IBackendService backendService;

    @Override
    public void handle( final ConvertionRequest convertionRequest ) {
        try {
            final File convertedFile = new File( convertionRequest.getFileToConvert().getParentFile(), "converted_" + convertionRequest.getFileToConvert().getName() + ".wav" );
            ProgressObservableEncoderProgressListener progressListener = new ProgressObservableEncoderProgressListener( new Progress() );
            SerializeOnChangeObserver serializeOnChangeObserver = new SerializeOnChangeObserver( convertionRequest.getFileToConvert().getParentFile(), new JSONDataSerializer() );
            serializeOnChangeObserver.setNamePrefix( "convertionProgress" );
            progressListener.addObserver( serializeOnChangeObserver );
            progressListener.addObserver( new ProgressObservableCompletionListener() {
                @Override
                protected void complete( ProgressObservable observable, Object arg ) {
                    try {
                        MultimediaInfo multimediaInfo = encoder.getInfo( convertedFile );
                        ConvertionResponse convertionResponse = new ConvertionResponse( convertionRequest.getRequestIdentifier(), map( convertedFile, multimediaInfo, encoderBuilder.isSigned(), encoderBuilder.isBigEndian() ) );
                        backendService.notify( convertionResponse );
                    }
                    catch ( Exception e ) {
                        if ( log.isErrorEnabled() ) {
                            log.error( ExceptionUtils.getStackTrace( e ) );
                        }
                    }
                }

                @Override
                protected boolean isCompleted( ProgressObservable observable, Object arg ) {
                    return observable.getProgress() >= 100;
                }
            } );
            executorService.submit( encoderBuilder.buildEncoder( convertionRequest.getFileToConvert(), convertedFile, progressListener ) );
        }
        catch ( Exception e ) {
            if ( log.isErrorEnabled() ) {
                log.error( ExceptionUtils.getStackTrace( e ) );
            }
        }
    }

    @Override
    public String[] getAcceptableFormats() {
        try {
            return encoder.getSupportedDecodingFormats();
        }
        catch ( EncoderException e ) {
            return new String[] { };
        }
    }

    public static AudioInfo map( File audioFile, MultimediaInfo multimediaInfo, boolean signed, boolean bigEndian ) {
        AudioInfo audioInfo = new AudioInfo( audioFile );
        audioInfo.setChannels( multimediaInfo.getAudio().getChannels() );
        audioInfo.setDuration( multimediaInfo.getDuration() );
        audioInfo.setSampleRate( multimediaInfo.getAudio().getSamplingRate() );
        if ( log.isErrorEnabled() ) {
            log.info( "multimediaInfo.getAudio().getBitRate()" + multimediaInfo.getAudio().getBitRate() );
            log.info( "multimediaInfo.getAudio().getSamplingRate()" + multimediaInfo.getAudio().getSamplingRate() );
            log.info( "setSampleSizeInBits(" + ( multimediaInfo.getAudio().getBitRate() * 1000 ) / multimediaInfo.getAudio().getSamplingRate() + ")" );
        }
        audioInfo.setSampleSizeInBits( ( multimediaInfo.getAudio().getBitRate() * 1000 ) / multimediaInfo.getAudio().getSamplingRate() );
        audioInfo.setSigned( signed );
        audioInfo.setBigEndian( bigEndian );
        return audioInfo;
    }

}
