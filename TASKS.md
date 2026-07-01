# SpecPilot – kompletný plán úloh

Tento súbor slúži ako hlavný checklist pre vývoj projektu SpecPilot.

Projekt: **AI-assisted requirements and architecture management platform**  
Stack: **Java 25, Spring Boot 4, React, TypeScript, PostgreSQL, Flyway, Keycloak, Docker, AI integration**

---

## Stavové značky

Používaj tieto značky pri práci:

- [ ] TODO
- [x] DONE
- [~] IN PROGRESS
- [!] BLOCKED

---

# Phase 0 – Príprava repozitára

## Cieľ

Vytvoriť čistý repozitár, základnú dokumentáciu a štruktúru projektu.

## Checklist

- [x] Vytvoriť Git repozitár `specpilot`
- [x] Pridať hlavný `README.md`
- [x] Pridať slovenský `README.sk.md`, ak budeš chcieť mať aj SK verziu
- [x] Pridať `.gitignore`
- [x] Pridať `.editorconfig`
- [x] Pridať `.env.example`
- [x] Pridať `AI_DEVELOPMENT_LOG.md`
- [x] Pridať priečinok `docs`
- [x] Pridať priečinok `backend`
- [x] Pridať priečinok `frontend`
- [x] Pridať priečinok `infra`
- [x] Pridať priečinok `scripts`
- [x] Pridať priečinok `.github/workflows`
- [x] Vytvoriť prvý commit: `chore: initialize repository structure`

## Výstup

Repozitár má pripravenú štruktúru a dokumentáciu pred implementáciou.

---

# Phase 1 – Backend foundation

## Cieľ

Vytvoriť základ Spring Boot backendu.

## Checklist

- [ ] Vytvoriť Spring Boot projekt v priečinku `backend`
- [ ] Nastaviť Java 25
- [ ] Nastaviť Maven wrapper
- [ ] Pridať dependency: Spring Web
- [ ] Pridať dependency: Spring Validation
- [ ] Pridať dependency: Spring Data JPA
- [ ] Pridať dependency: PostgreSQL Driver
- [ ] Pridať dependency: Flyway
- [ ] Pridať dependency: Spring Security
- [ ] Pridať dependency: OAuth2 Client
- [ ] Pridať dependency: Actuator
- [ ] Pridať dependency: MapStruct
- [ ] Pridať dependency: Lombok, ak ho chceš používať
- [ ] Pridať dependency: springdoc-openapi
- [ ] Pridať dependency: JUnit 5
- [ ] Pridať dependency: Mockito
- [ ] Pridať dependency: Testcontainers
- [ ] Nastaviť základný package `sk.martin.specpilot`
- [ ] Vytvoriť package štruktúru:
  - [ ] `auth`
  - [ ] `user`
  - [ ] `project`
  - [ ] `requirement`
  - [ ] `ai`
  - [ ] `review`
  - [ ] `adr`
  - [ ] `audit`
  - [ ] `common`
  - [ ] `config`
