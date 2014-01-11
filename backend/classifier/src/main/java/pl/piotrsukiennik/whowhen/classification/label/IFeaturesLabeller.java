package pl.piotrsukiennik.whowhen.classification.label;

import java.util.List;

/**
 * @author Piotr Sukiennik
 */
public interface IFeaturesLabeller {
    String getName();

    LabelledIndexIntervals label( List<double[]> input );

}
