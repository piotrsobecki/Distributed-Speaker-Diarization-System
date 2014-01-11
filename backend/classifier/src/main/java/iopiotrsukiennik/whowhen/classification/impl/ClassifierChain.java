package iopiotrsukiennik.whowhen.classification.impl;

import iopiotrsukiennik.whowhen.classification.label.LabelledIndexIntervals;
import iopiotrsukiennik.whowhen.classification.smooth.Smoother;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Piotr Sukiennik
 */

public class ClassifierChain {

    private File classifierDataDirectory;

    private ILabelingClassifier[] classifiers;

    private ILabelingClusterer clutererTemplate;

    public ClassifierChain( ILabelingClassifier[] classifiers, ILabelingClusterer clutererTemplate, File classifierDataDirectory ) {
        this.classifierDataDirectory = classifierDataDirectory;
        this.classifiers = classifiers;
        this.clutererTemplate = clutererTemplate;

    }

    public List<Map<String, List<int[]>>> process( List<double[]> features ) {
        return process( features, null );
    }

    public List<Map<String, List<int[]>>> process( List<double[]> features, Integer expectedClusterCount ) {
        Map<String, List<double[]>> classifiedFeatures = new HashMap<String, List<double[]>>();
        String[][] labelsAppliedToFeatureVectors = new String[features.size()][classifiers.length];

        LabelledIndexIntervals[] labelledIndexIntervals = applyClassifing( features, classifiedFeatures, labelsAppliedToFeatureVectors );
        List<Map<String, List<int[]>>> leveledLabelledIntervals = new ArrayList<Map<String, List<int[]>>>();

        for ( LabelledIndexIntervals labelledIntervals : labelledIndexIntervals ) {
            leveledLabelledIntervals.add( labelledIntervals.getLabelledIndexRanges() );
        }
        long from = System.currentTimeMillis();

        if ( clutererTemplate != null && ( expectedClusterCount == null || expectedClusterCount != 0 ) ) {
            ILabelingClusterer clusterer_Cloned = clutererTemplate.clone();
            if ( expectedClusterCount != null ) {
                clusterer_Cloned.setExpectedNumberClusters( expectedClusterCount );
            }
            LabelledIndexIntervals clustersLabelledIndexIntervals = applyClustering( clusterer_Cloned, features, classifiedFeatures, labelsAppliedToFeatureVectors );
            leveledLabelledIntervals.add( clustersLabelledIndexIntervals.getLabelledIndexRanges() );
        }
        long to = System.currentTimeMillis();
        System.out.println( "CLUSTERING TOOK: " + ( to - from ) );
        return leveledLabelledIntervals;
    }


    public LabelledIndexIntervals applyClustering( ILabelingClusterer clusterer, List<double[]> features, Map<String, List<double[]>> classifiedFeatures, String[][] labelsAppliedToFeatureVectors ) {

        trainClusters( clusterer, classifiedFeatures );

        Smoother smoother = new Smoother();
        smoother.setRange( 5 );
        for ( int i = 0; i < features.size(); i++ ) {
            for ( String label : labelsAppliedToFeatureVectors[i] ) {
                if ( clusterer.getRequiredLabel().equals( label ) ) {
                    LabelingClassificationResult classificationResult = clusterer.getClassification( features.get( i ) );
                    List<double[]> classifiedFeaturesByCluster;
                    if ( ( classifiedFeaturesByCluster = classifiedFeatures.get( classificationResult.getLabel() ) ) == null ) {
                        classifiedFeaturesByCluster = new ArrayList<double[]>();
                        classifiedFeatures.put( classificationResult.getLabel(), classifiedFeaturesByCluster );
                    }

                    smoother.submit( classificationResult.getLabel() );
                }
                else {
                    smoother.submit( null );
                }
            }

        }

        LabelledIndexIntervals clustersLabelledIndexIntervals = new LabelledIndexIntervals();
        List<String> correctedLabels = smoother.getWithCorrectionApplied();
        for ( int i = 0; i < correctedLabels.size(); i++ ) {
            String label;
            if ( ( label = correctedLabels.get( i ) ) != null ) {
                clustersLabelledIndexIntervals.applyLabel( label, i );
            }
        }

        return clustersLabelledIndexIntervals;
    }

    public LabelledIndexIntervals[] applyClassifing( List<double[]> features, Map<String, List<double[]>> classifiedFeatures, String[][] labelsAppliedToFeatureVectors ) {
        LabelledIndexIntervals[] labelledIndexIntervals = new LabelledIndexIntervals[classifiers.length];
        for ( int i = 0; i < labelledIndexIntervals.length; i++ ) {
            labelledIndexIntervals[i] = new LabelledIndexIntervals();
        }
        for ( int featuresIndex = 0; featuresIndex < features.size(); featuresIndex++ ) {
            labelsAppliedToFeatureVectors[featuresIndex] = new String[1];
            for ( int i = 0; i < classifiers.length; i++ ) {
                ILabelingClassifier labelingClassifier = classifiers[i];
                LabelingClassificationResult labelingClassificationResult = labelingClassifier.getClassification( features.get( featuresIndex ), labelsAppliedToFeatureVectors[featuresIndex] );
                if ( labelingClassificationResult != null ) {
                    labelledIndexIntervals[i].applyLabel( labelingClassificationResult.getLabel(), featuresIndex );
                    List<double[]> cachedFeaturesList = classifiedFeatures.get( labelingClassificationResult.getLabel() );
                    if ( cachedFeaturesList == null ) {
                        cachedFeaturesList = new ArrayList<double[]>();
                        classifiedFeatures.put( labelingClassificationResult.getLabel(), cachedFeaturesList );
                    }
                    cachedFeaturesList.add( features.get( featuresIndex ) );
                    labelsAppliedToFeatureVectors[featuresIndex][i] = labelingClassificationResult.getLabel();
                }
            }
        }
        return labelledIndexIntervals;
    }

    public void trainClusters( ILabelingClusterer clusterer, Map<String, List<double[]>> classifiedFeatures ) {
        if ( classifiedFeatures.get( clusterer.getRequiredLabel() ) != null ) {
            clusterer.train( classifiedFeatures.get( clusterer.getRequiredLabel() ) );
        }

    }


    public File getClassifierDataDirectory() {
        return classifierDataDirectory;
    }

    public void setClassifierDataDirectory( File classifierDataDirectory ) {
        this.classifierDataDirectory = classifierDataDirectory;
    }
}
