# 0005 - Store Full AI Generation History

## Status

Accepted

## Context

AI-generated content is the core "responsible AI usage" demonstration of this project: SpecPilot must be able to show, for any requirement, exactly what was sent to the AI provider, which prompt version and model produced the result, and who triggered it — including every time a requirement's analysis was regenerated.

## Decision

Every AI analysis call creates a new, immutable `AiGeneration` record linked to the requirement: `inputText`, `promptVersion`, `model`, `outputText`, `createdBy`, `createdAt`. Regenerating an analysis creates a new `AiGeneration` record rather than overwriting the previous one — nothing is ever updated or deleted from this table. The requirement's current status (`AI_GENERATED`) reflects that at least one generation exists, but the full list is always available via `GET /api/projects/{projectId}/requirements/{requirementId}/ai-generations`.

## Consequences

- Complete, auditable trail of every AI interaction per requirement, including superseded/regenerated attempts.
- Storage grows with every regeneration; acceptable for MVP scope. A retention/archival policy can be added later if generation volume becomes a concern (see Future Improvements).
- Requires a dedicated history endpoint distinct from "the current requirement," since a requirement doesn't have a single canonical AI output — it has a history of them, and a human ultimately decides what becomes the approved content.
- The AI provider must be called only from the backend, and API keys are never exposed to the frontend, so that this history can be trusted as an accurate record of what was actually sent/received.

## Alternatives Considered

- **Overwrite the previous AI output on regeneration** — rejected. Loses auditability and directly contradicts the project's core premise that AI usage must be traceable, not just its latest output.
- **Store only the final human-approved text, discard AI drafts** — rejected. Removes the ability to compare AI draft vs. human-approved version, which is an explicit feature goal.