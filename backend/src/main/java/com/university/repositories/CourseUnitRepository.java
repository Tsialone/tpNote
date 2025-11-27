package com.university.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.university.models.CourseUnit;
import java.util.Optional;

public interface CourseUnitRepository extends JpaRepository<CourseUnit, Long> {
    Optional<CourseUnit> findByCode(String code);
}