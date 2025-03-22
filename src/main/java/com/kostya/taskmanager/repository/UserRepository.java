package com.kostya.taskmanager.repository;

import com.kostya.taskmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<Object> findByEmail(String email);
    Optional<Object> findByUsername(String username);
}
