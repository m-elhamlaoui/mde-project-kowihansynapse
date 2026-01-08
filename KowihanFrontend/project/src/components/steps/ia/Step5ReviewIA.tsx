import { ArrowLeft, Sparkles, Database, Code, Package, Loader2 } from 'lucide-react';

interface ProjectDataIA {
  sessionId: string | null;
  objective: string;
  database: string | null;
  framework: string | null;
  entities: string;
}

interface Step5Props {
  isDark: boolean;
  data: ProjectDataIA;
  onGenerate: () => void;
  onBack: () => void;
  isProcessing?: boolean;
}

export default function Step5ReviewIA({ isDark, data, onGenerate, onBack, isProcessing = false }: Step5Props) {
  const entitiesList = data.entities.split('|').map(e => e.trim()).filter(e => e);

  return (
    <div className="space-y-8">
      <div>
        <div className="flex items-center gap-3 mb-4">
          <Sparkles className={`w-10 h-10 ${isDark ? 'text-cyan-400' : 'text-cyan-600'}`} />
          <h2 className={`text-4xl font-bold ${isDark ? 'text-white' : 'text-gray-900'}`}>
            ✅ Review & Generate
          </h2>
        </div>
        <p className={`text-lg ${isDark ? 'text-cyan-200' : 'text-cyan-700'}`}>
          Review your configuration before AI generates your project
        </p>
      </div>

      {/* Objective */}
      <div className={`p-6 rounded-xl ${isDark ? 'bg-white/5 border border-cyan-500/30' : 'bg-gray-50 border border-cyan-200'}`}>
        <div className="flex items-center gap-2 mb-3">
          <Sparkles className={`w-5 h-5 ${isDark ? 'text-cyan-400' : 'text-cyan-600'}`} />
          <h3 className={`text-lg font-semibold ${isDark ? 'text-white' : 'text-gray-900'}`}>
            Project Objective
          </h3>
        </div>
        <p className={isDark ? 'text-gray-300' : 'text-gray-700'}>
          {data.objective}
        </p>
      </div>

      {/* Database */}
      <div className={`p-6 rounded-xl ${isDark ? 'bg-white/5 border border-cyan-500/30' : 'bg-gray-50 border border-cyan-200'}`}>
        <div className="flex items-center gap-2 mb-3">
          <Database className={`w-5 h-5 ${isDark ? 'text-cyan-400' : 'text-cyan-600'}`} />
          <h3 className={`text-lg font-semibold ${isDark ? 'text-white' : 'text-gray-900'}`}>
            Database
          </h3>
        </div>
        <p className={`text-2xl font-bold ${isDark ? 'text-cyan-400' : 'text-cyan-600'}`}>
          {data.database}
        </p>
      </div>

      {/* Framework */}
      <div className={`p-6 rounded-xl ${isDark ? 'bg-white/5 border border-cyan-500/30' : 'bg-gray-50 border border-cyan-200'}`}>
        <div className="flex items-center gap-2 mb-3">
          <Code className={`w-5 h-5 ${isDark ? 'text-cyan-400' : 'text-cyan-600'}`} />
          <h3 className={`text-lg font-semibold ${isDark ? 'text-white' : 'text-gray-900'}`}>
            Framework
          </h3>
        </div>
        <p className={`text-2xl font-bold ${isDark ? 'text-cyan-400' : 'text-cyan-600'}`}>
          {data.framework}
        </p>
      </div>

      {/* Entities */}
      <div className={`p-6 rounded-xl ${isDark ? 'bg-white/5 border border-cyan-500/30' : 'bg-gray-50 border border-cyan-200'}`}>
        <div className="flex items-center gap-2 mb-3">
          <Package className={`w-5 h-5 ${isDark ? 'text-cyan-400' : 'text-cyan-600'}`} />
          <h3 className={`text-lg font-semibold ${isDark ? 'text-white' : 'text-gray-900'}`}>
            Entities ({entitiesList.length})
          </h3>
        </div>
        <div className="space-y-2">
          {entitiesList.map((entity, idx) => {
            const [name, ...fields] = entity.split(':');
            return (
              <div key={idx} className={`p-3 rounded-lg ${isDark ? 'bg-white/5' : 'bg-white'}`}>
                <p className={`font-semibold mb-1 ${isDark ? 'text-white' : 'text-gray-900'}`}>
                  {name.trim()}
                </p>
                <p className={`text-sm ${isDark ? 'text-gray-400' : 'text-gray-600'}`}>
                  {fields.join(':').trim()}
                </p>
              </div>
            );
          })}
        </div>
      </div>

      {/* AI Info */}
      <div className={`p-4 rounded-xl ${isDark ? 'bg-gradient-to-r from-cyan-500/10 to-blue-500/10 border border-cyan-500/30' : 'bg-gradient-to-r from-cyan-50 to-blue-50 border border-cyan-200'}`}>
        <p className={`text-sm ${isDark ? 'text-cyan-300' : 'text-cyan-700'}`}>
          🤖 <strong>AI will analyze your requirements and:</strong>
          <br />
          • Detect relationships between entities automatically
          <br />
          • Generate optimized models with proper field types
          <br />
          • Create RESTful endpoints with CRUD operations
          <br />
          • Add input validation and error handling
          <br />
          • Include security best practices
          <br />
          • Generate comprehensive documentation
        </p>
      </div>

      {/* Loading indicator */}
      {isProcessing && (
        <div className={`p-4 rounded-xl flex items-center gap-3 ${
          isDark ? 'bg-cyan-500/20 border border-cyan-500' : 'bg-cyan-100 border border-cyan-300'
        }`}>
          <Loader2 className={`w-6 h-6 animate-spin ${isDark ? 'text-cyan-400' : 'text-cyan-600'}`} />
          <p className={`font-semibold ${isDark ? 'text-cyan-300' : 'text-cyan-700'}`}>
            AI is processing your request... This may take a few moments.
          </p>
        </div>
      )}

      {/* Buttons */}
      <div className="flex gap-4">
        <button
          onClick={onBack}
          disabled={isProcessing}
          className={`flex-1 py-4 rounded-xl font-semibold text-lg transition-all flex items-center justify-center gap-2 ${
            isProcessing
              ? 'bg-gray-500/20 text-gray-500 cursor-not-allowed'
              : isDark
              ? 'bg-white/5 hover:bg-white/10 text-white'
              : 'bg-gray-100 hover:bg-gray-200 text-gray-900'
          }`}
        >
          <ArrowLeft className="w-5 h-5" />
          Back
        </button>
        <button
          onClick={onGenerate}
          disabled={isProcessing}
          className={`flex-1 py-4 rounded-xl font-semibold text-lg transition-all flex items-center justify-center gap-2 ${
            isProcessing
              ? 'bg-gray-500/20 text-gray-500 cursor-not-allowed'
              : isDark
              ? 'bg-gradient-to-r from-cyan-600 to-blue-600 hover:from-cyan-500 hover:to-blue-500 text-white'
              : 'bg-gradient-to-r from-cyan-500 to-blue-500 hover:from-cyan-400 hover:to-blue-400 text-white'
          }`}
        >
          {isProcessing ? (
            <>
              <Loader2 className="w-5 h-5 animate-spin" />
              Processing...
            </>
          ) : (
            <>
              🤖 Generate with AI
            </>
          )}
        </button>
      </div>
    </div>
  );
}
