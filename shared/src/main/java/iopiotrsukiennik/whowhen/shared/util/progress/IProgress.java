package iopiotrsukiennik.whowhen.shared.util.progress;

/**
 * Created with IntelliJ IDEA.
 * User: Piotr
 * Date: 11.11.12
 * Time: 17:24
 * To change this template use File | Settings | File Templates.
 */
public interface IProgress  {
    float getProgress();

    void setProgress(float progress);

    String getStatus();

    void setStatus(String status);
}
