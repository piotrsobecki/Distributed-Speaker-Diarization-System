package iopiotrsukiennik.whowhen.shared.util.progress;

import java.io.Serializable;
import java.util.Observable;

/**
 * @author Piotr Sukiennik
 */
public class ProgressObservable extends Observable implements IProgress, Serializable {
    private Progress progress;

    public ProgressObservable( Progress progress ) {
        this.progress = progress;
    }

    public String getStatus() {
        return progress.getStatus();
    }

    public void setStatus( String status ) {
        this.progress.setStatus( status );
        this.setChanged();
        this.notifyObservers();
    }

    public float getProgress() {
        return progress.getProgress();
    }

    public void setProgress( float progress ) {
        this.progress.setProgress( progress );
        this.setChanged();
        this.notifyObservers();
    }
}
