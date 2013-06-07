package iopiotrsukiennik.whowhen.backend.api.inner.convertion;

import iopiotrsukiennik.whowhen.backend.api.IRequestIdentifierBound;
import iopiotrsukiennik.whowhen.backend.api.inner.IConvertionServiceRequest;

import java.io.File;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Piotr
 * Date: 08.11.12
 * Time: 23:26
 * To change this template use File | Settings | File Templates.
 */
public class ConvertionRequest  extends IRequestIdentifierBound implements IConvertionServiceRequest, Serializable {


    public ConvertionRequest(String requestIdentifier, File fileToConvert) {
        super(requestIdentifier);
        this.fileToConvert = fileToConvert;
    }

    private File fileToConvert;

    public File getFileToConvert() {
        return fileToConvert;
    }

    public void setFileToConvert(File fileToConvert) {
        this.fileToConvert = fileToConvert;
    }

    @Override
    public String toString() {
        return "ConvertionRequest{" +
                "fileToConvert=" + fileToConvert +
                "} " + super.toString();
    }
}
