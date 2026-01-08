// src/config/api.ts

export const API_BASE_URL = import.meta.env.VITE_API_URL || 'http://localhost:5000';

export const API_ENDPOINTS = {
  // Common
  health: '/api/health',
  compareMode: '/api/compare/modes',
  compareStatus: '/api/compare/status',
  
  // MDE Endpoints
  mde: {
    configSave: '/api/config/save',
    configList: '/api/config/list',
    uploadClassDiagram: '/api/diagram/upload/xmi',
    uploadSequenceDiagram: '/api/interactions/upload/sequence',
    analyzeClassDiagram: (id: string) => `/api/diagram/analyze/${id}`,
    analyzeSequenceDiagram: (id: string) => `/api/interactions/analyze/${id}`,
    generateProject: '/api/generate/project',
    downloadProject: (filename: string) => `/api/generate/download/${filename}`,
    springBootStatus: '/api/generate/spring-boot-status',
    listSequenceDiagrams: '/api/interactions/list/sequence',
    listClassDiagrams: '/api/diagram/list/xmi',
  },
  
  // IA Endpoints
  ia: {
    start: '/api/ia/start',
    process: '/api/ia/process',
    download: (sessionId: string) => `/api/ia/download/${sessionId}`,
    recommendations: (sessionId: string) => `/api/ia/recommendations/${sessionId}`,
    sessions: '/api/ia/sessions',
    health: '/api/ia/health',
  },
};

// Types
export interface MDEConfig {
  projectName: string;
  framework: 'DJANGO' | 'FLASK';
  description: string;
  pythonVersion?: string;
  database: {
    type: 'POSTGRESQL' | 'MYSQL' | 'SQLITE' | 'ORACLE';
    host: string;
    port: string | number;
    name: string;
    username?: string;
    password?: string;
  };
  authentication: {
    enabled: boolean;
    method: 'JWT' | 'SESSION';
    tokenExpiryMinutes: number;
  };
  apiFeatures: {
    pagination: boolean;
    filtering: boolean;
    swagger: boolean;
    corsEnabled: boolean;
  };
}

export interface MDEGenerateRequest {
  diagram_id: string;
  config_id: string;
  sequence_diagram_ids?: string[];
}

export interface IAProcessRequest {
  session_id: string;
  user_input: string;
}

export interface IAStartResponse {
  success: boolean;
  session_id: string;
  step: 'objectif' | 'database' | 'framework' | 'entities' | 'confirmation' | 'complete';
  message: string;
  examples?: string[];
  timestamp: string;
}

export interface IAProcessResponse {
  success: boolean;
  step: 'objectif' | 'database' | 'framework' | 'entities' | 'confirmation' | 'complete';
  message: string;
  options?: string[];
  suggestions?: any;
  timestamp: string;
}

export interface HealthCheckResponse {
  service: string;
  status: 'healthy' | 'unhealthy';
  version: string;
  modes: {
    mde: {
      status: 'available' | 'unavailable';
      active_sessions: number;
      spring_boot_url: string;
    };
    ia: {
      status: 'available' | 'unavailable';
      active_sessions: number;
      ia_service_url: string;
    };
  };
  total_sessions: number;
}

// API Helper Functions
export class APIClient {
  static async get(endpoint: string) {
    const response = await fetch(`${API_BASE_URL}${endpoint}`);
    if (!response.ok) {
      throw new Error(`HTTP ${response.status}: ${response.statusText}`);
    }
    return response.json();
  }

  static async post(endpoint: string, data?: any) {
    const response = await fetch(`${API_BASE_URL}${endpoint}`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: data ? JSON.stringify(data) : undefined,
    });
    
    const contentType = response.headers.get('content-type');
    if (contentType && contentType.includes('application/json')) {
      return response.json();
    }
    
