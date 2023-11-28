package com.learntoday.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trainer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int trainerId;
	private String name;
	private String password;
	private String roles;
}
