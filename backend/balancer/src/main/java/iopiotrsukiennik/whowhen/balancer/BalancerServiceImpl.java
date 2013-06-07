package iopiotrsukiennik.whowhen.balancer;

import iopiotrsukiennik.whowhen.backend.api.IRequestIdentifierBound;
import iopiotrsukiennik.whowhen.backend.api.inner.InnerBackendService;
import iopiotrsukiennik.whowhen.backend.api.inner.balancer.BalancerService;
import iopiotrsukiennik.whowhen.backend.api.inner.classification.ClassificationRequest;
import iopiotrsukiennik.whowhen.backend.api.inner.classification.ClassificationService;
import iopiotrsukiennik.whowhen.backend.api.inner.convertion.ConvertionRequest;
import iopiotrsukiennik.whowhen.backend.api.inner.convertion.ConvertionService;
import iopiotrsukiennik.whowhen.backend.api.inner.processing.ProcessingService;
import iopiotrsukiennik.whowhen.backend.api.inner.processing.ProcessingRequest;
import iopiotrsukiennik.whowhen.backend.api.inner.splitter.SplitterService;
import iopiotrsukiennik.whowhen.backend.api.inner.splitter.SplitterRequest;
import iopiotrsukiennik.whowhen.shared.aop.MonitorAfter;
import iopiotrsukiennik.whowhen.shared.aop.MonitorBefore;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Piotr
 * Date: 09.11.12
 * Time: 00:15
 * To change this template use File | Settings | File Templates.
 */

@MonitorBefore
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
    public void handle(ClassificationRequest classificationRequest) {
        classificationBalancer.handle(classificationRequest);
    }

    @Override
    public void handle(ConvertionRequest convertionRequest) {
        conversionBalancer.handle(convertionRequest);
    }

    @Override
    public String[] getAcceptableFormats() {
       return conversionBalancer.getAcceptableFormats();
    }

    @Override
    public void handle(ProcessingRequest processingRequest) {
        processingBalancer.handle(processingRequest);
    }

    @Override
    public void handle(SplitterRequest splitterRequest) {
       splitterBalancer.handle(splitterRequest);
    }
}
