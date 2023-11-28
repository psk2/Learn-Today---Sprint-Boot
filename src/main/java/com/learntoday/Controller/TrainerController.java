package com.learntoday.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learntoday.DTO.TrainerDto;
import com.learntoday.entity.Trainer;
import com.learntoday.service.TrainerService;

@RestController
@RequestMapping("/trainer")
public class TrainerController {
	private TrainerService trainerService;

	public TrainerController(TrainerService trainerService) {
		super();
		this.trainerService = trainerService;
	}

	@PostMapping("/signUp")
	public ResponseEntity<?> signUp(@RequestBody TrainerDto trainer) {
		return trainerService.signUp(trainer);
	}
	
	@PutMapping("/updatePassword")
	public ResponseEntity<?> updatePassword(@RequestBody Trainer trainer) {
		return trainerService.updatePassword(trainer);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Trainer>> getTrainers() {
		return trainerService.getTrainers();
	}
}
