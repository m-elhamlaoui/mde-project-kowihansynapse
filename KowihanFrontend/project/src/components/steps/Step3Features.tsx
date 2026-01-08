import { useState } from 'react';
import { Shield, Package, Gauge, Lock, Mail, FileText, Sparkles } from 'lucide-react';

interface Feature {
  id: string;
  name: string;
  description: string;
  icon: React.ComponentType<{ className?: string }>;
  position: { x: number; y: number };
}

interface Step3FeaturesProps {
  isDark: boolean;
  onNext: (features: string[]) => void;
  onBack: () => void;
}

const features: Feature[] = [
  {
    id: 'jwt',
    name: 'JWT Authentication',
    description: 'Secure token-based authentication',
    icon: Shield,
    position: { x: 20, y: 30 },
  },
  {
    id: 'docker',
    name: 'Dockerization',
    description: 'Container-ready deployment',
    icon: Package,
    position: { x: 70, y: 20 },
  },
  {
    id: 'rate-limit',
    name: 'API Rate Limiting',
    description: 'Protect against abuse',
    icon: Gauge,
    position: { x: 50, y: 70 },
  },
  {
    id: 'encryption',
    name: 'Data Encryption',
    description: 'Encrypt sensitive data',
    icon: Lock,
    position: { x: 15, y: 80 },
  },
  {
    id: 'email',
    name: 'Email Integration',
    description: 'Send transactional emails',
    icon: Mail,
    position: { x: 80, y: 75 },
  },
  {
    id: 'docs',
    name: 'API Documentation',
    description: 'Auto-generated docs',
    icon: FileText,
    position: { x: 45, y: 15 },
  },
];

