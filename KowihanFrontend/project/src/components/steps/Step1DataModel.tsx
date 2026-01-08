import { useState } from 'react';
import { Sparkles, Upload, FileCode } from 'lucide-react';

interface Step1DataModelProps {
  isDark: boolean;
  initialIdea: string;
  onNext: (data: { description: string; umlFile?: File }) => void;
}

export default function Step1DataModel({ isDark, initialIdea, onNext }: Step1DataModelProps) {
  const [activeTab, setActiveTab] = useState<'ai' | 'uml'>('ai');
  const [description, setDescription] = useState(initialIdea);
  const [umlFile, setUmlFile] = useState<File | null>(null);
  const [isDragging, setIsDragging] = useState(false);

  const handleFileUpload = (e: React.ChangeEvent<HTMLInputElement>) => {
    const file = e.target.files?.[0];
    if (file) {
      setUmlFile(file);
    }
  };

  const handleDrop = (e: React.DragEvent) => {
    e.preventDefault();
    setIsDragging(false);
    const file = e.dataTransfer.files[0];
    if (file) {
      setUmlFile(file);
    }
  };

  const handleSubmit = () => {
    onNext({ description, umlFile: umlFile || undefined });
  };

  const highlightKeywords = (text: string) => {
    const keywords = ['user', 'post', 'comment', 'api', 'database', 'model', 'entity', 'table'];
    let highlighted = text;

    keywords.forEach((keyword) => {
      const regex = new RegExp(`\\b${keyword}\\b`, 'gi');
      highlighted = highlighted.replace(
        regex,
        `<span class="text-[${isDark ? '#FF1B8D' : '#FF006E'}] font-semibold">$&</span>`
      );
    });

    return highlighted;
  };

  return (
    <div className="space-y-8">
      <div className="text-center space-y-2">
        <h2
          className={`text-4xl font-black ${isDark ? 'text-white' : 'text-[#1A0B2E]'}`}
          style={{ fontFamily: 'Orbitron, monospace' }}
        >
          Define Core Concepts
        </h2>
        <p className={`text-lg ${isDark ? 'text-[#E0B0FF]' : 'text-[#7B2D8A]'}`}>
          Describe your data model or upload a structural blueprint
        </p>
      </div>

      <div className="flex justify-center space-x-4">
        <button
          onClick={() => setActiveTab('ai')}
          className={`px-8 py-3 rounded-2xl font-semibold transition-smooth ${
            activeTab === 'ai'
              ? isDark
                ? 'bg-gradient-to-r from-[#FF1B8D] to-[#8B00FF] text-white glow-intense'
                : 'bg-gradient-to-r from-[#FF006E] to-[#8B00FF] text-white glow-pink'
              : isDark
              ? 'bg-[#2D1B4E] text-[#E0B0FF] border-2 border-[#FF1B8D33]'
              : 'bg-white text-[#7B2D8A] border-2 border-[#FFD6E8]'
          }`}
        >
          <div className="flex items-center space-x-2">
            <Sparkles className="w-5 h-5" />
            <span>AI Synapse</span>
          </div>
        </button>

        <button
          onClick={() => setActiveTab('uml')}
          className={`px-8 py-3 rounded-2xl font-semibold transition-smooth ${
            activeTab === 'uml'
              ? isDark
                ? 'bg-gradient-to-r from-[#00FFFF] to-[#8B00FF] text-white glow-cyan'
                : 'bg-gradient-to-r from-[#00F5FF] to-[#8B00FF] text-white glow-cyan'
              : isDark
              ? 'bg-[#2D1B4E] text-[#E0B0FF] border-2 border-[#FF1B8D33]'
              : 'bg-white text-[#7B2D8A] border-2 border-[#FFD6E8]'
          }`}
        >
          <div className="flex items-center space-x-2">
            <FileCode className="w-5 h-5" />
            <span>Structural Blueprint</span>
          </div>
        </button>
      </div>

      <div className="max-w-4xl mx-auto">
        {activeTab === 'ai' ? (
          <div className="space-y-4">
            <div className="relative">
              <textarea
                value={description}
                onChange={(e) => setDescription(e.target.value)}
                rows={12}
                placeholder="Describe your data model in detail..."
                className={`w-full px-6 py-4 rounded-3xl border-2 outline-none transition-smooth resize-none font-mono text-sm ${
                  isDark
                    ? 'bg-[#2D1B4E] border-[#FF1B8D] text-white placeholder-[#E0B0FF] glow-intense'
                    : 'bg-white border-[#FF006E] text-[#1A0B2E] placeholder-[#7B2D8A] glow-pink'
                }`}
                style={{
                  backdropFilter: 'blur(20px)',
                }}
              />
              {description && (
                <div className="absolute top-4 right-4">
                  <div className="flex items-center space-x-2 px-3 py-1 rounded-full bg-[#00FF9D] bg-opacity-20">
                    <div className="w-2 h-2 rounded-full bg-[#00FF9D] animate-pulse-neon" />
                    <span className="text-xs font-semibold text-[#00FF9D]">Analyzing</span>
                  </div>
                </div>
              )}
            </div>

            <button
              onClick={handleSubmit}
              disabled={!description.trim()}
              className={`w-full py-4 rounded-2xl font-bold text-lg transition-smooth ${
                description.trim()
                  ? isDark
                    ? 'bg-gradient-to-r from-[#FF1B8D] via-[#8B00FF] to-[#00FFFF] text-white glow-intense hover:scale-105 animate-gradient'
                    : 'bg-gradient-to-r from-[#FF006E] via-[#8B00FF] to-[#00F5FF] text-white glow-multi hover:scale-105 animate-gradient'
                  : 'bg-gray-300 text-gray-500 cursor-not-allowed'
              }`}
              style={{ backgroundSize: '200% 200%' }}
            >
              Continue to Connections
            </button>
          </div>
        ) : (
          <div className="space-y-4">
            <div
              onDragOver={(e) => {
                e.preventDefault();
                setIsDragging(true);
              }}
              onDragLeave={() => setIsDragging(false)}
              onDrop={handleDrop}
              className={`relative border-4 border-dashed rounded-3xl p-12 transition-smooth ${
                isDragging
                  ? isDark
                    ? 'border-[#FF1B8D] bg-[#2D1B4E] glow-intense scale-105'
                    : 'border-[#FF006E] bg-[#FFF0F8] glow-pink scale-105'
                  : isDark
                  ? 'border-[#FF1B8D33] bg-[#2D1B4E]'
                  : 'border-[#FFD6E8] bg-white'
              }`}
            >
              {!umlFile ? (
                <div className="text-center space-y-4">
                  <div className={`inline-block p-6 rounded-3xl ${
                    isDark
                      ? 'bg-gradient-to-br from-[#FF1B8D] to-[#8B00FF] animate-float'
                      : 'bg-gradient-to-br from-[#FF006E] to-[#8B00FF] animate-float'
                  }`}>
                    <Upload className="w-12 h-12 text-white" />
                  </div>
                  <p className={`text-xl font-semibold ${isDark ? 'text-white' : 'text-[#1A0B2E]'}`}>
                    Drop your UML file here
                  </p>
                  <p className={`text-sm ${isDark ? 'text-[#E0B0FF]' : 'text-[#7B2D8A]'}`}>
                    or click to browse
                  </p>
                  <input
                    type="file"
                    onChange={handleFileUpload}
                    accept=".uml,.xml,.xmi"
                    className="absolute inset-0 w-full h-full opacity-0 cursor-pointer"
                  />
                </div>
              ) : (
                <div className="text-center space-y-4">
                  <div className={`inline-block p-6 rounded-3xl ${
                    isDark
                      ? 'bg-gradient-to-br from-[#39FF14] to-[#00FFFF] glow-cyan'
                      : 'bg-gradient-to-br from-[#00FF9D] to-[#00F5FF] glow-cyan'
                  }`}>
                    <FileCode className="w-12 h-12 text-white" />
                  </div>
                  <p className={`text-xl font-semibold ${isDark ? 'text-white' : 'text-[#1A0B2E]'}`}>
                    {umlFile.name}
                  </p>
                  <button
                    onClick={() => setUmlFile(null)}
                    className={`px-6 py-2 rounded-xl font-semibold transition-smooth ${
                      isDark
                        ? 'bg-[#FF0055] text-white hover:scale-105'
                        : 'bg-[#FF3366] text-white hover:scale-105'
                    }`}
                  >
                    Remove
                  </button>
                </div>
              )}
            </div>

            <button
              onClick={handleSubmit}
              disabled={!umlFile}
              className={`w-full py-4 rounded-2xl font-bold text-lg transition-smooth ${
                umlFile
                  ? isDark
                    ? 'bg-gradient-to-r from-[#00FFFF] via-[#8B00FF] to-[#FF1B8D] text-white glow-cyan hover:scale-105 animate-gradient'
                    : 'bg-gradient-to-r from-[#00F5FF] via-[#8B00FF] to-[#FF006E] text-white glow-cyan hover:scale-105 animate-gradient'
                  : 'bg-gray-300 text-gray-500 cursor-not-allowed'
              }`}
              style={{ backgroundSize: '200% 200%' }}
            >
              Continue to Connections
            </button>
          </div>
        )}
      </div>
    </div>
  );
}