# 0001 - Use a Modular Monolith

## Status

Accepted

## Context

SpecPilot has eight distinct functional areas (auth, project, requirement, ai, review, adr, audit, common). The system is built and operated by a single developer as a portfolio project, so operational complexity has to stay low, but the codebase still needs clear internal boundaries so each module can be reasoned about, tested and reviewed independently.

## Decision

Build a single deployable Spring Boot application, organized by feature package (`sk.martin.specpilot.<module>`) rather than by technical layer at the top level. Each module owns its own `controller`, `service`, `repository`, `domain`, `dto`, `mapper` and `exception` sub-packages. Cross-module communication happens through service interfaces and DTOs, never through shared entities or repositories.

## Consequences

- Single artifact to build, test and deploy — no distributed systems complexity (service discovery, network calls, distributed transactions).
- Module boundaries are enforced by convention and code review, not by process isolation — discipline is required to avoid modules reaching into each other's repositories/entities directly.
- If a module ever needs to be extracted into its own service, the existing package boundaries and DTO-based communication make that a smaller step than extracting from a layered (non-modular) monolith.

## Alternatives Considered

- **Microservices per module** — rejected. Adds deployment, networking and data-consistency complexity that isn't justified for a solo-developer portfolio project and would slow down delivery without adding relevant demonstrable skill.
- **Traditional layered monolith (single `controller`/`service`/`repository` package for the whole app)** — rejected. Would make it harder to see and enforce module boundaries as the codebase grows across eight functional areas.