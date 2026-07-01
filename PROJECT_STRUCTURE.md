# SpecPilot – kompletná štruktúra projektu

Tento súbor popisuje odporúčanú štruktúru priečinkov a súborov pre projekt SpecPilot.

Projekt je rozdelený na:

- `backend` – Spring Boot aplikácia
- `frontend` – React aplikácia
- `infra` – Docker, Keycloak, reverse proxy, observability
- `docs` – architektúra, ADR, AI dokumentácia, feature dokumentácia
- `scripts` – pomocné skripty
- `.github` – CI/CD workflow

---

# Root štruktúra

```text
specpilot
  .github
    workflows
      backend.yml
      frontend.yml

  backend
    .mvn
      wrapper
        maven-wrapper.properties
    src
      main
        java
          sk
            martin
              specpilot
                SpecpilotApplication.java
                auth
                user
                project
                requirement
                ai
                review
                adr
                audit
                common
                config
        resources
          application.yml
          application-local.yml
          application-test.yml
          db
            migration
    src
      test
        java
          sk
            martin
              specpilot
    mvnw
    mvnw.cmd
    pom.xml
    README.md

  frontend
    public
    src
      app
      pages
      features
      shared
      api
      router
      auth
      main.tsx
      App.tsx
    index.html
    package.json
    vite.config.ts
    tsconfig.json
    eslint.config.js
    README.md

  infra
    docker
      docker-compose.dev.yml
      docker-compose.observability.yml
    keycloak
      realm-export.json
      README.md
    nginx
      default.conf
      README.md
    observability
      prometheus
        prometheus.yml
      grafana
        dashboards
        provisioning
          datasources
          dashboards
      loki
        loki-config.yml
      tempo
        tempo.yml
      alloy
        config.alloy

  docs
    architecture
      system-context.md
      container-diagram.md
      security.md
      bff-flow.md
      database-model.md
      api-design.md
      backend-architecture.md
      frontend-architecture.md
    adr
      0001-use-modular-monolith.md
      0002-use-bff.md
      0003-use-keycloak.md
      0004-use-postgresql.md
      0005-store-ai-generation-history.md
      0006-human-review-for-ai-output.md
    ai
      ai-usage-policy.md
      prompt-templates.md
      ai-review-checklist.md
      ai-development-rules.md
    features
      project-management.md
      requirement-management.md
      ai-analysis.md
      review-workflow.md
      adr-management.md
      audit-logs.md

  scripts
    start-dev.sh
    stop-dev.sh
    reset-db.sh
    export-keycloak-realm.sh
    import-keycloak-realm.sh

  README.md
  README.sk.md
  TASKS.md
  PROJECT_STRUCTURE.md
  AI_DEVELOPMENT_LOG.md
  .gitignore
  .editorconfig
  .env.example
```

---

# Backend detail

## Package štruktúra

