package iopiotrsukiennik.whowhen.backend.api.outer;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Piotr Sukiennik
 */
public class GetTimelineResponse implements Serializable {
    private List<Map<String, List<double[]>>> levelledLabelledIntervals;

    public GetTimelineResponse( List<Map<String, List<double[]>>> levelledLabelledIntervals ) {
        this.levelledLabelledIntervals = levelledLabelledIntervals;
    }

    public List<Map<String, List<double[]>>> getLevelledLabelledIntervals() {
        return levelledLabelledIntervals;
    }
}
