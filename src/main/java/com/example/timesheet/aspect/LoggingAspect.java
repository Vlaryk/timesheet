package com.example.timesheet.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {
    //Before
    //AfterReturning
    //AfterThrowing
    //After = AfterReturning + AfterThrowing
    //Around ->


    //pointcut точка входа в аспект

    @Pointcut("execution(* com.example.timesheet.service.TimesheetService.*(..))")
    public void timesheetServiceMethodsPointCut () {
    }

    @Before(value = "timesheetServiceMethodsPointCut()")
    public void beforeTimesheetServiceFindById (JoinPoint jp) {
        String name = jp.getSignature().getName();
        if (jp.getArgs().length > 0) {
            StringBuilder res = new StringBuilder("TimesheetService." + name + "(");
            for (int i = 0; i < jp.getArgs().length ; i++) {
                Object arg = jp.getArgs()[i];
                res.append(arg.getClass().getSimpleName()).append(" = ").append(arg).append(",");
            }
            res.deleteCharAt(res.length() - 1).append(")");
            log.info(String.valueOf(res)); // TimesheetService.findById(Long = 3)
        } else log.info("TimesheetService.{}()",name);
    }
//
//    @After(value = "timesheetServiceMethodsPointCut()")
//    public void afterTimesheetServiceFindById (JoinPoint jp) {
//        String name = jp.getSignature().getName();
//        log.info("After -> TimesheetService#{}",name);
//    }
}