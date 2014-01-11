package iopiotrsukiennik.whowhen.classification.label;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;
import java.util.*;

/**
 * @author Piotr Sukiennik
 */
@JsonAutoDetect
public class LabelledIndexIntervals implements Serializable {

    @JsonProperty
    private List<double[]> resultData;

    @JsonIgnore
    private Map<String, Set<IndexInterval>> indexIntervals = new LinkedHashMap<String, Set<IndexInterval>>();

    @JsonIgnore
    private IndexInterval lastIndexInterval = new IndexInterval();

    public LabelledIndexIntervals() {
    }

    public IndexInterval addIndexInterval( IndexInterval indexInterval ) {
        Set<IndexInterval> intervalsForLabel = indexIntervals.get( indexInterval.getLabel() );
        if ( intervalsForLabel == null ) {
            intervalsForLabel = new TreeSet<IndexInterval>();
            indexIntervals.put( indexInterval.getLabel(), intervalsForLabel );
        }
        intervalsForLabel.add( indexInterval );
        return indexInterval;
    }

    public void applyLabel( String label, int where ) {
        lastIndexInterval = lastIndexInterval.add( label, where );
    }

    public List<double[]> getResultData() {
        return resultData;
    }

    public void setResultData( List<double[]> resultData ) {
        this.resultData = resultData;
    }

    @JsonProperty
    public Map<String, List<int[]>> getLabelledIndexRanges() {
        Map<String, List<int[]>> output = new TreeMap<String, List<int[]>>();
        for ( Map.Entry<String, Set<IndexInterval>> c : indexIntervals.entrySet() ) {
            List<int[]> ranges = new ArrayList<int[]>();
            for ( IndexInterval indexInterval : c.getValue() ) {
                ranges.add( indexInterval.getRange() );
            }
            output.put( c.getKey(), ranges );
        }
        return output;
    }

    public TreeSet<IndexInterval> getIndexIntervals() {
        TreeSet<IndexInterval> intervals = new TreeSet<IndexInterval>();
        for ( Map.Entry<String, Set<IndexInterval>> c : indexIntervals.entrySet() ) {
            intervals.addAll( c.getValue() );
        }
        return intervals;
    }

    public class IndexInterval implements Comparable<IndexInterval> {

        private String label;

        private int[] range = new int[2];

        IndexInterval() {

        }

        IndexInterval( String label, Integer from ) {
            this( label, from, from );
        }

        IndexInterval( String label, Integer from, Integer to ) {
            this( label, new int[] { from, to } );
        }

        IndexInterval( String label, int[] range ) {
            this.label = label;
            this.range = range;
            addIndexInterval( this );
        }

        public int[] getRange() {
            return range;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel( String label ) {
            this.label = label;
        }

        public int getFrom() {
            return range[0];
        }

        public IndexInterval add( String label, int index ) {
            if ( this.label == null || !this.label.equals( label ) || index - getTo() > 1 ) {
                return new IndexInterval( label, index );
            }
            setTo( index );
            return this;
        }

        public void setFrom( int from ) {
            this.range[0] = from;
        }

        public int getTo() {
            return range[1];
        }

        public void setTo( int to ) {
            this.range[1] = to;
        }

        public int compareTo( IndexInterval o ) {
            return Integer.compare( getFrom(), o.getFrom() );
        }

        @Override
        public boolean equals( Object o ) {
            if ( this == o )
                return true;
            if ( o == null || getClass() != o.getClass() )
                return false;

            IndexInterval that = (IndexInterval) o;

            if ( label != null ? !label.equals( that.label ) : that.label != null )
                return false;
            if ( !Arrays.equals( range, that.range ) )
                return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = label != null ? label.hashCode() : 0;
            result = 31 * result + ( range != null ? Arrays.hashCode( range ) : 0 );
            return result;
        }
    }
}
