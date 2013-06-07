package iopiotrsukiennik.whowhen.balancer;

import iopiotrsukiennik.whowhen.backend.api.inner.splitter.SplitterRequest;
import iopiotrsukiennik.whowhen.backend.api.inner.splitter.SplitterService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: Piotr
 * Date: 12.11.12
 * Time: 23:20
 * To change this template use File | Settings | File Templates.
 */
@Component
public class SplitterBalancer {

    @Resource(name = "splitters")
    private SplitterService[] splitterServices;


    public void handle(SplitterRequest splitterRequest) {
        int randomId = new Double(splitterServices.length*Math.random()).intValue();
        splitterServices[randomId].handle(splitterRequest);
    }
}
