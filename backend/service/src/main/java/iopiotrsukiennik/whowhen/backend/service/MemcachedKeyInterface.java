package iopiotrsukiennik.whowhen.backend.service;

/**
 * Created with IntelliJ IDEA.
 * User: Piotr
 * Date: 02.12.12
 * Time: 11:06
 * To change this template use File | Settings | File Templates.
 */
public class MemcachedKeyInterface {
    protected String requestIdentifier;

    public static final String ACCEPTABLE_FORMATS_KEY = "ww.acceptableformats";


    public MemcachedKeyInterface(String requestIdentifier) {
        this.requestIdentifier=requestIdentifier;
    }


    public String requestKey(){
        return "r."+requestIdentifier;
    }

    public String progressKey(){
        return "p."+requestIdentifier;
    }
    public String classificationResponseKey(){
        return "clr."+requestIdentifier;
    }
    public String convertionResponseKey(){
        return "cor."+requestIdentifier;
    }

    public String convertedAudioInfoResponseKey(){
        return "cor.ai."+requestIdentifier;
    }

    public String processingResponseKey(){
        return "pr."+requestIdentifier;
    }
    public String processingResponseIntervalLength(){
        return "pr.il."+requestIdentifier;
    }
    public String splitterResponseKey(){
        return "sr."+requestIdentifier;
    }
    public String getLabelsWithLevels(){
        return "lwl."+requestIdentifier;
    }

    public String getLabelTimeline(String label){
        return "lwl."+label+requestIdentifier;
    }
}
