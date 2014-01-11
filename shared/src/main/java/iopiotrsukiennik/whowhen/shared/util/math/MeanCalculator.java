package iopiotrsukiennik.whowhen.shared.util.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Piotr Sukiennik
 */
public abstract class MeanCalculator {
    public enum MeanMethod {ARITHMETIC, GEOMETRIC, HARMONIC}

    public static double calculateMean( double[] input, MeanMethod method ) {
        int len = input.length;
        if ( len == 0 ) {
            return Double.NaN;
        }
        else if ( len == 1 ) {
            return input[0];
        }
        switch ( method ) {
            case ARITHMETIC:
                return calculateArithmeticalMean( input );
            case GEOMETRIC:
                return calculateGeometricMean( input );
            case HARMONIC:
                return calculateHarmonicMean( input );
        }
        return Double.NaN;
    }

    public static double[] calculateMean( List<double[]> input, MeanMethod method ) {
        int len = input.size();
        if ( len == 0 ) {
            return null;
        }
        else if ( len == 1 ) {
            return input.get( 0 );
        }
        switch ( method ) {
            case ARITHMETIC:
                return calculateArithmeticalMean( input );
            case GEOMETRIC:
                return calculateGeometricMean( input );
            case HARMONIC:
                return calculateHarmonicMean( input );
        }
        return null;
    }

    private static double calculateArithmeticalMean( double[] input ) {
        double output = 0;
        for ( double d : input ) {
            output += d / input.length;
        }
        return output;
    }

    private static double calculateGeometricMean( double[] input ) {
        double output = 1;
        for ( double d : input ) {
            output *= d;
        }
        return Math.pow( output, ( 1 / input.length ) );
    }

    private static double calculateHarmonicMean( double[] input ) {
        double output = .0;
        for ( double d : input ) {
            output += 1 / d;
        }
        return input.length / output;
    }

    private static double[] calculateArithmeticalMean( List<double[]> input ) {
        double[] output = new double[input.get( 0 ).length];
        for ( double[] d : input ) {
            for ( int i = 0; i < d.length; i++ ) {
                output[i] += d[i];
            }
        }
        for ( int i = 0; i < output.length; i++ ) {
            output[i] = output[i] / input.size();
        }
        return output;
    }

    //tests
    public static void main( String... args ) {
        System.out.println( Arrays.toString( calculateMean( new ArrayList<double[]>() {{
            add( new double[] { 1, 2, 3 } );
            add( new double[] { 1, 2, 3 } );
            add( new double[] { 1, 2, 3 } );
        }}, MeanMethod.ARITHMETIC ) ) );

    }

    private static double[] calculateGeometricMean( List<double[]> input ) {
        double[] output = new double[input.get( 0 ).length];
        for ( double[] d : input ) {
            for ( int i = 0; i < d.length; i++ ) {
                output[i] *= d[i];
            }

        }
        for ( int i = 0; i < output.length; i++ ) {
            output[i] = Math.pow( output[i], ( 1 / input.size() ) );
        }
        return output;
    }

    private static double[] calculateHarmonicMean( List<double[]> input ) {
        double[] output = new double[input.get( 0 ).length];
        for ( double[] d : input ) {
            for ( int i = 0; i < d.length; i++ ) {
                output[i] += 1 / d[i];
            }

        }
        for ( int i = 0; i < output.length; i++ ) {
            output[i] = input.size() / output[i];
        }
        return output;
    }
}
