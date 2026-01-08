import { useState } from 'react';
import { Upload, FileText, ArrowLeft } from 'lucide-react';

interface Step2Props {
  isDark: boolean;
  onNext: (file?: File) => void;
  onBack: () => void;
}

export default function Step2SequenceDiagram({ isDark, onNext, onBack }: Step2Props) {
  const [file, setFile] = useState<File | null>(null);
  const [dragActive, setDragActive] = useState(false);

  const handleDrag = (e: React.DragEvent) => {
    e.preventDefault();
    e.stopPropagation();
    if (e.type === 'dragenter' || e.type === 'dragover') {
      setDragActive(true);
    } else if (e.type === 'dragleave') {
      setDragActive(false);
    }
  };

  const handleDrop = (e: React.DragEvent) => {
    e.preventDefault();
    e.stopPropagation();
    setDragActive(false);

    if (e.dataTransfer.files && e.dataTransfer.files[0]) {
      setFile(e.dataTransfer.files[0]);
    }
  };

  const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (e.target.files && e.target.files[0]) {
      setFile(e.target.files[0]);
    }
  };

  return (
    <div className="space-y-8">
      <div>
        <h2 className={`text-4xl font-bold mb-4 ${isDark ? 'text-white' : 'text-gray-900'}`}>
          🔄 Upload Sequence Diagram (Optional)
        </h2>
        <p className={`text-lg ${isDark ? 'text-purple-200' : 'text-purple-700'}`}>
          Define custom interactions or skip to use default CRUD operations
        </p>
      </div>

      <div
        onDragEnter={handleDrag}
        onDragLeave={handleDrag}
        onDragOver={handleDrag}
        onDrop={handleDrop}
        className={`border-2 border-dashed rounded-2xl p-12 text-center transition-all ${
          dragActive
            ? 'border-purple-500 bg-purple-500/10'
            : isDark
            ? 'border-purple-500/30 hover:border-purple-500/60'
            : 'border-purple-300/30 hover:border-purple-300/60'
        }`}
      >
        <input
          type="file"
          id="sequence-file"
          accept=".xmi,.uml,.xml"
          onChange={handleFileChange}
          className="hidden"
        />

        {!file ? (
          <label htmlFor="sequence-file" className="cursor-pointer">
            <Upload className={`w-16 h-16 mx-auto mb-4 ${isDark ? 'text-purple-400' : 'text-purple-600'}`} />
            <p className={`text-xl mb-2 ${isDark ? 'text-white' : 'text-gray-900'}`}>
              Drop your sequence diagram here (optional)
            </p>
            <p className={`text-sm ${isDark ? 'text-gray-400' : 'text-gray-600'}`}>
              Supports .xmi, .uml, .xml files
            </p>
          </label>
        ) : (
          <div className="flex items-center justify-center gap-3">
            <FileText className={`w-8 h-8 ${isDark ? 'text-green-400' : 'text-green-600'}`} />
            <div className="text-left">
              <p className={`font-semibold ${isDark ? 'text-white' : 'text-gray-900'}`}>
                {file.name}
              </p>
              <p className={`text-sm ${isDark ? 'text-gray-400' : 'text-gray-600'}`}>
                {(file.size / 1024).toFixed(2)} KB
              </p>
            </div>
            <button
              onClick={() => setFile(null)}
              className={`ml-4 px-3 py-1 rounded ${
                isDark ? 'bg-red-500/20 text-red-400' : 'bg-red-100 text-red-600'
              }`}
            >
              Remove
            </button>
          </div>
        )}
      </div>

      <div className={`p-4 rounded-xl ${isDark ? 'bg-blue-500/10 border border-blue-500/30' : 'bg-blue-50 border border-blue-200'}`}>
        <p className={`text-sm ${isDark ? 'text-blue-300' : 'text-blue-700'}`}>
          💡 If you skip this step, the system will generate default CRUD endpoints for all entities in your class diagram.
        </p>
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
          onClick={() => onNext(file || undefined)}
          className={`flex-1 py-4 rounded-xl font-semibold text-lg transition-all ${
            isDark
              ? 'bg-gradient-to-r from-purple-600 to-pink-600 hover:from-purple-500 hover:to-pink-500 text-white'
              : 'bg-gradient-to-r from-purple-500 to-pink-500 hover:from-purple-400 hover:to-pink-400 text-white'
          }`}
        >
          Next: Configuration →
        </button>
      </div>
    </div>
  );
}
