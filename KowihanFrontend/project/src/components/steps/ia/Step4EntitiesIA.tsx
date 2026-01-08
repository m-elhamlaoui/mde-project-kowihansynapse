import { useState } from 'react';
import { Package, ArrowLeft, Lightbulb } from 'lucide-react';

interface Step4Props {
  isDark: boolean;
  onNext: (entities: string) => void;
  onBack: () => void;
}

export default function Step4EntitiesIA({ isDark, onNext, onBack }: Step4Props) {
  const [entities, setEntities] = useState('');

  const example = `Book: title, isbn, year_published, pages | Author: name, birthdate, biography | Member: name, email, join_date | Loan: borrow_date, return_date, status`;

  return (
    <div className="space-y-8">
      <div>
        <div className="flex items-center gap-3 mb-4">
          <Package className={`w-10 h-10 ${isDark ? 'text-cyan-400' : 'text-cyan-600'}`} />
          <h2 className={`text-4xl font-bold ${isDark ? 'text-white' : 'text-gray-900'}`}>
            📋 Define Your Entities
          </h2>
        </div>
        <p className={`text-lg ${isDark ? 'text-cyan-200' : 'text-cyan-700'}`}>
          Describe your data model entities and their fields
        </p>
      </div>

      <div>
        <label className={`block text-sm font-medium mb-2 ${isDark ? 'text-gray-300' : 'text-gray-700'}`}>
          Entities Definition
        </label>
        <textarea
          value={entities}
          onChange={(e) => setEntities(e.target.value)}
          rows={8}
          placeholder={`Format: EntityName: field1, field2, field3 | AnotherEntity: field1, field2\n\nExample:\n${example}`}
          className={`w-full px-4 py-3 rounded-xl transition-all font-mono text-sm ${
            isDark
              ? 'bg-white/5 border border-cyan-500/30 text-white placeholder-gray-500 focus:border-cyan-500/60'
              : 'bg-white border border-cyan-300/30 text-gray-900 placeholder-gray-400 focus:border-cyan-300/60'
          }`}
        />
      </div>

      <div className={`p-6 rounded-xl ${isDark ? 'bg-cyan-500/10 border border-cyan-500/30' : 'bg-cyan-50 border border-cyan-200'}`}>
        <div className="flex items-center gap-2 mb-3">
          <Lightbulb className={`w-5 h-5 ${isDark ? 'text-cyan-400' : 'text-cyan-600'}`} />
          <h3 className={`font-semibold ${isDark ? 'text-white' : 'text-gray-900'}`}>
            Format Guide
          </h3>
        </div>
        <div className={`space-y-2 text-sm ${isDark ? 'text-cyan-200' : 'text-cyan-700'}`}>
          <p><strong>• Entity:</strong> Start with capital letter</p>
          <p><strong>• Fields:</strong> Comma-separated, lowercase with underscores</p>
          <p><strong>• Separator:</strong> Use | (pipe) to separate entities</p>
          <p><strong>• Relationships:</strong> AI will auto-detect (e.g., author_id → Author)</p>
        </div>
      </div>

      <div className={`p-4 rounded-xl ${isDark ? 'bg-blue-500/10 border border-blue-500/30' : 'bg-blue-50 border border-blue-200'}`}>
        <p className={`text-sm mb-2 font-semibold ${isDark ? 'text-blue-300' : 'text-blue-700'}`}>
          💡 Quick Examples:
        </p>
        <div className="space-y-2">
          <button
            onClick={() => setEntities(example)}
            className={`w-full text-left px-3 py-2 rounded text-xs ${
              isDark ? 'bg-white/5 hover:bg-white/10 text-blue-300' : 'bg-white hover:bg-blue-100 text-blue-700'
            }`}
          >
            Library: {example.substring(0, 50)}...
          </button>
          <button
            onClick={() => setEntities('Product: name, price, stock, description | Category: name, slug | Order: total, status, created_at | Customer: name, email, phone')}
            className={`w-full text-left px-3 py-2 rounded text-xs ${
              isDark ? 'bg-white/5 hover:bg-white/10 text-blue-300' : 'bg-white hover:bg-blue-100 text-blue-700'
            }`}
          >
            E-commerce: Product, Category, Order, Customer
          </button>
        </div>
      </div>

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
          onClick={() => onNext(entities)}
          disabled={!entities.trim() || entities.length < 10}
          className={`flex-1 py-4 rounded-xl font-semibold text-lg transition-all ${
            entities.trim() && entities.length >= 10
              ? isDark
                ? 'bg-gradient-to-r from-cyan-600 to-blue-600 hover:from-cyan-500 hover:to-blue-500 text-white'
                : 'bg-gradient-to-r from-cyan-500 to-blue-500 hover:from-cyan-400 hover:to-blue-400 text-white'
              : 'bg-gray-500/20 text-gray-500 cursor-not-allowed'
          }`}
        >
          Next: Review →
        </button>
      </div>
    </div>
  );
}