- [ ] Nastaviť `application.yml`
- [ ] Nastaviť `application-local.yml` (feature/* vetvy, lokálny Docker Postgres/Keycloak)
- [ ] Nastaviť `application-dev.yml` (deploy z `develop`, Neon "dev" DB branch)
- [ ] Nastaviť `application-prod.yml` (deploy z `main`, Neon "main" DB branch)
- [ ] Nastaviť `application-test.yml` (profil pre automatizované testy s Testcontainers)
- [ ] Nastaviť základný health endpoint cez Actuator
- [ ] Vytvoriť `GlobalExceptionHandler`
- [ ] Vytvoriť základný `BusinessException`
- [ ] Vytvoriť základný ProblemDetail response
- [ ] Spustiť backend lokálne
- [ ] Overiť `/actuator/health`

## Výstup

Backend sa vie spustiť a má základnú architektúru.

---

# Phase 2 – Frontend foundation

## Cieľ

Vytvoriť základ React frontendu.

## Checklist

- [ ] Vytvoriť React projekt cez Vite v priečinku `frontend`
- [ ] Nastaviť TypeScript
- [ ] Pridať Material UI
- [ ] Pridať React Router
- [ ] Pridať React Query
- [ ] Pridať React Hook Form
- [ ] Pridať Zod alebo Yup
- [ ] Pridať Axios alebo použiť Fetch API wrapper
- [ ] Nastaviť ESLint
- [ ] Nastaviť Prettier
- [ ] Vytvoriť základný layout
- [ ] Vytvoriť routing
- [ ] Vytvoriť `LoginPage`
- [ ] Vytvoriť `DashboardPage`
- [ ] Vytvoriť `UnauthorizedPage`
- [ ] Vytvoriť `NotFoundPage`
- [ ] Vytvoriť `apiClient`
- [ ] Vytvoriť základný `AppShell`
- [ ] Vytvoriť základný navigation/sidebar
- [ ] Spustiť frontend lokálne
- [ ] Overiť `http://localhost:5173`

## Výstup

Frontend sa vie spustiť a má základnú navigáciu.

---

# Phase 3 – Docker foundation

## Cieľ

Pripraviť lokálnu infraštruktúru cez Docker Compose.

## Checklist

- [ ] Vytvoriť `infra/docker/docker-compose.dev.yml`
- [ ] Pridať PostgreSQL service
- [ ] Pridať Keycloak service
- [ ] Pridať backend service
- [ ] Pridať frontend service
- [ ] Pridať nginx alebo caddy service, ak budeš chcieť proxy
- [ ] Pridať spoločnú Docker network
- [ ] Pridať volume pre PostgreSQL
- [ ] Pridať volume pre Keycloak, ak bude potrebné
- [ ] Pridať environment premenné
- [ ] Pridať healthcheck pre PostgreSQL
- [ ] Pridať healthcheck pre Keycloak
- [ ] Otestovať PostgreSQL
- [ ] Otestovať Keycloak
- [ ] Otestovať backend v Dockeri
- [ ] Otestovať frontend v Dockeri
- [ ] Pridať základný `README` k infraštruktúre

## Výstup

Lokálna infraštruktúra beží cez Docker Compose.

---

# Phase 4 – Keycloak setup

## Cieľ

Nastaviť Keycloak realm, clientov, role a test používateľov.

## Checklist

- [ ] Vytvoriť realm `specpilot`
- [ ] Vytvoriť client `specpilot-bff`
- [ ] Nastaviť client type podľa BFF flow
- [ ] Nastaviť valid redirect URI
- [ ] Nastaviť web origins
- [ ] Vytvoriť role:
  - [ ] `ADMIN`
  - [ ] `ANALYST`
  - [ ] `DEVELOPER`
  - [ ] `REVIEWER`
- [ ] Vytvoriť test používateľa admin
- [ ] Vytvoriť test používateľa analyst
- [ ] Vytvoriť test používateľa developer
- [ ] Vytvoriť test používateľa reviewer
- [ ] Priradiť používateľom role
- [ ] Nastaviť email, meno, priezvisko
- [ ] Overiť login v Keycloak UI
- [ ] Exportovať realm konfiguráciu
- [ ] Uložiť export do `infra/keycloak/realm-export.json`
- [ ] Nastaviť automatický import realmu pri štarte Keycloaku

## Výstup

Keycloak je pripravený na autentifikáciu aplikácie.

---

# Phase 5 – Backend Security / BFF

## Cieľ

Implementovať BFF login flow cez Spring Security a Keycloak.

## Checklist

- [ ] Pridať Spring Security konfiguráciu
- [ ] Nastaviť OAuth2 Client
- [ ] Nastaviť OIDC provider Keycloak
- [ ] Nastaviť client ID
- [ ] Nastaviť client secret
- [ ] Nastaviť redirect URI
- [ ] Nastaviť login endpoint
- [ ] Nastaviť logout endpoint
- [ ] Vytvoriť `AuthController`
- [ ] Implementovať `GET /api/auth/me`
- [ ] Vytvoriť `AuthMeResponse`
- [ ] Extrahovať subject z OIDC usera
- [ ] Extrahovať email z OIDC usera
- [ ] Extrahovať meno z OIDC usera
- [ ] Extrahovať role z tokenu alebo user info
- [ ] Namapovať role na aplikáciu
- [ ] Nastaviť session-based authentication
- [ ] Nastaviť CORS pre frontend
- [ ] Rozhodnúť CSRF stratégiu
- [ ] Otestovať login cez browser
- [ ] Otestovať logout
- [ ] Otestovať `/api/auth/me` pre prihláseného používateľa
- [ ] Otestovať `/api/auth/me` pre neprihláseného používateľa

## Výstup

Frontend sa vie prihlásiť cez backend a získať informácie o používateľovi.

---

# Phase 6 – Frontend Auth

## Cieľ

Napojenie frontendu na backend autentifikáciu.

## Checklist

- [ ] Vytvoriť `AuthProvider`
- [ ] Vytvoriť `useAuth`
- [ ] Vytvoriť `authApi`
- [ ] Implementovať volanie `/api/auth/me`
- [ ] Implementovať login redirect
- [ ] Implementovať logout
- [ ] Vytvoriť `ProtectedRoute`
- [ ] Vytvoriť `RoleProtectedRoute`
- [ ] Presmerovať neprihláseného používateľa
- [ ] Presmerovať používateľa bez role na `/unauthorized`
- [ ] Zobraziť meno používateľa v topbare
- [ ] Zobraziť role používateľa v UI
- [ ] Pridať loading stav pri kontrole autentifikácie
- [ ] Pridať error stav pri zlyhaní auth requestu

## Výstup

Frontend vie pracovať s autentifikovaným používateľom.

---

# Phase 7 – User module

## Cieľ

Synchronizovať Keycloak používateľa s aplikačnou databázou.

## Checklist

- [ ] Vytvoriť migráciu `users`
- [ ] Vytvoriť `UserEntity`
- [ ] Vytvoriť `UserRepository`
- [ ] Vytvoriť `UserService`
- [ ] Vytvoriť `CurrentUserService`
- [ ] Pri prvom prihlásení uložiť používateľa
- [ ] Uložiť `keycloakSubject`
- [ ] Uložiť email
- [ ] Uložiť fullName
- [ ] Aktualizovať používateľa pri zmene údajov
- [ ] Vrátiť interný user ID pre audit logy
- [ ] Pridať testy pre synchronizáciu používateľa

## Výstup

Aplikácia má vlastnú user entitu naviazanú na Keycloak.

---

# Phase 8 – Project backend

## Cieľ

Implementovať backend správu projektov.

## Checklist

- [ ] Vytvoriť migráciu `projects`
- [ ] Vytvoriť migráciu `project_members`
- [ ] Vytvoriť `ProjectEntity`
- [ ] Vytvoriť `ProjectStatus`
- [ ] Vytvoriť `ProjectMemberEntity`
- [ ] Vytvoriť `ProjectRole`
- [ ] Vytvoriť `ProjectRepository`
- [ ] Vytvoriť `ProjectMemberRepository`
- [ ] Vytvoriť DTO:
  - [ ] `CreateProjectRequest`
  - [ ] `UpdateProjectRequest`
  - [ ] `ProjectResponse`
  - [ ] `ProjectListItemResponse`
- [ ] Vytvoriť `ProjectMapper`
- [ ] Vytvoriť `ProjectService`
- [ ] Vytvoriť `ProjectController`
- [ ] Implementovať `GET /api/projects`
- [ ] Implementovať `POST /api/projects`
- [ ] Implementovať `GET /api/projects/{projectId}`
- [ ] Implementovať `PATCH /api/projects/{projectId}`
- [ ] Implementovať `DELETE /api/projects/{projectId}`
- [ ] Pridať validáciu názvu projektu
- [ ] Pridať validáciu statusu
- [ ] Pridať business exception pre neexistujúci projekt
- [ ] Pridať audit log pri vytvorení projektu
- [ ] Pridať audit log pri úprave projektu
- [ ] Pridať unit testy
- [ ] Pridať integračné testy

## Výstup

Backend podporuje správu projektov.

---

# Phase 9 – Project frontend

## Cieľ

Implementovať UI pre projekty.

## Checklist

- [ ] Vytvoriť `ProjectsPage`
- [ ] Vytvoriť `CreateProjectPage`
- [ ] Vytvoriť `EditProjectPage`, ak bude samostatná
- [ ] Vytvoriť `ProjectDetailPage`
- [ ] Vytvoriť `projectApi`
- [ ] Vytvoriť React Query hooks:
  - [ ] `useProjects`
  - [ ] `useProject`
  - [ ] `useCreateProject`
  - [ ] `useUpdateProject`
  - [ ] `useDeleteProject`
- [ ] Vytvoriť `ProjectForm`
- [ ] Pridať validáciu formulára
- [ ] Zobraziť serverové validation errors
- [ ] Zobraziť zoznam projektov
- [ ] Zobraziť detail projektu
- [ ] Pridať editáciu projektu
- [ ] Pridať delete/archive projektu
- [ ] Pridať loading states
- [ ] Pridať error states
- [ ] Pridať empty state

## Výstup

Používateľ vie cez UI spravovať projekty.

---

# Phase 10 – Requirement backend

## Cieľ

Implementovať backend správu požiadaviek.

## Checklist

- [ ] Vytvoriť migráciu `requirements`
- [ ] Vytvoriť `RequirementEntity`
- [ ] Vytvoriť `RequirementStatus`
- [ ] Vytvoriť `RequirementPriority`
- [ ] Vytvoriť `RequirementRepository`
- [ ] Vytvoriť DTO:
  - [ ] `CreateRequirementRequest`
  - [ ] `UpdateRequirementRequest`
  - [ ] `RequirementResponse`
  - [ ] `RequirementListItemResponse`
- [ ] Vytvoriť `RequirementMapper`
- [ ] Vytvoriť `RequirementService`
- [ ] Vytvoriť `RequirementController`
- [ ] Implementovať `GET /api/projects/{projectId}/requirements`
- [ ] Implementovať `POST /api/projects/{projectId}/requirements`
- [ ] Implementovať `GET /api/projects/{projectId}/requirements/{requirementId}`
- [ ] Implementovať `PATCH /api/projects/{projectId}/requirements/{requirementId}`
- [ ] Implementovať `DELETE /api/projects/{projectId}/requirements/{requirementId}`
- [ ] Overiť, že requirement patrí pod project
- [ ] Pridať validáciu title
- [ ] Pridať validáciu description
- [ ] Pridať validáciu priority
- [ ] Pridať business exception pre neexistujúci requirement
- [ ] Pridať audit log pri vytvorení
- [ ] Pridať audit log pri úprave
- [ ] Pridať unit testy
- [ ] Pridať integračné testy

## Výstup

Backend podporuje správu požiadaviek.

---

# Phase 11 – Requirement frontend

## Cieľ

Implementovať UI pre požiadavky.

## Checklist

- [ ] Vytvoriť `CreateRequirementPage`
- [ ] Vytvoriť `RequirementDetailPage`
- [ ] Vytvoriť `EditRequirementPage`, ak bude samostatná
- [ ] Vytvoriť `requirementApi`
- [ ] Vytvoriť React Query hooks:
  - [ ] `useRequirements`
  - [ ] `useRequirement`
  - [ ] `useCreateRequirement`
  - [ ] `useUpdateRequirement`
  - [ ] `useDeleteRequirement`
- [ ] Vytvoriť `RequirementForm`
- [ ] Pridať validáciu formulára
- [ ] Zobraziť status požiadavky
- [ ] Zobraziť prioritu požiadavky
- [ ] Pridať filtrovanie podľa statusu
- [ ] Pridať filtrovanie podľa priority
- [ ] Pridať editáciu
- [ ] Pridať delete
- [ ] Pridať loading states
- [ ] Pridať error states
- [ ] Pridať empty state

## Výstup

Používateľ vie cez UI spravovať požiadavky.

---

# Phase 12 – AI backend

## Cieľ

Implementovať AI analýzu požiadavky.

## Checklist

- [ ] Vytvoriť migráciu `ai_generations`
- [ ] Vytvoriť `AiGenerationEntity`
- [ ] Vytvoriť `AiGenerationRepository`
- [ ] Vytvoriť `AiClient` interface
- [ ] Vytvoriť `MockAiClient`
- [ ] Vytvoriť `OpenAiClient` alebo `AnthropicAiClient`
- [ ] Vytvoriť `PromptTemplateService`
- [ ] Vytvoriť prompt template pre requirement analysis
- [ ] Vytvoriť DTO:
  - [ ] `AiAnalysisRequest`
  - [ ] `AiAnalysisResponse`
  - [ ] `AiGenerationResponse`
- [ ] Vytvoriť `AiAnalysisService`
- [ ] Vytvoriť `AiAnalysisController`
- [ ] Implementovať `POST /api/projects/{projectId}/requirements/{requirementId}/ai-analysis`
- [ ] Implementovať `GET /api/projects/{projectId}/requirements/{requirementId}/ai-generations`
- [ ] Implementovať `GET /api/projects/{projectId}/requirements/{requirementId}/ai-generations/{generationId}`
- [ ] Uložiť input text
- [ ] Uložiť output text
- [ ] Uložiť prompt version
- [ ] Uložiť model
- [ ] Uložiť createdBy
- [ ] Zmeniť requirement status na `AI_GENERATED`
- [ ] Zabrániť AI generovaniu pre `APPROVED`
- [ ] Pridať audit log
- [ ] Pridať unit testy s `MockAiClient`
- [ ] Pridať integračné testy bez reálneho AI API

## Výstup

Backend vie generovať a ukladať AI analýzy.

---

# Phase 13 – AI frontend

## Cieľ

Implementovať UI pre AI analýzu.

## Checklist

- [ ] Vytvoriť `AiAnalysisPage`
- [ ] Vytvoriť `aiApi`
- [ ] Vytvoriť React Query hooks:
  - [ ] `useGenerateAiAnalysis`
  - [ ] `useAiGenerations`
  - [ ] `useAiGeneration`
- [ ] Pridať tlačidlo `Generate AI Analysis`
- [ ] Zobraziť loading počas generovania
- [ ] Zobraziť AI výstup
- [ ] Zobraziť summary
- [ ] Zobraziť user stories
- [ ] Zobraziť acceptance criteria
- [ ] Zobraziť API proposal
- [ ] Zobraziť database proposal
- [ ] Zobraziť risks
- [ ] Zobraziť test scenarios
- [ ] Zobraziť históriu AI generovaní
- [ ] Zobraziť prompt version
- [ ] Zobraziť model
- [ ] Pridať upozornenie: AI output is draft
- [ ] Pridať možnosť pregenerovania

## Výstup

Používateľ vie cez UI spustiť AI analýzu a pozrieť výsledky.

---

# Phase 14 – Review backend

## Cieľ

Implementovať review workflow.

## Checklist

- [ ] Vytvoriť migráciu `reviews`
- [ ] Vytvoriť migráciu `review_comments`
- [ ] Vytvoriť `ReviewEntity`
- [ ] Vytvoriť `ReviewCommentEntity`
- [ ] Vytvoriť `ReviewDecision`
- [ ] Vytvoriť `ReviewRepository`
- [ ] Vytvoriť `ReviewCommentRepository`
- [ ] Vytvoriť DTO:
  - [ ] `SubmitReviewRequest`
  - [ ] `ReviewDecisionRequest`
  - [ ] `ReviewResponse`
  - [ ] `ReviewCommentRequest`
  - [ ] `ReviewCommentResponse`
- [ ] Vytvoriť `ReviewService`
- [ ] Vytvoriť `ReviewController`
- [ ] Implementovať `POST /api/projects/{projectId}/requirements/{requirementId}/submit-review`
- [ ] Implementovať `POST /api/projects/{projectId}/requirements/{requirementId}/reviews`
- [ ] Implementovať `GET /api/projects/{projectId}/requirements/{requirementId}/reviews`
- [ ] Implementovať `POST /api/projects/{projectId}/requirements/{requirementId}/comments`
- [ ] Implementovať `GET /api/projects/{projectId}/requirements/{requirementId}/comments`
- [ ] Overiť status transitions
- [ ] Overiť, že schvaľovať môže iba REVIEWER alebo ADMIN
- [ ] Pridať audit log pri submit review
- [ ] Pridať audit log pri approve
- [ ] Pridať audit log pri reject
- [ ] Pridať audit log pri return for changes
- [ ] Pridať unit testy status transitions
- [ ] Pridať integračné testy

## Výstup

Backend podporuje review workflow.

---

# Phase 15 – Review frontend

## Cieľ

Implementovať UI pre review workflow.

## Checklist

- [ ] Vytvoriť `ReviewPage`
- [ ] Vytvoriť `reviewApi`
- [ ] Vytvoriť React Query hooks:
  - [ ] `useSubmitForReview`
  - [ ] `useCreateReviewDecision`
  - [ ] `useReviewHistory`
  - [ ] `useReviewComments`
  - [ ] `useAddReviewComment`
- [ ] Pridať tlačidlo `Submit for review`
- [ ] Pridať tlačidlo `Approve`
- [ ] Pridať tlačidlo `Reject`
- [ ] Pridať tlačidlo `Return for changes`
- [ ] Pridať formulár komentára
- [ ] Zobraziť review históriu
- [ ] Zobraziť komentáre
- [ ] Zobraziť aktuálny status
- [ ] Skryť akcie podľa role
- [ ] Zobraziť 403 error, ak backend odmietne akciu

## Výstup

Používateľ vie cez UI realizovať review workflow.

---

# Phase 16 – Requirement versioning

## Cieľ

Evidovať históriu zmien požiadaviek.

## Checklist

- [ ] Vytvoriť migráciu `requirement_versions`
- [ ] Vytvoriť `RequirementVersionEntity`
- [ ] Vytvoriť `RequirementVersionRepository`
- [ ] Vytvoriť `RequirementVersionService`
- [ ] Vytvoriť DTO:
  - [ ] `RequirementVersionResponse`
- [ ] Pri vytvorení requirementu vytvoriť verziu 1
- [ ] Pri dôležitej zmene vytvoriť novú verziu
- [ ] Uložiť version number
- [ ] Uložiť title
- [ ] Uložiť description
- [ ] Uložiť status
- [ ] Uložiť change reason
- [ ] Uložiť autora zmeny
- [ ] Implementovať endpoint na históriu verzií
- [ ] Zobraziť verzie vo frontende
- [ ] Pridať unit testy
- [ ] Pridať integračné testy

## Výstup

Aplikácia eviduje verzie požiadaviek.

---

# Phase 17 – ADR backend

## Cieľ

Implementovať správu architektonických rozhodnutí.

## Checklist

- [ ] Vytvoriť migráciu `adr_documents`
- [ ] Vytvoriť `AdrDocumentEntity`
- [ ] Vytvoriť `AdrStatus`
- [ ] Vytvoriť `AdrDocumentRepository`
- [ ] Vytvoriť DTO:
  - [ ] `CreateAdrRequest`
  - [ ] `UpdateAdrRequest`
  - [ ] `AdrResponse`
  - [ ] `AdrListItemResponse`
- [ ] Vytvoriť `AdrMapper`
- [ ] Vytvoriť `AdrService`
- [ ] Vytvoriť `AdrController`
- [ ] Implementovať `GET /api/projects/{projectId}/adr`
- [ ] Implementovať `POST /api/projects/{projectId}/adr`
- [ ] Implementovať `GET /api/projects/{projectId}/adr/{adrId}`
- [ ] Implementovať `PATCH /api/projects/{projectId}/adr/{adrId}`
- [ ] Implementovať `DELETE /api/projects/{projectId}/adr/{adrId}`
- [ ] Overiť väzbu ADR na project
- [ ] Pridať validácie
- [ ] Pridať audit logy
- [ ] Pridať unit testy
- [ ] Pridať integračné testy

## Výstup

Backend podporuje ADR dokumenty.

---

# Phase 18 – ADR frontend

## Cieľ

Implementovať UI pre ADR dokumenty.

## Checklist

- [ ] Vytvoriť `AdrListPage`
- [ ] Vytvoriť `AdrDetailPage`
- [ ] Vytvoriť `CreateAdrPage`
- [ ] Vytvoriť `adrApi`
- [ ] Vytvoriť React Query hooks
- [ ] Vytvoriť `AdrForm`
- [ ] Pridať validáciu
- [ ] Zobraziť ADR list
- [ ] Zobraziť ADR detail
- [ ] Pridať editáciu ADR
- [ ] Zobraziť status ADR
- [ ] Pridať loading states
- [ ] Pridať error states

## Výstup

Používateľ vie cez UI spravovať ADR dokumenty.

---

# Phase 19 – Audit module

## Cieľ

Implementovať audit logy.

## Checklist

- [ ] Vytvoriť migráciu `audit_logs`
- [ ] Vytvoriť `AuditLogEntity`
- [ ] Vytvoriť `AuditAction`
- [ ] Vytvoriť `AuditOutcome`
- [ ] Vytvoriť `AuditLogRepository`
- [ ] Vytvoriť `AuditService`
- [ ] Vytvoriť `AuditController`
- [ ] Implementovať `GET /api/audit-logs`
- [ ] Obmedziť endpoint iba pre `ADMIN`
- [ ] Pridať audit pri login success
- [ ] Pridať audit pri project create
- [ ] Pridať audit pri requirement create
- [ ] Pridať audit pri AI generation
- [ ] Pridať audit pri submit review
- [ ] Pridať audit pri approve
- [ ] Pridať audit pri reject
- [ ] Pridať audit pri return for changes
- [ ] Pridať audit pri ADR create
- [ ] Vytvoriť `AuditLogPage`
- [ ] Pridať filtrovanie audit logov
- [ ] Pridať integračné testy

## Výstup

Aplikácia eviduje dôležité akcie.

---

# Phase 20 – Error handling

## Cieľ

Zjednotiť spracovanie chýb.

## Checklist

- [ ] Vytvoriť `BusinessException`
- [ ] Vytvoriť `NotFoundException`
- [ ] Vytvoriť `ForbiddenOperationException`
- [ ] Vytvoriť `InvalidStatusTransitionException`
- [ ] Vytvoriť `AiProviderException`
- [ ] Vytvoriť `GlobalExceptionHandler`
- [ ] Spracovať `MethodArgumentNotValidException`
- [ ] Spracovať `ConstraintViolationException`
- [ ] Spracovať business exceptions
- [ ] Použiť `ProblemDetail`
- [ ] Pridať jednotný error format
- [ ] Pridať field errors do validation response
- [ ] Pridať testy validačných chýb
- [ ] Pridať testy business chýb

## Výstup

Backend vracia konzistentné error responses.

---

# Phase 21 – Authorization hardening

## Cieľ

Dotiahnuť autorizáciu na backend úrovni.

## Checklist

- [ ] Nastaviť endpoint-level security
- [ ] Nastaviť method-level security
- [ ] Pridať `@EnableMethodSecurity`
- [ ] Pridať `@PreAuthorize`, kde dáva zmysel
- [ ] Overiť ADMIN práva
- [ ] Overiť ANALYST práva
- [ ] Overiť DEVELOPER práva
- [ ] Overiť REVIEWER práva
- [ ] Zabrániť neautorizovanému AI generovaniu
- [ ] Zabrániť neautorizovanému review
- [ ] Zabrániť prístupu k audit logom pre neadmina
- [ ] Pridať security integration testy

## Výstup

Autorizácia je vynútená na backende.

---

# Phase 22 – Integration tests

## Cieľ

Pokryť hlavné endpointy integračnými testami.

## Checklist

- [ ] Nastaviť Testcontainers PostgreSQL
- [ ] Nastaviť test profile
- [ ] Otestovať Flyway migrácie
- [ ] Otestovať Project API
- [ ] Otestovať Requirement API
- [ ] Otestovať AI API s MockAiClient
- [ ] Otestovať Review API
- [ ] Otestovať ADR API
- [ ] Otestovať Audit API
- [ ] Otestovať 401 responses
- [ ] Otestovať 403 responses
- [ ] Otestovať 400 validation responses
- [ ] Otestovať 404 not found
- [ ] Otestovať 409 invalid status transition
- [ ] Pridať test data factory
- [ ] Pridať helper pre mock authenticated user

## Výstup

Kľúčové časti backendu sú pokryté integračnými testami.

---

# Phase 23 – Frontend polishing

## Cieľ

Zlepšiť UX a vizuálnu kvalitu aplikácie.

## Checklist

- [ ] Zjednotiť layout
- [ ] Pridať sidebar
- [ ] Pridať topbar
- [ ] Pridať breadcrumb navigáciu
- [ ] Zlepšiť tabuľky
- [ ] Zlepšiť formuláre
- [ ] Pridať empty states
- [ ] Pridať error states
- [ ] Pridať loading skeletons
- [ ] Pridať confirm dialogy
- [ ] Pridať toast notifikácie
- [ ] Pridať role-based buttons
- [ ] Pridať responzívnosť
- [ ] Skontrolovať použiteľnosť

## Výstup

Frontend pôsobí profesionálne.

---

# Phase 24 – CI/CD

## Cieľ

Pridať automatizované kontroly kvality.

## Checklist

- [ ] Vytvoriť `.github/workflows/backend.yml`
- [ ] Vytvoriť `.github/workflows/frontend.yml`
- [ ] Backend build
- [ ] Backend tests
- [ ] Frontend install
- [ ] Frontend lint
- [ ] Frontend build
- [ ] Pridať Docker build, ak bude potrebné
- [ ] Pridať badge do README
- [ ] Nastaviť branch protection, ak budeš používať GitHub
- [ ] Pridať check pred merge

## Výstup

Projekt má základnú CI pipeline.

---

# Phase 25 – Observability

## Cieľ

Pridať základnú observability vrstvu.

## Checklist

- [ ] Pridať Actuator endpointy
- [ ] Pridať Prometheus endpoint
- [ ] Pridať structured logging
- [ ] Pridať request logging
- [ ] Pridať audit logging
- [ ] Pridať Docker Compose pre Grafanu
- [ ] Pridať Prometheus
- [ ] Pridať Loki
- [ ] Pridať Grafana dashboard
- [ ] Pridať základné metriky
- [ ] Zobraziť health stav aplikácie
- [ ] Zobraziť počet requestov
- [ ] Zobraziť chybovosť endpointov

## Výstup

Aplikácia má základnú observability vrstvu.

---

# Phase 26 – Documentation

## Cieľ

Pripraviť projekt na prezentáciu.

## Checklist

- [ ] Doplniť `docs/architecture/system-context.md`
- [ ] Doplniť `docs/architecture/container-diagram.md`
- [ ] Doplniť `docs/architecture/security.md`
- [ ] Doplniť `docs/architecture/bff-flow.md`
- [ ] Doplniť `docs/architecture/database-model.md`
- [ ] Doplniť `docs/architecture/api-design.md`
- [ ] Doplniť `docs/adr/0001-use-modular-monolith.md`
- [ ] Doplniť `docs/adr/0002-use-bff.md`
- [ ] Doplniť `docs/adr/0003-use-keycloak.md`
- [ ] Doplniť `docs/adr/0004-use-postgresql.md`
- [ ] Doplniť `docs/adr/0005-store-ai-generation-history.md`
- [ ] Doplniť `docs/adr/0006-human-review-for-ai-output.md`
- [ ] Doplniť `docs/ai/ai-usage-policy.md`
- [ ] Doplniť `docs/ai/prompt-templates.md`
- [ ] Doplniť `docs/ai/ai-review-checklist.md`
- [ ] Doplniť screenshoty do README
- [ ] Doplniť finálny popis projektu
- [ ] Doplniť sekciu „What I learned“
- [ ] Doplniť sekciu „AI-assisted development approach“

## Výstup

Projekt je pripravený na GitHub a pohovor.

---

# MVP 1 – minimálna funkčná verzia

## Musí obsahovať

- [ ] Backend foundation
- [ ] Frontend foundation
- [ ] Docker Compose
- [ ] PostgreSQL
- [ ] Flyway
- [ ] Keycloak
- [ ] BFF login
- [ ] `/api/auth/me`
- [ ] Project CRUD
- [ ] Requirement CRUD
- [ ] Základné review statusy
- [ ] Základné testy
- [ ] README

---

# MVP 2 – AI verzia

## Musí obsahovať

- [ ] AI provider abstraction
- [ ] Mock AI client
- [ ] Reálny AI client, ak budeš chcieť
- [ ] AI generation endpoint
- [ ] Uloženie AI výstupu
- [ ] AI frontend page
- [ ] AI development log
- [ ] Testy bez reálneho AI API

---

# MVP 3 – portfólio verzia

## Musí obsahovať

- [ ] Review workflow
- [ ] ADR dokumenty
- [ ] Audit logy
- [ ] Requirement versioning
- [ ] Security testy
- [ ] Docker
- [ ] CI
- [ ] Dokumentácia
- [ ] Screenshoty
- [ ] Finálne README
