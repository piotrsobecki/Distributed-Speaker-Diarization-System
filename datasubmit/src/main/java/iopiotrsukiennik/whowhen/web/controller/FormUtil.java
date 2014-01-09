package iopiotrsukiennik.whowhen.web.controller;

import iopiotrsukiennik.whowhen.shared.form.RequestData;
import iopiotrsukiennik.whowhen.shared.form.WhoWhenRequestForm;

/**
 * @author: Piotr Sukiennik
 * @date: 09.01.14
 * @time: 20:19
 */
public class FormUtil {
    public static RequestData toRequestData(WhoWhenRequestForm form){
        RequestData requestData = new RequestData();
        requestData.setEmail(form.getEmail());
        requestData.setFileName(form.getFileName());
        requestData.setSpeakersCount(form.getSpeakersCount());
        return requestData;
    }
}
