import { useState } from 'react';
import { Zap } from 'lucide-react';
import NeuralBackground from './NeuralBackground';

interface LandingPageProps {
  isDark: boolean;
  onSubmit: (idea: string) => void;
}

export default function LandingPage({ isDark, onSubmit }: LandingPageProps) {
  const [isExpanded, setIsExpanded] = useState(false);
  const [idea, setIdea] = useState('');

  const handleOrbClick = () => {
    setIsExpanded(true);
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    if (idea.trim()) {
      onSubmit(idea);
    }
  };

  return (
    <div
      className={`min-h-screen relative overflow-hidden transition-colors duration-1000 ${
        isDark
          ? 'bg-gradient-to-br from-[#0A0118] via-[#1A0B2E] to-[#0A0118]'
          : 'bg-gradient-to-br from-[#FFF8F0] via-[#FFF0F8] to-[#FFF8F0]'
      }`}
    >
      <NeuralBackground isDark={isDark} />

      <div className="relative z-10 min-h-screen flex flex-col items-center justify-center px-8">
        <div className="text-center space-y-8 max-w-4xl">
          <h1
            className={`text-7xl md:text-8xl font-black tracking-tight animate-float ${
              isDark ? 'text-white' : 'text-[#1A0B2E]'
            }`}
            style={{ fontFamily: 'Orbitron, monospace' }}
          >
            KowihanSynapse
          </h1>

          <p
            className={`text-2xl md:text-3xl font-medium ${
              isDark ? 'text-[#E0B0FF]' : 'text-[#7B2D8A]'
            }`}
          >
            Connecting ideas to code, intelligently.
          </p>

          <div className="pt-16">
            {!isExpanded ? (
              <div className="flex flex-col items-center space-y-6">
                <p
                  className={`text-lg font-medium ${
                    isDark ? 'text-[#E0B0FF]' : 'text-[#7B2D8A]'
                  }`}
                >
                  What idea will we bring to life today?
                </p>

                <button
                  onClick={handleOrbClick}
                  className={`w-32 h-32 rounded-full animate-orb-pulse transition-smooth hover:scale-110 cursor-pointer group relative ${
                    isDark
                      ? 'bg-gradient-to-br from-[#FF1B8D] via-[#8B00FF] to-[#00FFFF]'
                      : 'bg-gradient-to-br from-[#FF006E] via-[#8B00FF] to-[#00F5FF]'
                  }`}
                >
                  <div className="absolute inset-0 rounded-full flex items-center justify-center">
                    <Zap
                      className={`w-16 h-16 ${
                        isDark ? 'text-white' : 'text-white'
                      } group-hover:scale-110 transition-smooth`}
                    />
                  </div>
                </button>
              </div>
            ) : (
              <form onSubmit={handleSubmit} className="max-w-3xl mx-auto">
                <div className="relative">
                  <input
                    type="text"
                    value={idea}
                    onChange={(e) => setIdea(e.target.value)}
                    placeholder="Describe your API idea..."
                    autoFocus
                    className={`w-full px-8 py-6 text-xl rounded-3xl border-2 outline-none transition-smooth ${
                      isDark
                        ? 'bg-[#2D1B4E] border-[#FF1B8D] text-white placeholder-[#E0B0FF] glow-intense'
                        : 'bg-white border-[#FF006E] text-[#1A0B2E] placeholder-[#7B2D8A] glow-pink'
                    }`}
                    style={{
                      backdropFilter: 'blur(20px)',
                    }}
                  />
                  {idea && (
                    <div className="absolute right-4 top-1/2 -translate-y-1/2">
                      <div className="w-3 h-3 rounded-full bg-[#00FF9D] animate-pulse-neon" />
                    </div>
                  )}
                </div>

                <p
                  className={`text-center mt-4 text-sm ${
                    isDark ? 'text-[#E0B0FF]' : 'text-[#7B2D8A]'
                  }`}
                >
                  Press Enter to begin synthesis
                </p>
              </form>
            )}
          </div>
        </div>
      </div>
    </div>
  );
}