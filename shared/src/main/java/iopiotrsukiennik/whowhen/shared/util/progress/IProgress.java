package iopiotrsukiennik.whowhen.shared.util.progress;

/**
 * @author Piotr Sukiennik
 */
public interface IProgress {
    float getProgress();

    void setProgress( float progress );

    String getStatus();

    void setStatus( String status );
}
