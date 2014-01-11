package pl.piotrsukiennik.whowhen.backend.api.inner.balancer;

import pl.piotrsukiennik.whowhen.backend.api.inner.classification.ClassificationService;
import pl.piotrsukiennik.whowhen.backend.api.inner.convertion.ConvertionService;
import pl.piotrsukiennik.whowhen.backend.api.inner.processing.ProcessingService;
import pl.piotrsukiennik.whowhen.backend.api.inner.splitter.SplitterService;

/**
 * @author Piotr Sukiennik
 */
public interface BalancerService extends SplitterService, ConvertionService, ProcessingService, ClassificationService {

}
