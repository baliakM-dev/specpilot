# CLAUDE.md — Backend

Java 25 / Spring Boot 4 conventions for this directory. Also read the [root `CLAUDE.md`](../CLAUDE.md).

## Package Layout

Feature-first, not layer-first, at the top level: `sk.martin.specpilot.<module>` (`auth`, `user`, `project`, `requirement`, `ai`, `review`, `adr`, `audit`, `common`, `config`). Inside each module:

```text
requirement/
  controller/RequirementController.java
  service/RequirementService.java
  repository/RequirementRepository.java
  domain/RequirementEntity.java, RequirementStatus.java, RequirementPriority.java
  dto/CreateRequirementRequest.java, RequirementResponse.java, ...
  mapper/RequirementMapper.java
  exception/RequirementNotFoundException.java
```

See [`PROJECT_STRUCTURE.md`](../PROJECT_STRUCTURE.md) for the full backend package tree. Don't reach into another module's `repository` or `domain` package directly — go through its `service` interface.

## Layering Rules

- **Controller** — request/response mapping only. No business logic, no direct repository access.
- **Service** — all business logic and rules (status transitions, authorization checks beyond role-level, validation of business invariants). Transactions (`@Transactional`) are declared here, not in controllers or repositories.
- **Repository** — Spring Data JPA interfaces only, no business logic.
- **Entities are never returned from a controller.** Every API response is a DTO, mapped via MapStruct (`*Mapper`).

## Validation & Errors

- Request DTOs use Bean Validation annotations (`@NotBlank`, `@Size`, etc.).
- All errors are translated to `ProblemDetail` responses via the shared `GlobalExceptionHandler` in `common.exception` — don't invent a new error response shape per module.
- Business rule violations (e.g. "cannot generate AI analysis for an approved requirement") throw a specific exception (extends `BusinessException`), mapped to a specific `type` URI and HTTP status — not a generic 400/500.

## Database

- All schema changes go through Flyway migrations in `src/main/resources/db/migration`, named `V{n}__description.sql`.
- **Never edit an existing, already-applied migration.** Add a new one instead.
- Primary keys are UUIDs. Every mutable entity has `createdAt`/`updatedAt`.

## Security

- Authorization is enforced at the backend with `@PreAuthorize` / method security (`@EnableMethodSecurity`) — never rely on the frontend hiding a button.
- The AI provider (`AiClient` implementations) is only ever called from the `ai` module's service layer. API keys come from environment variables, never hardcoded, never logged, never returned in any API response.
- See [`docs/architecture/security.md`](../docs/architecture/security.md) and [ADR 0002](../docs/adr/0002-use-bff.md) once filled in.

## Testing

- Follow **superpowers:test-driven-development** — write the test before the implementation.
- Unit tests (JUnit 5 + Mockito) for service-layer business logic and status transitions.
- Integration tests (Spring Boot Test + Testcontainers PostgreSQL + MockMvc/WebTestClient) for controllers, security rules, and Flyway migrations.
- AI-related tests always use `MockAiClient` — never call a real AI provider from a test.
- Cover 401 (unauthenticated), 403 (wrong role), 400 (validation), 404 (not found) and 409 (invalid status transition) cases for each module, not just the happy path.
