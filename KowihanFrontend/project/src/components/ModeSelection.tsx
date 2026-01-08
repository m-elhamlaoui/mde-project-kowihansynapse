import { motion } from 'framer-motion';
import { Network, Brain, ArrowRight, Workflow, Sparkles } from 'lucide-react';

interface ModeSelectionProps {
  isDark: boolean;
  onSelectMode: (mode: 'mde' | 'ia') => void;
  onBack: () => void;
}

export default function ModeSelection({ isDark, onSelectMode, onBack }: ModeSelectionProps) {
  return (
    <div
      className={`min-h-screen flex items-center justify-center transition-colors duration-1000 ${
        isDark
          ? 'bg-gradient-to-br from-[#0A0118] via-[#1A0B2E] to-[#0A0118]'
          : 'bg-gradient-to-br from-[#FFF8F0] via-[#FFF0F8] to-[#FFF8F0]'
      }`}
    >
      <div className="max-w-6xl mx-auto px-8">
        <motion.div
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          className="text-center mb-16"
        >
          <h1
            className={`text-6xl font-bold mb-6 ${
              isDark ? 'text-white' : 'text-gray-900'
            }`}
          >
            Choose Your Generation Mode
          </h1>
          <p
            className={`text-xl ${
              isDark ? 'text-purple-200' : 'text-purple-600'
            }`}
          >
            Select the approach that best fits your project
          </p>
        </motion.div>

        <div className="grid md:grid-cols-2 gap-8">
          {/* MDE Mode */}
          <motion.div
            initial={{ opacity: 0, x: -50 }}
            animate={{ opacity: 1, x: 0 }}
            transition={{ delay: 0.2 }}
            whileHover={{ scale: 1.02 }}
            onClick={() => onSelectMode('mde')}
            className={`cursor-pointer rounded-3xl p-8 transition-all duration-500 ${
              isDark
                ? 'glass-dark border border-purple-500/30 hover:border-purple-500/60'
                : 'glass-light border border-purple-300/30 hover:border-purple-300/60'
            }`}
          >
            <div className="flex items-center justify-between mb-6">
              <div
                className={`p-4 rounded-2xl ${
                  isDark ? 'bg-purple-500/20' : 'bg-purple-100'
                }`}
              >
                <Network
                  className={`w-12 h-12 ${
                    isDark ? 'text-purple-400' : 'text-purple-600'
                  }`}
                />
              </div>
              <ArrowRight
                className={`w-6 h-6 ${
                  isDark ? 'text-purple-400' : 'text-purple-600'
                }`}
              />
            </div>

            <h2
              className={`text-3xl font-bold mb-4 ${
                isDark ? 'text-white' : 'text-gray-900'
              }`}
            >
              Model-Driven Engineering
            </h2>

            <p
              className={`text-lg mb-6 ${
                isDark ? 'text-purple-200' : 'text-purple-700'
              }`}
            >
              UML Diagrams → XMI → Acceleo → Code
            </p>

            <div className="space-y-3 mb-8">
              <div
                className={`flex items-start gap-3 ${
                  isDark ? 'text-gray-300' : 'text-gray-700'
                }`}
              >
                <Workflow className="w-5 h-5 mt-1 flex-shrink-0" />
                <span>Upload UML class and sequence diagrams</span>
              </div>
              <div
                className={`flex items-start gap-3 ${
                  isDark ? 'text-gray-300' : 'text-gray-700'
                }`}
              >
                <Workflow className="w-5 h-5 mt-1 flex-shrink-0" />
                <span>Maximum precision and control</span>
              </div>
              <div
                className={`flex items-start gap-3 ${
                  isDark ? 'text-gray-300' : 'text-gray-700'
                }`}
              >
                <Workflow className="w-5 h-5 mt-1 flex-shrink-0" />
                <span>Full UML compliance and traceability</span>
              </div>
            </div>

            <div
              className={`inline-block px-4 py-2 rounded-lg text-sm font-semibold ${
                isDark
                  ? 'bg-purple-500/20 text-purple-300'
                  : 'bg-purple-100 text-purple-700'
              }`}
            >
              Best for: Enterprise projects, critical systems
            </div>
          </motion.div>

          {/* IA Mode */}
          <motion.div
            initial={{ opacity: 0, x: 50 }}
            animate={{ opacity: 1, x: 0 }}
            transition={{ delay: 0.3 }}
            whileHover={{ scale: 1.02 }}
            onClick={() => onSelectMode('ia')}
            className={`cursor-pointer rounded-3xl p-8 transition-all duration-500 ${
              isDark
                ? 'glass-dark border border-cyan-500/30 hover:border-cyan-500/60'
                : 'glass-light border border-cyan-300/30 hover:border-cyan-300/60'
            }`}
          >
            <div className="flex items-center justify-between mb-6">
              <div
                className={`p-4 rounded-2xl ${
                  isDark ? 'bg-cyan-500/20' : 'bg-cyan-100'
                }`}
              >
                <Brain
                  className={`w-12 h-12 ${
                    isDark ? 'text-cyan-400' : 'text-cyan-600'
                  }`}
                />
              </div>
              <ArrowRight
                className={`w-6 h-6 ${
                  isDark ? 'text-cyan-400' : 'text-cyan-600'
                }`}
              />
            </div>

            <h2
              className={`text-3xl font-bold mb-4 ${
                isDark ? 'text-white' : 'text-gray-900'
              }`}
            >
              AI-Powered Generation
            </h2>

            <p
              className={`text-lg mb-6 ${
                isDark ? 'text-cyan-200' : 'text-cyan-700'
              }`}
            >
              Natural Language → AI Analysis → Code
            </p>

            <div className="space-y-3 mb-8">
              <div
                className={`flex items-start gap-3 ${
                  isDark ? 'text-gray-300' : 'text-gray-700'
                }`}
              >
                <Sparkles className="w-5 h-5 mt-1 flex-shrink-0" />
                <span>Conversational interface with AI agents</span>
              </div>
              <div
                className={`flex items-start gap-3 ${
                  isDark ? 'text-gray-300' : 'text-gray-700'
                }`}
              >
                <Sparkles className="w-5 h-5 mt-1 flex-shrink-0" />
                <span>Intelligent suggestions and recommendations</span>
              </div>
              <div
                className={`flex items-start gap-3 ${
                  isDark ? 'text-gray-300' : 'text-gray-700'
                }`}
              >
                <Sparkles className="w-5 h-5 mt-1 flex-shrink-0" />
                <span>No UML knowledge required</span>
              </div>
            </div>

            <div
              className={`inline-block px-4 py-2 rounded-lg text-sm font-semibold ${
                isDark
                  ? 'bg-cyan-500/20 text-cyan-300'
                  : 'bg-cyan-100 text-cyan-700'
              }`}
            >
              Best for: Rapid prototyping, MVPs, startups
            </div>
          </motion.div>
        </div>

        <motion.div
          initial={{ opacity: 0 }}
          animate={{ opacity: 1 }}
          transition={{ delay: 0.5 }}
          className="text-center mt-12"
        >
          <button
            onClick={onBack}
            className={`px-6 py-3 rounded-xl transition-all ${
              isDark
                ? 'text-gray-400 hover:text-white'
                : 'text-gray-600 hover:text-gray-900'
            }`}
          >
            ← Back to landing
          </button>
        </motion.div>
      </div>
    </div>
  );
}