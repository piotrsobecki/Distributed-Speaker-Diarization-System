package iopiotrsukiennik.whowhen.web.controller;

import iopiotrsukiennik.whowhen.backend.api.outer.BackendRequest;
import iopiotrsukiennik.whowhen.backend.api.outer.BackendResponse;
import iopiotrsukiennik.whowhen.backend.api.outer.IBackendService;
import iopiotrsukiennik.whowhen.shared.form.WhoWhenRequestForm;
import iopiotrsukiennik.whowhen.web.util.RequestValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.util.concurrent.ExecutorService;

@Controller
public class DataSubmitController  {
    @Value("#{serviceProperties['frontend.apacheDeploymentPath']}")
    private String apacheDeploymentPath;

    @Value("#{requestProperties['maxFileSize']}")
    private String maxFileSize;

    public static final String TARGET_DATA_FILE_NAME = "file";

    @Resource
    private IBackendService backendService;

    @RequestMapping(value="/",method = RequestMethod.POST)
    public String indexPOST(@ModelAttribute(value="FORM") WhoWhenRequestForm form,BindingResult result)
    {
        RequestValidator requestValidator = new RequestValidator();
        requestValidator.validate(form,result);

        if(!result.hasErrors() && form.getFile().getSize() < Long.valueOf(maxFileSize)){
            FileOutputStream outputStream = null;
            try {
                String requestIdentifier=generateRequestIdentifier();
                //System.out.println("1");
                byte[] fileBytes =form.getFile().getFileItem().get();
                String targetDir = apacheDeploymentPath+requestIdentifier+File.separator;
                String filePath =  targetDir+ TARGET_DATA_FILE_NAME;
                File targetFile = new File(filePath);
               // System.out.println("2");
                if (!targetFile.exists()){
                    targetFile.getParentFile().mkdirs();
                    outputStream = new FileOutputStream(targetFile);
                    outputStream.write(fileBytes);
                    outputStream.close();
                }
                BackendRequest backendRequest = new BackendRequest(requestIdentifier,form.getRequestData(),targetFile);
                BackendResponse backendResponse = backendService.handle(backendRequest);
                return "redirect:/"+backendRequest.getRequestIdentifier()+"/request";

            } catch (Exception e) {
                e.printStackTrace();

            }
        }
        return "redirect:/?error=true";
    }

    protected String generateRequestIdentifier(){
        return java.util.UUID.randomUUID().toString();
    }

}
