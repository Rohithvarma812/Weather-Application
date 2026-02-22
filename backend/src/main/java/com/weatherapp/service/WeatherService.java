package com.weatherapp.service;

import com.weatherapp.dto.WeatherResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class WeatherService {
    private final WebClient webClient;
    private final String apiKey;

    public WeatherService(WebClient.Builder builder, @Value("${weather.api.base-url}") String baseUrl,
                          @Value("${weather.api.key}") String apiKey) {
        this.webClient = builder.baseUrl(baseUrl).build();
        this.apiKey = apiKey;
    }

    @Cacheable(cacheNames = "weather", key = "#lat + ':' + #lon")
    public Mono<WeatherResponse> getWeather(double lat, double lon) {
        return webClient.get()
                .uri(uri -> uri.path("/data/2.5/weather")
                        .queryParam("lat", lat)
                        .queryParam("lon", lon)
                        .queryParam("appid", apiKey)
                        .queryParam("units", "metric")
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Map.class)
                .map(this::toResponse);
    }

    @SuppressWarnings("unchecked")
    private WeatherResponse toResponse(Map<String, Object> payload) {
        var main = (Map<String, Object>) payload.get("main");
        var wind = (Map<String, Object>) payload.get("wind");
        var rainObj = (Map<String, Object>) payload.getOrDefault("rain", Map.of("1h", 0.0));
        String location = String.valueOf(payload.getOrDefault("name", "Unknown area"));
        double temp = ((Number) main.getOrDefault("temp", 0)).doubleValue();
        int humidity = ((Number) main.getOrDefault("humidity", 0)).intValue();
        double windSpeed = ((Number) wind.getOrDefault("speed", 0)).doubleValue();
        double rain = ((Number) rainObj.getOrDefault("1h", 0)).doubleValue();
        int aqi = (int) Math.min(500, Math.max(1, 50 + humidity - (int) temp));
        List<String> alerts = new ArrayList<>();
        if (temp > 35) alerts.add("Heat stress risk");
        if (windSpeed > 12) alerts.add("High wind caution");
        if (rain > 5) alerts.add("Heavy rain likely");
        return new WeatherResponse(location, temp, humidity, windSpeed, rain, aqi, Instant.now(), alerts);
    }
}
