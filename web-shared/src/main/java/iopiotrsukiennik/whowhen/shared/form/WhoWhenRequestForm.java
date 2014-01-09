package iopiotrsukiennik.whowhen.shared.form;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * Created with IntelliJ IDEA.
 * User: Piotr
 * Date: 14.10.12
 * Time: 15:23
 * To change this template use File | Settings | File Templates.
 */
public class WhoWhenRequestForm  {


    private String fileName;
    private String email;
    private Integer speakersCount;

    private CommonsMultipartFile file=null;

    public WhoWhenRequestForm() {
    }


    public CommonsMultipartFile getFile() {
        return file;
    }
    public void setFile(CommonsMultipartFile file) {
        this.file = file;
        this.fileName = file.getOriginalFilename();
    }

    public String getFileName() {
        return fileName;
    }


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
    @Override
    public String toString() {
        return "WhoWhenRequestForm{" +
                "fileName='" + fileName + '\'' +
                ", email='" + email + '\'' +
                ", file=" + file +
                '}';
    }
}
