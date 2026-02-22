package com.weatherapp.dto;

import jakarta.validation.constraints.NotBlank;

public record DecisionRequest(
        @NotBlank String purpose,
        String healthCondition,
        double heatSensitivity,
        double pollutionSensitivity
) {}
