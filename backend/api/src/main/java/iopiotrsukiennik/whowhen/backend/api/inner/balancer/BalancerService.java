package iopiotrsukiennik.whowhen.backend.api.inner.balancer;

import iopiotrsukiennik.whowhen.backend.api.inner.classification.ClassificationService;
import iopiotrsukiennik.whowhen.backend.api.inner.convertion.ConvertionService;
import iopiotrsukiennik.whowhen.backend.api.inner.processing.ProcessingService;
import iopiotrsukiennik.whowhen.backend.api.inner.splitter.SplitterService;

/**
 * @author Piotr Sukiennik
 */
public interface BalancerService extends SplitterService, ConvertionService, ProcessingService, ClassificationService {

}