export default function Step3Features({ isDark, onNext, onBack }: Step3FeaturesProps) {
  const [selectedFeatures, setSelectedFeatures] = useState<string[]>([]);

  const toggleFeature = (featureId: string) => {
    setSelectedFeatures((prev) =>
      prev.includes(featureId) ? prev.filter((id) => id !== featureId) : [...prev, featureId]
    );
  };

  const handleSubmit = () => {
    onNext(selectedFeatures);
  };

  return (
    <div className="space-y-8">
      <div className="text-center space-y-2">
        <h2
          className={`text-4xl font-black ${isDark ? 'text-white' : 'text-[#1A0B2E]'}`}
          style={{ fontFamily: 'Orbitron, monospace' }}
        >
          Evolve Capabilities
        </h2>
        <p className={`text-lg ${isDark ? 'text-[#E0B0FF]' : 'text-[#7B2D8A]'}`}>
          Select the features to integrate into your API
        </p>
      </div>

      <div className="max-w-6xl mx-auto">
        <div className="relative h-[600px] rounded-3xl overflow-hidden" style={{
          background: isDark
            ? 'radial-gradient(circle at 50% 50%, rgba(139, 0, 255, 0.1) 0%, transparent 70%)'
            : 'radial-gradient(circle at 50% 50%, rgba(255, 0, 110, 0.05) 0%, transparent 70%)',
        }}>
          <div
            className={`absolute inset-0 rounded-3xl ${
              isDark ? 'glass-dark' : 'glass-light'
            }`}
          />

          <svg className="absolute inset-0 w-full h-full pointer-events-none">
            {selectedFeatures.length > 0 && (
              <g>
                {features
                  .filter((f) => selectedFeatures.includes(f.id))
                  .map((feature) => (
                    <line
                      key={feature.id}
                      x1="50%"
                      y1="50%"
                      x2={`${feature.position.x}%`}
                      y2={`${feature.position.y}%`}
                      stroke={isDark ? 'url(#gradient-dark)' : 'url(#gradient-light)'}
                      strokeWidth="2"
                      className="animate-pulse-neon"
                    />
                  ))}
                <defs>
                  <linearGradient id="gradient-dark" x1="0%" y1="0%" x2="100%" y2="100%">
                    <stop offset="0%" stopColor="#FF1B8D" stopOpacity="0.8" />
                    <stop offset="50%" stopColor="#8B00FF" stopOpacity="0.6" />
                    <stop offset="100%" stopColor="#00FFFF" stopOpacity="0.8" />
                  </linearGradient>
                  <linearGradient id="gradient-light" x1="0%" y1="0%" x2="100%" y2="100%">
                    <stop offset="0%" stopColor="#FF006E" stopOpacity="0.6" />
                    <stop offset="50%" stopColor="#8B00FF" stopOpacity="0.4" />
                    <stop offset="100%" stopColor="#00F5FF" stopOpacity="0.6" />
                  </linearGradient>
                </defs>
              </g>
            )}
          </svg>

          <div
            className={`absolute top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 w-24 h-24 rounded-full flex items-center justify-center ${
              selectedFeatures.length > 0
                ? isDark
                  ? 'bg-gradient-to-br from-[#FF1B8D] via-[#8B00FF] to-[#00FFFF] animate-neural-pulse'
                  : 'bg-gradient-to-br from-[#FF006E] via-[#8B00FF] to-[#00F5FF] animate-neural-pulse'
                : isDark
                ? 'bg-[#2D1B4E] border-2 border-[#FF1B8D33]'
                : 'bg-white border-2 border-[#FFD6E8]'
            }`}
          >
            <Sparkles className={`w-12 h-12 ${selectedFeatures.length > 0 ? 'text-white' : isDark ? 'text-[#E0B0FF]' : 'text-[#7B2D8A]'}`} />
          </div>

          {features.map((feature) => {
            const Icon = feature.icon;
            const isSelected = selectedFeatures.includes(feature.id);

            return (
              <button
                key={feature.id}
                onClick={() => toggleFeature(feature.id)}
                className={`absolute p-6 rounded-3xl transition-smooth hover:scale-110 group ${
                  isSelected
                    ? isDark
                      ? 'bg-gradient-to-br from-[#FF1B8D] to-[#8B00FF] glow-intense'
                      : 'bg-gradient-to-br from-[#FF006E] to-[#8B00FF] glow-pink'
                    : isDark
                    ? 'bg-[#2D1B4E] border-2 border-[#FF1B8D33] hover:border-[#FF1B8D]'
                    : 'bg-white border-2 border-[#FFD6E8] hover:border-[#FF006E]'
                }`}
                style={{
                  left: `${feature.position.x}%`,
                  top: `${feature.position.y}%`,
                  transform: 'translate(-50%, -50%)',
                }}
              >
                <div className="flex flex-col items-center space-y-2 min-w-[120px]">
                  <Icon
                    className={`w-8 h-8 ${
                      isSelected ? 'text-white' : isDark ? 'text-[#E0B0FF]' : 'text-[#7B2D8A]'
                    }`}
                  />
                  <span
                    className={`font-bold text-sm text-center ${
                      isSelected ? 'text-white' : isDark ? 'text-white' : 'text-[#1A0B2E]'
                    }`}
                  >
                    {feature.name}
                  </span>
                </div>

                <div
                  className={`absolute bottom-full left-1/2 -translate-x-1/2 mb-4 px-4 py-2 rounded-xl opacity-0 group-hover:opacity-100 transition-smooth pointer-events-none whitespace-nowrap ${
                    isDark ? 'glass-dark' : 'glass-light'
                  }`}
                >
                  <p className={`text-sm ${isDark ? 'text-[#E0B0FF]' : 'text-[#7B2D8A]'}`}>
                    {feature.description}
                  </p>
                </div>
              </button>
            );
          })}
        </div>

        {selectedFeatures.length > 0 && (
          <div className="text-center mt-6">
            <p
              className={`text-lg font-semibold ${
                isDark ? 'text-[#E0B0FF]' : 'text-[#7B2D8A]'
              }`}
            >
              {selectedFeatures.length} feature{selectedFeatures.length !== 1 ? 's' : ''} selected
            </p>
          </div>
        )}

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
            onClick={handleSubmit}
            className={`flex-1 py-4 rounded-2xl font-bold text-lg transition-smooth ${
              isDark
                ? 'bg-gradient-to-r from-[#FF1B8D] via-[#8B00FF] to-[#00FFFF] text-white glow-intense hover:scale-105 animate-gradient'
                : 'bg-gradient-to-r from-[#FF006E] via-[#8B00FF] to-[#00F5FF] text-white glow-multi hover:scale-105 animate-gradient'
            }`}
            style={{ backgroundSize: '200% 200%' }}
          >
            {selectedFeatures.length > 0 ? 'Review Synthesis' : 'Skip to Review'}
          </button>
        </div>
      </div>
    </div>
  );
}