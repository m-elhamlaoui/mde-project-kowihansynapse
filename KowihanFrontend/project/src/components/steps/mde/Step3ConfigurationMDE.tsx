import { useState } from 'react';
import { ArrowLeft, Database, Shield, Settings } from 'lucide-react';

interface ProjectDataMDE {
  projectName: string;
  framework: 'DJANGO' | 'FLASK' | null;
  description: string;
  database: {
    type: 'POSTGRESQL' | 'MYSQL' | 'SQLITE' | 'ORACLE' | null;
    host: string;
    port: string;
    name: string;
    username: string;
    password: string;
  };
  authentication: {
    enabled: boolean;
    method: 'JWT' | 'SESSION' | null;
    tokenExpiryMinutes: number;
  };
  apiFeatures: {
    pagination: boolean;
    filtering: boolean;
    swagger: boolean;
    corsEnabled: boolean;
  };
}

interface Step3Props {
  isDark: boolean;
  initialData: Partial<ProjectDataMDE>;
  onNext: (config: Omit<ProjectDataMDE, 'umlClassDiagram' | 'umlSequenceDiagram' | 'diagramId' | 'sequenceDiagramId' | 'configId'>) => void;
  onBack: () => void;
}

export default function Step3ConfigurationMDE({ isDark, initialData, onNext, onBack }: Step3Props) {
  const [config, setConfig] = useState<Omit<ProjectDataMDE, 'umlClassDiagram' | 'umlSequenceDiagram' | 'diagramId' | 'sequenceDiagramId' | 'configId'>>({
    projectName: initialData.projectName || '',
    framework: initialData.framework || 'DJANGO',
    description: initialData.description || '',
    database: initialData.database || {
      type: 'POSTGRESQL',
      host: 'localhost',
      port: '5432',
      name: 'app_db',
      username: 'postgres',
      password: '',
    },
    authentication: initialData.authentication || {
      enabled: false,
      method: 'JWT',
      tokenExpiryMinutes: 60,
    },
    apiFeatures: initialData.apiFeatures || {
      pagination: true,
      filtering: true,
      swagger: true,
      corsEnabled: true,
    },
  });

  const updateDatabase = (field: string, value: any) => {
    setConfig(prev => ({
      ...prev,
      database: { ...prev.database, [field]: value }
    }));
  };

  const updateAuth = (field: string, value: any) => {
    setConfig(prev => ({
      ...prev,
      authentication: { ...prev.authentication, [field]: value }
    }));
  };

  const updateFeatures = (field: string, value: boolean) => {
    setConfig(prev => ({
      ...prev,
      apiFeatures: { ...prev.apiFeatures, [field]: value }
    }));
  };

  return (
    <div className="space-y-8">
      <div>
        <h2 className={`text-4xl font-bold mb-4 ${isDark ? 'text-white' : 'text-gray-900'}`}>
          ⚙️ Project Configuration
        </h2>
        <p className={`text-lg ${isDark ? 'text-purple-200' : 'text-purple-700'}`}>
          Configure your API project settings
        </p>
      </div>

      {/* Project Name */}
      <div>
        <label className={`block text-sm font-medium mb-2 ${isDark ? 'text-gray-300' : 'text-gray-700'}`}>
          Project Name
        </label>
        <input
          type="text"
          value={config.projectName}
          onChange={(e) => setConfig({...config, projectName: e.target.value})}
          placeholder="my_awesome_api"
          className={`w-full px-4 py-3 rounded-xl transition-all ${
            isDark
              ? 'bg-white/5 border border-purple-500/30 text-white placeholder-gray-500'
              : 'bg-white border border-purple-300/30 text-gray-900 placeholder-gray-400'
          }`}
        />
      </div>

      {/* Framework - Seulement DJANGO et FLASK */}
      <div>
        <label className={`block text-sm font-medium mb-2 ${isDark ? 'text-gray-300' : 'text-gray-700'}`}>
          Framework
        </label>
        <div className="grid grid-cols-2 gap-4">
          {(['DJANGO', 'FLASK'] as const).map((fw) => (
            <button
              key={fw}
              onClick={() => setConfig({...config, framework: fw})}
              className={`py-3 px-4 rounded-xl font-semibold transition-all ${
                config.framework === fw
                  ? isDark
                    ? 'bg-purple-600 text-white'
                    : 'bg-purple-500 text-white'
                  : isDark
                  ? 'bg-white/5 text-gray-300 hover:bg-white/10'
                  : 'bg-gray-100 text-gray-700 hover:bg-gray-200'
              }`}
            >
              {fw}
            </button>
          ))}
        </div>
      </div>

      {/* Database - Avec ORACLE */}
      <div className={`p-6 rounded-xl ${isDark ? 'bg-white/5 border border-purple-500/30' : 'bg-gray-50 border border-purple-200'}`}>
        <div className="flex items-center gap-2 mb-4">
          <Database className={`w-5 h-5 ${isDark ? 'text-purple-400' : 'text-purple-600'}`} />
          <h3 className={`text-lg font-semibold ${isDark ? 'text-white' : 'text-gray-900'}`}>
            Database Configuration
          </h3>
        </div>

        <div className="grid grid-cols-2 gap-4 mb-4">
          {(['POSTGRESQL', 'MYSQL', 'SQLITE', 'ORACLE'] as const).map((db) => (
            <button
              key={db}
              onClick={() => updateDatabase('type', db)}
              className={`py-2 px-3 rounded-lg font-medium transition-all ${
                config.database.type === db
                  ? isDark
                    ? 'bg-cyan-600 text-white'
                    : 'bg-cyan-500 text-white'
                  : isDark
                  ? 'bg-white/5 text-gray-300 hover:bg-white/10'
                  : 'bg-white text-gray-700 hover:bg-gray-100'
              }`}
            >
              {db}
            </button>
          ))}
        </div>

        {config.database.type !== 'SQLITE' && (
          <div className="grid grid-cols-2 gap-4">
            <input
              type="text"
              value={config.database.host}
              onChange={(e) => updateDatabase('host', e.target.value)}
              placeholder="Host"
              className={`px-4 py-2 rounded-lg ${
                isDark ? 'bg-white/5 border border-purple-500/20 text-white' : 'bg-white border border-gray-300 text-gray-900'
              }`}
            />
            <input
              type="text"
              value={config.database.port}
              onChange={(e) => updateDatabase('port', e.target.value)}
              placeholder={config.database.type === 'ORACLE' ? '1521' : 'Port'}
              className={`px-4 py-2 rounded-lg ${
                isDark ? 'bg-white/5 border border-purple-500/20 text-white' : 'bg-white border border-gray-300 text-gray-900'
              }`}
            />
            <input
              type="text"
              value={config.database.name}
              onChange={(e) => updateDatabase('name', e.target.value)}
              placeholder={config.database.type === 'ORACLE' ? 'Service Name / SID' : 'Database Name'}
              className={`px-4 py-2 rounded-lg ${
                isDark ? 'bg-white/5 border border-purple-500/20 text-white' : 'bg-white border border-gray-300 text-gray-900'
              }`}
            />
            <input
              type="text"
              value={config.database.username}
              onChange={(e) => updateDatabase('username', e.target.value)}
              placeholder="Username"
              className={`px-4 py-2 rounded-lg ${
                isDark ? 'bg-white/5 border border-purple-500/20 text-white' : 'bg-white border border-gray-300 text-gray-900'
              }`}
            />
          </div>
        )}
      </div>

      {/* Authentication */}
      <div className={`p-6 rounded-xl ${isDark ? 'bg-white/5 border border-purple-500/30' : 'bg-gray-50 border border-purple-200'}`}>
        <div className="flex items-center gap-2 mb-4">
          <Shield className={`w-5 h-5 ${isDark ? 'text-purple-400' : 'text-purple-600'}`} />
          <h3 className={`text-lg font-semibold ${isDark ? 'text-white' : 'text-gray-900'}`}>
            Authentication
          </h3>
        </div>

        <label className="flex items-center gap-3 mb-4 cursor-pointer">
          <input
            type="checkbox"
            checked={config.authentication.enabled}
            onChange={(e) => updateAuth('enabled', e.target.checked)}
            className="w-5 h-5"
          />
          <span className={isDark ? 'text-gray-300' : 'text-gray-700'}>
            Enable Authentication
          </span>
        </label>

        {config.authentication.enabled && (
          <div className="grid grid-cols-2 gap-4">
            <select
              value={config.authentication.method || 'JWT'}
              onChange={(e) => updateAuth('method', e.target.value)}
              className={`px-4 py-2 rounded-lg ${
                isDark ? 'bg-white/5 border border-purple-500/20 text-white' : 'bg-white border border-gray-300 text-gray-900'
              }`}
            >
              <option value="JWT">JWT</option>
              <option value="SESSION">Session</option>
            </select>
            <input
              type="number"
              value={config.authentication.tokenExpiryMinutes}
              onChange={(e) => updateAuth('tokenExpiryMinutes', parseInt(e.target.value))}
              placeholder="Token Expiry (minutes)"
              className={`px-4 py-2 rounded-lg ${
                isDark ? 'bg-white/5 border border-purple-500/20 text-white' : 'bg-white border border-gray-300 text-gray-900'
              }`}
            />
          </div>
        )}
      </div>

      {/* API Features */}
      <div className={`p-6 rounded-xl ${isDark ? 'bg-white/5 border border-purple-500/30' : 'bg-gray-50 border border-purple-200'}`}>
        <div className="flex items-center gap-2 mb-4">
          <Settings className={`w-5 h-5 ${isDark ? 'text-purple-400' : 'text-purple-600'}`} />
          <h3 className={`text-lg font-semibold ${isDark ? 'text-white' : 'text-gray-900'}`}>
            API Features
          </h3>
        </div>

        <div className="space-y-3">
          {Object.entries(config.apiFeatures).map(([key, value]) => (
            <label key={key} className="flex items-center gap-3 cursor-pointer">
              <input
                type="checkbox"
                checked={value}
                onChange={(e) => updateFeatures(key, e.target.checked)}
                className="w-5 h-5"
              />
              <span className={isDark ? 'text-gray-300' : 'text-gray-700'}>
                {key.charAt(0).toUpperCase() + key.slice(1).replace(/([A-Z])/g, ' $1')}
              </span>
            </label>
          ))}
        </div>
      </div>

      {/* Buttons */}
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
          onClick={() => onNext(config)}
          disabled={!config.projectName}
          className={`flex-1 py-4 rounded-xl font-semibold text-lg transition-all ${
            config.projectName
              ? isDark
                ? 'bg-gradient-to-r from-purple-600 to-pink-600 hover:from-purple-500 hover:to-pink-500 text-white'
                : 'bg-gradient-to-r from-purple-500 to-pink-500 hover:from-purple-400 hover:to-pink-400 text-white'
              : 'bg-gray-500/20 text-gray-500 cursor-not-allowed'
          }`}
        >
          Next: Review →
        </button>
      </div>
    </div>
  );
}
