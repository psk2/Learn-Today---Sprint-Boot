package com.learntoday.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.learntoday.entity.Student;
import com.learntoday.exceptions.StudentEnrollmentFailedException;
import com.learntoday.exceptions.StudentNotFoundException;
import com.learntoday.model.ResponseMessage;
import com.learntoday.repository.StudentRepository;

@Service
public class StudentService {
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentService.class);
	
	private StudentRepository studentRepo;

	public StudentService(StudentRepository studentRepo) {
		super();
		this.studentRepo = studentRepo;
	}

	public ResponseEntity<ResponseMessage> enrollCourse(Student student) {
		try {
			studentRepo.save(student);

			ResponseMessage response = new ResponseMessage();
			response.setMessage("Student Enrollment done successfully");
			
			return new ResponseEntity<ResponseMessage>(response, HttpStatus.CREATED);
		} catch (Exception ex) {
			LOGGER.error("--- Student Enrollment Failed. ---");
			throw new StudentEnrollmentFailedException("Student Enrollment Failed..");
		}
	}

	public ResponseEntity<?> deleteStudentEnrollment(int eid) {
		Optional<Student> optStudent = studentRepo.findByEnrollmentId(eid);

		if (optStudent.isPresent()) {
			studentRepo.deleteByEnrollmentId(eid);
			
			ResponseMessage response = new ResponseMessage();
			response.setMessage("Course created successfully");
			
			return new ResponseEntity<ResponseMessage>(response, HttpStatus.CREATED);
		} else {
			LOGGER.error("--- No matching student found!! ---");
			throw new StudentNotFoundException("No Matching Student Found for deletion");
		}

		
		
	}
}
