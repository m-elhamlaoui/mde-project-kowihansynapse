import { Check } from 'lucide-react';

interface Step {
  id: number;
  label: string;
  completed: boolean;
  active: boolean;
}

interface NeuralPathwayProps {
  steps: Step[];
  isDark: boolean;
}

export default function NeuralPathway({ steps, isDark }: NeuralPathwayProps) {
  return (
    <div className="relative py-12">
      <div className="max-w-5xl mx-auto px-8">
        <div className="relative flex items-center justify-between">
          <div
            className={`absolute top-1/2 left-0 right-0 h-0.5 -translate-y-1/2 ${
              isDark ? 'bg-[#2D1B4E]' : 'bg-[#FFD6E8]'
            }`}
          />

          {steps.map((step, index) => (
            <div key={step.id} className="relative flex flex-col items-center z-10">
              <div
                className={`w-16 h-16 rounded-full flex items-center justify-center transition-smooth ${
                  step.completed
                    ? isDark
                      ? 'bg-gradient-to-br from-[#39FF14] to-[#00FFFF] glow-cyan'
                      : 'bg-gradient-to-br from-[#00FF9D] to-[#00F5FF] glow-cyan'
                    : step.active
                    ? isDark
                      ? 'bg-gradient-to-br from-[#FF1B8D] via-[#8B00FF] to-[#00FFFF] animate-neural-pulse'
                      : 'bg-gradient-to-br from-[#FF006E] via-[#8B00FF] to-[#00F5FF] animate-neural-pulse'
                    : isDark
                    ? 'bg-[#2D1B4E] border-2 border-[#FF1B8D33]'
                    : 'bg-white border-2 border-[#FFD6E8]'
                }`}
              >
                {step.completed ? (
                  <Check className="w-8 h-8 text-white" />
                ) : (
                  <span
                    className={`text-xl font-bold ${
                      step.active
                        ? 'text-white'
                        : isDark
                        ? 'text-[#E0B0FF]'
                        : 'text-[#7B2D8A]'
                    }`}
                  >
                    {step.id}
                  </span>
                )}
              </div>

              <p
                className={`mt-4 text-sm font-semibold text-center whitespace-nowrap ${
                  step.active
                    ? isDark
                      ? 'text-[#FF1B8D]'
                      : 'text-[#FF006E]'
                    : isDark
                    ? 'text-[#E0B0FF]'
                    : 'text-[#7B2D8A]'
                }`}
              >
                {step.label}
              </p>

              {step.completed && index < steps.length - 1 && (
                <div
                  className={`absolute top-8 left-full w-full h-0.5 ${
                    isDark
                      ? 'bg-gradient-to-r from-[#39FF14] to-[#00FFFF]'
                      : 'bg-gradient-to-r from-[#00FF9D] to-[#00F5FF]'
                  }`}
                  style={{
                    width: `calc(100% + ${(100 / (steps.length - 1)) * 0.65}%)`,
                  }}
                />
              )}
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}