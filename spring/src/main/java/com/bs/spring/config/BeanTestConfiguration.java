package com.bs.spring.config;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.bs.spring.beantest.Animal;
import com.bs.spring.beantest.Department;
import com.bs.spring.beantest.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;

// 클래스 방식으로 bean등록해서 사용하기
// @Configuration  : pojo 클래스를 bean configuration으로 사용할 수 있다. 

@Configuration
@EnableWebMvc // Spring MVC 의 완전한 제어를 할 수 있다.
@ComponentScan(
		basePackages="com.bs.spring",
		includeFilters={
				@ComponentScan.Filter(
						type=FilterType.REGEX,
						pattern= {"com.bs.spring.include.*"}
						)
				},	//annotation 표시가 없더라도 해당되면 bean으로 등록한다.
		// ComponentScan에서 includeFilters 사용해서 bean 등록 없이 사용할 수 있게 한다.
		excludeFilters= { }	// includeFilters와 반대로 제외하는 것
		)
// com.bs.spring으로 등록
//servlet-context.xml에서 하는 <component-scan>에 대한 설정을 할 수 있다.
//@Import() // 다른 configuration을 가져와서 처리하는 것
public class BeanTestConfiguration {
	
	// @Configuration 선언 하면 springbeanconfiguration.xml과 동일한 기능
	// @Bean  : spring에서 사용할 bean을 자바 코드로 등록할 수 있다.
	// 			메소드 선언을 통해 등록한다.
	// method 이름이 servlet-context.xml에서 <beans:bean id=""> 값이 된다.
	@Bean
	@Order(1) // bean 우선 순위를 정할 수 있다. 
	public Animal ani() {
		return Animal.builder().name("킥킥").age(5).height(80).build();
	}
	
	@Bean
	@Qualifier("sol")
	// @Qualifier 등록된 bean에 특정 id값 부여하기
	// autowired해서 자동으로 객체를 찾는데, Department에 dept인지 sal인지 구분할 수 없어서 NoUniqueBeanDefinitionException 에러 발생
//	public Employee getEmployee(@Qualifier("sal") Department d) {
	// 안되면 @Autowired 으로 의존성 주입(spring ver3에서는 적어줘야함)
	
	// 매개변수에 Qualifier로 특정할 수 있다.
	public Employee getEmployee(@Qualifier("sal") Department d) {
		return Employee.builder().name("최솔").age(27).address("경기도 안양시").salary(200).dept(d).build();
	}
	
	@Bean
	public Department sal() {
		return Department.builder().deptCode(2L).deptTitle("영업부").deptLocation("서울").build();
	}
	
	// jdbc 연결하는 bean 등록할 수 있다.
	@Bean
	public BasicDataSource getDataSource() {
		BasicDataSource source= new BasicDataSource();
		source.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		source.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		source.setUsername("spring");
		source.setPassword("spring");
		return source;
	}
	
	/*
	 * @Bean public Gson gson() { return new Gson(); }
	 */
	
	// Bean으로 등록 (websocket)
	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}
	
	
}
