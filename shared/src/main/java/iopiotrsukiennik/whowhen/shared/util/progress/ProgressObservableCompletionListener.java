package iopiotrsukiennik.whowhen.shared.util.progress;

import java.util.Observable;
import java.util.Observer;

/**
 * Created with IntelliJ IDEA.
 * User: Piotr
 * Date: 11.11.12
 * Time: 00:48
 * To change this template use File | Settings | File Templates.
 */
public abstract class ProgressObservableCompletionListener implements Observer {
    @Override
    public void update(Observable o, Object arg) {
       if (o instanceof  ProgressObservable){
           if (isCompleted((ProgressObservable)o,arg)){
            complete((ProgressObservable) o,arg);
           }
       }
    }
    protected abstract void complete(ProgressObservable observable, Object arg);
    protected abstract boolean isCompleted(ProgressObservable observable, Object arg);
}
