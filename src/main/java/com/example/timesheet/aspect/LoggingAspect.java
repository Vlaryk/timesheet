package com.example.timesheet.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
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

//    @Pointcut("execution(* com.example.timesheet.service.TimesheetService.*(..))")
//    public void timesheetServiceMethodsPointCut () {
//    }
//
//    @Before(value = "timesheetServiceMethodsPointCut()")
//    public void beforeTimesheetServiceFindById (JoinPoint jp) {
//        String name = jp.getSignature().getName();
//        log.info("Before -> TimesheetService#{}",name);
//    }
//
//    @After(value = "timesheetServiceMethodsPointCut()")
//    public void afterTimesheetServiceFindById (JoinPoint jp) {
//        String name = jp.getSignature().getName();
//        log.info("After -> TimesheetService#{}",name);
//    }
}
