package com.learntoday.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learntoday.entity.Student;

import jakarta.transaction.Transactional;

public interface StudentRepository extends JpaRepository<Student, Integer>{

	@Transactional
	void deleteByEnrollmentId(int eid);

	Optional<Student> findByEnrollmentId(int eid);

}
