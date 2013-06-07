package iopiotrsukiennik.whowhen.backend.api.outer;

import iopiotrsukiennik.whowhen.shared.util.progress.Progress;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Piotr
 * Date: 11.11.12
 * Time: 17:15
 * To change this template use File | Settings | File Templates.
 */
public class GetTimelineResponse implements Serializable {
    private List<Map<String,List<double[]>>> levelledLabelledIntervals;

    public GetTimelineResponse(List<Map<String,List<double[]>>> levelledLabelledIntervals) {
        this.levelledLabelledIntervals = levelledLabelledIntervals;
    }

    public List<Map<String,List<double[]>>> getLevelledLabelledIntervals() {
        return levelledLabelledIntervals;
    }
}
