package iopiotrsukiennik.whowhen.classification.filter;

/**
 * Created with IntelliJ IDEA.
 * User: Piotr
 * Date: 01.11.12
 * Time: 16:04
 * To change this template use File | Settings | File Templates.
 */
public interface IFilteringClassifier {
    FilteringClassificationResult filters(double[] vector);
}
