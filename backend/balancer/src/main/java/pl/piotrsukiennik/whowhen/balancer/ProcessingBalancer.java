package pl.piotrsukiennik.whowhen.balancer;

import org.springframework.stereotype.Component;
import pl.piotrsukiennik.whowhen.backend.api.inner.processing.ProcessingRequest;
import pl.piotrsukiennik.whowhen.backend.api.inner.processing.ProcessingService;

import javax.annotation.Resource;

/**
 * @author Piotr Sukiennik
 */
@Component
public class ProcessingBalancer {

    @Resource( name = "processers" )
    private ProcessingService[] processingServices;


    public void handle( ProcessingRequest processingRequest ) {
        int randomId = new Double( processingServices.length * Math.random() ).intValue();
        processingServices[randomId].handle( processingRequest );
    }
}
