package iopiotrsukiennik.whowhen.convertion;


import iopiotrsukiennik.whowhen.shared.util.progress.Progress;
import iopiotrsukiennik.whowhen.shared.util.progress.ProgressObservable;
import it.sauronsoftware.jave.MultimediaInfo;

/**
 * Created with IntelliJ IDEA.
 * User: Piotr
 * Date: 21.10.12
 * Time: 11:52
 * To change this template use File | Settings | File Templates.
 */
public class ProgressObservableEncoderProgressListener extends ProgressObservable implements it.sauronsoftware.jave.EncoderProgressListener {
    public ProgressObservableEncoderProgressListener(Progress progress) {
        super(progress);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public void progress(int i) {
        this.setProgress(i/10);
    }
    public void message(String s) {}
    public void sourceInfo(MultimediaInfo multimediaInfo) {}
}
