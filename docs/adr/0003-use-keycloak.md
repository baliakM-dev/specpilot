# 0003 - Use Keycloak for Identity Management

## Status

Accepted

## Context

SpecPilot needs realistic authentication and role-based access control (ADMIN, ANALYST, DEVELOPER, REVIEWER) without building identity management from scratch, and the project explicitly aims to demonstrate production-oriented OIDC integration skills.

## Decision

Use Keycloak as the OIDC identity provider. Keycloak runs as a Docker Compose service for local development. The realm, clients and roles are defined once through the Keycloak admin UI and then exported to `infra/keycloak/realm-export.json`, which is imported automatically on container startup so the environment is reproducible from a clean checkout.

## Consequences

- Demonstrates a realistic, production-style OIDC/IAM integration rather than a toy auth implementation.
- Adds Keycloak as an extra moving part in local development (one more container, one more service to keep healthy).
- The realm export must be kept in sync manually whenever roles, clients or redirect URIs change — it is not generated from code.
- Test users (admin/analyst/developer/reviewer) are provisioned through the same realm export, so security/integration tests can rely on stable, known accounts.

## Alternatives Considered

- **Spring Security form login with an application-managed user table** — rejected. Would not demonstrate OIDC/enterprise IAM patterns, which is one of the project's explicit goals.
- **Hosted IdP (e.g. Auth0, Okta)** — rejected. Introduces an external dependency and potential cost for a self-hosted, portfolio-oriented demo that should run entirely via `docker compose up`.