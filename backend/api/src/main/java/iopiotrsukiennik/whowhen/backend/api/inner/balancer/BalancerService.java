package iopiotrsukiennik.whowhen.backend.api.inner.balancer;

import iopiotrsukiennik.whowhen.backend.api.IRequestIdentifierBound;
import iopiotrsukiennik.whowhen.backend.api.inner.InnerBackendService;
import iopiotrsukiennik.whowhen.backend.api.inner.classification.ClassificationService;
import iopiotrsukiennik.whowhen.backend.api.inner.convertion.ConvertionService;
import iopiotrsukiennik.whowhen.backend.api.inner.processing.ProcessingService;
import iopiotrsukiennik.whowhen.backend.api.inner.splitter.SplitterService;

/**
 * Created with IntelliJ IDEA.
 * User: Piotr
 * Date: 08.11.12
 * Time: 23:26
 * To change this template use File | Settings | File Templates.
 */
public interface BalancerService extends SplitterService, ConvertionService, ProcessingService, ClassificationService{

}
