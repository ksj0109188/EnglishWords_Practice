package com.project.common.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAdvice {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAdvice.class);

    @Around("execution(* com.project.*.service.*.*(..)) or " + "execution(* com.project.*.dao.*.*(..))")
    public Object aroundLogging(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        logger.info("-------------------------------------");
        logger.info("------------beforeLogging------------");

        logger.info("1:" + Arrays.toString(pjp.getArgs()));

        logger.info("2:" + pjp.getKind());

        logger.info("3:" + pjp.getSignature().getName());

        logger.info("4:" + pjp.getTarget().toString());

        logger.info("5:" + pjp.getThis().toString());

        Object result = pjp.proceed();

        logger.info("-------------------------------------");
        logger.info("-------------afterLogging------------");

        logger.info("1:" + Arrays.toString(pjp.getArgs()));

        logger.info("2:" + pjp.getKind());

        logger.info("3:" + pjp.getSignature().getName());

        logger.info("4:" + pjp.getTarget().toString());

        logger.info("5:" + pjp.getThis().toString());

        long endTime = System.currentTimeMillis();
        logger.info("==============================");
        logger.info(pjp.getSignature().getName() + " : " + (endTime - startTime)+"ms");
        logger.info("==============================");

        return result;
    }
}