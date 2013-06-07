package iopiotrsukiennik.whowhen.web.util;

import iopiotrsukiennik.whowhen.shared.form.WhoWhenRequestForm;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created with IntelliJ IDEA.
 * User: Piotr
 * Date: 27.01.13
 * Time: 18:56
 * To change this template use File | Settings | File Templates.
 */
public class RequestValidator implements Validator {
    @Override
    public boolean supports(Class aClass) {
        return WhoWhenRequestForm.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors,"file","File.null");
        ValidationUtils.rejectIfEmpty(errors,"speakersCount","speakersCount.null");
        if (!errors.hasErrors()){
             WhoWhenRequestForm whenRequestForm = ((WhoWhenRequestForm)o);
             if (whenRequestForm.getSpeakersCount()<0){
                errors.rejectValue("speakersCount","speakersCount.negative");
             }
        }
    }
}
