# CLAUDE.md

Instructions for Claude Code when working in this repository.

## What This Project Is

SpecPilot is an AI-assisted requirements and architecture management platform: Java 21 / Spring Boot backend, React / TypeScript frontend, Keycloak for auth (BFF pattern), PostgreSQL + Flyway, Docker Compose for local infra.

Full product description: [`README.md`](README.md) / [`README.sk.md`](README.sk.md).
Full folder layout: [`PROJECT_STRUCTURE.md`](PROJECT_STRUCTURE.md).
Full phased task checklist: [`TASKS.md`](TASKS.md) — always check which phase is currently active before starting work.

Subdirectory-specific conventions:
- [`backend/CLAUDE.md`](backend/CLAUDE.md) — Spring Boot / Java conventions
- [`frontend/CLAUDE.md`](frontend/CLAUDE.md) — React / TypeScript conventions
- [`infra/CLAUDE.md`](infra/CLAUDE.md) — Docker / Keycloak / observability conventions

## Non-Negotiable Architectural Rules

These come from recorded decisions in [`docs/adr/`](docs/adr/) — don't casually deviate from them; if a change is needed, update the ADR first.

1. **BFF pattern.** The frontend never handles OAuth tokens and never calls Keycloak or the AI provider directly. It only calls backend REST endpoints and checks identity via `GET /api/auth/me`. ([ADR 0002](docs/adr/0002-use-bff.md))
2. **AI output always needs human review.** No code path may set a requirement to `APPROVED` without going through `IN_REVIEW` and an explicit reviewer decision — even if AI produced the content. AI generation itself is optional and never gates the review workflow. ([ADR 0006](docs/adr/0006-human-review-for-ai-output.md))
3. **Every AI generation is stored, never overwritten.** Regeneration creates a new `AiGeneration` record. ([ADR 0005](docs/adr/0005-store-ai-generation-history.md))
4. **Real authorization is enforced on the backend**, always, regardless of what the frontend UI shows or hides.

## Development Workflow

- Follow **superpowers:test-driven-development** for all feature/bugfix work in this repo: write the failing test first, then the minimal implementation.
- Use **superpowers:brainstorming** before any new feature or architectural change, before touching code.
- Every non-trivial AI-assisted change gets an entry in [`AI_DEVELOPMENT_LOG.md`](AI_DEVELOPMENT_LOG.md) — see [`docs/ai/ai-development-rules.md`](docs/ai/ai-development-rules.md) for the full rules.
- New architectural decisions get a new file in `docs/adr/`, following the existing numbering and structure.
- Commit messages: short imperative subject (`feat:`, `fix:`, `chore:`, `docs:`, `test:` prefixes), matching the style already used in `PROJECT_STRUCTURE.md`'s suggested commit log.

## How to Run Locally

```bash
docker compose -f infra/docker/docker-compose.dev.yml up -d   # Postgres + Keycloak (+ frontend/backend if containerized)

cd backend && ./mvnw spring-boot:run
cd frontend && npm install && npm run dev
```

| Service | URL |
|---|---|
| Frontend | http://localhost:5173 |
| Backend API | http://localhost:8080 |
| Swagger UI | http://localhost:8080/swagger-ui.html |
| Keycloak | http://localhost:8081 |

## Documentation Map

- `docs/adr/` — why decisions were made (read before changing architecture)
- `docs/architecture/` — how the system actually works (filled in as each part is built)
- `docs/features/` — user-facing description of each module (filled in as each part is built)
- `docs/ai/` — AI usage policy (for the app) and AI development rules (for building the app)