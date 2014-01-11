package iopiotrsukiennik.whowhen.shared.util;

import javax.sound.sampled.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Piotr Sukiennik
 */
public abstract class AudioUtil {

    public static void saveAudioBytesToFile( byte[] audio, AudioFormat audioFormat, AudioFileFormat.Type outputType, File outputFile ) {
        InputStream input = new ByteArrayInputStream( audio );
        AudioInputStream ais = new AudioInputStream( input, audioFormat, audio.length / audioFormat.getFrameSize() );
        try {
            AudioSystem.write( ais, outputType, outputFile );
        }
        catch ( IOException ex ) {
            ex.printStackTrace();
        }
    }

    public static void joinAudioFiles( AudioFormat audioFormat, List<File> audioFiles, File output ) throws IOException, UnsupportedAudioFileException {
        output.getParentFile().mkdirs();
        output.delete();
        output.createNewFile();

        List<AudioInputStream> audioInputStreams = new ArrayList<AudioInputStream>();
        long totalFrameLength = 0;
        for ( File audioFile : audioFiles ) {
            AudioInputStream fileAudioInputStream = AudioSystem.getAudioInputStream( audioFile );
            audioInputStreams.add( fileAudioInputStream );
            totalFrameLength += fileAudioInputStream.getFrameLength();
        }

        AudioInputStream sequenceInputStream = new AudioInputStream( new SequenceInputStream( Collections.enumeration( audioInputStreams ) ), audioFormat, totalFrameLength );
        AudioSystem.write( sequenceInputStream, AudioFileFormat.Type.WAVE, output );
    }


    public static byte[] getAudioFileBytes( File audioFile, AudioFormat audioFormat ) {
        AudioInputStream ais = null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ais = AudioSystem.getAudioInputStream( audioFile );
            int bufferSize = (int) audioFormat.getSampleRate() * audioFormat.getFrameSize();
            byte buffer[] = new byte[bufferSize];
            int count = -1;
            while ( ( count = ais.read( buffer, 0, buffer.length ) ) > 0 ) {
                byteArrayOutputStream.write( buffer, 0, count );
            }
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();

        }
        catch ( IOException e ) {
            System.err.println( "I/O problems: " + e );

        }
        catch ( UnsupportedAudioFileException e ) {
            System.err.println( e.toString() );
        }
        finally {
            try {
                if ( ais != null ) {
                    ais.close();
                }
            }
            catch ( IOException e ) {
                e.printStackTrace();
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] splitAudioBytes( byte[] source, int audioSampleSizeInBits, double fromTimeMillis, double toTimeMillis, double totalLengthMillis ) {
        int sampleSizeInBytes = audioSampleSizeInBits / 8;
        int divisionPointFrom = (int) Math.floor( ( fromTimeMillis * source.length ) / totalLengthMillis );
        int divisionPointTo = (int) Math.floor( ( toTimeMillis * source.length ) / totalLengthMillis );
        divisionPointFrom += divisionPointFrom % sampleSizeInBytes;
        divisionPointTo += divisionPointTo % sampleSizeInBytes;
        return Arrays.copyOfRange( source, divisionPointFrom, divisionPointTo );
    }


}
