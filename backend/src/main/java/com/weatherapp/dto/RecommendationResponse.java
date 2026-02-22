package com.weatherapp.dto;

import java.util.List;

public record RecommendationResponse(
        String recommendation,
        String why,
        List<String> safetyFlags
) {}
