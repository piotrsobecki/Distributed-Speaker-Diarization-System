package pl.piotrsukiennik.whowhen.classification.filter;

/**
 * @author Piotr Sukiennik
 */
public interface IFilteringClassifier {
    FilteringClassificationResult filters( double[] vector );
}
