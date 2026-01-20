import { useState } from 'react';
import { Sparkles, Lightbulb } from 'lucide-react';

interface Step1Props {
  isDark: boolean;
  initialIdea: string;
  onNext: (objective: string) => void;
}

export default function Step1ObjectiveIA({ isDark, initialIdea, onNext }: Step1Props) {
  const [objective, setObjective] = useState(initialIdea);

  const suggestions = [
    'API de gestion de bibliothèque avec livres, auteurs et emprunts',
    'API de e-commerce avec produits, commandes et utilisateurs',
    'API de blog avec articles, commentaires et catégories',
    'API de gestion de tâches avec projets et équipes',
  ];

  return (
    <div className="space-y-8">
      <div>
        <div className="flex items-center gap-3 mb-4">
          <Sparkles className={`w-10 h-10 ${isDark ? 'text-cyan-400' : 'text-cyan-600'}`} />
          <h2 className={`text-4xl font-bold ${isDark ? 'text-white' : 'text-gray-900'}`}>
            🎯 What's your API objective?
          </h2>
        </div>
        <p className={`text-lg ${isDark ? 'text-cyan-200' : 'text-cyan-700'}`}>
          Describe your API in natural language. Be as detailed as you want!
        </p>
      </div>

      {/* Main Input */}
      <div>
        <label className={`block text-sm font-medium mb-2 ${isDark ? 'text-gray-300' : 'text-gray-700'}`}>
          Your API Objective
        </label>
        <textarea
          value={objective}
          onChange={(e) => setObjective(e.target.value)}
          rows={6}
          placeholder="Example: I want to build a library management API with books, authors, members, and borrowing system..."
          className={`w-full px-4 py-3 rounded-xl transition-all ${
            isDark
              ? 'bg-white/5 border border-cyan-500/30 text-white placeholder-gray-500 focus:border-cyan-500/60'
              : 'bg-white border border-cyan-300/30 text-gray-900 placeholder-gray-400 focus:border-cyan-300/60'
          }`}
        />
        <div className={`mt-2 text-sm ${isDark ? 'text-gray-400' : 'text-gray-600'}`}>
          {objective.length} characters
        </div>
      </div>

      {/* Suggestions */}
      <div className={`p-6 rounded-xl ${isDark ? 'bg-cyan-500/10 border border-cyan-500/30' : 'bg-cyan-50 border border-cyan-200'}`}>
        <div className="flex items-center gap-2 mb-3">
          <Lightbulb className={`w-5 h-5 ${isDark ? 'text-cyan-400' : 'text-cyan-600'}`} />
          <h3 className={`font-semibold ${isDark ? 'text-white' : 'text-gray-900'}`}>
            Need inspiration?
          </h3>
        </div>
        <div className="space-y-2">
          {suggestions.map((suggestion, index) => (
            <button
              key={index}
              onClick={() => setObjective(suggestion)}
              className={`w-full text-left px-4 py-2 rounded-lg transition-all ${
                isDark
                  ? 'bg-white/5 hover:bg-white/10 text-cyan-300'
                  : 'bg-white hover:bg-cyan-100 text-cyan-700'
              }`}
            >
              {suggestion}
            </button>
          ))}
        </div>
      </div>

      {/* Info Box */}
      <div className={`p-4 rounded-xl ${isDark ? 'bg-blue-500/10 border border-blue-500/30' : 'bg-blue-50 border border-blue-200'}`}>
        <p className={`text-sm ${isDark ? 'text-blue-300' : 'text-blue-700'}`}>
          💡 <strong>Tip:</strong> The more detailed your description, the better the AI can understand and generate your API. Include entities, relationships, and key features.
        </p>
      </div>

      {/* Next Button */}
      <button
        onClick={() => onNext(objective)}
        disabled={!objective.trim() || objective.length < 20}
        className={`w-full py-4 rounded-xl font-semibold text-lg transition-all ${
          objective.trim() && objective.length >= 20
            ? isDark
              ? 'bg-gradient-to-r from-cyan-600 to-blue-600 hover:from-cyan-500 hover:to-blue-500 text-white'
              : 'bg-gradient-to-r from-cyan-500 to-blue-500 hover:from-cyan-400 hover:to-blue-400 text-white'
            : 'bg-gray-500/20 text-gray-500 cursor-not-allowed'
        }`}
      >
        Next: Database →
      </button>
    </div>
  );
}
