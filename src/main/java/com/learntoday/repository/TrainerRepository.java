package com.learntoday.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learntoday.entity.Trainer;

public interface TrainerRepository extends JpaRepository<Trainer, Integer>{

	Optional<Trainer> getByTrainerId(int trainerId);
	
	Optional<Trainer> findByName(String trainerName);

}
