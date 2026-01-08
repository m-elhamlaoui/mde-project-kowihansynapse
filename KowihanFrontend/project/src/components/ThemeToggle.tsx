import { Sun, Moon } from 'lucide-react';

interface ThemeToggleProps {
  isDark: boolean;
  onToggle: () => void;
}

export default function ThemeToggle({ isDark, onToggle }: ThemeToggleProps) {
  return (
    <div className="flex items-center space-x-3">
      <span
        className={`text-sm font-semibold ${
          isDark ? 'text-[#E0B0FF]' : 'text-[#7B2D8A]'
        }`}
      >
        Theme
      </span>
      <button
        onClick={onToggle}
        className={`relative w-20 h-10 rounded-full transition-smooth ${
          isDark
            ? 'bg-gradient-to-r from-[#8B00FF] to-[#FF1B8D] glow-intense'
            : 'bg-gradient-to-r from-[#FFB800] to-[#FF006E] glow-pink'
        }`}
        aria-label="Toggle theme"
      >
        <div
          className={`absolute top-1 left-1 w-8 h-8 bg-white rounded-full flex items-center justify-center transition-smooth shadow-lg ${
            isDark ? 'translate-x-10' : 'translate-x-0'
          }`}
        >
          {isDark ? (
            <Moon className="w-5 h-5 text-[#8B00FF] animate-pulse-neon" />
          ) : (
            <Sun className="w-5 h-5 text-[#FFB800] animate-pulse-neon" />
          )}
        </div>
      </button>
    </div>
  );
}