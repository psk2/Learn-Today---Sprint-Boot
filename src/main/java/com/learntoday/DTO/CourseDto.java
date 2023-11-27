package com.learntoday.DTO;

import java.time.LocalDate;
import java.util.List;

import com.learntoday.entity.Student;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {

	@NotEmpty(message = "Title is Required")
	private String title;

	@Min(value = 100, message = "Minimum fee is 100")
	private float fees;

	private String description;

	@NotEmpty(message = "Trainer Name is Required")
	private String trainer;

	private LocalDate startDate;
	
	private List<Student> students;

}
