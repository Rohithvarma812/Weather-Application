package com.weatherapp.controller;

import com.weatherapp.dto.DecisionRequest;
import com.weatherapp.dto.RecommendationResponse;
import com.weatherapp.dto.WeatherResponse;
import com.weatherapp.service.RecommendationService;
import com.weatherapp.service.WeatherService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {
    private final WeatherService weatherService;
    private final RecommendationService recommendationService;

    public WeatherController(WeatherService weatherService, RecommendationService recommendationService) {
        this.weatherService = weatherService;
        this.recommendationService = recommendationService;
    }

    @GetMapping("/current")
    public Mono<WeatherResponse> current(@RequestParam double lat, @RequestParam double lon) {
        return weatherService.getWeather(lat, lon);
    }

    @PostMapping("/assistant")
    public Mono<RecommendationResponse> assistant(@RequestParam double lat, @RequestParam double lon,
                                                  @RequestBody @Valid DecisionRequest request) {
        return weatherService.getWeather(lat, lon)
                .map(weather -> recommendationService.generate(weather, request));
    }
}
