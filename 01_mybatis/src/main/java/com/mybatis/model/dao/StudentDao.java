package com.mybatis.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.mybatis.model.vo.Student;

public class StudentDao {

	public int insertStudent(SqlSession session) {
		// 기존 dao에서 preparedStatement, resultSet은 session에서 처리한다 
		// DAO에서 sql문을 실행할 때 session이 제공하는 메소드를 호출한다.
		// selectOne(), selectList(), insert(), update(), delete() ... 
		
		// Object로 매개변수를 하나만 더 받을 수 있기 때문에 파라미터를 객체로 만들어서 전달해야한다.
		// statement 인수는 "mapper태그namespace값.sql id값"
		int result=session.insert("student.insertStudent");
		return result;
	}
	
	public int inserStudentByName(SqlSession session, String name) {
		int result=session.insert("student.inserStudentByName", name);
		return result;
	}
	
	public int insertStudentAll(SqlSession session, Student s) {
		return session.insert("student.insertStudentAll", s); // object로 선언되어 있기 때문에 어떤 값을 넘겨도 가능
	}
	
	public int updateStudent(SqlSession session, Student s) {
		return session.update("student.updateStudent",s);
	}
	
	public int deleteStudent(SqlSession session, int no) {
		return session.delete("student.deleteStudent",no);
	}
	
	public int selectStudentCount(SqlSession session) {
		// selectOne() 메소드를 이용해서 데이터를 조회할 수 있다.
		// selectOne의 반환형이 T(generic type)이기 때문에 mapper.xml에 타입 설정할 때 반환형이 정해진다.
		// insert, update, delete와 같이 statement, [parameter]를 매개변수로 전달한다.
		// mapper에서 반환형을 정해주면 그 반환형으로 받으면 된다 (항상 반환 값이 int인 것이 아님)
		int count=session.selectOne("student.selectStudentCount");
		return count;
	}
	
	public Student selectStudentByNo(SqlSession session, int no) {
		return session.selectOne("student.selectStudentByNo", no);
	}
	
	public List<Student> selectStudentAll(SqlSession session){
		List<Student> list=session.selectList("student.selectStudentAll");
		return list;
	}
	public List<Student> selectStudentByName(SqlSession session, String name){
		return session.selectList("student.selectStudentByName",name);
	}
	
	public Map selectStudentMap(SqlSession session, int no) {
		return session.selectOne("student.selectStudentMap", no);
	}
	
	public List<Map> selectStudentListMap(SqlSession session) {
		return session.selectList("student.selectStudentListMap");
	}
	public List<Student> selectStudentPage(SqlSession session, int cPage, int numPerpage){
		// RowBounds 클래스 : 페이지 처리할 때는 마이바티스가 제공하는 페이징처리 클래스를 이용한다.
		// selectLis 호출 시 세번쨰 매개 변수에 RowBounds 클래스를 생성해서 전달한다.
		// 매개변수 있는 생성자를 이용한다.
		// 첫 번째 매개변수 : offset -> 시작 row 번호 (cPage-1)*numPerpage
		// 두 번째 매개변수 : 범위 -> numPerpage
		// new RowBounds((cPage-1)*numPerapege,numPerpage);
		RowBounds rb= new RowBounds((cPage-1)*numPerpage,numPerpage);
		return session.selectList("student.selectStudentPage",null, rb); 
		// RowBounds가 들어갈 수 있는 순서가 정해져있기 때문에 전달할 매개변수가 없어도 아무 값(null이나 뭐든) 넣어서 순서를 지켜야한다.
	}
}














