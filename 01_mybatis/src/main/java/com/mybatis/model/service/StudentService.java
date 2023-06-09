package com.mybatis.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.mybatis.common.SessionTemplate;
import com.mybatis.model.dao.StudentDao;
import com.mybatis.model.vo.Student;

public class StudentService {
	private StudentDao dao=new StudentDao();
	
	public int insertStudent() {
		SqlSession session= SessionTemplate.getSession();
		//System.out.println(session);
		int result=dao.insertStudent(session);
		if(result>0) session.commit();
		else session.rollback();
		session.close();
		return result;
	}
	
	public int inserStudentByName(String name) {
		SqlSession session=SessionTemplate.getSession();
		int result=dao.inserStudentByName(session,name);
		if(result>0) session.commit();
		else session.rollback();
		session.close();
		return result;
	}
	
	public int insertStudentAll(Student s) {
		SqlSession session=SessionTemplate.getSession();
		int result=dao.insertStudentAll(session,s);
		if(result>0) session.commit();
		else session.rollback();
		session.close();
		return result;
	}
	
	public int updateStudent(Student s) {
		SqlSession session=SessionTemplate.getSession();
		int result=dao.updateStudent(session,s);
		if(result>0) session.commit();
		else session.rollback();
		session.close();
		return result;
	}
	
	public int deleteStudent(int no) {
		SqlSession session=SessionTemplate.getSession();
		int result=dao.deleteStudent(session,no);
		if(result>0) session.commit();
		else session.rollback();
		session.close();
		return result;
	}
	
	public int selectStudentCount() {
		SqlSession session=SessionTemplate.getSession();
		int count= dao.selectStudentCount(session);
		session.close();
		return count;
	}
	
	public Student selectStudentByNo(int no) {
		SqlSession session=SessionTemplate.getSession();
		Student s= dao.selectStudentByNo(session, no);
		session.close();
		return s;
	}
	
	public List<Student> selectStudentAll(){
		SqlSession session= SessionTemplate.getSession();
		List<Student> list=dao.selectStudentAll(session);
		session.close();
		return list;
	}
	
	public List<Student> selectStudentByName(String name){
		SqlSession session= SessionTemplate.getSession();
		List<Student> list=dao.selectStudentByName(session, name);
		session.close();
		return list;
	}
	
	public Map selectStudentMap(int no) {
		SqlSession session= SessionTemplate.getSession();
		Map result=dao.selectStudentMap(session,no);
		session.close();
		return result;
	}
	
	public List<Map> selectStudentListMap() {
		SqlSession session=SessionTemplate.getSession();
		List<Map> result=dao.selectStudentListMap(session);
		session.close();
		return result;
	}
	
	public List<Student> selectStudentPage(int cPage, int numPerpage){
		SqlSession session= SessionTemplate.getSession();
		List<Student> result=dao.selectStudentPage(session, cPage,numPerpage);
		session.close();
		return result;
	}
	
}
