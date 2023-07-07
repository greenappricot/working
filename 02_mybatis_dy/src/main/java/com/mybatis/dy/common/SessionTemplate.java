package com.mybatis.dy.common;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SessionTemplate {
	
	public static SqlSession getSession() {
		SqlSession session= null;
		String file="mybatis-config.xml";
		try(InputStream is=Resources.getResourceAsStream(file);){
//			SqlSessionFactoryBuilder builder= new SqlSessionFactoryBuilder();
//			SqlSessionFactory factory=builder.build(is);
//			session=factory.openSession(false);	
//			한 줄로 줄일 수 있다
			session=new SqlSessionFactoryBuilder().build(is).openSession(false);
		}catch(IOException e) {
			e.printStackTrace();
		}
		return session;
	}
	
	public static SqlSession getWebSession() {
		String file="mybatis-config.xml";
		SqlSession session=null;
		try(InputStream is=Resources.getResourceAsStream(file);){
			session=new SqlSessionFactoryBuilder().build(is,"web").openSession(false); // config.xml에 있는 environment의 id을 넣으면 다른 연결을 할 수 있다.
		}catch(IOException e) {
			e.printStackTrace();
		}
		return session;
	}
	
}
