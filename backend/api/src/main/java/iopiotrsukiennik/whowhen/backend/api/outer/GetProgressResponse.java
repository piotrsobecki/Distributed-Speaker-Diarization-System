package iopiotrsukiennik.whowhen.backend.api.outer;

import iopiotrsukiennik.whowhen.shared.util.progress.Progress;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Piotr
 * Date: 11.11.12
 * Time: 17:15
 * To change this template use File | Settings | File Templates.
 */
public class GetProgressResponse implements Serializable {
    private Progress progress;

    public GetProgressResponse(Progress progress) {
        this.progress = progress;
    }

    public Progress getProgress() {
        return progress;
    }
}
