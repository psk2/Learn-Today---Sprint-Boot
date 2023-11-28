package com.learntoday.DTO;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
	
	@Min(value = 1, message="CourseId is required")
	private int courseId;

	@Min(value = 1, message="EnrollmentId is required")
	private int enrollmentId;

}
