import { Zap, ArrowLeft } from 'lucide-react';
import ThemeToggle from './ThemeToggle';

interface HeaderProps {
  isDark: boolean;
  onToggleTheme: () => void;
  mode?: 'mde' | 'ia' | null;
  onBackToModeSelection?: () => void;
}

export default function Header({ isDark, onToggleTheme, mode, onBackToModeSelection }: HeaderProps) {
  return (
    <header
      className={`sticky top-0 z-50 transition-all duration-500 ${
        isDark ? 'glass-dark' : 'glass-light'
      }`}
    >
      <div className="max-w-7xl mx-auto px-8 py-4 flex items-center justify-between">
        <div className="flex items-center space-x-3">
          <div
            className={`w-10 h-10 rounded-xl flex items-center justify-center animate-breathe ${
              isDark
                ? 'bg-gradient-to-br from-[#FF1B8D] to-[#8B00FF] glow-pink'
                : 'bg-gradient-to-br from-[#FF006E] to-[#8B00FF] glow-pink'
            }`}
          >
            <Zap className="w-6 h-6 text-white" />
          </div>
          <span
            className={`text-2xl font-black tracking-tight ${
              isDark ? 'text-white' : 'text-[#1A0B2E]'
            }`}
            style={{ fontFamily: 'Orbitron, monospace' }}
          >
            KowihanSynapse
          </span>
        </div>

        <div className="flex items-center gap-4">
          {/* Mode Badge */}
          {mode && (
            <div
              className={`px-4 py-2 rounded-lg font-semibold ${
                mode === 'mde'
                  ? isDark
                    ? 'bg-purple-500/20 text-purple-300'
                    : 'bg-purple-100 text-purple-700'
                  : isDark
                  ? 'bg-cyan-500/20 text-cyan-300'
                  : 'bg-cyan-100 text-cyan-700'
              }`}
            >
              {mode === 'mde' ? '🏗️ MDE Mode' : '🤖 IA Mode'}
            </div>
          )}

          {/* Back to Mode Selection Button */}
          {mode && onBackToModeSelection && (
            <button
              onClick={onBackToModeSelection}
              className={`px-4 py-2 rounded-lg font-medium transition-all flex items-center gap-2 ${
                isDark
                  ? 'bg-white/5 hover:bg-white/10 text-white'
                  : 'bg-gray-100 hover:bg-gray-200 text-gray-900'
              }`}
            >
              <ArrowLeft className="w-4 h-4" />
              Change Mode
            </button>
          )}

          <ThemeToggle isDark={isDark} onToggle={onToggleTheme} />
        </div>
      </div>
    </header>
  );
}
