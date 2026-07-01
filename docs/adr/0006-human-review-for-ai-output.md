# 0006 - Require Human Review Before AI Output Is Approved

## Status

Accepted

## Context

SpecPilot's central premise is that AI can produce a useful first draft, but a human remains responsible for correctness, security and architecture. This must be enforced in the workflow itself, not just stated as a principle — otherwise nothing stops an `AI_GENERATED` requirement from being treated as final without anyone actually reviewing it.

This ADR also resolves an ambiguity in the requirement lifecycle: the original status diagram (`DRAFT → AI_GENERATED → IN_REVIEW → APPROVED`) reads as if AI generation is a mandatory step before review, which contradicts the AI Analysis module being optional ("allow regeneration" implies it's opt-in, not required).

## Decision

A requirement can only reach `APPROVED` by passing through `IN_REVIEW` and receiving an explicit reviewer decision (`APPROVED`, `REJECTED`, or `RETURNED_FOR_CHANGES`). No code path may set a requirement to `APPROVED` automatically, regardless of whether AI was involved.

AI generation is **optional and non-blocking** for the review workflow:

- `DRAFT → IN_REVIEW` is a valid transition (submit for review without ever using AI).
- `DRAFT → AI_GENERATED → IN_REVIEW` is also valid (submit for review after using AI).
- `AI_GENERATED` only signals "AI has been used at least once on this requirement" — it never implies approval or bypasses the reviewer step.

Only users with `REVIEWER` or `ADMIN` role may record a review decision, enforced at the backend (`ReviewService`, checked independently of frontend UI state).

**Deferred for MVP1:** the previously mentioned "strict review mode" (a reviewer may not approve a requirement they authored themselves) is not implemented as a `Project`-level configuration flag in MVP1. It is tracked as a future improvement rather than left as a half-specified, unimplemented rule.

## Consequences

- The state machine in `RequirementService`/`ReviewService` must permit both `DRAFT → IN_REVIEW` and `DRAFT → AI_GENERATED → IN_REVIEW`, and must reject any other path to `APPROVED`.
- Every approval decision is traceable to a specific reviewer and timestamp via the `Review` entity, independent of how many times AI regenerated content beforehand.
- Frontend role-based UI (hiding the approve button from non-reviewers) is a UX convenience only; the authoritative check is server-side.

## Alternatives Considered

- **Auto-approve AI output above a confidence threshold** — rejected. Directly contradicts the project's core "AI assists, human decides" premise.
- **Make AI generation mandatory before a requirement can be reviewed** — rejected. Unnecessarily restrictive; many requirements may be simple enough not to need AI-assisted analysis at all.
- **Implement "strict review mode" now** — deferred. Would require a new `Project` field and additional authorization logic not otherwise needed for MVP1; revisit if a real need for self-review prevention emerges.