package mysite.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class RepositoryAspect {

    @Around(value = "execution(* *..*.repository.*(..))")
    public Object adviceAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        StopWatch sw = new StopWatch();
        sw.start();

        Object result = proceedingJoinPoint.proceed();

        sw.stop();

        String simpleName = proceedingJoinPoint.getSignature().getName();
        System.out.println("[Execution Time]["+simpleName+"."+proceedingJoinPoint.getSignature().getName()+"]: ]" + sw.getTotalTimeMillis() + "mills");

        return result;
    }
}
