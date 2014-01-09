package iopiotrsukiennik.whowhen.shared.event;

import iopiotrsukiennik.whowhen.shared.form.RequestData;

import java.util.Collection;
import java.util.Collections;

/**
 * @author: Piotr Sukiennik
 * @date: 09.01.14
 * @time: 17:38
 */
public class ProgressNotifierImpl implements ProgressNotifier {

    private Collection<ProgressListener> progressListeners = Collections.EMPTY_LIST;


    @Override
    public void notifyAll(int progress, String requestIdentifier) {
        for (ProgressListener progressListener: progressListeners){
            progressListener.notify(progress,requestIdentifier);
        }
    }

    @Override
    public void notifyAll(int progress, String requestIdentifier, RequestData request) {
        for (ProgressListener progressListener: progressListeners){
            progressListener.notify(progress,requestIdentifier,request);
        }
    }

    public Collection<ProgressListener> getProgressListeners() {
        return progressListeners;
    }

    public void setProgressListeners(Collection<ProgressListener> progressListeners) {
        this.progressListeners = progressListeners;
    }
}
