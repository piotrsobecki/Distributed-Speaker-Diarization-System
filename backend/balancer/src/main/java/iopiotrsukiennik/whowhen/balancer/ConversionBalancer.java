package iopiotrsukiennik.whowhen.balancer;

import iopiotrsukiennik.whowhen.backend.api.inner.convertion.ConvertionRequest;
import iopiotrsukiennik.whowhen.backend.api.inner.convertion.ConvertionService;
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
public class ConversionBalancer {

    @Resource(name = "converters")
    private ConvertionService[] convertionServices;


    public void handle(ConvertionRequest convertionRequest) {
        int randomId = new Double(convertionServices.length*Math.random()).intValue();
        convertionServices[randomId].handle(convertionRequest);
    }

    public String[] getAcceptableFormats() {
        int randomId = new Double(convertionServices.length*Math.random()).intValue();
        return convertionServices[randomId].getAcceptableFormats();
    }
}
