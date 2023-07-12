package com.bs.spring.demo.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bs.spring.demo.dao.DemoDao;
import com.bs.spring.demo.model.dto.Demo;

@Service // spring bean으로 등록됨(@Component 상속)
public class DemoServiceImpl implements DemoService {
	
	@Autowired
	private DemoDao dao; // type을 보고 가져오는데, Interface는 생성되지 않으니까 구현체인 DemoDaoImpl를 찾아서 가져온다.
	
	@Autowired // bean으로 등록했기 때문에 @Autowired해서 가져온다.
	private SqlSessionTemplate session;
	// spring 사용 전에는 service에서 session.close()해줬는데 commit, rollback, close 모두 spring이 관리한다.

	@Override
	public int insertDemo(Demo demo) {
		return dao.insertDemo(session, demo);
	}

	@Override
	public List<Demo> selectDemoAll() {
		return dao.selectDemoAll(session);
	}
	
	@Override
	public Demo selectDemo(int devNo) {
		return dao.selectDemo(session,devNo);
	}

}
