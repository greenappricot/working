package com.bs.spring.memo.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.bs.spring.memo.dao.MemoDao;
import com.bs.spring.memo.model.dto.Memo;

@Service
public class MemoServiceImpl implements MemoService {
	
	private MemoDao dao;
	
	private SqlSession session;
	
	// @Autowired 선언 말고 생성자를 통해서 DI 주입
	public MemoServiceImpl(MemoDao dao, SqlSession session) {
		this.dao=dao;
		this.session=session;
	}
	
	@Override
	public List<Memo> selectMemoAll(){
		return dao.selectMemoAll(session);
	}
	
	@Override
	public int insertMemo(Memo m) {
		return dao.insertMemo(session, m);
	}
	
	@Override
	public int deleteMemo(int memoNo) {
		return dao.deleteMemo(session, memoNo);
	}
}
