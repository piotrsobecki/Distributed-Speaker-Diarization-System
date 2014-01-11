package iopiotrsukiennik.whowhen.shared.serializer.impl;

import iopiotrsukiennik.whowhen.shared.util.ArrayUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Piotr Sukiennik
 */
public class CSVDataSerializer extends AbstractSerializer {
    public String getExtension() {
        return "csv";
    }

    public void doSerialize( Object o, File output ) {
        try {
            PrintWriter printWriter = new PrintWriter( new BufferedWriter( new FileWriter( output ) ) );
            for ( double[] doubles : (List<double[]>) o ) {
                String line = "";
                String comma = "";
                for ( double d : doubles ) {
                    line += comma + d;
                    comma = ",";
                }
                printWriter.println( ArrayUtil.join( doubles, "," ) );
            }
            printWriter.flush();
            printWriter.close();

        }
        catch ( IOException e ) {
            e.printStackTrace();
            throw new RuntimeException( e );
        }

    }


    public <T> T doDeserialize( File input, Class<T> targetClass ) {
        List<double[]> output = new ArrayList<double[]>();
        try {
            BufferedReader bufferedReader = new BufferedReader( new FileReader( input ) );
            String readLine = null;
            while ( ( readLine = bufferedReader.readLine() ) != null && !( readLine = readLine.trim() ).isEmpty() ) {
                String[] lineSplit = readLine.split( "," );
                double[] dataArray = new double[lineSplit.length];
                for ( int i = 0; i < lineSplit.length; i++ ) {
                    dataArray[i] = Double.parseDouble( lineSplit[i] );
                }
                output.add( dataArray );
            }
        }
        catch ( IOException e ) {
            e.printStackTrace();
            throw new RuntimeException( e );
        }
        return (T) output;
    }

    public boolean supportsSerialization( Class clazz ) {
        return List.class.isAssignableFrom( clazz );
    }

}
