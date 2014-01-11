package pl.piotrsukiennik.whowhen.shared.form;

import java.io.Serializable;

/**
 * @author Piotr Sukiennik
 */
public class RequestData implements Serializable {
    private String email;

    private String fileName;

    private Integer speakersCount;


    public Integer getSpeakersCount() {
        return speakersCount;
    }

    public void setSpeakersCount( Integer speakersCount ) {
        this.speakersCount = speakersCount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName( String fileName ) {
        this.fileName = fileName;
    }
}
