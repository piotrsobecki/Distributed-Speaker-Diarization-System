package iopiotrsukiennik.whowhen.shared.form;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Piotr
 * Date: 02.12.12
 * Time: 20:08
 * To change this template use File | Settings | File Templates.
 */
public class RequestData implements Serializable{
    private String email;
    private String fileName;
    private Integer speakersCount;


    public Integer getSpeakersCount() {
        return speakersCount;
    }

    public void setSpeakersCount(Integer speakersCount) {
        this.speakersCount = speakersCount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
