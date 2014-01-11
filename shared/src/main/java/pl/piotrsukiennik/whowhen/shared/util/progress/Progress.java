package pl.piotrsukiennik.whowhen.shared.util.progress;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.Map;

/**
 * @author Piotr Sukiennik
 */
public class Progress implements IProgress, Serializable {
    private float progress;

    private String status;

    private Map<String, String> progressInfo = new Hashtable<String, String>();

    public float getProgress() {
        return progress;
    }

    public void setProgress( float progress ) {
        this.progress = progress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus( String status ) {
        this.status = status;
    }

    public Map<String, String> getProgressInfo() {
        return progressInfo;
    }

    public void setProgressInfo( Map<String, String> progressInfo ) {
        this.progressInfo = progressInfo;
    }
}
