package com.bs.spring.board.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bs.spring.board.dao.BoardDao;
import com.bs.spring.board.model.dto.Attachment;
import com.bs.spring.board.model.dto.Board;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private SqlSession session;
	@Autowired
	private BoardDao dao;
	
	@Override
	public List<Board> selectBoardAll(Map<String, Object> page) {
		return dao.selectBoardAll(session, page);
	}

//	@Override
//	@Transactional
//	public int insertBoard(Board b) {
//		
//		//2개의 insert문을 실행한다.
//		log.info("실행전 : {}",b.getBoardNo());
//		int result=dao.insertBoard(session, b);
//		log.info("실행후 : {}",b.getBoardNo());
//		// 다중 파일 업로드
//		if(result>0) {
//			if(b.getFile().size()>0) {
//				for(Attachment a : b.getFile()) {
//					a.setBoardNo(b.getBoardNo());
//					result+=dao.insertAttachment(session,a);
////					if(result!=1) throw new RuntimeException("첨부파일의 내용이 이상합니다.");
//					//result=dao.insertAttachment(session,a);
//					//if(result==0) throw new RuntimeException("첨부파일의 내용이 이상합니다.");
//				}
//			}
//		}
//		// rollback 처리를 원하면 RuntimeException을 발생시키면 된다.
//		// if(result>0) throw new RuntimeException("내가 싫어 ! ");
//		if(result!=b.getFile().size()+1) throw new RuntimeException("첨부파일의 내용이 이상합니다.");
//		return result;
//	}
	
	@Override
	//@Transactional
	public int insertBoard(Board b) {
		
		//2개의 insert문을 실행한다.
		log.info("실행전 : {}",b.getBoardNo());
		int result=dao.insertBoard(session, b);
		log.info("실행후 : {}",b.getBoardNo());
		// 다중 파일 업로드
		if(result>0) {
			if(b.getFile().size()>0) {
				for(Attachment a : b.getFile()) {
					a.setBoardNo(b.getBoardNo());
					result+=dao.insertAttachment(session,a);
				}
			}
		}
		// rollback 처리를 원하면 RuntimeException을 발생시키면 된다.
		if(result!=b.getFile().size()+1) throw new RuntimeException("첨부파일의 내용이 이상합니다.");
		//if(result!=0) throw new RuntimeException("첨부파일의 내용이 이상합니다.");
		return result;
	}
	
	@Override
	public int countBoardAll() {
		return dao.countBoardAll(session);
	}
	
	@Override
	public Board selectBoardByNo(int boardNo) {
		return dao.selectBoardByNo(session, boardNo);
	}
	
}
