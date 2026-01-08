import { useState } from 'react';
import { Database, Check, X, Loader } from 'lucide-react';

interface DatabaseConfig {
  type: 'postgresql' | 'mysql' | 'sqlite' | 'oracle' | null;
  host: string;
  port: string;
  username: string;
  password: string;
  database: string;
}

interface Step2DatabaseProps {
  isDark: boolean;
  onNext: (data: DatabaseConfig) => void;
  onBack: () => void;
}

const databases = [
  { id: 'postgresql', name: 'PostgreSQL', color: '#336791' },
  { id: 'mysql', name: 'MySQL', color: '#4479A1' },
  { id: 'sqlite', name: 'SQLite', color: '#003B57' },
  { id: 'oracle', name: 'Oracle', color: '#47A248' },
];

export default function Step2Database({ isDark, onNext, onBack }: Step2DatabaseProps) {
  const [config, setConfig] = useState<DatabaseConfig>({
    type: null,
    host: 'localhost',
    port: '',
    username: '',
    password: '',
    database: '',
  });
  const [testing, setTesting] = useState(false);
  const [testResult, setTestResult] = useState<'success' | 'error' | null>(null);

  const handleDatabaseSelect = (type: DatabaseConfig['type']) => {
    setConfig({
      ...config,
      type,
      port: type === 'postgresql' ? '5432' : type === 'mysql' ? '3306' : type === 'oracle' ? '27017' : '',
    });
  };

  const handleTestConnection = async () => {
    setTesting(true);
    setTestResult(null);

    await new Promise((resolve) => setTimeout(resolve, 2000));

    setTestResult('success');
    setTesting(false);
  };

  const handleSubmit = () => {
    if (config.type) {
      onNext(config);
    }
  };

  const isFormValid = config.type && (config.type === 'sqlite' || (config.host && config.username && config.database));

  return (
    <div className="space-y-8">
      <div className="text-center space-y-2">
        <h2
          className={`text-4xl font-black ${isDark ? 'text-white' : 'text-[#1A0B2E]'}`}
          style={{ fontFamily: 'Orbitron, monospace' }}
        >
          Establish Connections
        </h2>
        <p className={`text-lg ${isDark ? 'text-[#E0B0FF]' : 'text-[#7B2D8A]'}`}>
          Connect to your database
        </p>
      </div>

      <div className="max-w-4xl mx-auto space-y-8">
        <div className="grid grid-cols-2 md:grid-cols-4 gap-4">
          {databases.map((db) => (
            <button
              key={db.id}
              onClick={() => handleDatabaseSelect(db.id as DatabaseConfig['type'])}
              className={`p-8 rounded-3xl transition-smooth hover:scale-105 ${
                config.type === db.id
                  ? isDark
                    ? 'bg-gradient-to-br from-[#FF1B8D] to-[#8B00FF] glow-intense'
                    : 'bg-gradient-to-br from-[#FF006E] to-[#8B00FF] glow-pink'
                  : isDark
                  ? 'bg-[#2D1B4E] border-2 border-[#FF1B8D33] hover:border-[#FF1B8D]'
                  : 'bg-white border-2 border-[#FFD6E8] hover:border-[#FF006E]'
              }`}
            >
              <div className="flex flex-col items-center space-y-3">
                <Database className={`w-12 h-12 ${config.type === db.id ? 'text-white' : isDark ? 'text-[#E0B0FF]' : 'text-[#7B2D8A]'}`} />
                <span className={`font-bold ${config.type === db.id ? 'text-white' : isDark ? 'text-white' : 'text-[#1A0B2E]'}`}>
                  {db.name}
                </span>
              </div>
            </button>
          ))}
        </div>

        {config.type && (
          <div className="space-y-6 animate-fadeInUp">
            <div
              className={`p-8 rounded-3xl space-y-6 transition-smooth ${
                isDark ? 'glass-dark' : 'glass-light'
              }`}
            >
              {config.type !== 'sqlite' && (
                <>
                  <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                    <div>
                      <label className={`block text-sm font-semibold mb-2 ${isDark ? 'text-[#E0B0FF]' : 'text-[#7B2D8A]'}`}>
                        Host
                      </label>
                      <input
                        type="text"
                        value={config.host}
                        onChange={(e) => setConfig({ ...config, host: e.target.value })}
                        className={`w-full px-4 py-3 rounded-2xl border-2 outline-none transition-smooth ${
                          isDark
                            ? 'bg-[#2D1B4E] border-[#FF1B8D33] text-white focus:border-[#FF1B8D] focus:glow-intense'
                            : 'bg-white border-[#FFD6E8] text-[#1A0B2E] focus:border-[#FF006E] focus:glow-pink'
                        }`}
                      />
                    </div>
                    <div>
                      <label className={`block text-sm font-semibold mb-2 ${isDark ? 'text-[#E0B0FF]' : 'text-[#7B2D8A]'}`}>
                        Port
                      </label>
                      <input
                        type="text"
                        value={config.port}
                        onChange={(e) => setConfig({ ...config, port: e.target.value })}
                        className={`w-full px-4 py-3 rounded-2xl border-2 outline-none transition-smooth ${
                          isDark
                            ? 'bg-[#2D1B4E] border-[#FF1B8D33] text-white focus:border-[#FF1B8D] focus:glow-intense'
                            : 'bg-white border-[#FFD6E8] text-[#1A0B2E] focus:border-[#FF006E] focus:glow-pink'
                        }`}
                      />
                    </div>
                  </div>

                  <div>
                    <label className={`block text-sm font-semibold mb-2 ${isDark ? 'text-[#E0B0FF]' : 'text-[#7B2D8A]'}`}>
                      Username
                    </label>
                    <input
                      type="text"
                      value={config.username}
                      onChange={(e) => setConfig({ ...config, username: e.target.value })}
                      className={`w-full px-4 py-3 rounded-2xl border-2 outline-none transition-smooth ${
                        isDark
                          ? 'bg-[#2D1B4E] border-[#FF1B8D33] text-white focus:border-[#FF1B8D] focus:glow-intense'
                          : 'bg-white border-[#FFD6E8] text-[#1A0B2E] focus:border-[#FF006E] focus:glow-pink'
                      }`}
                    />
                  </div>

                  <div>
                    <label className={`block text-sm font-semibold mb-2 ${isDark ? 'text-[#E0B0FF]' : 'text-[#7B2D8A]'}`}>
                      Password
                    </label>
                    <input
                      type="password"
                      value={config.password}
                      onChange={(e) => setConfig({ ...config, password: e.target.value })}
                      className={`w-full px-4 py-3 rounded-2xl border-2 outline-none transition-smooth ${
                        isDark
                          ? 'bg-[#2D1B4E] border-[#FF1B8D33] text-white focus:border-[#FF1B8D] focus:glow-intense'
                          : 'bg-white border-[#FFD6E8] text-[#1A0B2E] focus:border-[#FF006E] focus:glow-pink'
                      }`}
                    />
                  </div>
                </>
              )}

              <div>
                <label className={`block text-sm font-semibold mb-2 ${isDark ? 'text-[#E0B0FF]' : 'text-[#7B2D8A]'}`}>
                  {config.type === 'sqlite' ? 'Database File Path' : 'Database Name'}
                </label>
                <input
                  type="text"
                  value={config.database}
                  onChange={(e) => setConfig({ ...config, database: e.target.value })}
                  placeholder={config.type === 'sqlite' ? '/path/to/database.db' : 'my_database'}
                  className={`w-full px-4 py-3 rounded-2xl border-2 outline-none transition-smooth ${
                    isDark
                      ? 'bg-[#2D1B4E] border-[#FF1B8D33] text-white focus:border-[#FF1B8D] focus:glow-intense placeholder-[#E0B0FF]'
                      : 'bg-white border-[#FFD6E8] text-[#1A0B2E] focus:border-[#FF006E] focus:glow-pink placeholder-[#7B2D8A]'
                  }`}
                />
              </div>

              {isFormValid && (
                <button
                  onClick={handleTestConnection}
                  disabled={testing}
                  className={`w-full py-3 rounded-2xl font-semibold transition-smooth flex items-center justify-center space-x-2 ${
                    testing
                      ? 'bg-gray-400 cursor-not-allowed'
                      : testResult === 'success'
                      ? isDark
                        ? 'bg-[#39FF14] text-black'
                        : 'bg-[#00FF9D] text-black'
                      : testResult === 'error'
                      ? isDark
                        ? 'bg-[#FF0055] text-white'
                        : 'bg-[#FF3366] text-white'
                      : isDark
                      ? 'bg-[#00FFFF] text-black hover:scale-105'
                      : 'bg-[#00F5FF] text-black hover:scale-105'
                  }`}
                >
                  {testing ? (
                    <>
                      <Loader className="w-5 h-5 animate-spin" />
                      <span>Testing Connection...</span>
                    </>
                  ) : testResult === 'success' ? (
                    <>
                      <Check className="w-5 h-5" />
                      <span>Connection Successful</span>
                    </>
                  ) : testResult === 'error' ? (
                    <>
                      <X className="w-5 h-5" />
                      <span>Connection Failed</span>
                    </>
                  ) : (
                    <span>Test Connection</span>
                  )}
                </button>
              )}
            </div>
          </div>
        )}

        <div className="flex space-x-4">
          <button
            onClick={onBack}
            className={`flex-1 py-4 rounded-2xl font-bold text-lg transition-smooth ${
              isDark
                ? 'bg-[#2D1B4E] text-[#E0B0FF] border-2 border-[#FF1B8D33] hover:border-[#FF1B8D]'
                : 'bg-white text-[#7B2D8A] border-2 border-[#FFD6E8] hover:border-[#FF006E]'
            }`}
          >
            Back
          </button>
          <button
            onClick={handleSubmit}
            disabled={!isFormValid}
            className={`flex-1 py-4 rounded-2xl font-bold text-lg transition-smooth ${
              isFormValid
                ? isDark
                  ? 'bg-gradient-to-r from-[#FF1B8D] via-[#8B00FF] to-[#00FFFF] text-white glow-intense hover:scale-105 animate-gradient'
                  : 'bg-gradient-to-r from-[#FF006E] via-[#8B00FF] to-[#00F5FF] text-white glow-multi hover:scale-105 animate-gradient'
                : 'bg-gray-300 text-gray-500 cursor-not-allowed'
            }`}
            style={{ backgroundSize: '200% 200%' }}
          >
            Continue to Features
          </button>
        </div>
      </div>
    </div>
  );
}