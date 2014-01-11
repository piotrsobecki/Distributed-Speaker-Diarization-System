package iopiotrsukiennik.whowhen.splitter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Piotr Sukiennik
 */
public class IndexIntervalsToTimelineMapper {
    private double keyLengthMillis;

    public IndexIntervalsToTimelineMapper( double keyLengthMillis ) {
        System.out.printf( "New IndexIntervalsToTimelineMapper with keyLength = (%s) %s", keyLengthMillis, System.lineSeparator() );
        this.keyLengthMillis = keyLengthMillis;
    }

    protected double[] transforIndexesToMillis( int[] range ) {
        return new double[] { range[0] * keyLengthMillis, ( range[1] + 1 ) * keyLengthMillis };
    }

    public Map<String, List<double[]>> mapGroupByLabel( Map<String, List<int[]>> labeledIndexIntervals ) {
        Map<String, List<double[]>> output = new LinkedHashMap<String, java.util.List<double[]>>();
        for ( Map.Entry<String, List<int[]>> labeledIntervals : labeledIndexIntervals.entrySet() ) {
            List<double[]> longs = new ArrayList<double[]>();
            for ( int[] range : labeledIntervals.getValue() ) {
                longs.add( transforIndexesToMillis( range ) );
            }
            output.put( labeledIntervals.getKey(), longs );
        }
        return output;
    }


    public double getKeyLengthMillis() {
        return keyLengthMillis;
    }

    public void setKeyLengthMillis( double keyLengthMillis ) {
        this.keyLengthMillis = keyLengthMillis;
    }

    public class TimeInterval implements Comparable<TimeInterval> {
        private String label;

        private long[] range = new long[2];


        public TimeInterval( String label, long[] range ) {
            this.label = label;
            this.range = range;
        }

        public TimeInterval( String label, long from, long to ) {
            this( label, new long[] { from, to } );
        }

        public long getFrom() {
            return range[1];
        }

        public void setFrom( long from ) {
            this.range[1] = from;
        }

        public long getTo() {
            return range[0];
        }

        public void setTo( long to ) {
            this.range[0] = to;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel( String label ) {
            this.label = label;
        }

        public int compareTo( TimeInterval o ) {
            return Long.compare( getFrom(), o.getFrom() );
        }

        public long[] getRange() {
            return range;
        }
    }
}
