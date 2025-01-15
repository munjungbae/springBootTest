package com.zeus.common.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Aspect
public class ServiceLoggerAdvice {
	
	@Before("execution(* com.zeus.service.BoardService*.*(..))")
	public void startLog(JoinPoint jp) {
		
		log.info("===================================================================================");
		log.info("ServiceLoggerAdvice.startLog");
		log.info("jp.getSignature() = " + jp.getSignature());
		log.info("jp.getArgs() = " + Arrays.toString(jp.getArgs()));
		log.info("===================================================================================");
	}
	
	@After("execution(* com.zeus.service.BoardService*.*(..))")
	public void startLog2(JoinPoint jp) {
		
		log.info("===================================================================================");
		log.info("ServiceLoggerAdvice.stopLog");
		log.info("jp.getSignature() = " + jp.getSignature());
		log.info("jp.getArgs() = " + Arrays.toString(jp.getArgs()));
		log.info("===================================================================================");
	}
}
