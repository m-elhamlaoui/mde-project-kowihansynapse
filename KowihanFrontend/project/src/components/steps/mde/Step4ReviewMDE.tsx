import { ArrowLeft, CheckCircle, Database, Shield, Settings, FileText } from 'lucide-react';

interface ProjectDataMDE {
  projectName: string;
  framework: 'DJANGO' | 'FLASK' | null;
  description: string;
  database: {
    type: 'POSTGRESQL' | 'MYSQL' | 'SQLITE' | 'ORACLE' | null;
    host: string;
    port: string;
    name: string;
  };
  authentication: {
    enabled: boolean;
    method: 'JWT' | 'SESSION' | null;
  };
  apiFeatures: {
    pagination: boolean;
    filtering: boolean;
    swagger: boolean;
    corsEnabled: boolean;
  };
  umlClassDiagram?: File;
  umlSequenceDiagram?: File;
}

interface Step4Props {
  isDark: boolean;
  data: ProjectDataMDE;
  onGenerate: () => void;
  onBack: () => void;
}

export default function Step4ReviewMDE({ isDark, data, onGenerate, onBack }: Step4Props) {
  return (
    <div className="space-y-8">
      <div>
        <h2 className={`text-4xl font-bold mb-4 ${isDark ? 'text-white' : 'text-gray-900'}`}>
          ✅ Review & Generate
        </h2>
        <p className={`text-lg ${isDark ? 'text-purple-200' : 'text-purple-700'}`}>
          Review your configuration before generating the project
        </p>
      </div>

      <div className={`p-6 rounded-xl ${isDark ? 'bg-white/5 border border-purple-500/30' : 'bg-gray-50 border border-purple-200'}`}>
        <div className="flex items-center gap-2 mb-4">
          <FileText className={`w-5 h-5 ${isDark ? 'text-purple-400' : 'text-purple-600'}`} />
          <h3 className={`text-lg font-semibold ${isDark ? 'text-white' : 'text-gray-900'}`}>
            Project Information
          </h3>
        </div>
        
        <div className="space-y-2">
          <div className="flex justify-between">
            <span className={isDark ? 'text-gray-400' : 'text-gray-600'}>Project Name:</span>
            <span className={`font-semibold ${isDark ? 'text-white' : 'text-gray-900'}`}>
              {data.projectName}
            </span>
          </div>
          <div className="flex justify-between">
            <span className={isDark ? 'text-gray-400' : 'text-gray-600'}>Framework:</span>
            <span className={`font-semibold ${isDark ? 'text-white' : 'text-gray-900'}`}>
              {data.framework}
            </span>
          </div>
          <div className="flex justify-between">
            <span className={isDark ? 'text-gray-400' : 'text-gray-600'}>Class Diagram:</span>
            <span className={`font-semibold ${isDark ? 'text-green-400' : 'text-green-600'}`}>
              {data.umlClassDiagram?.name || 'None'}
            </span>
          </div>
          <div className="flex justify-between">
            <span className={isDark ? 'text-gray-400' : 'text-gray-600'}>Sequence Diagram:</span>
            <span className={`font-semibold ${isDark ? 'text-white' : 'text-gray-900'}`}>
              {data.umlSequenceDiagram?.name || 'Default CRUD'}
            </span>
          </div>
        </div>
      </div>

      <div className={`p-6 rounded-xl ${isDark ? 'bg-white/5 border border-purple-500/30' : 'bg-gray-50 border border-purple-200'}`}>
        <div className="flex items-center gap-2 mb-4">
          <Database className={`w-5 h-5 ${isDark ? 'text-cyan-400' : 'text-cyan-600'}`} />
          <h3 className={`text-lg font-semibold ${isDark ? 'text-white' : 'text-gray-900'}`}>
            Database
          </h3>
        </div>
        
        <div className="space-y-2">
          <div className="flex justify-between">
            <span className={isDark ? 'text-gray-400' : 'text-gray-600'}>Type:</span>
            <span className={`font-semibold ${isDark ? 'text-white' : 'text-gray-900'}`}>
              {data.database.type}
            </span>
          </div>
          {data.database.type !== 'SQLITE' && (
            <>
              <div className="flex justify-between">
                <span className={isDark ? 'text-gray-400' : 'text-gray-600'}>Host:</span>
                <span className={`font-semibold ${isDark ? 'text-white' : 'text-gray-900'}`}>
                  {data.database.host}:{data.database.port}
                </span>
              </div>
              <div className="flex justify-between">
                <span className={isDark ? 'text-gray-400' : 'text-gray-600'}>
                  {data.database.type === 'ORACLE' ? 'Service/SID:' : 'Database:'}
                </span>
                <span className={`font-semibold ${isDark ? 'text-white' : 'text-gray-900'}`}>
                  {data.database.name}
                </span>
              </div>
            </>
          )}
        </div>
      </div>

      <div className={`p-6 rounded-xl ${isDark ? 'bg-white/5 border border-purple-500/30' : 'bg-gray-50 border border-purple-200'}`}>
        <div className="flex items-center gap-2 mb-4">
          <Shield className={`w-5 h-5 ${isDark ? 'text-green-400' : 'text-green-600'}`} />
          <h3 className={`text-lg font-semibold ${isDark ? 'text-white' : 'text-gray-900'}`}>
            Authentication
          </h3>
        </div>
        
        <div className="flex items-center gap-2">
          {data.authentication.enabled ? (
            <>
              <CheckCircle className="w-5 h-5 text-green-500" />
              <span className={isDark ? 'text-gray-300' : 'text-gray-700'}>
                Enabled ({data.authentication.method})
              </span>
            </>
          ) : (
            <span className={isDark ? 'text-gray-500' : 'text-gray-500'}>
              Disabled
            </span>
          )}
        </div>
      </div>

      <div className={`p-6 rounded-xl ${isDark ? 'bg-white/5 border border-purple-500/30' : 'bg-gray-50 border border-purple-200'}`}>
        <div className="flex items-center gap-2 mb-4">
          <Settings className={`w-5 h-5 ${isDark ? 'text-blue-400' : 'text-blue-600'}`} />
          <h3 className={`text-lg font-semibold ${isDark ? 'text-white' : 'text-gray-900'}`}>
            API Features
          </h3>
        </div>
        
        <div className="grid grid-cols-2 gap-2">
          {Object.entries(data.apiFeatures).map(([key, value]) => (
            <div key={key} className="flex items-center gap-2">
              {value ? (
                <CheckCircle className="w-4 h-4 text-green-500" />
              ) : (
                <div className="w-4 h-4 rounded-full border-2 border-gray-500" />
              )}
              <span className={isDark ? 'text-gray-300' : 'text-gray-700'}>
                {key.charAt(0).toUpperCase() + key.slice(1).replace(/([A-Z])/g, ' $1')}
              </span>
            </div>
          ))}
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
          onClick={onGenerate}
          className={`flex-1 py-4 rounded-xl font-semibold text-lg transition-all ${
            isDark
              ? 'bg-gradient-to-r from-purple-600 to-pink-600 hover:from-purple-500 hover:to-pink-500 text-white'
              : 'bg-gradient-to-r from-purple-500 to-pink-500 hover:from-purple-400 hover:to-pink-400 text-white'
          }`}
        >
          🚀 Generate Project
        </button>
      </div>
    </div>
  );
}
