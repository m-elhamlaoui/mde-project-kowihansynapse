import { useState } from 'react';
import LandingPage from './components/LandingPage';
import Header from './components/Header';
import NeuralPathway from './components/NeuralPathway';
import ModeSelection from './components/ModeSelection';
// MDE Steps
import Step1DataModelMDE from './components/steps/mde/Step1DataModelMDE';
import Step2SequenceDiagram from './components/steps/mde/Step2SequenceDiagram';
import Step3ConfigurationMDE from './components/steps/mde/Step3ConfigurationMDE';
import Step4ReviewMDE from './components/steps/mde/Step4ReviewMDE';
// IA Steps
import Step1ObjectiveIA from './components/steps/ia/Step1ObjectiveIA';
import Step2DatabaseIA from './components/steps/ia/Step2DatabaseIA';
import Step3FrameworkIA from './components/steps/ia/Step3FrameworkIA';
import Step4EntitiesIA from './components/steps/ia/Step4EntitiesIA';
import Step5ReviewIA from './components/steps/ia/Step5ReviewIA';
// Generation
import GenerationScreen from './components/GenerationScreen';

type Screen = 'landing' | 'mode-selection' | 'workspace' | 'generating' | 'complete';
type GenerationMode = 'mde' | 'ia' | null;
type WorkflowStepMDE = 1 | 2 | 3 | 4;
type WorkflowStepIA = 1 | 2 | 3 | 4 | 5;

