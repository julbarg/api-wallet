package com.leovegas.apiwallet.repository;

import com.leovegas.apiwallet.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
