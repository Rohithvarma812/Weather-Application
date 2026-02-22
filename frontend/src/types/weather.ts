export type WeatherResponse = {
  location: string;
  temperature: number;
  humidity: number;
  windSpeed: number;
  rainMm: number;
  aqi: number;
  observedAt: string;
  alerts: string[];
};

export type RecommendationResponse = {
  recommendation: string;
  why: string;
  safetyFlags: string[];
};
