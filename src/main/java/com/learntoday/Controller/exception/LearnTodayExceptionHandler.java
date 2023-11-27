package com.learntoday.Controller.exception;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.learntoday.exceptions.CourseNotFoundException;
import com.learntoday.exceptions.CreateCourseException;
import com.learntoday.exceptions.CreateTrainerException;
import com.learntoday.exceptions.StudentEnrollmentFailedException;
import com.learntoday.exceptions.StudentNotFoundException;
import com.learntoday.exceptions.TrainerNotFoundException;
import com.learntoday.model.ApiError;

@RestControllerAdvice
public class LearnTodayExceptionHandler {
	
	@ExceptionHandler(value = CourseNotFoundException.class)
	public ResponseEntity<?> handleCourseNotFoundException(Exception ex) {
		ApiError error = new ApiError();
		error.setType(ex.getClass().getName());
		error.setMessage(ex.getMessage());
		error.setTime(LocalDateTime.now());
		
		return new ResponseEntity<ApiError>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = TrainerNotFoundException.class)
	public ResponseEntity<?> handleTrainerNotFoundException(Exception ex) {
		ApiError error = new ApiError();
		error.setType(ex.getClass().getName());
		error.setMessage(ex.getMessage());
		error.setTime(LocalDateTime.now());
		
		return new ResponseEntity<ApiError>(error, HttpStatus.NOT_FOUND);
	}
	

	@ExceptionHandler(value = StudentNotFoundException.class)
	public ResponseEntity<?> handleStudentNotFoundException(Exception ex) {
		ApiError error = new ApiError();
		error.setType(ex.getClass().getName());
		error.setMessage(ex.getMessage());
		error.setTime(LocalDateTime.now());
		
		return new ResponseEntity<ApiError>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = CreateCourseException.class)
	public ResponseEntity<?> handleCreateCourseException(Exception ex) {
		ApiError error = new ApiError();
		error.setType(ex.getClass().getName());
		error.setMessage(ex.getMessage());
		error.setTime(LocalDateTime.now());
		
		return new ResponseEntity<ApiError>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = StudentEnrollmentFailedException.class)
	public ResponseEntity<?> handleStudentEnrollmentFailedException(Exception ex) {
		ApiError error = new ApiError();
		error.setType(ex.getClass().getName());
		error.setMessage(ex.getMessage());
		error.setTime(LocalDateTime.now());
		
		return new ResponseEntity<ApiError>(error, HttpStatus.BAD_REQUEST);
	}
	

	@ExceptionHandler(value = CreateTrainerException.class)
	public ResponseEntity<?> handleCreateTrainerException(Exception ex) {
		ApiError error = new ApiError();
		error.setType(ex.getClass().getName());
		error.setMessage(ex.getMessage());
		error.setTime(LocalDateTime.now());
		
		return new ResponseEntity<ApiError>(error, HttpStatus.BAD_REQUEST);
	}

		
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidationFailureException(MethodArgumentNotValidException ex) {
		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		
		List<String> errorMessages = fieldErrors.stream().map(error -> error.getField()+" - "+ error.getDefaultMessage())
														 .collect(Collectors.toList());
		return new ResponseEntity<List<String>>(errorMessages, HttpStatus.BAD_REQUEST);
	}
	
}
