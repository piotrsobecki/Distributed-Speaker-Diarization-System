package iopiotrsukiennik.whowhen.shared.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: Piotr Sukiennik
 */
public class HttpUtil {

    public static String getDomainPath( HttpServletRequest httpServletRequest ) {
        String applicationPath = httpServletRequest.getServerName();
        if ( httpServletRequest.getRemotePort() != 80 ) {
            applicationPath += ":" + httpServletRequest.getServerPort();
        }
        return applicationPath;
    }

    public static String getApplicationContextPath( HttpServletRequest httpServletRequest ) {
        return getDomainPath( httpServletRequest ) + httpServletRequest.getContextPath();
    }

    public static String getServletPath( HttpServletRequest httpServletRequest ) {
        return getApplicationContextPath( httpServletRequest ) + httpServletRequest.getServletPath();
    }
}
