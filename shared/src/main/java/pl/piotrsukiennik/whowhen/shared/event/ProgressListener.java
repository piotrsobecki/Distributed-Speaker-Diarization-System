package pl.piotrsukiennik.whowhen.shared.event;

import pl.piotrsukiennik.whowhen.shared.form.RequestData;

/**
 * @author: Piotr Sukiennik
 */
public interface ProgressListener {
    void notify( int progress, final String requestIdentifier, final RequestData request );

    void notify( int progress, final String requestIdentifier );
}
