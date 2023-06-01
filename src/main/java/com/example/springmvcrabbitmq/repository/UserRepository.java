package com.example.springmvcrabbitmq.repository;

import com.example.springmvcrabbitmq.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
