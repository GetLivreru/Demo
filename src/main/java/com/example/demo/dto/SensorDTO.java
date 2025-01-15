package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;

public class SensorDTO {

    @NotBlank
    private String name;

    // Getter
    public String getName() {
        return name;
    }

    // Setter
    public void setName(String name) {
        this.name = name;
    }
}
