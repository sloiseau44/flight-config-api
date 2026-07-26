## Description
API REST sécurisée de gestion de configurations de vol pour hélicoptères Airbus.
Inspirée des besoins réels de traçabilité, d'intégrité et de contrôle d'accès du secteur aéronautique et défense.

## Architecture

Ce projet est composé de deux applications distinctes :

| Projet | Technologie | Description |
|---|---|---|
| `flight-config-api` | Spring Boot 4.1.0 | Backend REST API — sécurité, persistance, cryptographie |
| `flight-config-ui` | JavaFX | Client desktop — IHM de gestion des configurations |

Les deux projets communiquent via REST HTTP. Le client JavaFX consomme l'API Spring Boot.

## Repos GitHub
- API : https://github.com/sloiseau44/flight-config-api
- UI  : https://github.com/sloiseau44/flight-config-ui

## Stack technique
- Java 21, Spring Boot 4.1.0, Maven
- Spring Security + JWT (jjwt)
- Spring Data JPA / Hibernate, PostgreSQL 16
- Liquibase — migrations versionnées
- JUnit 5, Mockito, AssertJ, MockMvc
- Testcontainers — tests d'intégration
- Springdoc OpenAPI — Swagger UI
- Java Cryptography Architecture (JCA) — AES-256, SHA-256, RSA
- Docker / Docker Compose

## Domaine métier
Gestion du cycle de vie des configurations de vol :
- Un TECHNICIEN crée une configuration (DRAFT)
- Un TECHNICIEN la soumet pour validation (PENDING_VALIDATION)
- Un ADMIN la valide (VALIDATED)
- Un ADMIN la charge sur l'appareil (LOADED)

## Rôles
| Rôle | Droits |
|---|---|
| ADMIN | Accès complet, validation et chargement des configurations |
| TECHNICIEN | Créer et modifier des configurations |
| PILOTE | Consulter les configurations validées |

## Types d'appareils
H125, H145, H160, H175, H225

## Cybersécurité
- Authentification JWT stateless
- Contrôle d'accès RBAC par rôle (@PreAuthorize)
- BCrypt pour les mots de passe
- Chiffrement AES-256 des paramètres de vol en base
- Checksum SHA-256 pour vérification d'intégrité
- Signature numérique RSA des configurations validées
- Audit trail complet et immuable
- Validation des entrées (@Valid, @NotBlank)
- Secrets externalisés en variables d'environnement

## Démarrage rapide
```bash
docker-compose up -d
# puis lancer l'application depuis IntelliJ
# Swagger UI : http://localhost:8080/swagger-ui/index.html
# Credentials : admin / admin123
```

## Avancement

### ✅ Epic 1 — Setup
- [x] 1.1 Initialiser le projet Maven Spring Boot
- [x] 1.2 Configurer PostgreSQL, JPA et Liquibase
- [x] 1.3 Créer les migrations Liquibase (users, flight_configs, audit_logs)

### ⏳ Epic 2 — Entités & Persistence
- [ ] 2.1 Entité User avec rôles
- [ ] 2.2 Entité FlightConfig
- [ ] 2.3 Entité AuditLog

### ⏳ Epic 3 — Sécurité
- [ ] 3.1 Authentification JWT
- [ ] 3.2 Filtre JwtAuthenticationFilter
- [ ] 3.3 SecurityConfig avec rôles

### ⏳ Epic 4 — API FlightConfig
- [ ] 4.1 CRUD configurations
- [ ] 4.2 Workflow DRAFT → PENDING_VALIDATION → VALIDATED → LOADED
- [ ] 4.3 Endpoint paramètres déchiffrés

### ⏳ Epic 5 — Cybersécurité avancée
- [ ] 5.1 Chiffrement AES-256 des paramètres
- [ ] 5.2 Checksum SHA-256
- [ ] 5.3 Signature numérique RSA

### ⏳ Epic 6 — Audit & Docs
- [ ] 6.1 AuditLog sur chaque action sensible
- [ ] 6.2 Swagger UI
- [ ] 6.3 Tests d'intégration Testcontainers

### ⏳ Epic 7 — Robustesse
- [ ] 7.1 GlobalExceptionHandler
- [ ] 7.2 Validation des entrées
- [ ] 7.3 DataInitializer (admin par défaut)

### ⏳ Epic 8 — Déploiement
- [ ] 8.1 Dockerfile
- [ ] 8.2 docker-compose complet app + BDD

## Structure du projet
```
src/main/java/com/flightconfig/flightconfigapi/
├── auth/
├── config/
└── domain/
    ├── audit/
    ├── flightconfig/
    └── user/
```

### ⏳ Epic UI-1 — Setup JavaFX
- [ ] UI-1.1 Initialiser le projet JavaFX + Maven
- [ ] UI-1.2 Structure MVC

### ⏳ Epic UI-2 — Authentification
- [ ] UI-2.1 Écran de login
- [ ] UI-2.2 Appel POST /auth/login, stockage token

### ⏳ Epic UI-3 — Liste des configurations
- [ ] UI-3.1 TableView avec filtres

### ⏳ Epic UI-4 — Détail & Formulaire
- [ ] UI-4.1 Créer/modifier une configuration

### ⏳ Epic UI-5 — Workflow
- [ ] UI-5.1 Boutons Soumettre/Valider/Charger selon le rôle

### ⏳ Epic UI-6 — Vérification d'intégrité
- [ ] UI-6.1 Afficher le checksum SHA-256

### ⏳ Epic UI-7 — Audit
- [ ] UI-7.1 Écran historique des actions