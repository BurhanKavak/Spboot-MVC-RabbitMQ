package com.example.springmvcrabbitmq.repository;

import com.example.springmvcrabbitmq.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {
}
