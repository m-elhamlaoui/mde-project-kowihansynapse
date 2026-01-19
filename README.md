# KowihanSynapse: A Hybrid Model-Driven and AI-Powered Framework for REST API Generation


> A novel framework for accelerating robust REST API development by integrating a hybrid user interaction model with a sophisticated backend rooted in Model-Driven Engineering (MDE) and Artificial Intelligence (AI).

-----

## Table of Contents

1.  [Abstract](#1-abstract)
2.  [Introduction](#2-introduction)
3.  [System Architecture](#3-system-architecture)
    - [Architectural Layers](#31-architectural-layers)
    - [Data Flow](#32-data-flow)

## 1\. Abstract

KowihanSynapse is a hybrid framework designed to modernize REST API development by combining model-driven engineering and artificial intelligence within a unified generation process. It offers two complementary modes of operation: an MDE-based approach that transforms UML models expressed in Papyrus XMI into production-ready code using Acceleo model-to-text templates, and an AI-driven approach that enables API generation through structured conversational interaction with specialized AI agents. The framework supports multiple backend technologies, including Django and Flask, and integrates seamlessly with various database management systems such as PostgreSQL, MySQL, SQLite, and Oracle, delivering deployment-ready backend code that follows established architectural and development best practices.


## 2\. Introduction

The proliferation of service-oriented architectures has positioned the *REST API* as a cornerstone of modern software development. However, the process of scaffolding these APIs—defining data models, creating serialization logic, writing boilerplate for *CRUD* (Create, Read, Update, Delete) operations, and ensuring security is often a repetitive and error-prone task. This project posits that a synthesis of AI-driven natural language understanding and the formalisms of MDE can create a highly efficient generation pipeline. *KowihanSynapse* is an implementation of this thesis, providing a seamless workflow from high-level conceptualization to deployable code.

## 3\. System Architecture

The architecture of KowihanSynapse is designed as a modular, multi-layered system that separates concerns from user interaction to code generation. The data flows through a pipeline that progressively refines a high-level user request into a formal application model, which is then used as a single source of truth for code generation.

### 3.1 Architectural Layers
The proposed system architecture is organized into well-defined layers, each responsible for a specific set of functionalities. This layered design ensures modularity, scalability, and clear separation of concerns between user interaction, processing logic, model transformation, and code generation.

#### *Presentation Layer (Client-Side)*

This layer represents the user-facing interface of the system. It allows users to interact with the platform through UML model uploads or natural language input. The frontend is responsible for collecting user requirements, managing configurations, and displaying generated results. Communication with backend services is performed through RESTful APIs using JSON payloads.

#### *Application Layer (Server-Side)*

The application layer acts as the coordination hub of the system. It manages request routing, workflow orchestration, and service communication. This layer ensures that user inputs are correctly dispatched either to the MDE processing pipeline or to the AI-based generation services, while maintaining a unified interaction interface for the frontend.

#### *AI Processing Layer (Server-Side)*
This layer handles all artificial intelligence–related tasks. It processes natural language input, extracts domain entities and relationships, and infers system specifications using large language models.
#### *Model-Driven Engineering Layer (Server-Side)*
The Model-Driven Engineering layer is responsible for handling formal models and transformations. It validates UML models, applies metamodel constraints, and converts high-level system specifications into platform-independent models. This layer ensures consistency, correctness, and reusability through standardized modeling and transformation mechanisms.
#### *Code Generation Layer (Server-Side)*
This layer performs model-to-text transformations to produce executable backend source code. Based on the validated models, it applies predefined templates to generate framework-specific files, including data models, service layers, and configuration files.
#### *Persistence Layer (Server-Side)*
The persistence layer represents the data storage systems used by the generated backend applications. It supports multiple database management systems and abstracts data access through ORM mechanisms, ensuring portability and database independence.



### 3.2 Data Flow

The workflow can be summarized as follows:

<img width="2053" height="2390" alt="dataflow" src="https://github.com/user-attachments/assets/a77f140d-177a-416b-be27-dcfef51ae9f3" />



