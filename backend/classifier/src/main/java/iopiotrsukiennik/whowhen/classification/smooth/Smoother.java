package iopiotrsukiennik.whowhen.classification.smooth;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Piotr
 * Date: 16.12.12
 * Time: 19:48
 * To change this template use File | Settings | File Templates.
 */
public class Smoother {

    private Integer range = 1;

    private List<String> history = new ArrayList<String>();
    private Map<Integer,String> corrected = new LinkedHashMap<Integer,String>();
    int skipped = 0;

    public void submit(String label){
        submit(label,1);
    }



    public void submit(String label, int gap){
        for (int g=0; g<gap-1;g++){
            history.add(null);
        }
       if (label==null){
           history.add(null);
       }   else {
           int historySize = history.size();
           boolean ok=true;
           Integer i=historySize-1;
           Integer offset = 0;
           for (;i>historySize-range-1+offset && i>0 && i<historySize && ok;i--){
               if (!(history.get(i)!=null && history.get(i).equals(label) || (corrected.get(i)!=null && corrected.get(i).equals(label)))){
                   if (history.get(i)!=null){    //!gap
                       corrected.put(historySize, history.get(i));
                       ok=false;
                   } else {
                       offset+=1;
                   }
               }
           }
           if (ok){
               for (Integer ii = i+1; ii<=historySize;ii++){
                   if (corrected.get(ii)!= null && !corrected.get(ii).equals(label)){
                       corrected.remove(ii);
                   }
               }
           }
           history.add(label);

       }
    }


    public List<String> getWithCorrectionApplied(){
        List<String> output = new ArrayList<String>();
        output.addAll(history);
        for (Map.Entry<Integer,String> me: corrected.entrySet()){
            output.set(me.getKey(),me.getValue());
        }
        return output;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
       // this.range--;
    }

    public Map<Integer, String> getCorrected() {
        return corrected;
    }

    @Override
    public String toString() {
        return "Smoother{" +
                "range=" + range +
                ", history=" + history +
                ", corrected=" + corrected +
                '}';
    }
}
