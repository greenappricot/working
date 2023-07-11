package com.bs.spring.beantest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

// pojo 클래스를 생성한 뒤, configuration을 이용하지 않고 선언부에서 bean으로 등록할 수 있다
// @Component, @Controller, @Service, @Repository
// 어노테이션 이용해서 spring bean 등록할 수 있다.
// @Component : 기본 spring bean으로 등록할 때 사용
// @Controller, @Service, @Repository(Dao) : MVC 패턴에 의해서 역할이 지정된 클래스를 bean으로 등록할 때 사용

//@Data
@Component
public class FunctionalTest {
	private String name="test";
	
//	@Autowired
	private Animal a;
	
	// field에 autowired하기보다는 생성자를 이용해서 DI한다
//	public FunctionalTest(@Qualifier("dog") Animal a) {
//		this.a=a;
//	}
	
	//setter를 이용해서 DI
	@Autowired
	public void setA(@Qualifier("dog") Animal a) {
		this.a=a;
	}
	public Animal getA() {
		return this.a;
	}
	
}
