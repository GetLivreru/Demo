package com.example.demo;

import com.example.demo.controller.MeasurementController;
import com.example.demo.dto.MeasurementDTO;
import com.example.demo.service.MeasurementService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MeasurementControllerTest {

    @Mock
    private MeasurementService measurementService;

    @InjectMocks
    private MeasurementController measurementController;

    public MeasurementControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddMeasurement() {
        // Arrange
        MeasurementDTO measurementDTO = new MeasurementDTO();
        measurementDTO.setValue(25.0);
        measurementDTO.setRaining(false);

        doNothing().when(measurementService).addMeasurement(measurementDTO);

        // Act
        ResponseEntity<String> response = measurementController.addMeasurement(measurementDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Measurement added successfully.", response.getBody());

        verify(measurementService).addMeasurement(measurementDTO);
    }
}