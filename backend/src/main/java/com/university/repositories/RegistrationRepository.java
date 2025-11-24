package com.university.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.university.models.Registration;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {}
