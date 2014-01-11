package pl.piotrsukiennik.whowhen.balancer;

import org.springframework.stereotype.Component;
import pl.piotrsukiennik.whowhen.backend.api.inner.classification.ClassificationRequest;
import pl.piotrsukiennik.whowhen.backend.api.inner.classification.ClassificationService;

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
