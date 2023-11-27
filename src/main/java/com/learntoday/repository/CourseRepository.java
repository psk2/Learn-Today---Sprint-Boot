package com.learntoday.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learntoday.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Integer>{

	Optional<Course> findByCourseId(int courseId);

}
