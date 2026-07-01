# AI Development Rules

This document defines how AI coding tools (e.g. Claude Code) may be used to help *build* SpecPilot itself. For rules about how the *running application* uses AI on user data, see [`ai-usage-policy.md`](ai-usage-policy.md).

The goal is the same principle the application enforces on its own users: AI can produce a fast first draft, but the developer remains responsible for correctness, security and architecture of everything that gets committed.

## Rules

1. **AI may propose first drafts** of DTOs, service methods, React components, migrations and tests — but every draft is reviewed by the developer before it is committed, not merged as-is.
2. **Business logic, authorization checks and transaction boundaries are always manually verified**, even when AI proposed them. These are exactly the places where a subtly wrong AI suggestion causes real damage (e.g. a missing `@PreAuthorize`, a missing transaction, an off-by-one in a status transition).
3. **Generated SQL migrations are read line-by-line before being applied**, since Flyway migrations are effectively irreversible once run against a shared database.
4. **No AI-authored code is committed unless the developer can explain what it does.** If a generated method can't be explained line-by-line, it gets rewritten or simplified until it can.
5. **Every non-trivial AI-assisted feature gets an entry in [`AI_DEVELOPMENT_LOG.md`](../../AI_DEVELOPMENT_LOG.md)**, documenting what AI produced and what the human reviewer changed.
6. **Tests are not skipped because AI wrote the code faster.** AI-assisted code follows the same test-driven workflow as hand-written code.
7. **Secrets, API keys and `.env` contents are never pasted into AI tool prompts or logs.**

## Why This Matters for This Project

SpecPilot's entire premise is that AI-assisted software development requires human review, not blind trust. Applying a double standard — enforcing that discipline on the application's users while skipping it in the project's own development — would undermine the project's own thesis.