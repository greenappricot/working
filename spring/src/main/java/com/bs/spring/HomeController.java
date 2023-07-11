package com.bs.spring;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bs.spring.beantest.Animal;
import com.bs.spring.beantest.Employee;
import com.bs.spring.beantest.FunctionalTest;
import com.bs.spring.include.TargetComponent;

@Controller
// spring bean으로 설정 -> handler mapper에 전달함
public class HomeController {
	
	// servlet-context.xml에서 springbean으로 등록된 객체는 필드에 가져와서 사용할 수 있다. 
	/*
	 * @Autowired private Animal a;
	 */
	// Autowired Annotation을 선언하면 type을 보고 matching 한다.
	// Animal이 여러 개일 경우, NoUniqueBeanDefinitionException 발생 
	// bean을 등록할 때 같은 타입일 경우 주의해야한다.
	// 
	// 분리하는 방법 -> 필드명을 확인해서 필드명으로 불러온다.
	
//	@Autowired
//	private Animal bbo; bbo로 등록한 객체가 출력된다.
	
	// 기본적으로 @Autowired가 있어야 함
	// 중복된 타입이 있는 경우 @Qualifier 어노테이션 이용해서 특정 bean을 선택할 수 있다.
	// 어노테이션 뒤에 id값을 입력한다.
	@Autowired
	@Qualifier("dog")
	private Animal a;
	@Autowired
	@Qualifier("bbo")
	private Animal b;
	
	// springBean으로 등록되지 않은 객체를 Autowired 하면? NoSuchBeanDefinitionException 발생
	@Autowired(required=false) // 실행할 때 객체가 없을 수도 있으므로 required 속성에 false 값을 줄 수 있다.
	// 값이 있으면 출력되고 없어도 에러 나지 않고 null값이 출력됨 -> 잘 사용하지는 않는다. 
	private Employee emp;
	
	@Autowired
	private Employee emp2;
	
	// java로 등록한 bean가져오기
	@Autowired
	@Qualifier("ani")
	private Animal c; // beanTestConfiguration.java
	
	
	@Autowired
	@Qualifier("sol")
	private Employee sol;
	
	@Autowired
	List<Animal> animals;
	
	@Autowired
	private TargetComponent tc;
	
	// @어노테이션으로 bean등록
	@Autowired
	private FunctionalTest ft;
	
	// basePackage 외부에 있는 @Component : NoSuchBeanDefinitionException 에러 발생 
	// 
	@Autowired
	private Test test;
	
	// "/"로 들어오면 index.jsp로 실행되게 함 -> view resolver로 등록한 클래스가 실행해줌 -> servlet-context.xml
	@RequestMapping("/test")
	public String home() {
		System.out.println(a);
		System.out.println(b);
		System.out.println(emp);
		System.out.println(emp2);
		System.out.println(c);
		System.out.println(sol);
		animals.forEach(System.out::println);
		System.out.println(tc);
		System.out.println(ft);
		System.out.println(ft.getA());
//		System.out.println(test);
		
		// Resource: spring에서 파일을 불러올 수 있는 Resource 객체를 제공
		Resource resource= new ClassPathResource("mydata.properties");
		try {
			Properties prop=PropertiesLoaderUtils.loadProperties(resource);
			System.out.println(prop);
			resource= new FileSystemResource("D:\\ri\\spring_workspace\\spring\\src\\main\\resources\\test.txt");
			// filesystem에서 가져오는 것이기 때문에 해당 파일의 properties에서 절대 경로를 입력해야한다.
			//lines() : stream 반환
			Files.lines(Paths.get(resource.getURI()),Charset.forName("UTF-8")).forEach(System.out::println);
			
			// page에 대한 정보를 문자열로 가져올 수 있다.
			resource=new UrlResource("https://gdu.co.kr");
			InputStream is = resource.getInputStream();
			int d=0;
			StringBuffer sb= new StringBuffer();
			while((d=is.read())!=-1) {
				sb.append((char)d);
			}
			System.out.println(sb);
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return "index";
	}
}
