package com.learntoday.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.learntoday.DTO.TrainerDto;
import com.learntoday.entity.Trainer;
import com.learntoday.exceptions.CreateTrainerException;
import com.learntoday.model.ResponseMessage;
import com.learntoday.repository.TrainerRepository;

@Service
public class TrainerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TrainerService.class);
	private TrainerRepository trainerRepo;

	public TrainerService(TrainerRepository trainerRepo) {
		super();
		this.trainerRepo = trainerRepo;
	}

	public ResponseEntity<?> signUp(TrainerDto trnr) {
		try {
			Trainer trainer = new Trainer();
			trainer.setName(trnr.getName());
			trainer.setPassword(trnr.getPassword());
			trainer.setRoles(trnr.getRoles());
			
			trainerRepo.save(trainer);
		} catch(Exception ex) {
			LOGGER.error("--- Trainer creation Failed!! ---");
			throw new CreateTrainerException("Trainer creation Failed!!");
		}
		

		ResponseMessage response = new ResponseMessage();
		response.setMessage("Trainer Registered successfully");
		
		return new ResponseEntity<ResponseMessage>(response, HttpStatus.CREATED);
	}

	public ResponseEntity<?> updatePassword(Trainer trainer) {
		Optional<Trainer> optTrainer = trainerRepo.getByTrainerId(trainer.getTrainerId());
		
		if (optTrainer.isPresent()) {
			Trainer trnr = optTrainer.get();
			trnr.setPassword(trainer.getPassword());
			trainerRepo.save(trnr);
		} else {
			LOGGER.error("--- No matching trainer found!! ---");
		}
		return new ResponseEntity<String>("Password updated successfully", HttpStatus.CREATED);
	}
	
	public ResponseEntity<List<Trainer>> getTrainers() {
		return new ResponseEntity<List<Trainer>>(trainerRepo.findAll(), HttpStatus.CREATED);
	}
}
