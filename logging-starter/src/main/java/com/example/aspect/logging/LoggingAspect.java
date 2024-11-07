package com.example.aspect.logging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAspect {

    private final LoggingProperties properties;

    @Pointcut("@annotation(com.example.aspect.logging.Logging)")
    public void loggingPointCut() {
    }

    @Pointcut("@within(com.example.aspect.logging.Logging)")
    public void loggingTypePointCut() {
    }

    @Pointcut("execution(* com.example.timesheet.service.TimesheetService.*(..))")
    public void timesheetServiceMethodsPointCut () {
    }

    @Around(value = "loggingPointCut() || loggingTypePointCut()")
    public Object loggingMethod(ProceedingJoinPoint pjp) throws Throwable{
        String methodName = pjp.getSignature().getName();
        log.atLevel(properties.getLevel()).log("Before -> TimesheetService#{}", methodName);
        try {
            return pjp.proceed();
        } finally {
            log.atLevel(properties.getLevel()).log("After -> TimesheetService#{}", methodName);
        }
    }
}