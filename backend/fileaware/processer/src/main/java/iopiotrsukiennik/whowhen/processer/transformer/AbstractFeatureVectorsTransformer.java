package iopiotrsukiennik.whowhen.processer.transformer;

/**
 * Created with IntelliJ IDEA.
 * User: Piotr
 * Date: 10.12.12
 * Time: 21:21
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractFeatureVectorsTransformer implements IFeatureVectorsTransformer {
    private int columnsFrom;
    private int columnsTo;

    public int getColumnsFrom() {
        return columnsFrom;
    }

    public void setColumnsFrom(int columnsFrom) {
        this.columnsFrom = columnsFrom;
    }

    public int getColumnsTo() {
        return columnsTo;
    }

    public void setColumnsTo(int columnsTo) {
        this.columnsTo = columnsTo;
    }
}
