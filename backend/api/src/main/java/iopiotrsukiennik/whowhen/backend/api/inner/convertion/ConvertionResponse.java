package iopiotrsukiennik.whowhen.backend.api.inner.convertion;

import iopiotrsukiennik.whowhen.backend.api.IRequestIdentifierBound;
import iopiotrsukiennik.whowhen.backend.api.inner.InnerBackendResponse;
import iopiotrsukiennik.whowhen.backend.api.inner.util.AudioInfo;

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
