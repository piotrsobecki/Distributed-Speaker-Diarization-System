package iopiotrsukiennik.whowhen.shared.util.progress;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Piotr
 * Date: 11.11.12
 * Time: 17:22
 * To change this template use File | Settings | File Templates.
 */
public class Progress implements IProgress, Serializable{
    private float progress;
    private String status;
    private Map<String,String> progressInfo = new Hashtable<String, String>();

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Map<String, String> getProgressInfo() {
        return progressInfo;
    }

    public void setProgressInfo(Map<String, String> progressInfo) {
        this.progressInfo = progressInfo;
    }
}
