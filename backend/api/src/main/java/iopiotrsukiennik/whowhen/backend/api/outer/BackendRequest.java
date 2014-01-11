package iopiotrsukiennik.whowhen.backend.api.outer;

import iopiotrsukiennik.whowhen.backend.api.IRequestIdentifierBound;
import iopiotrsukiennik.whowhen.shared.form.RequestData;

import java.io.File;
import java.io.Serializable;

/**
 * @author Piotr Sukiennik
 */
public class BackendRequest extends IRequestIdentifierBound implements Serializable {

    private RequestData requestData;

    private File submittedFile;

    public BackendRequest( String requestIdentifier, RequestData requestData, File submittedFile ) {
        super( requestIdentifier );
        this.requestData = requestData;
        this.submittedFile = submittedFile;
    }

    public File getSubmittedFile() {
        return submittedFile;
    }

    public RequestData getRequestData() {
        return requestData;
    }
}
