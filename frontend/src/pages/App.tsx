import { useMemo, useState } from 'react';
import WeatherCard from '../components/WeatherCard';
import AlertPanel from '../components/AlertPanel';
import RecommendationBox from '../components/RecommendationBox';
import { useWeather } from '../hooks/useWeather';

export default function App() {
  const [dark, setDark] = useState(true);
  const [purpose, setPurpose] = useState('commute');
  const [coords] = useState({ lat: 12.9716, lon: 77.5946 });
  const { weather, recommendation, askAssistant } = useWeather(coords.lat, coords.lon);

  const rootClass = useMemo(() => dark ? 'dark bg-slate-900 text-white min-h-screen' : 'bg-slate-100 text-slate-900 min-h-screen', [dark]);

  return (
    <main className={rootClass}>
      <div className="max-w-5xl mx-auto px-4 py-6 space-y-4">
        <header className="flex items-center justify-between">
          <h1 className="text-2xl font-bold">Hyper-Local Weather Intelligence</h1>
          <button onClick={() => setDark(!dark)} className="rounded-lg border px-3 py-2">{dark ? 'Light' : 'Dark'} Mode</button>
        </header>

        {weather && <WeatherCard weather={weather} />}
        <div className="grid md:grid-cols-2 gap-4">
          <AlertPanel alerts={weather?.alerts ?? []} />
          <section className="rounded-2xl bg-slate-800 p-4">
            <h3 className="font-semibold">Weather-Driven Decision Assistant</h3>
            <input className="mt-3 w-full rounded-lg bg-slate-700 p-2" value={purpose} onChange={(e) => setPurpose(e.target.value)} />
            <button onClick={() => askAssistant(purpose)} className="mt-3 rounded-lg bg-cyan-600 px-4 py-2">Generate Recommendation</button>
            <RecommendationBox recommendation={recommendation} />
          </section>
        </div>
      </div>
    </main>
  );
}
