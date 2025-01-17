package mysite.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class MeasureExecutionAspect {

    @Around("execution(* *.repository.*.*(..)) || execution(* *.controller.*.*(..)) || execution(* *.service.*.*(..))")
    public Object adviceAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        StopWatch sw = new StopWatch();
        sw.start();

        Object result = proceedingJoinPoint.proceed();

        sw.stop();

        String className = proceedingJoinPoint.getTarget().getClass().getName();
        String methodName = proceedingJoinPoint.getSignature().getName();
        String taskName = className + "." + methodName + "()";
        sw.prettyPrint();
        System.out.println("[Execution Time]["+taskName+"]: " + sw.getTotalTimeMillis() + "mills");

        return result;
    }
}
