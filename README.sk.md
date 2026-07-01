# SpecPilot

[🇬🇧 English](README.md) | 🇸🇰 Slovenčina

**SpecPilot** je AI-asistovaná platforma na správu požiadaviek a architektonických rozhodnutí v softvérových projektoch.

Toto nie je obyčajná chatbot aplikácia. Je to fullstack systém, ktorý pomáha transformovať biznis nápady na štruktúrované požiadavky, user stories, akceptačné kritériá, technické návrhy, API návrhy, testovacie scenáre a architektonické rozhodnutia — pričom finálne rozhodnutie má vždy človek.

## Obsah

- [Účel projektu](#účel-projektu)
- [Hlavné ciele](#hlavné-ciele)
- [Cieľoví používatelia](#cieľoví-používatelia)
- [Moduly aplikácie](#moduly-aplikácie)
- [Technologický stack](#technologický-stack)
- [Prehľad architektúry](#prehľad-architektúry)
- [AI-asistovaný workflow](#ai-asistovaný-workflow)
- [Ako spustiť projekt](#ako-spustiť-projekt)
- [Štruktúra projektu a dokumentácia](#štruktúra-projektu-a-dokumentácia)
- [Roadmapa](#roadmapa)
- [Portfolio hodnota](#portfolio-hodnota)
- [Autor](#autor)

## Účel projektu

Moderný softvérový vývoj sa mení, pretože AI nástroje dokážu veľmi rýchlo generovať kód, testy, dokumentáciu a technické návrhy. AI výstupy však stále potrebujú ľudskú kontrolu.

SpecPilot demonštruje realistický workflow, kde AI pomáha pri analýze a vývoji softvéru, ale finálna zodpovednosť — za architektúru, kontrolu, validáciu, bezpečnosť a rozhodnutia — zostáva na developerovi.

## Hlavné ciele

1. Vybudovať fullstack aplikáciu pomocou moderného Java a React stacku.
2. Demonštrovať bezpečnú autentifikáciu cez Keycloak a Backend-for-Frontend architektúru.
3. Integrovať AI do reálneho softvérového workflow.
4. Ukladať a verzovať AI-generované výstupy.
5. Vyžadovať ľudské review pred schválením AI výstupu.
6. Použiť čistú backend architektúru s controllermi, service vrstvou, repository vrstvou, DTO a mappermi.
7. Použiť PostgreSQL s Flyway migráciami.
8. Zabezpečiť automatizované testy pre dôležitú business logiku a API endpointy.
9. Dokumentovať technické rozhodnutia pomocou ADR dokumentov.
10. Ukázať profesionálny vývojový workflow vhodný pre portfólio alebo pracovný pohovor.

## Cieľoví používatelia

| Rola | Zodpovednosti |
|---|---|
| **Analyst** | Vytvára projekty a business požiadavky, spúšťa AI analýzu, posiela požiadavky na review |
| **Developer** | Číta schválené požiadavky, kontroluje technický návrh, vytvára ADR dokumenty |
| **Reviewer** | Schvaľuje, zamieta alebo vracia na prepracovanie AI-generované a human-edited požiadavky |
| **Admin** | Spravuje používateľov a role, kontroluje audit logy, udržiava konfiguráciu |

## Moduly aplikácie

1. **Authentication** — Keycloak login, BFF session-based autentifikácia, `/api/auth/me`, chránené frontend routes.
2. **Project** — vytváranie, úprava, zoznam a správa softvérových projektov a ich členov.
3. **Requirement** — vytváranie a správa business/technických požiadaviek so statusom a prioritou.
4. **AI Analysis** — generuje štruktúrované návrhy (summary, user stories, acceptance criteria, API/DB návrhy, riziká) z požiadavky a ukladá kompletnú históriu generovaní.
5. **Review** — human-in-the-loop schvaľovací workflow: approve, reject, return for changes, komentáre.
6. **Versioning** — uchováva historické verzie požiadaviek pri ich zmenách.
7. **ADR** — architektonické rozhodnutia naviazané na projekt.
8. **Audit** — zaznamenáva dôležité akcie používateľov pre spätnú vysledovateľnosť.

## Technologický stack

**Backend:** Java 21, Spring Boot, Spring Security, OAuth2 Client, Spring Data JPA, PostgreSQL, Flyway, MapStruct, ProblemDetail, JUnit 5, Mockito, Testcontainers, springdoc-openapi

**Frontend:** React, TypeScript, Vite, Material UI, React Router, React Query, React Hook Form, Zod

**Infraštruktúra:** Docker, Docker Compose, Keycloak, Nginx/Caddy, GitHub Actions

**AI:** OpenAI API / Anthropic API, volané výhradne z backendu

## Prehľad architektúry

Aplikácia používa modulárnu monolitickú architektúru s Backend-for-Frontend (BFF) princípom: frontend nikdy nepracuje s access tokenmi ani nevolá AI providera priamo.

```text
Browser
  |  HTTPS
  v
Frontend (React)
  |  API volania, bez práce s tokenmi
  v
Backend (Spring Boot / BFF)  <--- OIDC login --->  Keycloak
  |  JPA / SQL                |  HTTPS
  v                           v
PostgreSQL                AI Provider API
```

## AI-asistovaný workflow

AI výstup nie je zdroj pravdy. Pre každé generovanie backend ukladá vstupný text, prompt template, verziu promptu, názov modelu, výstup a používateľa, ktorý generovanie spustil.

```text
DRAFT -> (generovanie AI analýzy) -> AI_GENERATED -> (submit for review) -> IN_REVIEW -> (approve) -> APPROVED
                                                                                |-> REJECTED
                                                                                |-> RETURNED_FOR_CHANGES
```

AI vytvorí draft. Človek (reviewer) ho upraví, schváli alebo zamietne — AI nikdy neschvaľuje svoj vlastný výstup.

## Ako spustiť projekt

```bash
docker compose -f infra/docker/docker-compose.dev.yml up -d

cd backend && ./mvnw spring-boot:run
cd frontend && npm install && npm run dev
```

| Služba | URL |
|---|---|
| Frontend | http://localhost:5173 |
| Backend API | http://localhost:8080 |
| Swagger UI | http://localhost:8080/swagger-ui.html |
| Keycloak | http://localhost:8081 |

## Štruktúra projektu a dokumentácia

Kompletnú štruktúru priečinkov nájdeš v [`PROJECT_STRUCTURE.md`](PROJECT_STRUCTURE.md) a kompletný fázovaný checklist vývoja v [`TASKS.md`](TASKS.md).

Podrobná technická dokumentácia (architektúra, databázový model, API design, security, ADR, AI usage policy) sa nachádza v priečinku `docs/` a dopĺňa sa priebežne s tým, ako sa jednotlivé časti systému implementujú.

## Roadmapa

Vývoj sleduje fázovaný plán v [`TASKS.md`](TASKS.md), rozdelený do troch MVP míľnikov:

- **MVP 1** — foundation, Docker, Keycloak, BFF login, project & requirement CRUD, základné review.
- **MVP 2** — AI provider integrácia, AI analysis endpoint a frontend.
- **MVP 3** — review workflow, ADR dokumenty, audit logy, requirement versioning, security testy, doladenie.

## Portfolio hodnota

Tento projekt demonštruje schopnosť pochopiť business požiadavky, navrhnúť bezpečný fullstack systém, zodpovedne integrovať AI, písať testy a dokumentovať architektonické rozhodnutia.

AI dokáže generovať drafty — no za architektúru, korektnosť, bezpečnosť, udržateľnosť a finálne rozhodnutia zodpovedá developer.

## Autor

**Martin Baliak**
Fullstack Developer — Java | Spring Boot | React | PostgreSQL | Docker | Keycloak | AI-assisted development