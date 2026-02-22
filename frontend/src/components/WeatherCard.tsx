import { WeatherResponse } from '../types/weather';

export default function WeatherCard({ weather }: { weather: WeatherResponse }) {
  return (
    <div className="rounded-2xl bg-slate-800 p-5 shadow-lg">
      <h2 className="text-xl font-bold">{weather.location}</h2>
      <p className="text-4xl font-bold mt-2">{weather.temperature.toFixed(1)}°C</p>
      <div className="grid grid-cols-2 gap-2 mt-4 text-sm">
        <p>Humidity: {weather.humidity}%</p>
        <p>Wind: {weather.windSpeed} m/s</p>
        <p>Rain: {weather.rainMm} mm</p>
        <p>AQI: {weather.aqi}</p>
      </div>
    </div>
  );
}
