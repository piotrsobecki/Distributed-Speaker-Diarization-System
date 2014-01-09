package iopiotrsukiennik.whowhen.shared.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: Piotr Sukiennik
 * @date: 09.01.14
 * @time: 18:26
 */
public class HttpUtil {

    public static String getDomainPath(HttpServletRequest httpServletRequest){
        String applicationPath = "http://"+httpServletRequest.getServerName();
        if (httpServletRequest.getRemotePort()!=80){
            applicationPath+=":"+httpServletRequest.getServerPort();
        }
        return applicationPath;
    }
    public static String getContextPath(HttpServletRequest httpServletRequest){
        return getDomainPath(httpServletRequest)+httpServletRequest.getContextPath();
    }
    public static String getApplicationPath(HttpServletRequest httpServletRequest){
        return getContextPath(httpServletRequest)+httpServletRequest.getServletPath();
    }
}
