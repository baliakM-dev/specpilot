# 0002 - Use Backend-for-Frontend (BFF) Authentication

## Status

Accepted

## Context

The React frontend needs to authenticate users against Keycloak. If the SPA handles the OAuth2/OIDC flow and holds the access token itself (e.g. in memory, localStorage or a cookie it controls), it becomes directly responsible for token storage, refresh and exposure — increasing the impact of any XSS vulnerability, since a compromised frontend would leak a token usable directly against protected APIs.

## Decision

The Spring Boot backend acts as a Backend-for-Frontend. It terminates the OAuth2/OIDC authorization code flow with Keycloak, keeps the access/ID/refresh tokens server-side, and issues the browser only an HttpOnly session cookie. The frontend never receives or stores a token; it only calls backend REST endpoints and reads its own identity via `GET /api/auth/me`.

As part of this decision, the previously open **CSRF strategy** question is resolved here: since authentication is session-cookie based, the backend enables CSRF protection using Spring Security's cookie-based double-submit token pattern for all state-changing requests (`POST`/`PATCH`/`DELETE`), and session cookies are set with `SameSite=Lax`. Read-only `GET` requests are exempt.

## Consequences

- Frontend code has no token handling, refresh logic or OIDC library dependency — simpler and smaller attack surface.
- Backend owns session lifecycle (login, logout, expiry) and must implement CSRF protection correctly, since cookie-based auth is vulnerable to CSRF by default.
- The AI provider API key and any other backend secrets are never reachable from the browser, since the frontend has no direct network path to third-party APIs.
- Adds one more responsibility to the backend (session + CSRF token issuance) compared to a pure stateless resource server.

## Alternatives Considered

- **SPA handles OIDC directly and holds the access token** — rejected. Increases XSS blast radius and requires token refresh logic in the frontend.
- **Access token in `localStorage`** — rejected. Same XSS exposure as above, with no BFF benefit.
- **Stateless JWT bearer tokens issued to the browser** — rejected for this project. Would remove the CSRF concern but reintroduces client-side token storage risk that the BFF pattern is specifically meant to avoid.