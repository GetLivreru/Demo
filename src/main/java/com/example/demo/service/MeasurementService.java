package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.MeasurementDTO;
import com.example.demo.entity.Measurement;
import com.example.demo.entity.Sensor;
import com.example.demo.repository.MeasurementRepository;

@Service
public class MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final SensorService sensorService;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository, SensorService sensorService) {
        this.measurementRepository = measurementRepository;
        this.sensorService = sensorService;
    }

    public Measurement addMeasurement(MeasurementDTO measurementDTO) {
        Sensor sensor = sensorService.findByName(measurementDTO.getSensor().getName());
        Measurement measurement = new Measurement();
        measurement.setValue(measurementDTO.getValue());
        measurement.setRaining(measurementDTO.getRaining());
        measurement.setSensor(sensor);
        return measurementRepository.save(measurement);
    }

    public List<Measurement> getAllMeasurements() {
        return measurementRepository.findAll();
    }

    public long getRainyDaysCount() {
        return measurementRepository.countByRainingTrue();
    }
}