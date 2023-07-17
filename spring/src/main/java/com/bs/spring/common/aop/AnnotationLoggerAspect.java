package com.bs.spring.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component // spring bean으로 등록 (base package안에 있어야 읽어옴)
@Aspect // 어노테이션 방식으로 aspect 만들기 -> 이렇게 선언하면 이 component가 aspect로 등록됨
public class AnnotationLoggerAspect {
	
	// pointcut설정
	//@Pointcut("execution(* com.bs.spring.member..*(..))")
	@Pointcut("within(com.bs.spring.member..*)") // member package 아래 전부
	public void loggerTest() {} // 어노테이션은 메소드 선언부, 클래스 선언부, 필드에 선언할 수 있다. @pointcut 어노테이션은 메소드에 선언함
	
	// 여러 개의 pointcut 등록해서 사용할 수 있다.
	@Pointcut("execution(* com.bs.spring.memo..*(..))")
	public void memoLogger() {}
	
	// advisor 설정 
	@Before("loggerTest()") // xml에서 pointcut ref로 불러온 것처럼 위에서 선언한 pointcut 메소드를 불러올 수 있다. 
	public void loggerBefore(JoinPoint jp) { // Joinpoint 와 혼동 주의
		log.debug("====== annotation aop before ======");
		Signature sig= jp.getSignature();
		log.debug(sig.getDeclaringTypeName()+" "+sig.getName());
		log.debug("==================================");
	}
	
	@After("memoLogger()")
	public void loggerAfter(JoinPoint jp) {
		log.debug("====== annotation aop after ======");
		Signature sig= jp.getSignature();
		log.debug(sig.getDeclaringTypeName()+" "+sig.getName());
		log.debug("============== after ==============");
	}
	
	// 메소드 실행 전, 후에 특정 로직을 실행할 때 사용 
	@Around("execution(* com.bs.spring..*DaoImpl.*(..))") // 바로 포인트컷을 선언할 수 있다.
	// Object를 반환하는 around는 매개변수로 받는 타입과 반환형이 다르다. 
	public Object daoLogger(ProceedingJoinPoint join) throws Throwable{
		// 전, 후 처리 로직은 한번에 설정할 수 있다. (before+after 느낌)
		// 전, 후를 구분하는 구문은 ProceedingJoinPoint 클래스가 제공하는 proceed()메소드를 이용한다.
		// proceed()메소드 호출한 시점을 기준으로 호출한 다음 라인은 후처리, 그 전 라인은 전처리 
		// proceed() 메소드는 Object를 반환한다.
		
		// 메소드 실행하는 시간 체크할 수 있다
		StopWatch stop= new StopWatch();
		stop.start();
		log.debug("=============== around logger dao before ===============");
		log.debug("================== 전처리 내용 구현 ====================");
		log.debug("========================================================");
		// 똑같이 Signature 객체를 이용해서 정보를 가져올 수 있다.
		// Signature sig=join.getSignature();
		// String classMethod = sig.getDeclaringType().getName()+" "+sig.getClass();
		// join.getTarget();
		Object obj=join.proceed();
		stop.stop();
		log.debug("=============== around logger dao after ===============");
		log.debug("================== 후 처리 내용 구현 ==================");
		log.debug("==== 실행 시간 : "+stop.getTotalTimeMillis()+"ms ======");
		log.debug("=======================================================");
		return obj; // 반환값은 내부적으로 사용함
	}
	
	// error 발생했을 때만 처리된다.
	@AfterThrowing(pointcut = "loggerTest()", throwing = "e") 
	public void afterThrowingLogger(JoinPoint jp, Throwable e) {
		log.debug("에러발생!!!!!!!!!");
		Signature sig= jp.getSignature();
		log.debug("{}", sig.getDeclaringTypeName()+" "+sig.getName());
		log.debug("{}",e.getMessage()); 
		// e.getStackTrace() (for문돌려서 trace들 확인할 수 있다)
		StackTraceElement[] stackTrace= e.getStackTrace();
		for(StackTraceElement element : stackTrace) {
			log.debug("{}", element);
		}
		
	}
	
	
}






