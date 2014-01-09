package iopiotrsukiennik.whowhen.web.controller;

import iopiotrsukiennik.whowhen.backend.api.outer.IBackendService;
import iopiotrsukiennik.whowhen.shared.form.WhoWhenRequestForm;
import iopiotrsukiennik.whowhen.shared.util.HttpUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@Controller
public class FrontController {

    @Resource(name = "backendService")
    private IBackendService backendService;

    @RequestMapping(value="/**/favicon.ico")
    public String faviconGet(){
        return "forward:/resources/ficon.ico";
    }

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String indexGet(){
        return "index";
    }
    @RequestMapping(value="/", method = RequestMethod.GET,params = "error=true")
    public String indexGetError(Map model){
        model.put("error",true);
        return "index";
    }

    @ModelAttribute("servletPath")
    public String getApplicationPath(HttpServletRequest request){
        return "http://"+HttpUtil.getServletPath(request);
    }


    @ModelAttribute("acceptableFormats")
    public String[] getAcceptableFormats(){
        return  backendService.getAcceptableFormats();
    }

    @ModelAttribute("FORM")
    public WhoWhenRequestForm getWhoWhenRequestForm(){
        return new WhoWhenRequestForm();
    }


}
