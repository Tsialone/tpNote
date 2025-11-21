package com.university.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.university.models.Student;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEtu(String etu);
}