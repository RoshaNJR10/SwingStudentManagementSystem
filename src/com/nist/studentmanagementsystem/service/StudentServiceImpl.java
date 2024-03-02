package com.nist.studentmanagementsystem.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import com.nist.studentmanagementsystem.databaseconnection.DatabaseConnection;
import com.nist.studentmanagementsystem.model.Student;

public class StudentServiceImpl implements StudentService {
PreparedStatement ps=null;
	@Override
	public void saveStudent(Student student) {
		String sql="Insert into student_form(name,address,age,phone,gender,faculty,email)values(?,?,?,?,?,?,?)";
		try {
		ps=DatabaseConnection.getConnection().prepareStatement(sql);
		ps.setString(1, student.getName());
		ps.setString(2, student.getAddress());
		ps.setInt(3, student.getAge());
		ps.setLong(4, student.getPhone());
		ps.setString(5, student.getGender());
		ps.setString(6, student.getFaculty());
		ps.setString(7, student.getEmail());
		ps.executeUpdate();
	}catch(Exception e) {
		System.out.println(e);
	}
	}
	@Override
	public List<Student> getStudentList() {
	ArrayList<Student> studentList=new ArrayList<>();	
		String sql="Select * from student_form";
		try {
		ps=DatabaseConnection.getConnection().prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			Student student=new Student();
			student.setId(rs.getInt("id"));//sout(rs.getInt("id");
			student.setName(rs.getString("name"));
			student.setAddress(rs.getString("address"));
			student.setAge(rs.getInt("age"));
			student.setPhone(rs.getLong("phone"));
			student.setGender(rs.getString("gender"));
			student.setFaculty(rs.getString("faculty"));
			student.setEmail(rs.getString("email"));
			
			studentList.add(student);
		}
		
		}
		catch (Exception e){
			System.out.println(e);
		}
		return studentList ;
	}
	
	@Override
	public List<Student> getStudentsByName(String name) {
		ArrayList< Student> studentList=new ArrayList<Student>();
		String sql="select * from  student_form where name like ?";
		try {
			ps=DatabaseConnection.getConnection().prepareStatement(sql);
			ps.setString(1, "%"+name+"%");
			ResultSet rs= ps.executeQuery();
			while(rs.next()) {
				Student student =new Student();
				student.setId(rs.getInt("id"));
				student.setAddress(rs.getString("address"));
				student.setName(rs.getString("name"));
				student.setAge(rs.getInt("age"));
				student.setEmail(rs.getString("email"));
				student.setFaculty(rs.getString("faculty"));
				student.setPhone(rs.getLong("phone"));
				student.setGender(rs.getString("gender"));
				
				studentList.add(student);
			}
		} 
		catch (Exception e) {
			System.out.println(e);
		}
		return studentList;
	}
	@Override
	public void deleteStudentById(int id) {
		String sql="Delete from student_form where id=?";
		try {
			ps=DatabaseConnection.getConnection().prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	@Override
	public void updateStudent(Student student) {
		String sql="update student_form set name=?,address=?,age=?,phone=?,gender=?,faculty=?,email=? where id=?";
		try {
			ps=DatabaseConnection.getConnection().prepareStatement(sql);
			ps.setString(1, student.getName());
			ps.setString(2, student.getAddress());
			ps.setInt(3, student.getAge());
			ps.setLong(4, student.getPhone());
			ps.setString(5, student.getGender());
			ps.setString(6, student.getFaculty());
			ps.setString(7, student.getEmail());
			ps.setInt(8, student.getId());
			ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	

}
