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
public class StaticController {

    @RequestMapping( value = "/**/favicon.ico" )
    public String faviconGet() {
        return "forward:/resources/ficon.ico";
    }

    @RequestMapping( value = "/about", method = RequestMethod.GET)
    public String aboutGet( ) {
        return "about";
    }
    @RequestMapping( value = "/contact", method = RequestMethod.GET )
    public String contactGet( ) {
        return "contact";
    } @RequestMapping( value = "/support", method = RequestMethod.GET )
      public String supportGet(  ) {
        return "support";
    }


}
