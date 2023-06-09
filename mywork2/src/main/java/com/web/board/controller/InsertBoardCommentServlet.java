package com.web.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.board.model.service.BoardService;
import com.web.board.model.vo.BoardComment;

/**
 * Servlet implementation class InsertBoardCommentServlet
 */
@WebServlet("/board/insertComment.do")
public class InsertBoardCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertBoardCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// insertboard에서 작성한 댓글을 서버에 등록하는 서블릿 
		BoardComment bc=BoardComment.builder()
							.boardRef(Integer.parseInt(request.getParameter("boardRef")))
							.level(Integer.parseInt(request.getParameter("level")))
							.boardCommentWriter(request.getParameter("boardCommentWriter"))
							.boardCommentContent(request.getParameter("boardCommentContent"))
							.boardCommentRef(Integer.parseInt(request.getParameter("boardCommentRef")))
							.build();
		
		int result= new BoardService().insertBoardComment(bc);
		
		String view;
		if(result>0) {
			//boardview로 이동
			view=request.getContextPath()+"/board/boardView.do?boardNo="+bc.getBoardRef(); // jsp로 바로 이동시키면 setAttribute하는 값이 없기 때문에 nullpointException 발생함
			response.sendRedirect(view); // getRequestDispatcher쓰면 querystring으로 insert한 값이 주소값에 계속 남아있기 때문에 sendRedirect로 전환해야한다.
		}else {
			request.setAttribute("msg", "댓글 등록 실패 ;(");
			request.setAttribute("loc", "/board/boardView.do?boardNo="+bc.getBoardRef());
			view="/views/common/msg.jsp";
			request.getRequestDispatcher(view).forward(request,response);
			// msg나오고 화면 전환되면 dispatcher 사용해도 상관없는데, 바로 화면 전환시 url에 값 남아있는지 확인 필요함
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
