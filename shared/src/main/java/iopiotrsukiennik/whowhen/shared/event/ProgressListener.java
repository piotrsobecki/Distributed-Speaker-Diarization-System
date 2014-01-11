package iopiotrsukiennik.whowhen.shared.event;

import iopiotrsukiennik.whowhen.shared.form.RequestData;

/**
 * @author: Piotr Sukiennik
 */
public interface ProgressListener {
    void notify( int progress, final String requestIdentifier, final RequestData request );

    void notify( int progress, final String requestIdentifier );
}
