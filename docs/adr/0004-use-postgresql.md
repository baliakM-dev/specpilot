# 0004 - Use PostgreSQL as the Primary Database

## Status

Accepted

## Context

The domain (projects, requirements, versions, AI generations, reviews, ADRs, audit logs) is inherently relational: most entities reference each other through foreign keys, and consistency across those relationships matters (e.g. a review must reference an existing requirement). The audit log also needs to store semi-structured metadata per event.

## Decision

Use PostgreSQL as the sole datastore, with schema managed exclusively through Flyway migrations under `backend/src/main/resources/db/migration`. All primary keys are UUIDs. Every mutable entity carries `createdAt`/`updatedAt` timestamps. `AuditLog.metadata` uses a `jsonb` column for event-specific details that don't need their own relational columns.

## Consequences

- Foreign keys enforce referential integrity at the database level (e.g. a `Requirement` cannot reference a non-existent `Project`).
- `jsonb` gives flexible, queryable storage for audit metadata without introducing a second datastore.
- Flyway migrations give a reviewable, ordered history of schema changes and make Testcontainers-based integration tests reproducible from a clean database.
- UUID primary keys avoid leaking sequential IDs (e.g. total requirement count) and make merging data across environments safer, at the cost of slightly larger index sizes than sequential integers.

## Alternatives Considered

- **MySQL** — rejected. No compelling advantage over PostgreSQL for this domain, and PostgreSQL's `jsonb` support is a better fit for the audit log's semi-structured metadata.
- **A NoSQL document store (e.g. MongoDB)** — rejected. The domain is relational by nature (projects → requirements → versions/reviews/AI generations), and modeling that in a document store would mean re-implementing referential integrity in application code.