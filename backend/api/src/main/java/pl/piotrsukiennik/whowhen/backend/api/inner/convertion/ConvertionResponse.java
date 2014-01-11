package pl.piotrsukiennik.whowhen.backend.api.inner.convertion;

import pl.piotrsukiennik.whowhen.backend.api.IRequestIdentifierBound;
import pl.piotrsukiennik.whowhen.backend.api.inner.InnerBackendResponse;
import pl.piotrsukiennik.whowhen.backend.api.inner.util.AudioInfo;

import java.io.Serializable;

/**
 * @author Piotr Sukiennik
 */
public class ConvertionResponse extends IRequestIdentifierBound implements Serializable, InnerBackendResponse {

    private AudioInfo convertedAudioInfo;

    public ConvertionResponse( String requestIdentifier, AudioInfo convertedAudioInfo ) {
        super( requestIdentifier );
        this.convertedAudioInfo = convertedAudioInfo;
    }

    public AudioInfo getConvertedAudioInfo() {
        return convertedAudioInfo;
    }

    @Override
    public String toString() {
        return "ConvertionResponse{" +
         "convertedAudioInfo=" + convertedAudioInfo +
         "} " + super.toString();
    }
}
