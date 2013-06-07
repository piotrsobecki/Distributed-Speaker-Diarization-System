package iopiotrsukiennik.whowhen.shared.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created with IntelliJ IDEA.
 * User: Piotr
 * Date: 01.12.12
 * Time: 22:11
 * To change this template use File | Settings | File Templates.
 */
@Target(value = {ElementType.METHOD, ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface MonitorAfter {
}