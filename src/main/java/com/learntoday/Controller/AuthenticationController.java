package com.learntoday.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learntoday.DTO.AuthRequest;
import com.learntoday.exceptions.TrainerNotFoundException;
import com.learntoday.model.AuthenticationResponse;
import com.learntoday.service.JwtService;

@RestController
@RequestMapping("/api")
public class AuthenticationController {
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

	@Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;
    
	@PostMapping("/authenticate")
    public AuthenticationResponse authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
		 LOGGER.info("--- Trainer : {}, {}", authRequest.getUsername(), authRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            String jwt = jwtService.generateToken(authRequest.getUsername());
            
            AuthenticationResponse ar = new AuthenticationResponse();
            ar.setMessage("Authentication Success");
            ar.setJwt(jwt);
            ar.setUsername(authRequest.getUsername());
            
            return ar;
        } else {
            throw new TrainerNotFoundException("invalid user request !");
        }


    }
}
