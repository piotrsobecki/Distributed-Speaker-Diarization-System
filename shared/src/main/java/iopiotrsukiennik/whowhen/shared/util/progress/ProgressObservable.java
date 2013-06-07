package iopiotrsukiennik.whowhen.shared.util.progress;

import java.io.Serializable;
import java.util.Observable;

/**
 * Created with IntelliJ IDEA.
 * User: Piotr
 * Date: 03.11.12
 * Time: 21:25
 * To change this template use File | Settings | File Templates.
 */
public class ProgressObservable extends Observable implements IProgress,Serializable{
    private Progress progress;

    public ProgressObservable(Progress progress) {
        this.progress = progress;
    }

    public String getStatus() {
        return progress.getStatus();
    }

    public void setStatus(String status) {
        this.progress.setStatus(status);
        this.setChanged();
        this.notifyObservers();
    }

    public float getProgress() {
        return progress.getProgress();
    }

    public void setProgress(float progress) {
        this.progress.setProgress(progress);
        this.setChanged();
        this.notifyObservers();
    }
}