```text
sk.martin.specpilot
  SpecpilotApplication.java

  auth
    controller
      AuthController.java
    dto
      AuthMeResponse.java
    service
      CurrentUserService.java
      AuthUserSyncService.java

  user
    domain
      UserEntity.java
    repository
      UserRepository.java
    service
      UserService.java
    dto
      UserResponse.java

  project
    controller
      ProjectController.java
    service
      ProjectService.java
    repository
      ProjectRepository.java
      ProjectMemberRepository.java
    domain
      ProjectEntity.java
      ProjectMemberEntity.java
      ProjectStatus.java
      ProjectRole.java
    dto
      CreateProjectRequest.java
      UpdateProjectRequest.java
      ProjectResponse.java
      ProjectListItemResponse.java
    mapper
      ProjectMapper.java
    exception
      ProjectNotFoundException.java

  requirement
    controller
      RequirementController.java
      RequirementVersionController.java
    service
      RequirementService.java
      RequirementVersionService.java
    repository
      RequirementRepository.java
      RequirementVersionRepository.java
    domain
      RequirementEntity.java
      RequirementVersionEntity.java
      RequirementStatus.java
      RequirementPriority.java
    dto
      CreateRequirementRequest.java
      UpdateRequirementRequest.java
      RequirementResponse.java
      RequirementListItemResponse.java
      RequirementVersionResponse.java
    mapper
      RequirementMapper.java
      RequirementVersionMapper.java
    exception
      RequirementNotFoundException.java
      InvalidRequirementStatusException.java

  ai
    controller
      AiAnalysisController.java
    service
      AiAnalysisService.java
      PromptTemplateService.java
    client
      AiClient.java
      MockAiClient.java
      OpenAiClient.java
      AnthropicAiClient.java
    repository
      AiGenerationRepository.java
    domain
      AiGenerationEntity.java
    dto
      AiAnalysisRequest.java
      AiAnalysisResponse.java
      AiGenerationResponse.java
    mapper
      AiGenerationMapper.java
    exception
      AiProviderException.java

  review
    controller
      ReviewController.java
    service
      ReviewService.java
    repository
      ReviewRepository.java
      ReviewCommentRepository.java
    domain
      ReviewEntity.java
      ReviewCommentEntity.java
      ReviewDecision.java
    dto
      SubmitReviewRequest.java
      ReviewDecisionRequest.java
      ReviewResponse.java
      ReviewCommentRequest.java
      ReviewCommentResponse.java
    mapper
      ReviewMapper.java
      ReviewCommentMapper.java
    exception
      InvalidReviewTransitionException.java

  adr
    controller
      AdrController.java
    service
      AdrService.java
    repository
      AdrDocumentRepository.java
    domain
      AdrDocumentEntity.java
      AdrStatus.java
    dto
      CreateAdrRequest.java
      UpdateAdrRequest.java
      AdrResponse.java
      AdrListItemResponse.java
    mapper
      AdrMapper.java
    exception
      AdrNotFoundException.java

  audit
    controller
      AuditController.java
    service
      AuditService.java
    repository
      AuditLogRepository.java
    domain
      AuditLogEntity.java
      AuditAction.java
      AuditOutcome.java
    dto
      AuditLogResponse.java
    mapper
      AuditLogMapper.java

  common
    exception
      BusinessException.java
      NotFoundException.java
      ForbiddenOperationException.java
      InvalidStatusTransitionException.java
      GlobalExceptionHandler.java
      ErrorCode.java
    web
      PageResponse.java
      ApiResponse.java
    validation
      ValidationErrorResponse.java

  config
    SecurityConfig.java
    CorsConfig.java
    OpenApiConfig.java
    JacksonConfig.java
    JpaAuditingConfig.java
    AiConfig.java
```

---

# Backend resources

```text
backend/src/main/resources
  application.yml
  application-local.yml
  application-test.yml
  db
    migration
      V1__create_users_table.sql
      V2__create_projects_table.sql
      V3__create_project_members_table.sql
      V4__create_requirements_table.sql
      V5__create_requirement_versions_table.sql
      V6__create_ai_generations_table.sql
      V7__create_reviews_table.sql
      V8__create_review_comments_table.sql
      V9__create_adr_documents_table.sql
      V10__create_audit_logs_table.sql
  prompts
    requirement-analysis-v1.md
```

---

# Backend test štruktúra

```text
backend/src/test/java/sk/martin/specpilot
  TestSpecpilotApplication.java

  common
    AbstractIntegrationTest.java
    TestSecurityConfig.java
    TestDataFactory.java

  project
    ProjectServiceTest.java
    ProjectControllerIntegrationTest.java

  requirement
    RequirementServiceTest.java
    RequirementControllerIntegrationTest.java
    RequirementVersionServiceTest.java

  ai
    AiAnalysisServiceTest.java
    AiAnalysisControllerIntegrationTest.java
    MockAiClientTest.java

  review
    ReviewServiceTest.java
    ReviewControllerIntegrationTest.java

  adr
    AdrServiceTest.java
    AdrControllerIntegrationTest.java

  audit
    AuditServiceTest.java
    AuditControllerIntegrationTest.java

  security
    AuthorizationIntegrationTest.java
```

---

# Frontend detail

## Frontend štruktúra

