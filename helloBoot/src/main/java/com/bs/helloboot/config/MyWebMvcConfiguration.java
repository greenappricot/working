package com.bs.helloboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.bs.helloboot.common.interceptor.LoggerInterceptor;
import com.bs.helloboot.websocket.ChattingServer;

@Configuration // 환경 설정 파일
@EnableWebSocket
public class MyWebMvcConfiguration implements WebMvcConfigurer, WebSocketConfigurer {
	
	// ChattingServer DI주입해서 연결
	private ChattingServer chatting;
	public MyWebMvcConfiguration(ChattingServer chatting) {
		this.chatting= chatting;
	}
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(chatting, "/chatting"); // chatting이라는 주소로 요청하면 웹소켓이랑 연결한다.
	}
	
	
	// web.xml 역할을 하는 일반 pojo 클래스 생성
	// WebMvcConfigurer interface를 상속해서 WebMvcConfigurer의 메소드를 재정의해서 사용할 수 있다.
	// view, interceptor, CORS(Cross-Origin Resource Sharing)에 대한 허용 설정 등을 할 수 있다.
	
	// 1. view ) 기본 화면 전환에 대한 설정 하는 메소드 재정의
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// 내부적으로 페이지 전달할 때 등록할 수 있다.
		registry.addViewController("/").setViewName("index");
		registry.addViewController("/test").setViewName("test");
		registry.addViewController("/chattingpage").setViewName("chattingpage");
	}
	
	// 2. Interceptor 설정
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//common.interceptor 패키지의 LoggerInterceptor 클래스를 등록하기
		registry.addInterceptor(new LoggerInterceptor()).addPathPatterns("/member/*");
	}
	
	//3. CORS error 설정
	// 3-1 제공해주는 메소드를 이용해서 일괄적으로 허용해주는 방법
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// react에서 사용하는 서버는 3000번이므로 react에서 보내는 요청을 허용하기 위해 설정한다.
		registry.addMapping("/**").allowedOrigins("http://localhost:3000");
		// /* : 한 depth만 모두  /** : 그 아래 모두
	}
	// 3-2 컨트롤러마다 어노테이션을 선언해서 허용해주는 방법 : 해당 컨트롤러에 @CrossOrigin(value = "url") 선언한다.
	
	
	
}