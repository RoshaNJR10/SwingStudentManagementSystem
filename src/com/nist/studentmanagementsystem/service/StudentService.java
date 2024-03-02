package com.nist.studentmanagementsystem.service;

import java.util.List;

import com.nist.studentmanagementsystem.model.Student;

public interface StudentService {
	
	public void saveStudent(Student student) ;
	public List<Student> getStudentList();
	public List<Student> getStudentsByName(String name);
	public void deleteStudentById(int id);
	public void updateStudent(Student student);
}
