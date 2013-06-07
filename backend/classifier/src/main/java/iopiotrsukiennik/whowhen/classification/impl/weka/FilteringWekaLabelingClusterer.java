package iopiotrsukiennik.whowhen.classification.impl.weka;

import iopiotrsukiennik.whowhen.classification.impl.ILabelingClusterer;
import weka.clusterers.Clusterer;
import weka.clusterers.SimpleKMeans;
import weka.clusterers.XMeans;
import weka.core.Instance;
import weka.core.Instances;
import weka.filters.Filter;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Piotr
 * Date: 10.12.12
 * Time: 00:25
 * To change this template use File | Settings | File Templates.
 */
public class FilteringWekaLabelingClusterer<F extends Filter> extends WekaLabelingClusterer {
    private Class<F> filterClass;

    public FilteringWekaLabelingClusterer(Clusterer clusterer, Class<F> filterClass) {
        super(clusterer);
        this.filterClass = filterClass;
    }

    @Override
    public void train(List<double[]> data) {
        try{

            Filter filter = filterClass.newInstance();
            Instances instances = WekaUtil.toInstances(data,getRequiredLabel());
            filter.setInputFormat(instances);
            for (int i = 0; i < instances.numInstances(); i++) {
                filter.input(instances.instance(i));
            }
            filter.batchFinished();



            Instances newData = filter.getOutputFormat();
            Instance processed;
            while ((processed = filter.output()) != null) {
                newData.add(processed);
            }
            clusterer.buildClusterer(newData);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
