package com.example.spring_first_app.repository;

import com.example.spring_first_app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByName(String username);

    Optional<User> findById(Integer id);
}