```text
frontend/src
  main.tsx
  App.tsx

  app
    AppShell.tsx
    providers
      QueryProvider.tsx
      AuthProvider.tsx
      ThemeProvider.tsx
    layout
      MainLayout.tsx
      Sidebar.tsx
      Topbar.tsx
      PageContainer.tsx

  router
    AppRouter.tsx
    ProtectedRoute.tsx
    RoleProtectedRoute.tsx
    routes.ts

  api
    apiClient.ts
    authApi.ts
    projectApi.ts
    requirementApi.ts
    aiApi.ts
    reviewApi.ts
    adrApi.ts
    auditApi.ts

  auth
    useAuth.ts
    authTypes.ts

  pages
    LoginPage.tsx
    DashboardPage.tsx
    UnauthorizedPage.tsx
    NotFoundPage.tsx

  features
    projects
      pages
        ProjectsPage.tsx
        CreateProjectPage.tsx
        ProjectDetailPage.tsx
        EditProjectPage.tsx
      components
        ProjectForm.tsx
        ProjectCard.tsx
        ProjectTable.tsx
      hooks
        useProjects.ts
        useProject.ts
        useCreateProject.ts
        useUpdateProject.ts
        useDeleteProject.ts
      types
        projectTypes.ts

    requirements
      pages
        CreateRequirementPage.tsx
        RequirementDetailPage.tsx
        EditRequirementPage.tsx
      components
        RequirementForm.tsx
        RequirementStatusBadge.tsx
        RequirementPriorityBadge.tsx
        RequirementTable.tsx
        RequirementVersionList.tsx
      hooks
        useRequirements.ts
        useRequirement.ts
        useCreateRequirement.ts
        useUpdateRequirement.ts
        useDeleteRequirement.ts
        useRequirementVersions.ts
      types
        requirementTypes.ts

    ai
      pages
        AiAnalysisPage.tsx
      components
        AiAnalysisResult.tsx
        AiGenerationHistory.tsx
        AiDraftWarning.tsx
      hooks
        useGenerateAiAnalysis.ts
        useAiGenerations.ts
        useAiGeneration.ts
      types
        aiTypes.ts

    review
      pages
        ReviewPage.tsx
      components
        ReviewActions.tsx
        ReviewCommentForm.tsx
        ReviewCommentList.tsx
        ReviewHistory.tsx
      hooks
        useSubmitForReview.ts
        useCreateReviewDecision.ts
        useReviewHistory.ts
        useReviewComments.ts
        useAddReviewComment.ts
      types
        reviewTypes.ts

    adr
      pages
        AdrListPage.tsx
        AdrDetailPage.tsx
        CreateAdrPage.tsx
        EditAdrPage.tsx
      components
        AdrForm.tsx
        AdrStatusBadge.tsx
        AdrTable.tsx
      hooks
        useAdrList.ts
        useAdr.ts
        useCreateAdr.ts
        useUpdateAdr.ts
        useDeleteAdr.ts
      types
        adrTypes.ts

    audit
      pages
        AuditLogPage.tsx
      components
        AuditLogTable.tsx
        AuditLogFilters.tsx
      hooks
        useAuditLogs.ts
      types
        auditTypes.ts

  shared
    components
      LoadingState.tsx
      ErrorState.tsx
      EmptyState.tsx
      ConfirmDialog.tsx
      FormTextField.tsx
      FormSelect.tsx
      StatusBadge.tsx
    hooks
      useToast.ts
    utils
      dateUtils.ts
      errorUtils.ts
      roleUtils.ts
    types
      commonTypes.ts
```

---

# Infra detail

## Docker

```text
infra/docker
  docker-compose.dev.yml
  docker-compose.observability.yml
```

## Keycloak

```text
infra/keycloak
  realm-export.json
  README.md
```

## Nginx

```text
infra/nginx
  default.conf
  README.md
```

## Observability

```text
infra/observability
  prometheus
    prometheus.yml
  grafana
    dashboards
      spring-boot-dashboard.json
      keycloak-dashboard.json
      postgres-dashboard.json
    provisioning
      datasources
        datasources.yml
      dashboards
        dashboards.yml
  loki
    loki-config.yml
  tempo
    tempo.yml
  alloy
    config.alloy
```

---

# Docs detail

## Architecture docs

```text
docs/architecture
  system-context.md
  container-diagram.md
  security.md
  bff-flow.md
  database-model.md
  api-design.md
  backend-architecture.md
  frontend-architecture.md
```

## ADR docs

```text
docs/adr
  0001-use-modular-monolith.md
  0002-use-bff.md
  0003-use-keycloak.md
  0004-use-postgresql.md
  0005-store-ai-generation-history.md
  0006-human-review-for-ai-output.md
```

## AI docs

```text
docs/ai
  ai-usage-policy.md
  prompt-templates.md
  ai-review-checklist.md
  ai-development-rules.md
```

## Feature docs

```text
docs/features
  project-management.md
  requirement-management.md
  ai-analysis.md
  review-workflow.md
  adr-management.md
  audit-logs.md
```

---

# Pomocné súbory v root priečinku

```text
README.md
README.sk.md
TASKS.md
PROJECT_STRUCTURE.md
AI_DEVELOPMENT_LOG.md
.env.example
.gitignore
.editorconfig
```

---

# Odporúčané commity

```text
chore: initialize repository structure
chore: add backend foundation
chore: add frontend foundation
chore: add docker compose infrastructure
feat: configure keycloak authentication
feat: implement bff auth flow
feat: add project management backend
feat: add project management frontend
feat: add requirement management backend
feat: add requirement management frontend
feat: add ai analysis backend
feat: add ai analysis frontend
feat: add review workflow
feat: add adr management
feat: add audit logs
test: add integration tests
docs: add architecture documentation
```
