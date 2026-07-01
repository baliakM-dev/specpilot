# AI Usage Policy

This document defines how SpecPilot's *application* uses AI at runtime (generating requirement analysis for end users). For rules about using AI tools to help *build* SpecPilot's own codebase, see [`ai-development-rules.md`](ai-development-rules.md).

## Rules

1. **AI output must be stored for traceability.** Every AI analysis call is persisted as an `AiGeneration` record — input text, prompt version, model, output, timestamp, and the user who triggered it. See [ADR 0005](../adr/0005-store-ai-generation-history.md).
2. **AI output must be reviewed before approval.** No requirement reaches `APPROVED` status without an explicit human reviewer decision. See [ADR 0006](../adr/0006-human-review-for-ai-output.md).
3. **AI output must not directly modify approved content.** Regenerating analysis on an `APPROVED` requirement is rejected by the backend; a new requirement version or explicit reviewer action is required first.
4. **The AI provider is called only from the backend.** The frontend never talks to OpenAI/Anthropic/any AI provider directly.
5. **API keys are never exposed to the frontend.** They live only in backend environment variables (`AI_API_KEY`), never in frontend bundles, responses, or logs.
6. **Prompt templates are versioned.** Each `AiGeneration` records the `promptVersion` used, so past outputs remain interpretable even after the template changes.
7. **Final approved content belongs to the human reviewer.** The AI's output is a draft; whatever gets approved is the reviewer's responsibility, even if it started as an unedited AI draft.
8. **No sensitive personal data is deliberately sent to the AI provider or logged.** Requirement text is user-authored business content, but authors should avoid pasting real customer PII into requirement descriptions; this policy does not currently implement automated PII redaction (tracked as a future improvement if real user data is ever involved).

## Non-Goals

- SpecPilot's AI Analysis module is not a chatbot and does not maintain conversational state between requests.
- AI is never treated as a source of truth — every generated field (summary, user stories, acceptance criteria, API/DB proposals, risks) is explicitly a draft until a human approves it.