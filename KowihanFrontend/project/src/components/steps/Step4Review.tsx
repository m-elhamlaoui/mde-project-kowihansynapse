import { Database, Sparkles, Check } from 'lucide-react';

interface ReviewData {
  dataModel: string;
  database: {
    type: string;
    host?: string;
    database: string;
  };
  features: string[];
}

interface Step4ReviewProps {
  isDark: boolean;
  data: ReviewData;
  onGenerate: () => void;
  onBack: () => void;
}

export default function Step4Review({ isDark, data, onGenerate, onBack }: Step4ReviewProps) {
  return (
    <div className="space-y-8">
      <div className="text-center space-y-2">
        <h2
          className={`text-4xl font-black ${isDark ? 'text-white' : 'text-[#1A0B2E]'}`}
          style={{ fontFamily: 'Orbitron, monospace' }}
        >
          Synthesize Blueprint
        </h2>
        <p className={`text-lg ${isDark ? 'text-[#E0B0FF]' : 'text-[#7B2D8A]'}`}>
          Review your configuration before generation
        </p>
      </div>

      <div className="max-w-6xl mx-auto">
        <div className="relative h-[500px] rounded-3xl overflow-hidden">
          <div
            className={`absolute inset-0 rounded-3xl p-8 ${
              isDark ? 'glass-dark' : 'glass-light'
            }`}
          >
            <div className="h-full flex items-center justify-center">
              <svg viewBox="0 0 800 400" className="w-full h-full">
                <g>
                  <circle
                    cx="400"
                    cy="200"
                    r="60"
                    fill={isDark ? 'url(#core-gradient-dark)' : 'url(#core-gradient-light)'}
                    className="animate-breathe"
                  />
                  <text
                    x="400"
                    y="210"
                    textAnchor="middle"
                    fill="white"
                    fontSize="20"
                    fontWeight="bold"
                  >
                    API Core
                  </text>
                </g>

                <g>
                  <circle
                    cx="250"
                    cy="100"
                    r="50"
                    fill={isDark ? 'url(#data-gradient-dark)' : 'url(#data-gradient-light)'}
                    className="animate-float"
                  />
                  <text
                    x="250"
                    y="105"
                    textAnchor="middle"
                    fill="white"
                    fontSize="16"
                    fontWeight="bold"
                  >
                    Data
                  </text>
                  <line
                    x1="250"
                    y1="150"
                    x2="360"
                    y2="180"
                    stroke={isDark ? '#FF1B8D' : '#FF006E'}
                    strokeWidth="3"
                    className="animate-pulse-neon"
                  />
                </g>

                <g>
                  <circle
                    cx="550"
                    cy="100"
                    r="50"
                    fill={isDark ? 'url(#db-gradient-dark)' : 'url(#db-gradient-light)'}
                    className="animate-float"
                    style={{ animationDelay: '0.5s' }}
                  />
                  <text
                    x="550"
                    y="105"
                    textAnchor="middle"
                    fill="white"
                    fontSize="16"
                    fontWeight="bold"
                  >
                    Database
                  </text>
                  <line
                    x1="550"
                    y1="150"
                    x2="440"
                    y2="180"
                    stroke={isDark ? '#00FFFF' : '#00F5FF'}
                    strokeWidth="3"
                    className="animate-pulse-neon"
                    style={{ animationDelay: '0.3s' }}
                  />
                </g>

                {data.features.length > 0 && (
                  <g>
                    <circle
                      cx="400"
                      cy="320"
                      r="50"
                      fill={isDark ? 'url(#features-gradient-dark)' : 'url(#features-gradient-light)'}
                      className="animate-float"
                      style={{ animationDelay: '1s' }}
                    />
                    <text
                      x="400"
                      y="325"
                      textAnchor="middle"
                      fill="white"
                      fontSize="16"
                      fontWeight="bold"
                    >
                      Features
                    </text>
                    <line
                      x1="400"
                      y1="260"
                      x2="400"
                      y2="270"
                      stroke={isDark ? '#FFD000' : '#FFB800'}
                      strokeWidth="3"
                      className="animate-pulse-neon"
                      style={{ animationDelay: '0.6s' }}
                    />
                  </g>
                )}

                <defs>
                  <linearGradient id="core-gradient-dark" x1="0%" y1="0%" x2="100%" y2="100%">
                    <stop offset="0%" stopColor="#FF1B8D" />
                    <stop offset="100%" stopColor="#8B00FF" />
                  </linearGradient>
                  <linearGradient id="core-gradient-light" x1="0%" y1="0%" x2="100%" y2="100%">
                    <stop offset="0%" stopColor="#FF006E" />
                    <stop offset="100%" stopColor="#8B00FF" />
                  </linearGradient>

                  <linearGradient id="data-gradient-dark" x1="0%" y1="0%" x2="100%" y2="100%">
                    <stop offset="0%" stopColor="#8B00FF" />
                    <stop offset="100%" stopColor="#00FFFF" />
                  </linearGradient>
                  <linearGradient id="data-gradient-light" x1="0%" y1="0%" x2="100%" y2="100%">
                    <stop offset="0%" stopColor="#8B00FF" />
                    <stop offset="100%" stopColor="#00F5FF" />
                  </linearGradient>

                  <linearGradient id="db-gradient-dark" x1="0%" y1="0%" x2="100%" y2="100%">
                    <stop offset="0%" stopColor="#00FFFF" />
                    <stop offset="100%" stopColor="#FFD000" />
                  </linearGradient>
                  <linearGradient id="db-gradient-light" x1="0%" y1="0%" x2="100%" y2="100%">
                    <stop offset="0%" stopColor="#00F5FF" />
                    <stop offset="100%" stopColor="#FFB800" />
                  </linearGradient>

                  <linearGradient id="features-gradient-dark" x1="0%" y1="0%" x2="100%" y2="100%">
                    <stop offset="0%" stopColor="#FFD000" />
                    <stop offset="100%" stopColor="#FF1B8D" />
                  </linearGradient>
                  <linearGradient id="features-gradient-light" x1="0%" y1="0%" x2="100%" y2="100%">
                    <stop offset="0%" stopColor="#FFB800" />
                    <stop offset="100%" stopColor="#FF006E" />
                  </linearGradient>
                </defs>
              </svg>
            </div>
          </div>
        </div>

        <div className="grid grid-cols-1 md:grid-cols-3 gap-6 mt-8">
          <div
            className={`p-6 rounded-3xl transition-smooth hover:scale-105 ${
              isDark ? 'glass-dark' : 'glass-light'
            }`}
          >
            <div className="flex items-center space-x-3 mb-4">
              <Sparkles className={isDark ? 'text-[#FF1B8D]' : 'text-[#FF006E]'} />
              <h3 className={`font-bold text-lg ${isDark ? 'text-white' : 'text-[#1A0B2E]'}`}>
                Data Model
              </h3>
            </div>
            <p className={`text-sm ${isDark ? 'text-[#E0B0FF]' : 'text-[#7B2D8A]'}`}>
              {data.dataModel.substring(0, 120)}...
            </p>
          </div>

          <div
            className={`p-6 rounded-3xl transition-smooth hover:scale-105 ${
              isDark ? 'glass-dark' : 'glass-light'
            }`}
          >
            <div className="flex items-center space-x-3 mb-4">
              <Database className={isDark ? 'text-[#00FFFF]' : 'text-[#00F5FF]'} />
              <h3 className={`font-bold text-lg ${isDark ? 'text-white' : 'text-[#1A0B2E]'}`}>
                Database
              </h3>
            </div>
            <div className="space-y-2">
              <p className={`text-sm font-semibold ${isDark ? 'text-white' : 'text-[#1A0B2E]'}`}>
                {data.database.type.toUpperCase()}
              </p>
              {data.database.host && (
                <p className={`text-sm ${isDark ? 'text-[#E0B0FF]' : 'text-[#7B2D8A]'}`}>
                  {data.database.host}
                </p>
              )}
              <p className={`text-sm ${isDark ? 'text-[#E0B0FF]' : 'text-[#7B2D8A]'}`}>
                {data.database.database}
              </p>
            </div>
          </div>

          <div
            className={`p-6 rounded-3xl transition-smooth hover:scale-105 ${
              isDark ? 'glass-dark' : 'glass-light'
            }`}
          >
            <div className="flex items-center space-x-3 mb-4">
              <Check className={isDark ? 'text-[#FFD000]' : 'text-[#FFB800]'} />
              <h3 className={`font-bold text-lg ${isDark ? 'text-white' : 'text-[#1A0B2E]'}`}>
                Features
              </h3>
            </div>
            {data.features.length > 0 ? (
              <div className="space-y-2">
                {data.features.slice(0, 3).map((feature) => (
                  <p
                    key={feature}
                    className={`text-sm ${isDark ? 'text-[#E0B0FF]' : 'text-[#7B2D8A]'}`}
                  >
                    {feature}
                  </p>
                ))}
                {data.features.length > 3 && (
                  <p className={`text-sm font-semibold ${isDark ? 'text-[#FFD000]' : 'text-[#FFB800]'}`}>
                    +{data.features.length - 3} more
                  </p>
                )}
              </div>
            ) : (
              <p className={`text-sm ${isDark ? 'text-[#E0B0FF]' : 'text-[#7B2D8A]'}`}>
                No additional features
              </p>
            )}
          </div>
        </div>

        <div className="flex space-x-4 mt-8">
          <button
            onClick={onBack}
            className={`flex-1 py-4 rounded-2xl font-bold text-lg transition-smooth ${
              isDark
                ? 'bg-[#2D1B4E] text-[#E0B0FF] border-2 border-[#FF1B8D33] hover:border-[#FF1B8D]'
                : 'bg-white text-[#7B2D8A] border-2 border-[#FFD6E8] hover:border-[#FF006E]'
            }`}
          >
            Back
          </button>
          <button
            onClick={onGenerate}
            className={`flex-1 py-4 rounded-2xl font-bold text-lg transition-smooth hover:scale-105 animate-gradient ${
              isDark
                ? 'bg-gradient-to-r from-[#FF1B8D] via-[#8B00FF] to-[#00FFFF] text-white glow-intense'
                : 'bg-gradient-to-r from-[#FF006E] via-[#8B00FF] to-[#00F5FF] text-white glow-multi'
            }`}
            style={{ backgroundSize: '200% 200%' }}
          >
            Begin Synthesis
          </button>
        </div>
      </div>
    </div>
  );
}