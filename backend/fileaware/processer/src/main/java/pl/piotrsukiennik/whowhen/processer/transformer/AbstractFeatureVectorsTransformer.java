package pl.piotrsukiennik.whowhen.processer.transformer;

/**
 * @author Piotr Sukiennik
 */
public abstract class AbstractFeatureVectorsTransformer implements IFeatureVectorsTransformer {
    private int columnsFrom;

    private int columnsTo;

    public int getColumnsFrom() {
        return columnsFrom;
    }

    public void setColumnsFrom( int columnsFrom ) {
        this.columnsFrom = columnsFrom;
    }

    public int getColumnsTo() {
        return columnsTo;
    }

    public void setColumnsTo( int columnsTo ) {
        this.columnsTo = columnsTo;
    }
}
