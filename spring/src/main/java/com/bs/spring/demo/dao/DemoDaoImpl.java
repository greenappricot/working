package com.bs.spring.demo.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.bs.spring.demo.model.dto.Demo;

@Repository // db와 연결해서 bean 가져오니까 repository 선언(@Component 상속)
public class DemoDaoImpl implements DemoDao {

	@Override
	public int insertDemo(SqlSessionTemplate session, Demo m) {
		return session.insert("dev.insertDemo",m);
	}

	@Override
	public List<Demo> selectDemoAll(SqlSession session) {
		return session.selectList("dev.selectDemoAll");
	}
	
	@Override
	public Demo selectDemo(SqlSession session, int devNo) {
		return session.selectOne("dev.selectDemo",devNo);
	}

}
