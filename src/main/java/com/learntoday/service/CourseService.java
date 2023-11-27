package com.learntoday.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.learntoday.DTO.CourseDto;
import com.learntoday.entity.Course;
import com.learntoday.exceptions.CourseNotFoundException;
import com.learntoday.exceptions.CreateCourseException;
import com.learntoday.model.ResponseMessage;
import com.learntoday.repository.CourseRepository;

import jakarta.validation.Valid;

@Service
public class CourseService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CourseService.class);

	private CourseRepository courseRepo;

	public CourseService(CourseRepository courseRepo) {
		super();
		this.courseRepo = courseRepo;
	}

	public ResponseEntity<?> getAllCourses() {
		return new ResponseEntity<List<Course>>(courseRepo.findAll(), HttpStatus.OK);
	}

	public ResponseEntity<?> getCourseById(@PathVariable int courseId) {
		Optional<Course> optCourse = courseRepo.findByCourseId(courseId);
		if (optCourse.isPresent()) {
			Course course = optCourse.get();
			return new ResponseEntity<Course>(course, HttpStatus.OK);
		} else {
			LOGGER.error("--- No matching course found!! ---");
			throw new CourseNotFoundException("No matching course found!!");
		}
	}

	public ResponseEntity<?> createCourse(@Valid CourseDto course) {
		try {
			Course crs = new Course();
			crs.setDescription(course.getDescription());
			crs.setFees(course.getFees());
			crs.setDescription(course.getDescription());
			crs.setTitle(course.getTitle());
			crs.setTrainer(course.getTrainer());
			crs.setStartDate(course.getStartDate());
			courseRepo.save(crs);
			
			ResponseMessage response = new ResponseMessage();
			response.setMessage("Course created successfully");
			
			return new ResponseEntity<ResponseMessage>(response, HttpStatus.CREATED);
		} catch (Exception ex) {
			LOGGER.error("--- Course Creation Failed!! ---");
			throw new CreateCourseException("Course Creation Failed");
		}
	}
}
