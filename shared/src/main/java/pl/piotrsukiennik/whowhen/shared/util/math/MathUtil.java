package pl.piotrsukiennik.whowhen.shared.util.math;

/**
 * @author Piotr Sukiennik
 */
public class MathUtil {
    public static double euclidianDistance( double[] doubles1, double[] doubles2 ) {
        double result = 0;
        for ( int i = 0; i < doubles1.length; i++ ) {
            result += Math.pow( doubles1[i] - doubles2[i], 2 );
        }
        return Math.sqrt( result );
    }

    public static double[] sub( double[] dArr1, double[] dArr2 ) {
        double[] result = new double[dArr1.length];
        for ( int i = 0; i < dArr1.length; i++ ) {
            result[i] = dArr1[i] - dArr2[i];
        }
        return result;
    }
}
