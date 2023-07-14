package com.bs.spring.memo.service;

import java.util.List;

import com.bs.spring.memo.model.dto.Memo;

public interface MemoService {
	List<Memo> selectMemoAll();
	int insertMemo(Memo m);
	int deleteMemo(int memoNo);
}
