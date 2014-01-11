package iopiotrsukiennik.whowhen.classification.impl.weka;

import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;

import java.util.List;

/**
 * @author Piotr Sukiennik
 */
public class WekaUtil {

    public static Instances toInstances( List<double[]> data, String label ) {
        FastVector attributes = new FastVector();
        for ( int i = 0; i < data.get( 0 ).length; i++ ) {
            attributes.addElement( new Attribute( "attribute" + i ) );
        }
        Instances instances = new Instances( label, attributes, data.size() );
        for ( double[] dArr : data ) {
            instances.add( toInstance( dArr ) );
        }
        return instances;
    }

    public static Instance toInstance( double[] vector ) {
        return new Instance( 1, vector );
    }
}