    // Si ce n'est pas du JSON, retourner le texte
    const text = await response.text();
    throw new Error(`Expected JSON but got: ${text.substring(0, 100)}`);
  }

  static async uploadFile(endpoint: string, formData: FormData) {
    const response = await fetch(`${API_BASE_URL}${endpoint}`, {
      method: 'POST',
      body: formData,
    });
    return response.json();
  }

  static async download(endpoint: string, filename: string) {
    const response = await fetch(`${API_BASE_URL}${endpoint}`);
    
    if (!response.ok) {
      const error = await response.json();
      throw new Error(error.error || 'Download failed');
    }
    
    const blob = await response.blob();
    const url = window.URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.href = url;
    a.download = filename;
    document.body.appendChild(a);
    a.click();
    window.URL.revokeObjectURL(url);
    document.body.removeChild(a);
  }
}

// Health Check
export async function checkAPIHealth(): Promise<{
  isHealthy: boolean;
  mdeAvailable: boolean;
  iaAvailable: boolean;
  data?: HealthCheckResponse;
  error?: any;
}> {
  try {
    const data = await APIClient.get(API_ENDPOINTS.health);
    return {
      isHealthy: data.status === 'healthy',
      mdeAvailable: data.modes?.mde?.status === 'available',
      iaAvailable: data.modes?.ia?.status === 'available',
      data,
    };
  } catch (error) {
    return {
      isHealthy: false,
      mdeAvailable: false,
      iaAvailable: false,
      error,
    };
  }
}

// MDE API Functions
export async function saveMDEConfig(config: MDEConfig) {
  return APIClient.post(API_ENDPOINTS.mde.configSave, config);
}

export async function uploadClassDiagram(file: File) {
  const formData = new FormData();
  formData.append('xmi_file', file);
  return APIClient.uploadFile(API_ENDPOINTS.mde.uploadClassDiagram, formData);
}

export async function uploadSequenceDiagram(file: File, classDiagramId: string) {
  const formData = new FormData();
  formData.append('sequence_file', file);
  formData.append('class_diagram_id', classDiagramId);
  return APIClient.uploadFile(API_ENDPOINTS.mde.uploadSequenceDiagram, formData);
}

export async function generateMDEProject(request: MDEGenerateRequest) {
  return APIClient.post(API_ENDPOINTS.mde.generateProject, request);
}

export async function listClassDiagrams() {
  return APIClient.get(API_ENDPOINTS.mde.listClassDiagrams);
}

export async function listSequenceDiagrams() {
  return APIClient.get(API_ENDPOINTS.mde.listSequenceDiagrams);
}

// IA API Functions
export async function startIASession(): Promise<IAStartResponse> {
  return APIClient.post(API_ENDPOINTS.ia.start, {});
}

export async function processIAInput(sessionId: string, userInput: string): Promise<IAProcessResponse> {
  return APIClient.post(API_ENDPOINTS.ia.process, {
    session_id: sessionId,
    user_input: userInput,
  });
}

export async function downloadIAProject(sessionId: string, filename?: string) {
  const downloadFilename = filename || `${sessionId}.zip`;
  window.location.href = `${API_BASE_URL}${API_ENDPOINTS.ia.download(sessionId)}`;
}

export async function checkIAProjectReady(sessionId: string): Promise<boolean> {
  try {
    const response = await fetch(`${API_BASE_URL}${API_ENDPOINTS.ia.download(sessionId)}`, {
      method: 'HEAD',
    });
    return response.ok;
  } catch (error) {
    return false;
  }
}

export async function getIARecommendations(sessionId: string) {
  return APIClient.get(API_ENDPOINTS.ia.recommendations(sessionId));
}

export async function listIASessions() {
  return APIClient.get(API_ENDPOINTS.ia.sessions);
}

// Comparison API Functions
export async function compareModes() {
  return APIClient.get(API_ENDPOINTS.compareMode);
}

export async function compareStatus() {
  return APIClient.get(API_ENDPOINTS.compareStatus);
}
