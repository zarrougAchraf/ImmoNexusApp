package tn.devteam.immonexus.Configurations;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class AspectLogging {
   /*
@Before("execution(public * tn.devteam.immonexus.Services.*.* (..))")
    public void LogMethodEntry(JoinPoint joinPoint){
    String name = joinPoint.getSignature().getName();
    log.info("In method :" + name+":");
}*/



    /*
@AfterReturning("execution( * tn.devteam.immonexus.Services.*.add*(..))")
    public void logMethodExit1(JoinPoint joinPoint){
    String name = joinPoint.getSignature().getName();
    log.info("Out of method without errors:" + name+":");
}
/*
     */
/*
@AfterThrowing("execution(* tn.devteam.immonexus.Services.*.*(..))")
public void logMethodExit2(JoinPoint joinPoint) {
    String name = joinPoint.getSignature().getName();
    log.info("Out of method without errors:" + joinPoint.getSignature().getName()+":");
}
@After("execution(* tn.devteam.immonexus.Services.*.*(..))")
public void logMethodExit(JoinPoint joinPoint){
    String name = joinPoint.getSignature().getName();
        log.info("Out of method :" + name+":");
}
*/
    /*

    @Around("execution(* tn.devteam.immonexus.Services.*.*(..))")
    public Object profile(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        Object obj = pjp.proceed();
        long elapsedTime = System.currentTimeMillis() - start;
        log.info("Method execution time: " + elapsedTime + " milliseconds.");
        return obj;
    }

     */
}
