package iopiotrsukiennik.whowhen.balancer;

import iopiotrsukiennik.whowhen.backend.api.inner.classification.ClassificationRequest;
import iopiotrsukiennik.whowhen.backend.api.inner.classification.ClassificationService;
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
public class ClassificationBalancer  {

    @Resource(name = "classifiers")
    private ClassificationService[] classificationService;


    public void handle(ClassificationRequest classificationRequest) {
        int randomId = new Double(classificationService.length*Math.random()).intValue();
        classificationService[randomId].handle(classificationRequest);
    }
}
