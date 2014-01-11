package iopiotrsukiennik.whowhen.classification.impl.weka;

import weka.clusterers.Clusterer;
import weka.core.Instance;
import weka.core.Instances;
import weka.filters.Filter;

import java.util.List;

/**
 * @author Piotr Sukiennik
 */
public class FilteringWekaLabelingClusterer<F extends Filter> extends WekaLabelingClusterer {
    private Class<F> filterClass;

    public FilteringWekaLabelingClusterer( Clusterer clusterer, Class<F> filterClass ) {
        super( clusterer );
        this.filterClass = filterClass;
    }

    @Override
    public void train( List<double[]> data ) {
        try {

            Filter filter = filterClass.newInstance();
            Instances instances = WekaUtil.toInstances( data, getRequiredLabel() );
            filter.setInputFormat( instances );
            for ( int i = 0; i < instances.numInstances(); i++ ) {
                filter.input( instances.instance( i ) );
            }
            filter.batchFinished();

            Instances newData = filter.getOutputFormat();
            Instance processed;
            while ( ( processed = filter.output() ) != null ) {
                newData.add( processed );
            }
            clusterer.buildClusterer( newData );
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
    }


}
