import { useState } from 'react';
import { Code, ArrowLeft, CheckCircle } from 'lucide-react';

interface Step3Props {
  isDark: boolean;
  onNext: (framework: string) => void;
  onBack: () => void;
}

const frameworks = [
  {
    name: 'Django',
    description: 'The web framework for perfectionists with deadlines',
    icon: '🎸',
    features: ['Batteries included', 'Admin panel', 'ORM', 'Security'],
    bestFor: 'Full-featured applications, rapid development',
    popularity: 'Most popular',
  },
  {
    name: 'Flask',
    description: 'Lightweight and flexible micro-framework',
    icon: '🌶️',
    features: ['Minimalist', 'Flexible', 'Extensible', 'Easy to learn'],
    bestFor: 'Simple APIs, microservices, prototypes',
    popularity: 'Very popular',
  },
];

export default function Step3FrameworkIA({ isDark, onNext, onBack }: Step3Props) {
  const [selected, setSelected] = useState<string>('');

  return (
    <div className="space-y-8">
      <div>
        <div className="flex items-center gap-3 mb-4">
          <Code className={`w-10 h-10 ${isDark ? 'text-cyan-400' : 'text-cyan-600'}`} />
          <h2 className={`text-4xl font-bold ${isDark ? 'text-white' : 'text-gray-900'}`}>
            🐍 Choose Your Framework
          </h2>
        </div>
        <p className={`text-lg ${isDark ? 'text-cyan-200' : 'text-cyan-700'}`}>
          Select the Python framework for your API
        </p>
      </div>

      {/* Framework Options */}
      <div className="grid gap-4">
        {frameworks.map((fw) => (
          <button
            key={fw.name}
            onClick={() => setSelected(fw.name)}
            className={`text-left p-6 rounded-xl transition-all ${
              selected === fw.name
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
                <span className="text-4xl">{fw.icon}</span>
                <div>
                  <div className="flex items-center gap-2">
                    <h3 className={`text-xl font-bold ${isDark ? 'text-white' : 'text-gray-900'}`}>
                      {fw.name}
                    </h3>
                    <span className={`px-2 py-1 rounded text-xs font-bold ${
                      isDark ? 'bg-green-500/20 text-green-400' : 'bg-green-100 text-green-700'
                    }`}>
                      {fw.popularity}
                    </span>
                  </div>
                  <p className={`text-sm ${isDark ? 'text-gray-400' : 'text-gray-600'}`}>
                    {fw.description}
                  </p>
                </div>
              </div>
              {selected === fw.name && (
                <CheckCircle className="w-6 h-6 text-cyan-500" />
              )}
            </div>

            <div className="flex flex-wrap gap-2 mb-3">
              {fw.features.map((feature, idx) => (
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
              <strong>Best for:</strong> {fw.bestFor}
            </p>
          </button>
        ))}
      </div>

      {/* Buttons */}
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
          Next: Entities →
        </button>
      </div>
    </div>
  );
}
