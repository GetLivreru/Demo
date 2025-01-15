package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Sensor;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {
    boolean existsByName(String name);
    Optional<Sensor> findByName(String name);
}
