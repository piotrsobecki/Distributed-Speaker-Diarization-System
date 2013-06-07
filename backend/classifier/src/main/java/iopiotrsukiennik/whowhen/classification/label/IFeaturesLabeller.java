package iopiotrsukiennik.whowhen.classification.label;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Piotr
 * Date: 25.10.12
 * Time: 23:14
 * To change this template use File | Settings | File Templates.
 */
public interface IFeaturesLabeller {
    String getName();
    LabelledIndexIntervals label(List<double[]> input);

}
