package com.learntoday.DTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainerDto {

	@NotEmpty
	private String password;
	@NotEmpty
	private String name;
	@NotEmpty
	private String roles;
}
