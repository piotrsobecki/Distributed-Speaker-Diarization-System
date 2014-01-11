package pl.piotrsukiennik.whowhen.classification.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Piotr Sukiennik
 */
public class ArffBuilder {

    private Map<String, List<double[]>> dataSets = new HashMap<String, List<double[]>>();

    private String relationName;

    private int dataDimension;

    public ArffBuilder( String relationName, int dataDimension ) {
        this.relationName = relationName;
        this.dataDimension = dataDimension;
    }

    public static ArffBuilder build( String relationName, int dataDimension, Map<String, List<double[]>> data ) {
        ArffBuilder arffBuilder = new ArffBuilder( relationName, dataDimension );
        for ( Map.Entry<String, List<double[]>> me : data.entrySet() ) {
            arffBuilder.addClassDataSet( me );
        }
        return arffBuilder;
    }

    public synchronized void addClassDataSet( Map.Entry<String, List<double[]>> data ) {
        this.addClassDataSet( data.getKey(), data.getValue() );
    }

    public synchronized void addClassDataSet( String className, List<double[]> data ) {
        List<double[]> inDataSet = dataSets.get( className );
        if ( inDataSet == null ) {
            dataSets.put( className, data );
        }
        else {
            inDataSet.addAll( data );
        }
    }

    public synchronized List<double[]> popClass( String className ) {
        return dataSets.remove( className );
    }

    public synchronized void reset() {
        dataSets = new HashMap<String, List<double[]>>();
    }

    public synchronized void save( File targetFile ) {
        try {
            targetFile.createNewFile();
            BufferedWriter bw = new BufferedWriter( new FileWriter( targetFile ) );

            bw.write( "@RELATION " );
            bw.write( relationName );
            bw.write( System.lineSeparator() );
            for ( int i = 0; i < this.dataDimension; i++ ) {
                bw.write( "@ATTRIBUTE " + i + " NUMERIC" );
                bw.write( System.lineSeparator() );
            }
            bw.write( "@ATTRIBUTE class {" );
            String comma = "";
            for ( String key : dataSets.keySet() ) {
                bw.write( comma );
                bw.write( key );
                comma = ",";
            }
            bw.write( "}" );
            bw.write( System.lineSeparator() );
            bw.write( "@DATA" );
            for ( Map.Entry<String, List<double[]>> dataSetEntry : dataSets.entrySet() ) {
                bw.write( System.lineSeparator() );
                for ( double[] dArr : dataSetEntry.getValue() ) {
                    for ( double d : dArr ) {
                        bw.write( d + "," );
                    }
                    bw.write( dataSetEntry.getKey() );
                    bw.write( System.lineSeparator() );
                }

            }
            bw.flush();
            bw.close();

        }
        catch ( IOException e ) {
            e.printStackTrace();
            throw new RuntimeException( e );
        }
    }
}
