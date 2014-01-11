package iopiotrsukiennik.whowhen.balancer;

import iopiotrsukiennik.whowhen.backend.api.inner.splitter.SplitterRequest;
import iopiotrsukiennik.whowhen.backend.api.inner.splitter.SplitterService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Piotr Sukiennik
 */
@Component
public class SplitterBalancer {

    @Resource(name = "splitters")
    private SplitterService[] splitterServices;


    public void handle( SplitterRequest splitterRequest ) {
        int randomId = new Double( splitterServices.length * Math.random() ).intValue();
        splitterServices[randomId].handle( splitterRequest );
    }
}
