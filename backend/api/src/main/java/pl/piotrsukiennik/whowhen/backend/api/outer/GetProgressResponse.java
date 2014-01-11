package pl.piotrsukiennik.whowhen.backend.api.outer;

import pl.piotrsukiennik.whowhen.shared.util.progress.Progress;

import java.io.Serializable;

/**
 * @author Piotr Sukiennik
 */
public class GetProgressResponse implements Serializable {
    private Progress progress;

    public GetProgressResponse( Progress progress ) {
        this.progress = progress;
    }

    public Progress getProgress() {
        return progress;
    }
}
