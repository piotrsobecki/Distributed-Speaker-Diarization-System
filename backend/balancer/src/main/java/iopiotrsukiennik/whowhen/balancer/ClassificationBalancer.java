package iopiotrsukiennik.whowhen.balancer;

import iopiotrsukiennik.whowhen.backend.api.inner.classification.ClassificationRequest;
import iopiotrsukiennik.whowhen.backend.api.inner.classification.ClassificationService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Piotr Sukiennik
 */
@Component
public class ClassificationBalancer {

    @Resource(name = "classifiers")
    private ClassificationService[] classificationService;


    public void handle( ClassificationRequest classificationRequest ) {
        int randomId = new Double( classificationService.length * Math.random() ).intValue();
        classificationService[randomId].handle( classificationRequest );
    }
}
