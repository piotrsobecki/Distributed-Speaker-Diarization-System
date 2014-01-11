package iopiotrsukiennik.whowhen.convertion;


import iopiotrsukiennik.whowhen.shared.util.progress.Progress;
import iopiotrsukiennik.whowhen.shared.util.progress.ProgressObservable;
import it.sauronsoftware.jave.MultimediaInfo;

/**
 * @author Piotr Sukiennik
 */
public class ProgressObservableEncoderProgressListener extends ProgressObservable implements it.sauronsoftware.jave.EncoderProgressListener {
    public ProgressObservableEncoderProgressListener( Progress progress ) {
        super( progress );    //To change body of overridden methods use File | Settings | File Templates.
    }

    public void progress( int i ) {
        this.setProgress( i / 10 );
    }

    public void message( String s ) {
    }

    public void sourceInfo( MultimediaInfo multimediaInfo ) {
    }
}
