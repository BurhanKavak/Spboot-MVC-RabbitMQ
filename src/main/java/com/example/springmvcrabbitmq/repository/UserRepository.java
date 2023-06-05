package com.example.springmvcrabbitmq.repository;

import com.example.springmvcrabbitmq.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByNameAndEmail(String name, String email);

    Optional<User> findByAccounts_Id(Long accountId);
}
