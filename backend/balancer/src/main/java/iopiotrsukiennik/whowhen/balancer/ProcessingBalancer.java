package iopiotrsukiennik.whowhen.balancer;

import iopiotrsukiennik.whowhen.backend.api.inner.processing.ProcessingRequest;
import iopiotrsukiennik.whowhen.backend.api.inner.processing.ProcessingService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Piotr Sukiennik
 */
@Component
public class ProcessingBalancer {

    @Resource(name = "processers")
    private ProcessingService[] processingServices;


    public void handle( ProcessingRequest processingRequest ) {
        int randomId = new Double( processingServices.length * Math.random() ).intValue();
        processingServices[randomId].handle( processingRequest );
    }
}
