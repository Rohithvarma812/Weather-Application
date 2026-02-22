import { useEffect, useState } from 'react';
import { api } from '../api/client';
import { RecommendationResponse, WeatherResponse } from '../types/weather';

export function useWeather(lat: number, lon: number) {
  const [weather, setWeather] = useState<WeatherResponse | null>(null);
  const [recommendation, setRecommendation] = useState<RecommendationResponse | null>(null);

  useEffect(() => {
    api.get('/weather/current', { params: { lat, lon } }).then((res) => setWeather(res.data));
  }, [lat, lon]);

  const askAssistant = async (purpose: string) => {
    const res = await api.post('/weather/assistant',
      { purpose, healthCondition: 'asthma', heatSensitivity: 33, pollutionSensitivity: 110 },
      { params: { lat, lon } }
    );
    setRecommendation(res.data);
  };

  return { weather, recommendation, askAssistant };
}
