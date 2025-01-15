package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Measurement;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Long> {
    long countByRainingTrue();
}