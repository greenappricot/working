package com.bs.spring.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggerAspect {
	// 일반 pojo 클래스
	
	// 중간에 실행되기 때문에 반환형이 없다.
	// 어떤 메소드가 실행되기 전에 실행되는 메소드
	public void loggerBefore(JoinPoint jp) {
		log.debug("----- AOP LoggerBefore -----");
		Signature sig=jp.getSignature();
		log.debug(sig.getDeclaringTypeName()+" "+sig.getName()); // class 이름 / method 이름
		
		Object[] arg=jp.getArgs(); // 메소드가 실행될 때 전달되는 매개변수의 인수 값을 가져온다. (Object[]로 반환)
		if(arg!=null) {
			for(Object o : arg) {
				log.debug("{}",o);
			}
		}
		
		log.debug("----------------------------");
	}
	
	public void loggerAfter(JoinPoint jp) {
		// 조회된 이후에 logger가 찍힌다
		// 역순으로 찍힌다
		log.debug("----- AOP LoggerAfter -----");
		Signature sig=jp.getSignature();
		log.debug(sig.getDeclaringTypeName()+" "+sig.getName()); // class 이름 / method 이름
		log.debug("----------------------------");
	}
		
}
