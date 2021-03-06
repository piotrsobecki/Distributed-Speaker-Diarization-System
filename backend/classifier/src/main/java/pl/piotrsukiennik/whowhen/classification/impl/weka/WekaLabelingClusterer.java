package pl.piotrsukiennik.whowhen.classification.impl.weka;

import pl.piotrsukiennik.whowhen.classification.impl.ILabelingClusterer;
import pl.piotrsukiennik.whowhen.classification.impl.LabelingClassificationResult;
import weka.clusterers.Clusterer;
import weka.clusterers.NumberOfClustersRequestable;
import weka.core.Instance;

import java.util.Arrays;
import java.util.List;

/**
 * @author Piotr Sukiennik
 */

public class WekaLabelingClusterer implements ILabelingClusterer, Cloneable {
    protected Clusterer clusterer;

    private String requiredLabel;


    @Override
    public void setExpectedNumberClusters( int numberClusters ) {
        if ( numberClusters > 0 && clusterer instanceof NumberOfClustersRequestable ) {
            try {
                ( (NumberOfClustersRequestable) clusterer ).setNumClusters( numberClusters );
            }
            catch ( Exception e ) {
                throw new RuntimeException( e );
            }
        }
    }

    public WekaLabelingClusterer( Clusterer clusterer ) {
        this.clusterer = clusterer;
    }

    @Override
    public LabelingClassificationResult getClassification( double[] vector ) {
        return this.getClassification( vector, new String[] { } );
    }

    @Override
    public LabelingClassificationResult getClassification( double[] vector, String... labels ) {
        return getClassificationDistribution( vector )[0];
    }

    @Override
    public LabelingClassificationResult[] getClassificationDistribution( double[] vector ) {
        Instance instance = WekaUtil.toInstance( vector );
        try {
            double[] distribution = clusterer.distributionForInstance( instance );
            LabelingClassificationResult[] labelingClassificationResults = new LabelingClassificationResult[distribution.length];
            for ( int i = 0; i < distribution.length; i++ ) {
                labelingClassificationResults[i] = new LabelingClassificationResult( "cluster" + i, distribution[i] );
            }
            Arrays.sort( labelingClassificationResults, LabelingClassificationResult.PROBABILITY_COMPARATOR );
            return labelingClassificationResults;
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void train( List<double[]> data ) {

        try {
            clusterer.buildClusterer( WekaUtil.toInstances( data, getRequiredLabel() ) );

        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    public void setRequiredLabel( String requiredLabel ) {
        this.requiredLabel = requiredLabel;
    }

    @Override
    public String getRequiredLabel() {
        return requiredLabel;
    }

    @Override
    public WekaLabelingClusterer clone() {
        try {
            WekaLabelingClusterer wekaLabelingClusterer = new WekaLabelingClusterer( clusterer.getClass().newInstance() );
            wekaLabelingClusterer.setRequiredLabel( this.getRequiredLabel() );
            return wekaLabelingClusterer;
        }
        catch ( Exception e ) {
            throw new RuntimeException( e );
        }
    }
}

