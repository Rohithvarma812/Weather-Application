package com.weatherapp.service;

import com.weatherapp.dto.DecisionRequest;
import com.weatherapp.dto.RecommendationResponse;
import com.weatherapp.dto.WeatherResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendationService {
    public RecommendationResponse generate(WeatherResponse weather, DecisionRequest request) {
        List<String> flags = new ArrayList<>(weather.alerts());
        String recommendation = "Good conditions for " + request.purpose();
        StringBuilder why = new StringBuilder("Based on temperature %.1f°C, humidity %d%%, wind %.1f m/s and rain %.1fmm."
                .formatted(weather.temperature(), weather.humidity(), weather.windSpeed(), weather.rainMm()));

        if (weather.rainMm() > 2) {
            recommendation = "Carry rain protection; consider delaying " + request.purpose();
            flags.add("Rain disruption");
        }
        if (weather.temperature() > request.heatSensitivity()) {
            recommendation = "Avoid peak afternoon hours for " + request.purpose();
            flags.add("Heat-sensitive warning");
            why.append(" Your custom heat threshold was exceeded.");
        }
        if (weather.aqi() > request.pollutionSensitivity()) {
            flags.add("Air quality concern");
            why.append(" Pollution index exceeds your configured tolerance.");
        }
        if ("asthma".equalsIgnoreCase(request.healthCondition()) && weather.humidity() > 80) {
            flags.add("Asthma trigger probability elevated");
            why.append(" High humidity can be an asthma trigger.");
        }
        return new RecommendationResponse(recommendation, why.toString(), flags);
    }
}
