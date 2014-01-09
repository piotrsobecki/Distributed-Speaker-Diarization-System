package iopiotrsukiennik.whowhen.shared.event;

import iopiotrsukiennik.whowhen.shared.form.RequestData;

/**
 * @author: Piotr Sukiennik
 * @date: 09.01.14
 * @time: 17:36
 */
public interface ProgressNotifier{
    void notifyAll(int progress, final String requestIdentifier, final RequestData request);
    void notifyAll(int progress, final String requestIdentifier);
}
