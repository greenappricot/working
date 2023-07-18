package com.bs.spring.board.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bs.spring.board.model.dto.Attachment;
import com.bs.spring.board.model.dto.Board;
import com.bs.spring.board.service.BoardService;
import com.bs.spring.common.PageFactory;
import com.bs.spring.member.model.dto.Member;

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
	
	@RequestMapping("/boardForm.do")
	public String boardForm() {
		return "board/boardForm";
	}
	
	@RequestMapping("/insertBoard.do")
	public String insertBoard(Board b, String writer, MultipartFile[] upFile, HttpSession session, Model m) {
		log.info("{}",b);
		log.info("{}",upFile);
		
		// Board 객체에서 Member 객체를 참조해서 boardWriter를 가져오기 때문에 따로 writer를 받아와서 처리해야한다.
		// Member 생성해서 넣어야함
		//Member member=new Member().builder().userId(writer).build();
		
//		Member member=(Member)session.getAttribute("loginMember");
		b.setBoardWriter((Member)session.getAttribute("loginMember"));
		
		//MultipartFile에서 제공하는 메소드를 이용해서 
		//파일을 저장할 수 있음 -> transferTo()메소드를 이용
		//절대경로 가져오기
		String path=session.getServletContext().getRealPath("/resources/upload/board/");
		//파일명에 대한 renamed규칙을 설정
		//직접리네임규칙을 만들어서 저장해보자.
		//yyyyMMdd_HHmmssSSS_랜덤값
//		List<Attachment> files=new ArrayList();
		if(upFile!=null) {
			for(MultipartFile mf:upFile) {
				if(!mf.isEmpty()) {
					String oriName=mf.getOriginalFilename();
					String ext=oriName.substring(oriName.lastIndexOf("."));
					Date today=new Date(System.currentTimeMillis());
					SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
					int rdn=(int)(Math.random()*10000)+1;
					String rename=sdf.format(today)+"_"+rdn+ext;
					
					try {
						mf.transferTo(new File(path+rename));
					}catch(IOException e) {
						e.printStackTrace();
					}
					
					Attachment file=Attachment.builder()
							.originalFilename(oriName)
							.renamedFilename(rename)
							.build();
					
					b.getFile().add(file);
				}
			}
		}
		try {
			service.insertBoard(b);
		}catch(RuntimeException e) {
			// 입력 실패 시 업로드 한 데이터 삭제하기
			for(Attachment a : b.getFile()) {
				File delFile = new File(path+a.getRenamedFilename());
				delFile.delete();
			}
			m.addAttribute("msg","게시글 등록 실패 :P");
			m.addAttribute("loc","/board/boardForm.do");
			return "common/msg"; // service에서 exception 발생하면 msg 출력한다.
		}
		return "redirect:/board/boardList.do";
	}
	
	@RequestMapping("/selectBoardByNo.do")
	public String selectBoardByNo(int boardNo, Model m) {
		Board b=service.selectBoardByNo(boardNo);
		m.addAttribute("boards",b);
		return "board/boardView";
	}
	
	// 파일 다운로드
	@RequestMapping("/fileDownload")
	public void fileDownload(String oriName, String reName, OutputStream out, @RequestHeader(value="user-agent") String header, HttpSession session, HttpServletResponse response) {
		String path=session.getServletContext().getRealPath("/resources/upload/board/"); // 실제 경로
		File downloadFile = new File(path+reName);
		try(FileInputStream fis = new FileInputStream(downloadFile);
			BufferedInputStream bis = new BufferedInputStream(fis); // 빠른 처리를 위해 buffer 사용	
			BufferedOutputStream bos = new BufferedOutputStream(out);) {
			
			boolean isMS = header.contains("Trident") || header.contains("MSIE");
			String encodeRename="";
			if(isMS) {
				encodeRename = URLEncoder.encode(oriName,"UTF-8");
				encodeRename = encodeRename.replaceAll("\\+","%20");
			}else {
				encodeRename = new String(oriName.getBytes("UTF-8"),"ISO-8859-1");
			}
			response.setContentType("application/octet-stream;charset=utf-8"); // contentType정해준다
			response.setHeader("Content-Disposition", "attachment;filename=\""+encodeRename+"\"");
			
			int read = -1;
			while((read=bis.read())!=-1) {
				bos.write(read);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
}




