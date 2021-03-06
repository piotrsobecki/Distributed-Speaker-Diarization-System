package pl.piotrsukiennik.whowhen.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.piotrsukiennik.whowhen.backend.api.outer.*;
import pl.piotrsukiennik.whowhen.shared.util.HttpUtil;
import pl.piotrsukiennik.whowhen.shared.util.progress.Progress;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller()
public class RequestController {

    @Resource( name = "backendService" )
    private IBackendService backendService;

    @RequestMapping( value = "{requestId}/request", method = RequestMethod.GET )
    public String indexGet( @PathVariable( value = "requestId" ) String requestIdentifier, Map<String, String> model ) {
        if ( getProgress( requestIdentifier ) != null ) {
            model.put( "requestIdentifier", "" + requestIdentifier );
            return "results";
        }
        else {
            return "redirect:/";
        }
    }

    @RequestMapping( value = "{requestId}/progress", method = RequestMethod.GET )
    public
    @ResponseBody
    Progress getProgress( @PathVariable( value = "requestId" ) String requestIdentifier ) {
        GetProgressRequest getProgressRequest = new GetProgressRequest( requestIdentifier );
        GetProgressResponse getProgressResponse = backendService.getProgress( getProgressRequest );
        Progress progress = getProgressResponse.getProgress();
        return getProgressResponse.getProgress();
    }

    @RequestMapping( value = "{requestId}/timeline", method = RequestMethod.GET )
    public
    @ResponseBody
    List<Map<String, List<double[]>>> getTimeline( @PathVariable( value = "requestId" ) String requestIdentifier ) {
        GetTimelineRequest getProgressRequest = new GetTimelineRequest( requestIdentifier );
        GetTimelineResponse getProgressResponse = backendService.handle( getProgressRequest );
        return getProgressResponse.getLevelledLabelledIntervals();
    }

    @ModelAttribute( "domainPath" )
    public String getDomainPath( HttpServletRequest request ) {
        return HttpUtil.getDomainPath( request );
    }

    @ModelAttribute( "applicationPath" )
    public String getApplicationPath( HttpServletRequest request ) {
        return HttpUtil.getApplicationContextPath( request );
    }

}
