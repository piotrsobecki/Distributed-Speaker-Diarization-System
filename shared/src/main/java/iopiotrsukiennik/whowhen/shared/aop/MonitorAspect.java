package iopiotrsukiennik.whowhen.shared.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: Piotr
 * Date: 01.12.12
 * Time: 22:11
 * To change this template use File | Settings | File Templates.
 */
@Component
@Aspect
public class MonitorAspect {

    private Logger logger = LoggerFactory.getLogger(MonitorAspect.class);

    @Before(value = "!@within(iopiotrsukiennik.whowhen.shared.aop.MonitorExclude) && !@annotation(MonitorExclude) && (@within(iopiotrsukiennik.whowhen.shared.aop.MonitorBefore) || @annotation(iopiotrsukiennik.whowhen.shared.aop.MonitorBefore))")
        public void before(JoinPoint joinPoint) throws Throwable {
        logger.info("[M] BEFORE: " + joinPoint.getSignature().getDeclaringType().getSimpleName() + "." + joinPoint.getSignature().getName()+"("+Arrays.toString(joinPoint.getArgs())+")");
    }

    @After(value = "!@within(iopiotrsukiennik.whowhen.shared.aop.MonitorExclude) && !@annotation(MonitorExclude) && (@within(iopiotrsukiennik.whowhen.shared.aop.MonitorAfter) || @annotation(iopiotrsukiennik.whowhen.shared.aop.MonitorAfter))")
    public void after(JoinPoint joinPoint) throws Throwable {
        logger.info("[M] AFTER: " + joinPoint.getSignature().getDeclaringType().getSimpleName() + "." + joinPoint.getSignature().getName());
    }
}
