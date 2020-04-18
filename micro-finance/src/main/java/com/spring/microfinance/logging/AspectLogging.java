package com.spring.microfinance.logging;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.spring.microfinance.util.Constant;

@Component
@Aspect
public class AspectLogging {

	private static final Logger LOGGER = LoggerFactory.getLogger(AspectLogging.class);

	/**
	 * Logger for activities api
	 * 
	 * @param joinPoint
	 */
	@Before("execution(* com.spring.microfinance.borrower.api.*.*(..))")
	public void activityMethodStart(JoinPoint joinPoint) {
		LOGGER.info(Constant.LOG_ENTRY + joinPoint.getSignature().getName() + "()");
		LOGGER.debug(Constant.ARGUEMENT + Arrays.toString(joinPoint.getArgs()));
	}

	@AfterReturning(value = "execution(* com.spring.microfinance.borrower.api.*.*(..))", returning = "result")
	public void activityAfterReturning(JoinPoint joinPoint, Object result) {
		LOGGER.debug(Constant.RETURN_VALUE + result);
		LOGGER.info(Constant.LOG_EXIT + joinPoint.getSignature().getName());
	}

}
