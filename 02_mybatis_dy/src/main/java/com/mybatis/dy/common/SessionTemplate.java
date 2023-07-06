package com.mybatis.dy.common;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
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
	
}
