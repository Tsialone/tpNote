package com.university.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.university.models.StudentCourseUnitScore;

public interface StudentCourseUnitScoreRepository extends JpaRepository<StudentCourseUnitScore, Long> {}