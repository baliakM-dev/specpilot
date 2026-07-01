# CLAUDE.md — Frontend

React / TypeScript conventions for this directory. Also read the [root `CLAUDE.md`](../CLAUDE.md).

## Structure

Feature-first under `src/features/<domain>/` (`pages`, `components`, `hooks`, `types`), plus shared cross-cutting code in `src/shared/`, routing in `src/router/`, API clients in `src/api/`. See [`PROJECT_STRUCTURE.md`](../PROJECT_STRUCTURE.md) for the full tree. Don't put feature-specific components in `shared/` — only put things there that are genuinely reused across 2+ features.

## Server State

- All server data goes through **React Query** (`useQuery`/`useMutation`) via hooks in each feature's `hooks/` folder (`useProjects`, `useCreateProject`, etc.). Don't duplicate server state in local `useState` + manual `fetch`.
- API calls go through a typed client per domain in `src/api/` (`projectApi.ts`, `requirementApi.ts`, ...) — components never call `fetch`/`axios` directly.

## Forms

- **React Hook Form** + **Zod** schema validation for every form. Validate on the client for UX, but always treat the backend's validation response as authoritative — display server-side field errors even when client validation passed.

## Auth

- The frontend **never stores or handles access tokens**. Authentication state comes only from `GET /api/auth/me` via the `useAuth` hook.
- `ProtectedRoute` / `RoleProtectedRoute` are **UX conveniences only** — hiding a button or redirecting to `/unauthorized` improves the experience, but the backend is always the real authority. Never assume a hidden UI element means the action is actually blocked.

## UI Conventions

- Material UI components; shared primitives (`LoadingState`, `ErrorState`, `EmptyState`, `ConfirmDialog`, `StatusBadge`) live in `shared/components/` — reuse them instead of one-off implementations per page.
- Every list/detail page needs an explicit loading state, error state, and empty state — not just the happy path.

## Testing

- Follow **superpowers:test-driven-development** where applicable (hooks, utils, form validation logic).
- Mock API calls at the `api/` client boundary, not deep inside components.
