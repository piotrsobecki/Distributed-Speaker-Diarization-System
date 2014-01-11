package iopiotrsukiennik.whowhen.shared.util.math;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Piotr Sukiennik
 */
public abstract class StatisticsCalculator {

    public static double calculateStandartDeviation( double variance ) {
        return Math.sqrt( variance );
    }


    public static double calculateVariance( double[] dArr ) {
        double mean = MeanCalculator.calculateMean( dArr, MeanCalculator.MeanMethod.ARITHMETIC );
        double output = .0;
        for ( double d : dArr ) {
            output += Math.pow( d - mean, 2 );
        }
        return output / dArr.length;
    }

    public static double[] calculateVariances( List<double[]> dMatrix ) {
        List<double[]> inverted = invert( dMatrix );
        double[] output = new double[inverted.size()];

        for ( int i = 0; i < inverted.size(); i++ ) {
            output[i] = calculateVariance( inverted.get( i ) );
        }
        return output;
    }

    public static double calculateStandartDeviation( double[] dArr ) {
        return calculateStandartDeviation( calculateVariance( dArr ) );
    }

    public static double[] calculateStandartDeviations( List<double[]> dMatrix ) {
        List<double[]> inverted = invert( dMatrix );
        double[] output = new double[inverted.size()];
        for ( int i = 0; i < inverted.size(); i++ ) {
            output[i] = calculateStandartDeviation( inverted.get( i ) );
        }
        return output;
    }

    public static List<double[]> invert( List<double[]> dMatrix ) {
        List<double[]> inverted = new ArrayList<double[]>();
        for ( int i = 0; i < dMatrix.get( i ).length; i++ ) {
            double[] column = new double[dMatrix.size()];
            for ( int j = 0; j < dMatrix.size(); j++ ) {
                column[j] = dMatrix.get( j )[i];
            }
            inverted.add( column );
        }
        return inverted;
    }


}
