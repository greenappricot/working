package com.web.notice.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FileDownloadServlet
 */
@WebServlet("/fileDownload.do")
public class FileDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileDownloadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 파일에 대한 다운로드 기능 구현하기
		// 1. 클라이언트가 보낸 파일 이름 받기
		String fileName=request.getParameter("name"); // noticeView.jsp의 function에서 location.assign으로 보낸 name을 받아온다.
		
		// 2. 스트림으로 파일 주고 받는다 
		// 2-1 파일에 대한 절대 경로를 가져온다
		String path= getServletContext().getRealPath("/upload/notice/");
		File f=new File(path+fileName);
		
		// 3. InputStream 생성 -> FileInputStream 생성 해서 파일을 하드로 가져오기
		FileInputStream fis= new FileInputStream(f);
		BufferedInputStream bis= new BufferedInputStream(fis);
		
		// 4. 한글파일명 깨지지 않도록 인코딩 처리하기
		String fileRename= "";
		String header=request.getHeader("user-agent"); // request header에서 user-agent browser에 대한 정보를 가져온다.
		// IE, 그외 브라우저랑 인코딩 처리 방식이 달라서 인코딩을 분리해서 처리해야한다.
		boolean isMSIE= header.indexOf("MSIE")!=-1 || header.indexOf("Trident")!=-1;
		if(isMSIE) {
			fileRename=URLEncoder.encode(fileName,"UTF-8").replaceAll("\\+","%20");
		}else {
			fileRename=new String(fileName.getBytes("UTF-8"),"ISO-8859-1");
		}
		
		// 5. 응답 헤더 설정 -> contentType 설정
		response.setContentType("application/octet-stream"); // 파일 형식 무관하게 받을 수 있다. 
		response.setHeader("Content-disposition", "attachment;filename="+fileRename); // 파일명 바로 뜨게 할거라서 인코딩 처리 했다.
		// attachment option에 index주면 browser가 열 수 있는 파일 형식을 바로 오픈한다.
		
		// 6. 사용자에게 파일 보내기
		// 6-1 클라이언트의 스트림 가져오기 -> binary 파일이므로 getOutputStream() 사용 (string일때는 getWriter()
		ServletOutputStream sos= response.getOutputStream();
		BufferedOutputStream bos= new BufferedOutputStream(sos);
		
		int read= -1;
		while((read=bis.read())!=-1) {
			bos.write(read);
		}
		bis.close();
		bos.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
