# SpecPilot

🇬🇧 English | [🇸🇰 Slovenčina](README.sk.md)

**SpecPilot** is an AI-assisted requirements and architecture management platform for software projects.

This is not a chatbot application. It is a fullstack system that helps transform business ideas into structured requirements, user stories, acceptance criteria, technical proposals, API designs, test scenarios and architecture decision records — with a human always in control of the final result.

## Table of Contents

- [Project Purpose](#project-purpose)
- [Main Goals](#main-goals)
- [Target Users](#target-users)
- [Application Modules](#application-modules)
- [Technology Stack](#technology-stack)
- [Architecture Overview](#architecture-overview)
- [AI-Assisted Workflow](#ai-assisted-workflow)
- [How to Run](#how-to-run)
- [Project Structure & Documentation](#project-structure--documentation)
- [Roadmap](#roadmap)
- [Portfolio Value](#portfolio-value)
- [Author](#author)

## Project Purpose

Modern software development is changing because AI tools can generate code, tests, documentation and technical suggestions very quickly. However, AI-generated output still needs human control.

SpecPilot demonstrates a realistic workflow where AI helps with software analysis and development, while final responsibility — architecture, review, validation, security and decisions — stays with the developer.

## Main Goals

1. Build a fullstack application using modern Java and React technologies.
2. Demonstrate secure authentication using Keycloak and a Backend-for-Frontend architecture.
3. Integrate AI into a real software workflow.
4. Store and version AI-generated outputs.
5. Require human review before AI-generated content is approved.
6. Provide clean backend architecture with controllers, services, repositories, DTOs and mappers.
7. Use PostgreSQL with Flyway migrations.
8. Provide automated tests for important business logic and API endpoints.
9. Document technical decisions using ADR documents.
10. Show a professional development workflow suitable for a portfolio or job interview.

## Target Users

| Role | Responsibilities |
|---|---|
| **Analyst** | Creates projects and business requirements, triggers AI analysis, submits requirements for review |
| **Developer** | Reads approved requirements, reviews technical proposals, creates ADR documents |
| **Reviewer** | Approves, rejects or requests changes to AI-generated and human-edited requirements |
| **Admin** | Manages users and roles, reviews audit logs, maintains configuration |

## Application Modules

1. **Authentication** — Keycloak login, BFF session-based authentication, `/api/auth/me`, protected frontend routes.
2. **Project** — create, update, list and manage software projects and their members.
3. **Requirement** — create and manage business/technical requirements with status and priority.
4. **AI Analysis** — generates structured proposals (summary, user stories, acceptance criteria, API/DB proposals, risks) from a requirement, and stores the full generation history.
5. **Review** — human approval workflow: approve, reject, return for changes, comment.
6. **Versioning** — keeps historical versions of requirements as they change.
7. **ADR** — architecture decision records linked to a project.
8. **Audit** — records important user actions for traceability.

## Technology Stack

**Backend:** Java 25, Spring Boot 4, Spring Security, OAuth2 Client, Spring Data JPA, PostgreSQL, Flyway, MapStruct, ProblemDetail, JUnit 5, Mockito, Testcontainers, springdoc-openapi

**Frontend:** React, TypeScript, Vite, Material UI, React Router, React Query, React Hook Form, Zod

**Infrastructure:** Docker, Docker Compose, Keycloak, Nginx/Caddy, GitHub Actions

**AI:** OpenAI API / Anthropic API, called only from the backend

## Architecture Overview

The application uses a modular monolithic architecture with a Backend-for-Frontend (BFF) principle: the frontend never handles access tokens or calls the AI provider directly.

```text
Browser
  |  HTTPS
  v
Frontend (React)
  |  API calls, no token handling
  v
Backend (Spring Boot / BFF)  <--- OIDC login --->  Keycloak
  |  JPA / SQL                |  HTTPS
  v                           v
PostgreSQL                AI Provider API
```

## AI-Assisted Workflow

AI is not treated as a source of truth. For every generation, the backend stores the input text, prompt template, prompt version, model name, output and the user who triggered it.

```text
DRAFT -> (generate AI analysis) -> AI_GENERATED -> (submit for review) -> IN_REVIEW -> (approve) -> APPROVED
                                                                              |-> REJECTED
                                                                              |-> RETURNED_FOR_CHANGES
```

AI produces a draft. A human reviewer edits, approves or rejects it — the AI never approves its own output.

## How to Run

```bash
docker compose -f infra/docker/docker-compose.dev.yml up -d

cd backend && ./mvnw spring-boot:run
cd frontend && npm install && npm run dev
```

| Service | URL |
|---|---|
| Frontend | http://localhost:5173 |
| Backend API | http://localhost:8080 |
| Swagger UI | http://localhost:8080/swagger-ui.html |
| Keycloak | http://localhost:8081 |

## Project Structure & Documentation

See [`PROJECT_STRUCTURE.md`](PROJECT_STRUCTURE.md) for the full folder layout and [`TASKS.md`](TASKS.md) for the complete phased development checklist.

In-depth technical documentation (architecture, database model, API design, security, ADRs, AI usage policy) lives under `docs/` and is filled in progressively as each part of the system is built.

## Roadmap

Development follows the phased plan in [`TASKS.md`](TASKS.md), grouped into three MVP milestones:

- **MVP 1** — foundation, Docker, Keycloak, BFF login, project & requirement CRUD, basic review.
- **MVP 2** — AI provider integration, AI analysis endpoint and frontend.
- **MVP 3** — review workflow, ADR documents, audit logs, requirement versioning, security tests, polish.

## Portfolio Value

This project demonstrates the ability to understand business requirements, design a secure fullstack system, integrate AI responsibly, write tests, and document architectural decisions.

AI can generate drafts — but the developer remains responsible for architecture, correctness, security, maintainability and final decisions.

## Author

**Martin Baliak**
Fullstack Developer — Java | Spring Boot | React | PostgreSQL | Docker | Keycloak | AI-assisted development