package com.zeus.common.aop;

import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Aspect
public class ServiceLoggerAdvice {

	// 핵심코드 실행 후 무조건 작동
//	@After("execution(* com.zeus.service.BoardService*.*(..))")

	// 핵심코드 실행 전 작동
//	@Before("execution(* com.zeus.service.BoardService*.*(..))")
//	public void startLog(JoinPoint jp) {
//		
//		log.info("===================================================================================");
//		log.info("ServiceLoggerAdvice.startLog");
//		log.info("jp.getSignature() = " + jp.getSignature());
//		log.info("jp.getArgs() = " + Arrays.toString(jp.getArgs()));
//		log.info("===================================================================================");
//	}

	// 핵심코드가 정삭적으로 종료 될 시 작동
//	@AfterReturning(pointcut = "execution(* com.zeus.service.BoardService*.*(..))", returning = "result")
//	public void stopLog(JoinPoint jp, Object result) {
//		
//		log.info("===================================================================================");
//		log.info("ServiceLoggerAdvice.stopLog");
//		if(result != null ) {
//			log.info("result = " + result.toString());
//		}
//		log.info("===================================================================================");
//	}

	// 핵심코드에서 예외 발생 시 작동
	@AfterThrowing(pointcut = "execution(* com.zeus.service.BoardService*.*(..))", throwing = "e")
	public void exceptionLog(JoinPoint jp, Exception e) {

		log.info("===================================================================================");
		log.info("ServiceLoggerAdvice.exceptionLog");
		if (e != null) {
			log.info("exception = " + e.toString());
		}
		log.info("===================================================================================");
	}

	// 핵심코드 실행 전, 후 시 작동
	@Around("execution(* com.zeus.service.BoardService*.*(..))")
	public Object timeLog(ProceedingJoinPoint pjp) throws Throwable {
		long startTime = System.currentTimeMillis();
		log.info("-------------------------------------------------");
		log.info("pjp.getArgs() = " + pjp.getArgs());
		Object obj = null;
		try {
			obj = pjp.proceed();
		} catch (Exception e) {
			e.printStackTrace();
		}
		long stopTime = System.currentTimeMillis();
		log.info((pjp.getSignature().getName() + " : " + (startTime - stopTime)));
		log.info("-------------------------------------------------");

		return obj;
	};

}
