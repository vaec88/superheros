package com.superheros.common;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * Calculate execution time.
 * @author Victor
 *
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

	/**
	 * Calculate execution time and print log.
	 * @param joinPoint Method with annotation {@link ExecutionTime}.
	 * @return 
	 * @throws Throwable.
	 */
	@Around("@annotation(ExecutionTime)")
	public Object printLog(ProceedingJoinPoint joinPoint) throws Throwable {
		Date start = new Date();
		Object proceed = joinPoint.proceed();
		Long currentTime = new Date().getTime() - start.getTime();
		log.info(joinPoint.getSignature() + " executed in " + currentTime + " ms");
		return proceed;
	}
}
