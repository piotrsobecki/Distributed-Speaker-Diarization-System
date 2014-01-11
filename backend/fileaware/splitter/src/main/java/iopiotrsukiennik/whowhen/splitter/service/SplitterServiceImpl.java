package iopiotrsukiennik.whowhen.splitter.service;

import iopiotrsukiennik.whowhen.backend.api.inner.splitter.SplitterRequest;
import iopiotrsukiennik.whowhen.backend.api.inner.splitter.SplitterResponse;
import iopiotrsukiennik.whowhen.backend.api.inner.splitter.SplitterService;
import iopiotrsukiennik.whowhen.backend.api.inner.util.AudioInfo;
import iopiotrsukiennik.whowhen.backend.api.inner.util.AudioInfoUtil;
import iopiotrsukiennik.whowhen.backend.api.outer.IBackendService;
import iopiotrsukiennik.whowhen.splitter.AudioFileSplitter;
import iopiotrsukiennik.whowhen.splitter.IndexIntervalsToTimelineMapper;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Piotr Sukiennik
 */
@Component("splitterServiceImpl")
public class SplitterServiceImpl implements SplitterService {

    @Resource
    private IBackendService backendService;

    private Log logger = LogFactory.getLog( SplitterServiceImpl.class );

    private ExecutorService executorService = Executors.newFixedThreadPool( 4 );

    @Override
    public void handle( final SplitterRequest splitterRequest ) {
        executorService.submit( new Runnable() {
            @Override
            public void run() {
                try {
                    IndexIntervalsToTimelineMapper intervalsToTimelineMapper = new IndexIntervalsToTimelineMapper( splitterRequest.getSingleIntervalLength() );

                    List<Map<String, List<double[]>>> timelines = new ArrayList<Map<String, List<double[]>>>();
                    Map<String, List<double[]>> wholeTimeline = new LinkedHashMap<String, List<double[]>>();
                    for ( Map<String, List<int[]>> labeledIntervals : splitterRequest.getLabeledIntervalsKey() ) {
                        Map<String, List<double[]>> timeline = intervalsToTimelineMapper.mapGroupByLabel( labeledIntervals );
                        timelines.add( timeline );
                        wholeTimeline.putAll( timeline );
                    }

                    AudioFileSplitter audioFileSplitter = new AudioFileSplitter();
                    AudioInfo audioInfo = splitterRequest.getAudioInfo();
                    AudioFormat audioFormat = AudioInfoUtil.convert( audioInfo );
                    audioFileSplitter.split( audioFormat, audioInfo.getDuration(), audioInfo.getAudioFile(), wholeTimeline, splitterRequest.getOutputDirectory(), AudioFileFormat.Type.WAVE );

                    SplitterResponse splitterResponse = new SplitterResponse( splitterRequest.getRequestIdentifier(), timelines );
                    backendService.notify( splitterResponse );
                }
                catch ( Exception e ) {
                    if ( logger.isErrorEnabled() ) {
                        logger.error( ExceptionUtils.getStackTrace( e ) );
                    }
                }

            }
        } );
    }
}
