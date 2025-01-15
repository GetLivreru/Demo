package com.example.demo;

import com.example.demo.controller.SensorController;
import com.example.demo.dto.SensorDTO;
import com.example.demo.service.SensorService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
 
public class SensorControllerTest {

    @Mock
    private SensorService sensorService;

    @InjectMocks
    private SensorController sensorController;

    public SensorControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterSensor() {
        // Arrange
        SensorDTO sensorDTO = new SensorDTO();
        sensorDTO.setName("Temperature Sensor");

        doNothing().when(sensorService).registerSensor(sensorDTO);

        // Act
        ResponseEntity<String> response = sensorController.registerSensor(sensorDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Sensor registered successfully.", response.getBody());

        verify(sensorService).registerSensor(sensorDTO);
    }
}