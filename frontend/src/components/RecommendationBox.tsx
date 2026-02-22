import { RecommendationResponse } from '../types/weather';

export default function RecommendationBox({ recommendation }: { recommendation: RecommendationResponse | null }) {
  if (!recommendation) return null;
  return (
    <div className="rounded-2xl bg-emerald-900/60 p-4">
      <h3 className="font-semibold">AI Decision Assistant</h3>
      <p className="mt-2">{recommendation.recommendation}</p>
      <p className="mt-2 text-sm opacity-90"><strong>Why this recommendation?</strong> {recommendation.why}</p>
    </div>
  );
}
