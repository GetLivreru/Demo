package com.example.demo.service;

import com.example.demo.dto.SensorDTO;
import com.example.demo.entity.Sensor;
import com.example.demo.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SensorService {

    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    // Method to register a new sensor
    public void registerSensor(SensorDTO sensorDTO) {
        // Check if the sensor already exists by its name
        Optional<Sensor> existingSensor = sensorRepository.findByName(sensorDTO.getName());
        if (existingSensor.isPresent()) {
            throw new IllegalArgumentException("Sensor with this name already exists.");
        }

        // Create and save the new sensor
        Sensor sensor = new Sensor();
        sensor.setName(sensorDTO.getName());
        sensorRepository.save(sensor);
    }

    // Optional: Find sensor by name (for measurement logic)
    public Sensor findByName(String name) {
        Optional<Sensor> sensor = sensorRepository.findByName(name);
        if (sensor.isPresent()) {
            return sensor.get();
        } else {
            throw new IllegalArgumentException("Sensor not found with name: " + name);
        }
    }
}
