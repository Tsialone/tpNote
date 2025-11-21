package com.university.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.university.models.University;

public interface UniversityRepository extends JpaRepository<University, Long> {}