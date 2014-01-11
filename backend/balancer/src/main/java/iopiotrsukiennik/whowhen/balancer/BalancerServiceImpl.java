package iopiotrsukiennik.whowhen.balancer;

import iopiotrsukiennik.whowhen.backend.api.inner.balancer.BalancerService;
import iopiotrsukiennik.whowhen.backend.api.inner.classification.ClassificationRequest;
import iopiotrsukiennik.whowhen.backend.api.inner.convertion.ConvertionRequest;
import iopiotrsukiennik.whowhen.backend.api.inner.processing.ProcessingRequest;
import iopiotrsukiennik.whowhen.backend.api.inner.splitter.SplitterRequest;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Piotr Sukiennik
 */

@Component("balancerServiceImpl")
public class BalancerServiceImpl implements BalancerService {

    @Resource
    private ClassificationBalancer classificationBalancer;

    @Resource
    private ConversionBalancer conversionBalancer;

    @Resource
    private ProcessingBalancer processingBalancer;

    @Resource
    private SplitterBalancer splitterBalancer;

    @Override
    public void handle( ClassificationRequest classificationRequest ) {
        classificationBalancer.handle( classificationRequest );
    }

    @Override
    public void handle( ConvertionRequest convertionRequest ) {
        conversionBalancer.handle( convertionRequest );
    }

    @Override
    public String[] getAcceptableFormats() {
        return conversionBalancer.getAcceptableFormats();
    }

    @Override
    public void handle( ProcessingRequest processingRequest ) {
        processingBalancer.handle( processingRequest );
    }

    @Override
    public void handle( SplitterRequest splitterRequest ) {
        splitterBalancer.handle( splitterRequest );
    }
}
