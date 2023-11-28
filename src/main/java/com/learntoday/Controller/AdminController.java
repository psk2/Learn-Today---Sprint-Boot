package com.learntoday.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learntoday.DTO.AuthRequest;
import com.learntoday.DTO.CourseDto;
import com.learntoday.exceptions.TrainerNotFoundException;
import com.learntoday.service.CourseService;
import com.learntoday.service.JwtService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/course")
public class AdminController {
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);
	
	private CourseService courseService;

	@Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;
	
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

	@PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
		 LOGGER.info("--- Trainer : {}, {}", authRequest.getUsername(), authRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new TrainerNotFoundException("invalid user request !");
        }


    }
	 
}
