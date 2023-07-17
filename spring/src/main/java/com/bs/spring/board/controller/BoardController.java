package com.bs.spring.board.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bs.spring.board.model.dto.Board;
import com.bs.spring.board.service.BoardService;
import com.bs.spring.common.PageFactory;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/board")
@Slf4j
public class BoardController {
	
	private BoardService service;
	
	@Autowired
	public BoardController(BoardService service) {
		this.service=service;
	}
	
	@RequestMapping("/boardList.do")
	public String selectBoardAll(Model m, @RequestParam(value="cPage",defaultValue="1") int cPage, @RequestParam(value="numPerpage", defaultValue="5") int numPerpage, HttpServletRequest request) {
		// RequestParam으로 선언해서 try/ catch 사용하지 않아도 된다.
//		cPage=(cPage-1)*numPerpage+1;
//		numPerpage=cPage*numPerpage;
		
//		Map<String, Object> page= new HashMap<String, Object>(); // 값이 안 들어올 가능성이 있으면 Map 사용하면 안된다.
//		page.put("cPage", cPage);
//		page.put("numPerpage", numPerpage);
//		List<Board> list=service.selectBoardAll(page);
		List<Board> list=service.selectBoardAll(Map.of("cPage",cPage,"numPerpage", numPerpage));
		
		int totalContents = service.countBoardAll();
//		int totalPage = (int)Math.ceil((double)totalContents/numPerpage);
//		int pageBarSize=5;
//		int pageNo = ((cPage-1)/pageBarSize)*pageBarSize+1;
//		int pageEnd = pageNo+pageBarSize-1;
//		String pageBar="";
//		if(pageNo==1) {
//			pageBar+="""
//					<ul class='pagination'>
//					<li class='page-item disabled'>
//						<a class='page-link' href='#'>이전</a>
//					<li class=''>
//					</li>
//					""";
//		}else {
//			pageBar+="<li class='page-item'>";
//			pageBar+="<a class='page-link' href='"+request.getRequestURI()+"?cPage="+(pageNo-1)+"'>이전</a>";
//			pageBar+="</li>";
//		}
//		while(!(pageNo>pageEnd||pageNo>totalPage)) {
//			if(cPage==pageNo) {
//				pageBar+="<li class='page-item disabled'>";
//				pageBar+="<a class='page-link' href='#'>"+pageNo+"</a>";
//				pageBar+="</li>";
//			}else {
//				pageBar+="<li class='page-item'>";
//				pageBar+="<a class='page-link' href='"+request.getRequestURI()
//						+"?cPage="+(pageNo)+"'>"+pageNo+"</a>";
//				pageBar+="</li>";
//			}
//			pageNo++;
//		}
//		
//		if(pageNo>totalPage) {
//			pageBar+="""
//					<li class='page-item disabled'>
//						<a class='page-link' href='#'>다음</a>
//					<li class=''>
//					</li>
//					""";
//		}else {
//			pageBar+="<li class='page-item'>";
//			pageBar+="<a class='page-link' href='"+request.getRequestURI()+"?cPage="+(pageNo)+"'>다음</a>";
//			pageBar+="</li>";
//		}
//		pageBar+="</ul>";
		
		//m.addAttribute("pageBar", PageFactory.getPage(cPage, numPerpage, totalContents, "/spring/board/selectBoardAll.do"));
		//m.addAttribute("pageBar", PageFactory.getPage(cPage, numPerpage, totalContents, request.getRequestURI()));
		m.addAttribute("pageBar", PageFactory.getPage(cPage, numPerpage, totalContents, "boardList.do"));
		m.addAttribute("boards",list);
		m.addAttribute("totalContents",totalContents);
		return "board/boardList";
	}
	
	@RequestMapping("/insertBoardPage.do")
	public String insertBoardPage() {
		return "board/insertBoard";
	}
	
	@RequestMapping("/insertBoard.do")
	public String insertBoard(Board b, Model m) {
		
		int result = service.insertBoard(b);
		if(result>0) {
			m.addAttribute("msg","등록 성공");
			m.addAttribute("loc","/");
		}else {
			m.addAttribute("msg","등록 실패");
			m.addAttribute("loc","/insertBoard.do");
		}
		return "common/msg";
	}
	
	@RequestMapping("/selectBoardByNo.do")
	public String selectBoardByNo(int boardNo, Model m) {
		Board b=service.selectBoardByNo(boardNo);
		m.addAttribute("boards",b);
		return "board/boardView";
	}
	
}
