package pl.piotrsukiennik.whowhen.shared.util.progress;

import java.util.Observable;
import java.util.Observer;

/**
 * @author Piotr Sukiennik
 */
public abstract class ProgressObservableCompletionListener implements Observer {
    @Override
    public void update( Observable o, Object arg ) {
        if ( o instanceof ProgressObservable ) {
            if ( isCompleted( (ProgressObservable) o, arg ) ) {
                complete( (ProgressObservable) o, arg );
            }
        }
    }

    protected abstract void complete( ProgressObservable observable, Object arg );

    protected abstract boolean isCompleted( ProgressObservable observable, Object arg );
}
