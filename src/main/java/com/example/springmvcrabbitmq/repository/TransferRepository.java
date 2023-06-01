package com.example.springmvcrabbitmq.repository;

import com.example.springmvcrabbitmq.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfer,Long> {
}
