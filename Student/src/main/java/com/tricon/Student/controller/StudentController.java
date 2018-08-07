package com.tricon.Student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tricon.Student.model.Student;
import com.tricon.Student.service.IStudentService;

@RestController
public class StudentController {

	@Autowired
	private IStudentService  student;
	
	@RequestMapping("/health")
	public List<Student> ping() {
		System.out.println("Inside: Controller");
		return student.getName();
	}
}
