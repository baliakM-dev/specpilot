# CLAUDE.md — Infra

Docker / Keycloak / observability conventions for this directory. Also read the [root `CLAUDE.md`](../CLAUDE.md).

## Docker Compose

- `infra/docker/docker-compose.dev.yml` is the single source of truth for local services (PostgreSQL, Keycloak, and optionally backend/frontend/nginx).
- Every service that another service depends on (e.g. backend depending on Postgres/Keycloak) must have a proper healthcheck, and dependents must use `depends_on: condition: service_healthy` — not a fixed startup delay.
- Observability services (Prometheus, Grafana, Loki, Tempo, Alloy) live in a separate `docker-compose.observability.yml` and are only wired in during Phase 25 ([`TASKS.md`](../TASKS.md)) — don't add them earlier "just in case."

## Secrets & Environment

- Never commit real secrets. `.env.example` at the repo root documents every required variable with placeholder values; real values go in a local, gitignored `.env`.
- Backend gets its config (DB credentials, Keycloak client secret, AI API key) exclusively via environment variables — never hardcoded in `docker-compose.dev.yml` or committed config files.

## Keycloak

- The realm, clients, roles and test users are configured once via the Keycloak admin UI, then exported to `infra/keycloak/realm-export.json`, which is imported automatically on container startup.
- **Any change to roles, clients, or redirect URIs in the Keycloak UI must be re-exported** to `realm-export.json` immediately — otherwise a clean checkout won't reproduce it.

## Port Conventions

| Service | Port |
|---|---|
| Frontend | 5173 |
| Backend | 8080 |
| PostgreSQL | 5432 |
| Keycloak | 8081 |
| Nginx/Caddy | 80 |
| Grafana | 3000 |

Keep these consistent across `docker-compose.dev.yml`, `.env.example`, and both READMEs.
