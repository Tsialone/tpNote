package com.university.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.university.models.StudyPeriod;

public interface StudyPeriodRepository extends JpaRepository<StudyPeriod, Long> {}
