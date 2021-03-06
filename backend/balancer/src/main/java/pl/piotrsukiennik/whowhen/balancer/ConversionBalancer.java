package pl.piotrsukiennik.whowhen.balancer;

import org.springframework.stereotype.Component;
import pl.piotrsukiennik.whowhen.backend.api.inner.convertion.ConvertionRequest;
import pl.piotrsukiennik.whowhen.backend.api.inner.convertion.ConvertionService;

import javax.annotation.Resource;

/**
 * @author Piotr Sukiennik
 */
@Component
public class ConversionBalancer {

    @Resource( name = "converters" )
    private ConvertionService[] convertionServices;


    public void handle( ConvertionRequest convertionRequest ) {
        int randomId = new Double( convertionServices.length * Math.random() ).intValue();
        convertionServices[randomId].handle( convertionRequest );
    }

    public String[] getAcceptableFormats() {
        int randomId = new Double( convertionServices.length * Math.random() ).intValue();
        return convertionServices[randomId].getAcceptableFormats();
    }
}
