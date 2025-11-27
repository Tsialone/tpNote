package com.university.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.university.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
