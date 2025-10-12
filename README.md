# KowihanSynapse: A Hybrid Model-Driven and AI-Powered Framework for REST API Generation


> A novel framework for accelerating robust REST API development by integrating a hybrid user interaction model with a sophisticated backend rooted in Model-Driven Engineering (MDE) and Artificial Intelligence (AI).

-----

## Table of Contents

1.  [Abstract](#1-abstract)
2.  [Introduction](#2-introduction)
3.  [System Architecture](#3-system-architecture)
    - [Architectural Layers](#31-architectural-layers)
    - [Data Flow](#32-data-flow)
4.  [Technology Stack](#4-technology-stack)

## 1\. Abstract

This document outlines the *KowihanSynapse* project, a novel framework designed to accelerate the development of robust RESTful APIs. The project addresses the inefficiencies inherent in manual API creation by integrating a hybrid user interaction model with a sophisticated backend rooted in Model-Driven Engineering (MDE) and Artificial Intelligence (AI). The system leverages a conversational AI for initial requirements elicitation, which then transitions to a guided graphical user interface (GUI) for detailed model refinement and configuration. The validated application model is subsequently transformed into production-quality source code for backend frameworks such as Django and Flask. This methodology aims to reduce development time, minimize human error, and enforce architectural best practices, thereby serving as a significant contribution to the field of software automation and AI-assisted engineering.

## 2\. Introduction

The proliferation of service-oriented architectures has positioned the *REST API* as a cornerstone of modern software development. However, the process of scaffolding these APIs—defining data models, creating serialization logic, writing boilerplate for *CRUD* (Create, Read, Update, Delete) operations, and ensuring security—is often a repetitive and error-prone task. This project posits that a synthesis of AI-driven natural language understanding and the formalisms of MDE can create a highly efficient generation pipeline. *KowihanSynapse* is an implementation of this thesis, providing a seamless workflow from high-level conceptualization to deployable code.

## 3\. System Architecture

The architecture of KowihanSynapse is designed as a modular, multi-layered system that separates concerns from user interaction to code generation. The data flows through a pipeline that progressively refines a high-level user request into a formal application model, which is then used as a single source of truth for code generation.

### 3.1 Architectural Layers

#### *Presentation Layer (Client-Side)*

This layer is the user's primary point of interaction.

  - *Conversational Interface*: An AI-powered chatbot serves as the initial entry point. Its role is to engage the user in a natural language dialogue to capture the project's name, core purpose, and a preliminary sketch of the data entities.
  - *Guided Configuration UI*: A Single-Page Application (SPA) built in React that provides a structured, wizard-style interface. This UI takes the initial context from the chatbot and allows the user to perform detailed configuration, including uploading formal models (UML), refining data structures, selecting a database, and choosing additional features.

#### *Application Layer (Server-Side)*

This is the core backend logic that orchestrates the entire process.

  - *API Gateway*: Manages all incoming requests from the client, routing them to the appropriate service. It exposes endpoints for the chatbot interaction (/api/chat-intake) and for the main configuration wizard (/api/generate-project).
  - *AI Orchestrator*: Powered by a Large Language Model (LLM), this component manages the conversational logic. It processes user input, extracts key entities, and maintains the conversational context.
  - *Model Processor*: This component is responsible for creating a formal, structured application model. It operates in two modes:
      - *UML Parser*: Parses uploaded XMI (XML Metadata Interchange) files from UML class diagrams into a structured format.
      - *Text Analyzer*: Uses the AI Orchestrator to translate the natural language description into the same structured format.

#### *Model Transformation & Generation Layer*

  - *Model Validator*: The structured data model, received from the Model Processor, is validated against a formal Metamodel (defined using Pydantic). This ensures the model is complete, consistent, and logically sound before generation begins.
  - *MDE Code Generation Engine*: This is the heart of the Model-to-Text Transformation (M2T) process. It uses the validated model and user configuration choices as input for a templating engine (Jinja2). It systematically applies a set of templates to generate the file-by-file source code of the target application.

### 3.2 Data Flow

The workflow can be summarized as follows:

<img width="6512" height="17134" alt="workflow" src="https://github.com/user-attachments/assets/08c460f3-e95c-418c-819e-bb81d57ebdea" />


## 4\. Technology Stack

The selection of technologies is based on robustness, scalability, and ecosystem maturity, primarily centered around Python and JavaScript.


<img width="19095" height="9473" alt="tech-architecture" src="https://github.com/user-attachments/assets/5c24eb2e-7e41-4aad-bf07-da2999e909d3" />

  - *Frontend*

      - *React*: For building the interactive and dynamic configuration wizard (SPA).
      - *Material-UI (MUI) / Ant Design*: A component library to ensure a consistent and professional user interface.
      - *React-Chatbot-Kit*: For embedding the initial conversational interface.
      - *State Management*: React Context API or Redux Toolkit for managing the application's global configuration state.

  - *Backend*

      - *Primary Framework*: FLASK for building the backend REST APIs that serve the client.
      - *Model Validation*: Pydantic for defining the formal metamodel and validating the application models.

  - *Artificial Intelligence & NLP*

      - *Large Language Models (LLM)*: open source llms their respective APIs.
      - *Orchestration Framework*: LangChain to structure the interaction between the LLM, prompts, and external data sources.
      - *Retrieval-Augmented Generation (RAG)*:
          - *Vector Database*: ChromaDB for indexing and retrieving best practices from documentation.

  - *Model-Driven Engineering (MDE)*

      - *Model Parsing*: lxml library for parsing UML/XMI files.
      - *Model-to-Text Transformation (M2T)*: Jinja2 as the templating engine for code generation.

  - *Target Generated Frameworks*

      - Django with Django REST Framework (DRF).
      - Flask with Flask-SQLAlchemy and Marshmallow.

  - *DevOps*

      - *Docker*: For containerizing both the KowihanSynapse service itself and the generated applications
      - *GitHub Actions*: For automating the CI/CD pipeline, including building Docker images, running tests, and deploying to Kubernetes.
      - *Kubernetes*: For orchestrating the containerized services in production.
