import { useState, useEffect } from 'react';
import { Download, Sparkles, Database, FileCode, Shield, Loader2 } from 'lucide-react';

interface GenerationScreenProps {
  isDark: boolean;
  mode: 'mde' | 'ia';
  onComplete: () => void;
  sessionId?: string; // Pour IA seulement
}

export default function GenerationScreen({ isDark, mode, onComplete, sessionId }: GenerationScreenProps) {
  const [progress, setProgress] = useState(0);
  const [currentPhase, setCurrentPhase] = useState(0);
  const [isComplete, setIsComplete] = useState(false);
  const [isDownloadReady, setIsDownloadReady] = useState(false);

  const phasesMDE = [
    { name: 'Analyzing UML', icon: Database, color: isDark ? '#FF1B8D' : '#FF006E' },
    { name: 'Building Models', icon: Database, color: isDark ? '#00FFFF' : '#00F5FF' },
    { name: 'Creating Endpoints', icon: FileCode, color: isDark ? '#FFD000' : '#FFB800' },
    { name: 'Finalizing', icon: Shield, color: isDark ? '#8B00FF' : '#8B00FF' },
  ];

  const phasesIA = [
    { name: 'AI Analyzing', icon: Sparkles, color: isDark ? '#00FFFF' : '#00F5FF' },
    { name: 'Building Models', icon: Database, color: isDark ? '#FF1B8D' : '#FF006E' },
    { name: 'Creating Endpoints', icon: FileCode, color: isDark ? '#FFD000' : '#FFB800' },
    { name: 'Finalizing', icon: Shield, color: isDark ? '#8B00FF' : '#8B00FF' },
  ];

  const phases = mode === 'mde' ? phasesMDE : phasesIA;

  useEffect(() => {
    const totalDuration = 8000;
    const interval = 50;
    const steps = totalDuration / interval;
    const increment = 100 / steps;

    const timer = setInterval(() => {
      setProgress((prev) => {
        const newProgress = Math.min(prev + increment, 100);

        const newPhase = Math.floor((newProgress / 100) * phases.length);
        if (newPhase !== currentPhase && newPhase < phases.length) {
          setCurrentPhase(newPhase);
        }

        if (newProgress >= 100) {
          clearInterval(timer);
          
          // ✅ Mode MDE: téléchargement immédiat (comportement original)
          if (mode === 'mde') {
            setTimeout(() => {
              setIsComplete(true);
              setIsDownloadReady(true); // MDE est toujours prêt
            }, 500);
          } 
          // ✅ Mode IA: vérifier si le projet est prêt avant d'activer le bouton
          else {
            setIsComplete(true);
            checkIAProjectReady();
          }
        }

        return newProgress;
      });
    }, interval);

    return () => clearInterval(timer);
  }, []);

  // ✅ Vérification spécifique au mode IA
  const checkIAProjectReady = async () => {
    if (!sessionId) {
      console.error('❌ No sessionId for IA mode');
      setIsDownloadReady(true); // Fallback
      return;
    }

    console.log('🔍 Checking if IA project is ready...');
    let attempts = 0;
    const maxAttempts = 60; // 2 minutes max

    const checkInterval = setInterval(async () => {
      attempts++;
      console.log(`🔍 Attempt ${attempts}/${maxAttempts}`);

      try {
        const response = await fetch(
          `http://localhost:5000/api/ia/download/${sessionId}`,
          { method: 'HEAD' }
        );

        if (response.ok) {
          console.log('✅ IA project is ready!');
          clearInterval(checkInterval);
          setIsDownloadReady(true);
        } else if (attempts >= maxAttempts) {
          console.error('⏱️ Timeout waiting for IA project');
          clearInterval(checkInterval);
          alert('Generation timeout. The project may still be generating. Check backend logs.');
          setIsDownloadReady(true); // Permettre le téléchargement quand même
        }
      } catch (error) {
        if (attempts >= maxAttempts) {
          console.error('❌ Error checking IA project:', error);
          clearInterval(checkInterval);
          alert('Error checking project status.');
          setIsDownloadReady(true);
        }
      }
    }, 2000);
  };

  const handleDownload = () => {
    // ✅ Mode IA: télécharger depuis l'endpoint IA
    if (mode === 'ia' && sessionId) {
      window.location.href = `http://localhost:5000/api/ia/download/${sessionId}`;
    }
    // ✅ Mode MDE: le téléchargement a déjà été fait dans handleGenerateMDE
    
    onComplete();
  };

  return (
    <div
      className={`min-h-screen relative overflow-hidden transition-colors duration-1000 ${
        isDark
          ? 'bg-gradient-to-br from-[#0A0118] via-[#1A0B2E] to-[#0A0118]'
          : 'bg-gradient-to-br from-[#FFF8F0] via-[#FFF0F8] to-[#FFF8F0]'
      }`}
    >
      <div className="relative z-10 min-h-screen flex flex-col items-center justify-center px-8">
        <div className="max-w-4xl w-full space-y-12">
          {!isComplete ? (
            <>
              <div className="text-center space-y-4">
                <h2
                  className={`text-5xl font-black animate-pulse-neon ${
                    isDark ? 'text-white' : 'text-[#1A0B2E]'
                  }`}
                  style={{ fontFamily: 'Orbitron, monospace' }}
                >
                  {mode === 'mde' ? '🏗️ Synthesizing Your API' : '🤖 AI Crafting Your API'}
                </h2>
                <p className={`text-xl ${isDark ? 'text-[#E0B0FF]' : 'text-[#7B2D8A]'}`}>
                  {phases[currentPhase].name}...
                </p>
                {mode === 'ia' && (
                  <p className={`text-sm ${isDark ? 'text-cyan-300' : 'text-cyan-600'}`}>
                    🤖 5 AI agents working in parallel
                  </p>
                )}
              </div>

              {/* Phases Grid */}
              <div className="relative">
                <div className="grid grid-cols-2 md:grid-cols-4 gap-6 mb-12">
                  {phases.map((phase, index) => {
                    const Icon = phase.icon;
                    const isActive = index === currentPhase;
                    const isCompleted = index < currentPhase;

                    return (
                      <div
                        key={phase.name}
                        className={`p-6 rounded-3xl transition-smooth ${
                          isActive
                            ? isDark
                              ? 'glass-dark glow-intense scale-110'
                              : 'glass-light glow-pink scale-110'
                            : isCompleted
                            ? isDark
                              ? 'bg-[#2D1B4E] border-2 border-[#39FF14]'
                              : 'bg-white border-2 border-[#00FF9D]'
                            : isDark
                            ? 'bg-[#2D1B4E] border-2 border-[#FF1B8D33]'
                            : 'bg-white border-2 border-[#FFD6E8]'
                        }`}
                      >
                        <div className="flex flex-col items-center space-y-3">
                          <Icon
                            className={`w-10 h-10 ${
                              isActive
                                ? 'animate-pulse-neon'
                                : isCompleted
                                ? isDark
                                  ? 'text-[#39FF14]'
                                  : 'text-[#00FF9D]'
                                : isDark
                                ? 'text-[#E0B0FF]'
                                : 'text-[#7B2D8A]'
                            }`}
                            style={{ color: isActive ? phase.color : undefined }}
                          />
                          <span
                            className={`text-sm font-semibold text-center ${
                              isActive
                                ? isDark
                                  ? 'text-white'
                                  : 'text-[#1A0B2E]'
                                : isCompleted
                                ? isDark
                                  ? 'text-[#39FF14]'
                                  : 'text-[#00FF9D]'
                                : isDark
                                ? 'text-[#E0B0FF]'
                                : 'text-[#7B2D8A]'
                            }`}
                          >
                            {phase.name}
                          </span>
                        </div>
                      </div>
                    );
                  })}
                </div>

                {/* Progress Bar */}
                <div className={`relative h-4 rounded-full overflow-hidden ${
                  isDark ? 'bg-[#2D1B4E]' : 'bg-[#FFD6E8]'
                }`}>
                  <div
                    className={`absolute top-0 left-0 h-full transition-all duration-300 rounded-full ${
                      mode === 'mde'
                        ? isDark
                          ? 'bg-gradient-to-r from-[#FF1B8D] via-[#8B00FF] to-[#00FFFF] glow-intense'
                          : 'bg-gradient-to-r from-[#FF006E] via-[#8B00FF] to-[#00F5FF] glow-multi'
                        : isDark
                        ? 'bg-gradient-to-r from-[#00FFFF] via-[#8B00FF] to-[#FFD000] glow-cyan'
                        : 'bg-gradient-to-r from-[#00F5FF] via-[#8B00FF] to-[#FFB800] glow-cyan'
                    }`}
                    style={{ width: `${progress}%` }}
                  />
                </div>

                <div className="text-center mt-4">
                  <span
                    className={`text-3xl font-black ${isDark ? 'text-white' : 'text-[#1A0B2E]'}`}
                    style={{ fontFamily: 'Orbitron, monospace' }}
                  >
                    {Math.floor(progress)}%
                  </span>
                </div>
              </div>

              <div className={`text-center space-y-2 ${
                isDark ? 'text-[#E0B0FF]' : 'text-[#7B2D8A]'
              }`}>
                <p className="text-sm">
                  {mode === 'mde' 
                    ? 'Building your API from UML diagrams with Acceleo'
                    : 'Building your production-ready API with AI best practices'}
                </p>
                <div className="flex items-center justify-center space-x-2">
                  <div className="w-2 h-2 rounded-full bg-current animate-pulse-neon" />
                  <div className="w-2 h-2 rounded-full bg-current animate-pulse-neon" style={{ animationDelay: '0.2s' }} />
                  <div className="w-2 h-2 rounded-full bg-current animate-pulse-neon" style={{ animationDelay: '0.4s' }} />
                </div>
              </div>
            </>
          ) : (
            <div className="text-center space-y-8 animate-scaleIn">
              <div className="inline-block">
                <div
                  className={`w-32 h-32 rounded-full flex items-center justify-center mb-6 ${
                    mode === 'mde'
                      ? isDark
                        ? 'bg-gradient-to-br from-[#FF1B8D] to-[#8B00FF] glow-pink'
                        : 'bg-gradient-to-br from-[#FF006E] to-[#8B00FF] glow-pink'
                      : isDark
                      ? 'bg-gradient-to-br from-[#00FFFF] to-[#8B00FF] glow-cyan'
                      : 'bg-gradient-to-br from-[#00F5FF] to-[#8B00FF] glow-cyan'
                  }`}
                >
                  {mode === 'mde' ? (
                    <Database className="w-16 h-16 text-white animate-pulse-neon" />
                  ) : (
                    <Sparkles className="w-16 h-16 text-white animate-pulse-neon" />
                  )}
                </div>
              </div>

              <div className="space-y-4">
                <h2
                  className={`text-5xl font-black ${isDark ? 'text-white' : 'text-[#1A0B2E]'}`}
                  style={{ fontFamily: 'Orbitron, monospace' }}
                >
                  {mode === 'mde' ? '🏗️ Generation Complete!' : '🤖 AI Synthesis Complete!'}
                </h2>
                <p className={`text-xl ${isDark ? 'text-[#E0B0FF]' : 'text-[#7B2D8A]'}`}>
                  Your API is ready to deploy
                </p>
              </div>

              <button
                onClick={handleDownload}
                disabled={!isDownloadReady}
                className={`px-12 py-5 rounded-2xl font-bold text-xl transition-smooth inline-flex items-center space-x-3 ${
                  isDownloadReady
                    ? 'hover:scale-110 animate-gradient'
                    : 'cursor-not-allowed opacity-50'
                } ${
                  mode === 'mde'
                    ? isDark
                      ? 'bg-gradient-to-r from-[#FF1B8D] via-[#8B00FF] to-[#00FFFF] text-white glow-pink'
                      : 'bg-gradient-to-r from-[#FF006E] via-[#8B00FF] to-[#00F5FF] text-white glow-multi'
                    : isDark
                    ? 'bg-gradient-to-r from-[#00FFFF] via-[#8B00FF] to-[#FFD000] text-black glow-cyan'
                    : 'bg-gradient-to-r from-[#00F5FF] via-[#8B00FF] to-[#FFB800] text-black glow-cyan'
                }`}
                style={{ backgroundSize: '200% 200%' }}
              >
                {isDownloadReady ? (
                  <>
                    <Download className="w-6 h-6" />
                    <span>Download Artifact</span>
                  </>
                ) : (
                  <>
                    <Loader2 className="w-6 h-6 animate-spin" />
                    <span>Preparing download...</span>
                  </>
                )}
              </button>

              <div className={`text-sm ${isDark ? 'text-[#E0B0FF]' : 'text-[#7B2D8A]'}`}>
                {isDownloadReady 
                  ? 'Ready for production deployment'
                  : mode === 'ia'
                  ? '🤖 AI is finalizing your project... (this may take 30-60 seconds)'
                  : 'Processing...'}
              </div>
            </div>
          )}
        </div>
      </div>
    </div>
  );
}