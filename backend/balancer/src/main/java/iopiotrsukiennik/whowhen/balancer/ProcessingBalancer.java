package iopiotrsukiennik.whowhen.balancer;

import iopiotrsukiennik.whowhen.backend.api.inner.processing.ProcessingRequest;
import iopiotrsukiennik.whowhen.backend.api.inner.processing.ProcessingService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: Piotr
 * Date: 12.11.12
 * Time: 23:21
 * To change this template use File | Settings | File Templates.
 */
@Component
public class ProcessingBalancer  {

    @Resource(name = "processers")
    private ProcessingService[] processingServices;


    public void handle(ProcessingRequest processingRequest) {
        int randomId = new Double(processingServices.length*Math.random()).intValue();
        processingServices[randomId].handle(processingRequest);
    }
}
