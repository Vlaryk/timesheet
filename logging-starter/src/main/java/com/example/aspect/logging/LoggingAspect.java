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
        if (!properties.isPrintArgs()) {
            log.atLevel(properties.getLevel()).log("Before -> TimesheetService#{}", methodName);
            try {
                return pjp.proceed();
            } finally {
                log.atLevel(properties.getLevel()).log("After -> TimesheetService#{}", methodName);
            }
        } else {
            String r;
            if (pjp.getArgs().length > 0) {
                StringBuilder res = new StringBuilder("TimesheetService." + methodName + "(");
                for (int i = 0; i < pjp.getArgs().length; i++) {
                    Object arg = pjp.getArgs()[i];
                    res.append(arg.getClass().getSimpleName()).append(" = ").append(arg).append(",");
                }
                res.deleteCharAt(res.length() - 1).append(")");
                r = String.valueOf(res);
                log.atLevel(properties.getLevel()).log("Before -> " + r);
            } else {
                r = "TimesheetService.{}()" + methodName;
                log.atLevel(properties.getLevel()).log("Before -> " + r);
            }
            try {
                return pjp.proceed();
            } finally {
                log.atLevel(properties.getLevel()).log("After -> " + r);
            }
        }
    }
}