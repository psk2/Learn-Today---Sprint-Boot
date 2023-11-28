package com.learntoday.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learntoday.DTO.StudentDto;
import com.learntoday.service.CourseService;
import com.learntoday.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

	private CourseService courseService;
	private StudentService stuService;

	public StudentController(CourseService courseService, StudentService stuService) {
		super();
		this.courseService = courseService;
		this.stuService = stuService;
	}
	

	@GetMapping("/courses/all")
	public ResponseEntity<?> getAllCourses() {
		return courseService.getAllCourses();
	}
	

	@PostMapping("/enroll")
	public ResponseEntity<?> enrollCourse(@RequestBody StudentDto student) {
		return stuService.enrollCourse(student);
	}
	

	@DeleteMapping("/unroll/{eid}")
	public ResponseEntity<?> deleteStudentEnrollment (@PathVariable int eid) {
		return stuService.deleteStudentEnrollment(eid);
	}

}
