package com.example.demo.controller;

import com.example.demo.dto.SensorDTO;
import com.example.demo.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final SensorService sensorService;

    @Autowired
    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @PostMapping("/registration")
    public ResponseEntity<String> registerSensor(@Valid @RequestBody SensorDTO sensorDTO) {
        try {
            // Call the registerSensor method
            sensorService.registerSensor(sensorDTO);
            return ResponseEntity.ok("Sensor registered successfully.");
        } catch (IllegalArgumentException e) {
            // Handle case where sensor already exists
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
