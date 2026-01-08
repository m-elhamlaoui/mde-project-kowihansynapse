import { useState } from 'react';
import { Upload, FileText } from 'lucide-react';

interface Step1Props {
  isDark: boolean;
  initialIdea: string;
  onNext: (data: { umlFile: File; description: string }) => void;
}

export default function Step1DataModelMDE({ isDark, initialIdea, onNext }: Step1Props) {
  const [file, setFile] = useState<File | null>(null);
  const [description, setDescription] = useState(initialIdea);
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

  const handleSubmit = () => {
    if (file) {
      onNext({ umlFile: file, description });
    }
  };

  return (
    <div className="space-y-8">
      <div>
        <h2 className={`text-4xl font-bold mb-4 ${isDark ? 'text-white' : 'text-gray-900'}`}>
          📐 Upload UML Class Diagram
        </h2>
        <p className={`text-lg ${isDark ? 'text-purple-200' : 'text-purple-700'}`}>
          Upload your UML class diagram (.xmi, .uml, .xml)
        </p>
      </div>

      {/* File Upload Area */}
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
          id="uml-file"
          accept=".xmi,.uml,.xml"
          onChange={handleFileChange}
          className="hidden"
        />

        {!file ? (
          <label htmlFor="uml-file" className="cursor-pointer">
            <Upload className={`w-16 h-16 mx-auto mb-4 ${isDark ? 'text-purple-400' : 'text-purple-600'}`} />
            <p className={`text-xl mb-2 ${isDark ? 'text-white' : 'text-gray-900'}`}>
              Drop your UML file here or click to browse
            </p>
            <p className={`text-sm ${isDark ? 'text-gray-400' : 'text-gray-600'}`}>
              Supports .xmi, .uml, .xml files from Papyrus, Eclipse UML
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

      {/* Description */}
      <div>
        <label className={`block text-sm font-medium mb-2 ${isDark ? 'text-gray-300' : 'text-gray-700'}`}>
          Project Description
        </label>
        <textarea
          value={description}
          onChange={(e) => setDescription(e.target.value)}
          rows={4}
          placeholder="Describe your data model and project..."
          className={`w-full px-4 py-3 rounded-xl transition-all ${
            isDark
              ? 'bg-white/5 border border-purple-500/30 text-white placeholder-gray-500'
              : 'bg-white border border-purple-300/30 text-gray-900 placeholder-gray-400'
          }`}
        />
      </div>

      {/* Next Button */}
      <button
        onClick={handleSubmit}
        disabled={!file}
        className={`w-full py-4 rounded-xl font-semibold text-lg transition-all ${
          file
            ? isDark
              ? 'bg-gradient-to-r from-purple-600 to-pink-600 hover:from-purple-500 hover:to-pink-500 text-white'
              : 'bg-gradient-to-r from-purple-500 to-pink-500 hover:from-purple-400 hover:to-pink-400 text-white'
            : 'bg-gray-500/20 text-gray-500 cursor-not-allowed'
        }`}
      >
        Next: Sequence Diagram →
      </button>
    </div>
  );
}
