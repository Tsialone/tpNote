package com.university.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.university.models.ExamSession;

public interface ExamSessionRepository extends JpaRepository<ExamSession, Long> {}
