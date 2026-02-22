export default function AlertPanel({ alerts }: { alerts: string[] }) {
  return (
    <div className="rounded-2xl bg-rose-900/60 p-4">
      <h3 className="font-semibold">Health & Severe Alerts</h3>
      <ul className="list-disc ml-6 mt-2 text-sm">
        {alerts.length ? alerts.map((a) => <li key={a}>{a}</li>) : <li>No active alerts</li>}
      </ul>
    </div>
  );
}
