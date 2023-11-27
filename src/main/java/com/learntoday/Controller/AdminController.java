package com.learntoday.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learntoday.DTO.CourseDto;
import com.learntoday.service.CourseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/course")
public class AdminController {
	
	private CourseService courseService;
	
	public AdminController(CourseService courseService) {
		super();
		this.courseService = courseService;
	}

	@GetMapping("/all")
	public ResponseEntity<?> getAllCourses() {
		return courseService.getAllCourses();
	}
	
	@GetMapping("/{courseId}")
	public ResponseEntity<?> getCourseById(@PathVariable int courseId) {
		return courseService.getCourseById(courseId);
	}
	
	@PostMapping("/new")
	public ResponseEntity<?> createCourse(@Valid @RequestBody CourseDto course){
		return courseService.createCourse(course);
	}
	 
}
