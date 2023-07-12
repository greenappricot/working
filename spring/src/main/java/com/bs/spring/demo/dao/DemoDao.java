package com.bs.spring.demo.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;

import com.bs.spring.demo.model.dto.Demo;

public interface DemoDao {
	
	int insertDemo(SqlSessionTemplate session, Demo m); // SqlSession, SqlSessionTemplate 둘 다 사용해도 됨(상속받았기 때문)
	List<Demo> selectDemoAll(SqlSession session);
	Demo selectDemo(SqlSession session, int devNo);
}
