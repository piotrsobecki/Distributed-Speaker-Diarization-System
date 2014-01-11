package pl.piotrsukiennik.whowhen.web.controller;

import pl.piotrsukiennik.whowhen.shared.form.RequestData;
import pl.piotrsukiennik.whowhen.shared.form.WhoWhenRequestForm;

/**
 * @author: Piotr Sukiennik
 */
public class FormUtil {
    public static RequestData toRequestData( WhoWhenRequestForm form ) {
        RequestData requestData = new RequestData();
        requestData.setEmail( form.getEmail() );
        requestData.setFileName( form.getFileName() );
        requestData.setSpeakersCount( form.getSpeakersCount() );
        return requestData;
    }
}
