package com.weatherapp.dto;

import java.time.Instant;
import java.util.List;

public record WeatherResponse(
        String location,
        double temperature,
        int humidity,
        double windSpeed,
        double rainMm,
        int aqi,
        Instant observedAt,
        List<String> alerts
) {}
