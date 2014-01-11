package pl.piotrsukiennik.whowhen.web.util;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import pl.piotrsukiennik.whowhen.shared.form.WhoWhenRequestForm;

/**
 * @author Piotr Sukiennik
 */
public class RequestValidator implements Validator {
    @Override
    public boolean supports( Class aClass ) {
        return WhoWhenRequestForm.class.isAssignableFrom( aClass );
    }

    @Override
    public void validate( Object o, Errors errors ) {
        ValidationUtils.rejectIfEmpty( errors, "file", "File.null" );
        ValidationUtils.rejectIfEmpty( errors, "speakersCount", "speakersCount.null" );
        if ( !errors.hasErrors() ) {
            WhoWhenRequestForm whenRequestForm = ( (WhoWhenRequestForm) o );
            if ( whenRequestForm.getSpeakersCount() < 0 ) {
                errors.rejectValue( "speakersCount", "speakersCount.negative" );
            }
        }
    }
}
