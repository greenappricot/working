package com.web.board.model.dao;

import static com.web.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.web.board.model.vo.Board;
import com.web.board.model.vo.BoardComment;

public class BoardDao {
	
	private Properties sql=new Properties();
	public BoardDao() {
		String path=BoardDao.class.getResource("/sql/board/boardsql.properties").getPath();
		try(FileReader fr=new FileReader(path);) {
			sql.load(fr);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private Board getBoard(ResultSet rs) throws SQLException {
		return Board.builder().boardNo(rs.getInt("BOARD_NO")).boardTitle(rs.getString("BOARD_TITLE")).boardWriter(rs.getString("BOARD_WRITER"))
				.boardContent(rs.getString("BOARD_CONTENT")).boardOriginalFilename(rs.getString("BOARD_ORIGINAL_FILENAME"))
				.boardRenameFilename(rs.getString("BOARD_RENAMED_FILENAME")).boardDate(rs.getDate("BOARD_DATE")).boardReadCount(rs.getInt("BOARD_READCOUNT")).build();
	}
	
	public int selectBoardCount(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectBoardCount"));
			rs=pstmt.executeQuery();
			while(rs.next()) result=rs.getInt(1);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	public List<Board> selectBoard(Connection conn, int cPage, int numPerpage){
		List<Board> b= new ArrayList<Board>();
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectBoard"));
			pstmt.setInt(1, (cPage-1)*numPerpage+1);
			pstmt.setInt(2, cPage*numPerpage);
			rs=pstmt.executeQuery();
			while(rs.next()) b.add(getBoard(rs));
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return b;
	}
	
	public Board selectBoardByNo(Connection conn, int boardNo) {
		Board b=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectBoardByNo"));
			pstmt.setInt(1,boardNo);
			rs=pstmt.executeQuery();
			while(rs.next()) b=getBoard(rs);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return b;
	}
	
	public int updateBoardReadCount(Connection conn,int boardNo) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("updateBoardReadCount"));
			pstmt.setInt(1, boardNo);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	
	public int insertBoardComment(Connection conn, BoardComment bc) {
		int result=0;
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("insertBoardComment"));
			pstmt.setInt(1, bc.getLevel());
			pstmt.setString(2, bc.getBoardCommentWriter());
			pstmt.setString(3, bc.getBoardCommentContent());
			pstmt.setInt(4, bc.getBoardRef());
			pstmt.setString(5, bc.getBoardCommentRef()==0?null:String.valueOf(bc.getBoardCommentRef())); //fk 설정되어있기 때문에 null이거나 참조키가 들어가야한다. 
			// default로 0 넣었기 때문에 참조하는 값이 없으면 null/ 있으면 참조번호 넣는 로직 -> INT에 NULL 저장할 수 없으므로 nullpointerExpection 발생
			// 오라클은 자동형변환 잘 되기 때문에 int아니고 String으로 넣어도 참조값을 알아서 처리한다.
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public List<BoardComment> selectBoardComment(Connection conn, int boardNo){
		ResultSet rs=null;
		PreparedStatement pstmt=null;
		List<BoardComment> list=new ArrayList<BoardComment>();
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectBoardCommentByNo"));
			pstmt.setInt(1, boardNo);
			rs=pstmt.executeQuery();
			while(rs.next()) list.add(getBoardComment(rs));
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return list;
	}
	
//	public int insertBoardCommentReply(Connection conn, int boardNo, int boardCommentRef) {
//		int result=0;
//		PreparedStatement pstmt=null;
//		try {
//			pstmt=conn.prepareStatement(sql.getProperty("insertBoardCommentReply"));
//			result=pstmt.executeUpdate();
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}finally {
//			close(pstmt);
//		}
//		return result;
//	}
	
	private BoardComment getBoardComment(ResultSet rs) throws SQLException {
		return BoardComment.builder()
				.boardCommentNo(rs.getInt("BOARD_COMMENT_NO"))
				.level(rs.getInt("BOARD_COMMENT_LEVEL"))
				.boardCommentWriter(rs.getString("BOARD_COMMENT_WRITER"))
				.boardCommentContent(rs.getString("BOARD_COMMENT_CONTENT"))
				.boardCommentDate(rs.getDate("BOARD_COMMENT_DATE"))
				.boardCommentRef(rs.getInt("BOARD_REF"))
				.boardRef(rs.getInt("BOARD_REF")).build();
	}
	
}
