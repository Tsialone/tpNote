package com.university.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.university.models.AcademicYear;

public interface AcademicYearRepository extends JpaRepository<AcademicYear, Long> {}
