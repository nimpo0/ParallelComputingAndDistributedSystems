package com.example.logistics_api.repository;

import com.example.logistics_api.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
    boolean existsByName(String name);
}