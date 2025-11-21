package com.s5.rallye.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.s5.rallye.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
