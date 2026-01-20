import { useState } from 'react';
import { Database, ArrowLeft, CheckCircle } from 'lucide-react';

interface Step2Props {
  isDark: boolean;
  onNext: (database: string) => void;
  onBack: () => void;
}

const databases = [
  {
    name: 'PostgreSQL',
    description: 'Powerful, open source relational database',
    icon: '🐘',
    features: ['ACID compliant', 'JSON support', 'Full-text search'],
    bestFor: 'Complex queries, data integrity',
  },
  {
    name: 'MySQL',
    description: 'Popular open source relational database',
    icon: '🐬',
    features: ['Fast', 'Reliable', 'Wide adoption'],
    bestFor: 'Web applications, read-heavy workloads',
  },
  {
    name: 'SQLite',
    description: 'Lightweight, serverless database',
    icon: '🪶',
    features: ['Zero-config', 'Embedded', 'Self-contained'],
    bestFor: 'Development, small apps, mobile',
  },
  {
    name: 'Oracle',
    description: 'Enterprise-grade database system',
    icon: '🏛️',
    features: ['High performance', 'Scalable', 'Enterprise features'],
    bestFor: 'Large enterprises, mission-critical systems',
  },
];

export default function Step2DatabaseIA({ isDark, onNext, onBack }: Step2Props) {
  const [selected, setSelected] = useState<string>('');

  return (
    <div className="space-y-8">
      <div>
        <div className="flex items-center gap-3 mb-4">
          <Database className={`w-10 h-10 ${isDark ? 'text-cyan-400' : 'text-cyan-600'}`} />
          <h2 className={`text-4xl font-bold ${isDark ? 'text-white' : 'text-gray-900'}`}>
            💾 Choose Your Database
          </h2>
        </div>
        <p className={`text-lg ${isDark ? 'text-cyan-200' : 'text-cyan-700'}`}>
          Select the database that best fits your needs
        </p>
      </div>

      <div className="grid gap-4">
        {databases.map((db) => (
          <button
            key={db.name}
            onClick={() => setSelected(db.name)}
            className={`text-left p-6 rounded-xl transition-all ${
              selected === db.name
                ? isDark
                  ? 'bg-cyan-500/20 border-2 border-cyan-500'
                  : 'bg-cyan-100 border-2 border-cyan-500'
                : isDark
                ? 'bg-white/5 border-2 border-transparent hover:border-cyan-500/30'
                : 'bg-gray-50 border-2 border-transparent hover:border-cyan-300'
            }`}
          >
            <div className="flex items-start justify-between mb-3">
              <div className="flex items-center gap-3">
                <span className="text-4xl">{db.icon}</span>
                <div>
                  <h3 className={`text-xl font-bold ${isDark ? 'text-white' : 'text-gray-900'}`}>
                    {db.name}
                  </h3>
                  <p className={`text-sm ${isDark ? 'text-gray-400' : 'text-gray-600'}`}>
                    {db.description}
                  </p>
                </div>
              </div>
              {selected === db.name && (
                <CheckCircle className="w-6 h-6 text-cyan-500" />
              )}
            </div>

            <div className="flex flex-wrap gap-2 mb-3">
              {db.features.map((feature, idx) => (
                <span
                  key={idx}
                  className={`px-3 py-1 rounded-full text-xs font-medium ${
                    isDark
                      ? 'bg-cyan-500/20 text-cyan-300'
                      : 'bg-cyan-200 text-cyan-700'
                  }`}
                >
                  {feature}
                </span>
              ))}
            </div>

            <p className={`text-sm ${isDark ? 'text-gray-400' : 'text-gray-600'}`}>
              <strong>Best for:</strong> {db.bestFor}
            </p>
          </button>
        ))}
      </div>

      <div className="flex gap-4">
        <button
          onClick={onBack}
          className={`flex-1 py-4 rounded-xl font-semibold text-lg transition-all flex items-center justify-center gap-2 ${
            isDark
              ? 'bg-white/5 hover:bg-white/10 text-white'
              : 'bg-gray-100 hover:bg-gray-200 text-gray-900'
          }`}
        >
          <ArrowLeft className="w-5 h-5" />
          Back
        </button>
        <button
          onClick={() => onNext(selected)}
          disabled={!selected}
          className={`flex-1 py-4 rounded-xl font-semibold text-lg transition-all ${
            selected
              ? isDark
                ? 'bg-gradient-to-r from-cyan-600 to-blue-600 hover:from-cyan-500 hover:to-blue-500 text-white'
                : 'bg-gradient-to-r from-cyan-500 to-blue-500 hover:from-cyan-400 hover:to-blue-400 text-white'
              : 'bg-gray-500/20 text-gray-500 cursor-not-allowed'
          }`}
        >
          Next: Framework →
        </button>
      </div>
    </div>
  );
}
