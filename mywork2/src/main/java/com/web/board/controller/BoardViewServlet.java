package com.web.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.board.model.service.BoardService;
import com.web.board.model.vo.Board;
import com.web.board.model.vo.BoardComment;

/**
 * Servlet implementation class BoardViewServlet
 */
@WebServlet("/board/boardView.do")
public class BoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int boardNo=Integer.parseInt(request.getParameter("boardNo"));

		
		
		// 쿠키에 저장하기 전에 이전값이 있는지 확인하는 로직이 필요하다. -> reWrite되기 때문에
		Cookie[] cookies=request.getCookies();
		String boardRead="";
		boolean isRead=false;
		if(cookies!=null) {
			for(Cookie c:cookies) {
				if(c.getName().equals("boardRead")){ // c.getName()==key값
					boardRead=c.getValue(); // 이전 값 있는지 확인하고 있으면 가져와서 변수에 저장한다.
					
					// 이전 값과 일치하는 값이 있으면 쿠키에 저장하지 않는 분기처리
					if(boardRead.contains("|"+boardNo+"|")) {
						isRead=true;
					}
					break;
				}
			}
		}
		// 읽은 boardNo를 모두 가져와서 쿠키에 저장한다. --> parsing에서 확인할 수 있다.
		if(!isRead) {
			Cookie c= new Cookie("boardRead",boardRead+"|"+boardNo+"|");  // |1||2||3| 
			c.setMaxAge(60*60*24); // 하루동안 쿠키 유지
			response.addCookie(c);
		}
		
		//Board b=new BoardService().selectBoardByNo(boardNo);
		// b가 null일 경우에 대한 분기처리 필요함 -> 없으면 nullpointexception 발생
		Board b=new BoardService().selectBoardByNo(boardNo,isRead); // update문을 처리하기 때문에 같이 넘겨서 처리한다.
		
		
		// 댓글 가져와서 출력하기
		List<BoardComment> list=new BoardService().selectBoardComment(boardNo);
		request.setAttribute("comment",list);
		
		request.setAttribute("board", b);
		request.getRequestDispatcher("/views/board/boardView.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
