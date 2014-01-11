package pl.piotrsukiennik.whowhen.shared.event;

import pl.piotrsukiennik.whowhen.shared.form.RequestData;

/**
 * @author: Piotr Sukiennik
 */
public interface ProgressNotifier {
    void notifyAll( int progress, final String requestIdentifier, final RequestData request );

    void notifyAll( int progress, final String requestIdentifier );
}
