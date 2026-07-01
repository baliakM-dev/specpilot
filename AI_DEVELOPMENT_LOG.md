# AI Development Log

This file documents how AI was used during the development of SpecPilot, and what the human developer changed, reviewed or rejected. The goal is to demonstrate responsible, transparent AI-assisted development — the same principle the application itself enforces on its own AI-generated content.

For each feature, add an entry using the template below.

## Template

```markdown
## Feature: <feature name>

AI used for:
- ...

Human review changes:
- ...
```

## Example

```markdown
## Feature: Requirement AI Analysis

AI used for:
- initial DTO proposal
- first version of service method
- first draft of React form
- test case suggestions

Human review changes:
- moved business logic from controller to service
- added transaction boundary
- added authorization checks
- replaced entity response with DTO
- added validation for requirement status
- added integration test for forbidden access
- reviewed generated SQL migration manually
```

---