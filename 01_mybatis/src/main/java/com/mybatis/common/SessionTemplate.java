package com.mybatis.common;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SessionTemplate {
	// mybatis가 제공하는 sqlSession객체를 생성해주는 공용 메소드 선언 -> static으로 선언하면 공용으로 사용하기 편리 
	
	public static SqlSession getSession() {
		// SqlSession객체를 생성하는 방법
		// 1. SqlSessionFactoryBuilder 클래스의 build() 멤버 메소드 이용
		//		- SqlSessionFactoryBuilder생성
		//		- build()메소드 실행
		//		- build()메소드의 인수로 mybatis-config.xml 파일 실행 (file 이므로 stream으로 파싱해서 전달한다.)
		// 2. SqlSessionFactory 클래스를 가져올 수 있다. 
		//		- openSession() 메소드를 이용
		// 		- 인수로 true, false 설정 -> autoCommit에 대한 설정
		// 3. SqlSession클래스를 가져온다.
		
		// session이 다 가지고 있기 때문에 close, commit, rollback을 따로 메소드로 만들 필요 없음 -> mybatis 알아서 처리함 -> transaction처리만 하면 됨
		SqlSession session=null;
		String file="mybatis-config.xml"; // 파일명으로 불러오기 때문에 꼭 mybatis-config일 필요 없음 
		
		try(InputStream is=Resources.getResourceAsStream(file);) {
			//InputStream is=Resources.getResourceAsStream(file); // stream으로 파싱해서 보내준다
			SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
			SqlSessionFactory factory= builder.build(is); // SqlSessionFactoryBuilder에 설정 파일과 연결된 stream을 넣어준다.
			session= factory.openSession(false); // auto commit 설정
		}catch(IOException e) {
			e.printStackTrace();
		}
		return session;
	}
	
	
}
