package pl.piotrsukiennik.whowhen.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.piotrsukiennik.whowhen.backend.api.outer.IBackendService;
import pl.piotrsukiennik.whowhen.shared.form.WhoWhenRequestForm;
import pl.piotrsukiennik.whowhen.shared.util.HttpUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@Controller
public class FrontController {

    @Resource( name = "backendService" )
    private IBackendService backendService;

    @RequestMapping( value = "/**/favicon.ico" )
    public String faviconGet() {
        return "forward:/resources/ficon.ico";
    }

    @RequestMapping( value = "/", method = RequestMethod.GET )
    public String indexGet() {
        return "index";
    }

    @RequestMapping( value = "/", method = RequestMethod.GET, params = "error=true" )
    public String indexGetError( Map model ) {
        model.put( "error", true );
        return "index";
    }

    @ModelAttribute( "domainPath" )
    public String getDomainPath( HttpServletRequest request ) {
        return HttpUtil.getDomainPath( request );
    }

    @ModelAttribute( "servletPath" )
    public String getApplicationPath( HttpServletRequest request ) {
        return HttpUtil.getServletPath( request );
    }


    @ModelAttribute( "acceptableFormats" )
    public String[] getAcceptableFormats() {
        return backendService.getAcceptableFormats();
    }

    @ModelAttribute( "FORM" )
    public WhoWhenRequestForm getWhoWhenRequestForm() {
        return new WhoWhenRequestForm();
    }


}
