package pl.piotrsukiennik.whowhen.backend.api.inner.convertion;

import pl.piotrsukiennik.whowhen.backend.api.IRequestIdentifierBound;
import pl.piotrsukiennik.whowhen.backend.api.inner.IConvertionServiceRequest;

import java.io.File;
import java.io.Serializable;

/**
 * @author Piotr Sukiennik
 */
public class ConvertionRequest extends IRequestIdentifierBound implements IConvertionServiceRequest, Serializable {


    public ConvertionRequest( String requestIdentifier, File fileToConvert ) {
        super( requestIdentifier );
        this.fileToConvert = fileToConvert;
    }

    private File fileToConvert;

    public File getFileToConvert() {
        return fileToConvert;
    }

    public void setFileToConvert( File fileToConvert ) {
        this.fileToConvert = fileToConvert;
    }

    @Override
    public String toString() {
        return "ConvertionRequest{" +
         "fileToConvert=" + fileToConvert +
         "} " + super.toString();
    }
}