interface ProjectDataMDE {
  projectName: string;
  framework: 'DJANGO' | 'FLASK' | null;
  description: string;
  database: {
    type: 'POSTGRESQL' | 'MYSQL' | 'SQLITE' | 'ORACLE'|null;
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
  umlClassDiagram?: File;
  umlSequenceDiagram?: File;
  diagramId?: string;
  sequenceDiagramId?: string;
  configId?: string;
}

interface ProjectDataIA {
  sessionId: string | null;
  objective: string;
  database: 'PostgreSQL' | 'MySQL' | 'SQLite' |  'Oracle' |null;
  framework: 'Django' | 'Flask'  | null;
  entities: string;
  currentStep: 'objectif' | 'database' | 'framework' | 'entities' | 'confirmation' | 'complete';
}

function App() {
  const [isDark, setIsDark] = useState(true);
  const [currentScreen, setCurrentScreen] = useState<Screen>('landing');
  const [generationMode, setGenerationMode] = useState<GenerationMode>(null);
  const [currentStepMDE, setCurrentStepMDE] = useState<WorkflowStepMDE>(1);
  const [currentStepIA, setCurrentStepIA] = useState<WorkflowStepIA>(1);
  
  const [projectDataMDE, setProjectDataMDE] = useState<ProjectDataMDE>({
    projectName: '',
    framework: 'DJANGO',
    description: '',
    database: {
      type: null,
      host: 'localhost',
      port: '5432',
      name: '',
      username: '',
      password: '',
    },
    authentication: {
      enabled: false,
      method: null,
      tokenExpiryMinutes: 60,
    },
    apiFeatures: {
      pagination: true,
      filtering: true,
      swagger: true,
      corsEnabled: true,
    },
  });

  const [projectDataIA, setProjectDataIA] = useState<ProjectDataIA>({
    sessionId: null,
    objective: '',
    database: null,
    framework: null,
    entities: '',
    currentStep: 'objectif',
  });
const [activeIASessionId, setActiveIASessionId] = useState<string | null>(null);
  const stepsMDE = [
    { id: 1, label: 'UML Class Diagram', completed: currentStepMDE > 1, active: currentStepMDE === 1 },
    { id: 2, label: 'Sequence Diagram', completed: currentStepMDE > 2, active: currentStepMDE === 2 },
    { id: 3, label: 'Configuration', completed: currentStepMDE > 3, active: currentStepMDE === 3 },
    { id: 4, label: 'Review', completed: currentStepMDE > 4, active: currentStepMDE === 4 },
  ];

  const stepsIA = [
    { id: 1, label: 'Objective', completed: currentStepIA > 1, active: currentStepIA === 1 },
    { id: 2, label: 'Database', completed: currentStepIA > 2, active: currentStepIA === 2 },
    { id: 3, label: 'Framework', completed: currentStepIA > 3, active: currentStepIA === 3 },
    { id: 4, label: 'Entities', completed: currentStepIA > 4, active: currentStepIA === 4 },
    { id: 5, label: 'Review', completed: currentStepIA > 5, active: currentStepIA === 5 },
  ];

  
  const handleLandingSubmit = (idea: string) => {
    setProjectDataMDE((prev) => ({
      ...prev,
      description: idea,
      projectName: idea.substring(0, 50),
    }));
    setProjectDataIA((prev) => ({
      ...prev,
      objective: idea,
    }));
    setCurrentScreen('mode-selection');
  };

  // Mode Selection
  const handleModeSelection = (mode: 'mde' | 'ia') => {
    setGenerationMode(mode);
    setCurrentScreen('workspace');
    
    if (mode === 'ia') {
      // Demarrer une session IA
      startIASession();
    }
  };

  // IA Session Management
  const startIASession = async () => {
    try {
      const response = await fetch('http://localhost:5000/api/ia/start', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
      });
      
      const data = await response.json();
      
      if (data.success && data.session_id) {
        setProjectDataIA((prev) => ({
          ...prev,
          sessionId: data.session_id,
          currentStep: data.step,
        }));
      }
    } catch (error) {
      console.error('Error starting IA session:', error);
    }
  };
  const [isProcessing, setIsProcessing] = useState(false);
  const processIAInput = async (userInput: string) => {
  if (!projectDataIA.sessionId) return;
  
  setIsProcessing(true); 
  
  try {
    const response = await fetch('http://localhost:5000/api/ia/process', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        session_id: projectDataIA.sessionId,
        user_input: userInput,
      }),
    });
    
    const data = await response.json();
    
    if (data.success) {
      setProjectDataIA((prev) => ({
        ...prev,
        currentStep: data.step,
      }));
      
      // Avancer l'étape dans l'UI
      if (data.step === 'database' && currentStepIA === 1) setCurrentStepIA(2);
      else if (data.step === 'framework' && currentStepIA === 2) setCurrentStepIA(3);
      else if (data.step === 'entities' && currentStepIA === 3) setCurrentStepIA(4);
      else if (data.step === 'confirmation' && currentStepIA === 4) setCurrentStepIA(5);
      else if (data.step === 'complete') setCurrentScreen('generating');
    }
    
    return data;
  } catch (error) {
    console.error('Error processing IA input:', error);
    alert('Error processing your input. Please try again.');
  } finally {
    setIsProcessing(false); 
  }
};
  // MDE Workflow Handlers
  const handleStep1MDENext = async (data: { umlFile: File; description: string }) => {
    const formData = new FormData();
    formData.append('xmi_file', data.umlFile);

    try {
      const response = await fetch('http://localhost:5000/api/diagram/upload/xmi', {
        method: 'POST',
        body: formData,
      });

      const result = await response.json();

      if (result.success) {
        setProjectDataMDE((prev) => ({
          ...prev,
          umlClassDiagram: data.umlFile,
          description: data.description,
          diagramId: result.diagram_id,
        }));
        setCurrentStepMDE(2);
      }
    } catch (error) {
      console.error('Error uploading UML diagram:', error);
    }
  };

  const handleStep2MDENext = async (sequenceFile?: File) => {
    if (sequenceFile && projectDataMDE.diagramId) {
      const formData = new FormData();
      formData.append('sequence_file', sequenceFile);
      formData.append('class_diagram_id', projectDataMDE.diagramId);

      try {
        const response = await fetch('http://localhost:5000/api/interactions/upload/sequence', {
          method: 'POST',
          body: formData,
        });

        const result = await response.json();

        if (result.success) {
          setProjectDataMDE((prev) => ({
            ...prev,
            umlSequenceDiagram: sequenceFile,
            sequenceDiagramId: result.diagram_id,
          }));
        }
      } catch (error) {
        console.error('Error uploading sequence diagram:', error);
      }
    }
    
    setCurrentStepMDE(3);
  };

  const handleStep3MDENext = async (config: Omit<ProjectDataMDE, 'umlClassDiagram' | 'umlSequenceDiagram' | 'diagramId' | 'sequenceDiagramId' | 'configId'>) => {
    try {
      const response = await fetch('http://localhost:5000/api/config/save', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(config),
      });

      const result = await response.json();

      if (result.success) {
        setProjectDataMDE((prev) => ({
          ...prev,
          ...config,
          configId: result.config_id,
        }));
        setCurrentStepMDE(4);
      }
    } catch (error) {
      console.error('Error saving configuration:', error);
    }
  };

  const handleGenerateMDE = async () => {
    if (!projectDataMDE.diagramId || !projectDataMDE.configId) return;

    setCurrentScreen('generating');

    try {
      const payload: any = {
        diagram_id: projectDataMDE.diagramId,
        config_id: projectDataMDE.configId,
      };

      if (projectDataMDE.sequenceDiagramId) {
        payload.sequence_diagram_ids = [projectDataMDE.sequenceDiagramId];
      }

      const response = await fetch('http://localhost:5000/api/generate/project', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload),
      });

      const result = await response.json();

      if (result.success) {
        // Télécharger le projet
        const downloadUrl = result.download_url;
        window.location.href = `http://localhost:5000${downloadUrl}`;
        
        setTimeout(() => {
          setCurrentScreen('complete');
        }, 2000);
      }
    } catch (error) {
      console.error('Error generating project:', error);
    }
  };

  // IA Workflow Handlers
  const handleStep1IANext = async (objective: string) => {
    const result = await processIAInput(objective);
    if (result?.success) {
      setProjectDataIA((prev) => ({
        ...prev,
        objective,
      }));
    }
  };

  const handleStep2IANext = async (database: string) => {
    const result = await processIAInput(database);
    if (result?.success) {
      setProjectDataIA((prev) => ({
        ...prev,
        database: database as any,
      }));
    }
  };

  const handleStep3IANext = async (framework: string) => {
  setIsProcessing(true);
  
  try {
    const result = await processIAInput(framework);
    
    console.log('Step 3 (Framework) result:', result);
    
    if (result?.success) {
      setProjectDataIA((prev) => ({
        ...prev,
        framework: framework as any,
      }));
      
     
      setCurrentStepIA(4);
    } else {
      console.error('Step 3 failed:', result);
      alert('Error processing framework. Please try again.');
    }
  } catch (error) {
    console.error('Error in handleStep3IANext:', error);
    alert('An error occurred. Please try again.');
  } finally {
    setIsProcessing(false);
  }
};

  const handleStep4IANext = async (entities: string) => {
    const result = await processIAInput(entities);
    if (result?.success) {
      setProjectDataIA((prev) => ({
        ...prev,
        entities,
      }));
    }
  };

 const handleGenerateIA = async () => {
  setIsProcessing(true);
  
  try {
    console.log('Confirming generation...');
    
    const currentSessionId = projectDataIA.sessionId;
    
    if (!currentSessionId) {
      alert('Session ID is missing.');
      setIsProcessing(false);
      return;
    }
    
    console.log('Using sessionId:', currentSessionId);
    
    const result = await processIAInput('oui');
    
    console.log('Confirmation result:', result);
    
    if (!result?.success) {
      alert('Error confirming generation.');
      setIsProcessing(false);
      return;
    }
    
    const finalSessionId = result.session_id || currentSessionId;
    
    console.log('Final sessionId for download:', finalSessionId);
    
    setProjectDataIA((prev) => ({
      ...prev,
      sessionId: finalSessionId,
    }));
    
    setActiveIASessionId(finalSessionId);
    
    setCurrentScreen('generating');
    setIsProcessing(false);
    
  } catch (error) {
    console.error('Error:', error);
    alert('An error occurred.');
    setIsProcessing(false);
  }
};
  const handleToggleTheme = () => {
    setIsDark(!isDark);
  };


  if (currentScreen === 'landing') {
    return <LandingPage isDark={isDark} onSubmit={handleLandingSubmit} />;
  }

  if (currentScreen === 'mode-selection') {
    return (
      <ModeSelection
        isDark={isDark}
        onSelectMode={handleModeSelection}
        onBack={() => setCurrentScreen('landing')}
      />
    );
  }

  if (currentScreen === 'generating' || currentScreen === 'complete') {

  const sessionIdToPass = generationMode === 'ia' 
    ? (activeIASessionId || projectDataIA.sessionId || undefined) 
    : undefined;
  
  console.log('Passing sessionId to GenerationScreen:', {
    mode: generationMode,
    activeIASessionId,
    projectDataIASessionId: projectDataIA.sessionId,
    finalSessionId: sessionIdToPass
  });
  
  return (
    <GenerationScreen
      isDark={isDark}
      mode={generationMode!}
      onComplete={() => setCurrentScreen('complete')}
      sessionId={sessionIdToPass}  
    />
  );
}

  const currentSteps = generationMode === 'mde' ? stepsMDE : stepsIA;

  return (
    <div
      className={`min-h-screen transition-colors duration-1000 ${
        isDark
          ? 'bg-gradient-to-br from-[#0A0118] via-[#1A0B2E] to-[#0A0118]'
          : 'bg-gradient-to-br from-[#FFF8F0] via-[#FFF0F8] to-[#FFF8F0]'
      }`}
    >
      <Header
        isDark={isDark}
        onToggleTheme={handleToggleTheme}
        mode={generationMode}
        onBackToModeSelection={() => {
          setCurrentScreen('mode-selection');
          setCurrentStepMDE(1);
          setCurrentStepIA(1);
        }}
      />

      <NeuralPathway steps={currentSteps} isDark={isDark} />

      <div className="max-w-7xl mx-auto px-8 pb-16">
        <div
          className={`rounded-3xl p-12 transition-all duration-500 ${
            isDark ? 'glass-dark' : 'glass-light'
          }`}
        >
          {/* MDE Workflow */}
          {generationMode === 'mde' && (
            <>
              {currentStepMDE === 1 && (
                <Step1DataModelMDE
                  isDark={isDark}
                  initialIdea={projectDataMDE.description}
                  onNext={handleStep1MDENext}
                />
              )}

              {currentStepMDE === 2 && (
                <Step2SequenceDiagram
                  isDark={isDark}
                  onNext={handleStep2MDENext}
                  onBack={() => setCurrentStepMDE(1)}
                />
              )}

              {currentStepMDE === 3 && (
                <Step3ConfigurationMDE
                  isDark={isDark}
                  onNext={handleStep3MDENext}
                  onBack={() => setCurrentStepMDE(2)}
                  initialData={projectDataMDE}
                />
              )}

              {currentStepMDE === 4 && (
                <Step4ReviewMDE
                  isDark={isDark}
                  data={projectDataMDE}
                  onGenerate={handleGenerateMDE}
                  onBack={() => setCurrentStepMDE(3)}
                />
              )}
            </>
          )}

          {/* IA Workflow */}
          {generationMode === 'ia' && (
            <>
              {currentStepIA === 1 && (
                <Step1ObjectiveIA
                  isDark={isDark}
                  initialIdea={projectDataIA.objective}
                  onNext={handleStep1IANext}
                />
              )}

              {currentStepIA === 2 && (
                <Step2DatabaseIA
                  isDark={isDark}
                  onNext={handleStep2IANext}
                  onBack={() => setCurrentStepIA(1)}
                />
              )}

              {currentStepIA === 3 && (
                <Step3FrameworkIA
                  isDark={isDark}
                  onNext={handleStep3IANext}
                  onBack={() => setCurrentStepIA(2)}
                />
              )}

              {currentStepIA === 4 && (
                <Step4EntitiesIA
                  isDark={isDark}
                  onNext={handleStep4IANext}
                  onBack={() => setCurrentStepIA(3)}
                />
              )}

              {currentStepIA === 5 && (
          <Step5ReviewIA
            isDark={isDark}
            data={projectDataIA}
            onGenerate={handleGenerateIA}
            onBack={() => setCurrentStepIA(4)}
            isProcessing={isProcessing}  
          />
        )}
            </>
          )}
        </div>
      </div>
    </div>
  );
}

export default App;