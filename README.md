# El arte de la hipertrofia muscular

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5-green)
![React](https://img.shields.io/badge/React-Vite-blue)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue)
![MongoDB](https://img.shields.io/badge/MongoDB-NoSQL-green)
![Docker](https://img.shields.io/badge/Docker-Compose-blue)

---

# Description

El arte de la hipertrofia muscular es un MVP orientado al seguimiento de hipertrofia natural.

El objetivo del proyecto es permitir que usuarios puedan registrar:

- entrenamientos
- recuperación
- nutrición
- bienestar físico y emocional
- progreso general

Además, el proyecto contempla la posibilidad de que un usuario sea supervisado por un entrenador.

El sistema fue construido utilizando arquitectura hexagonal, persistencia híbrida PostgreSQL + MongoDB y un frontend React conectado al backend mediante endpoints REST.

---

# Technologies

## Backend

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Data MongoDB
- Maven
- Swagger / OpenAPI

## Frontend

- React
- Vite
- JavaScript
- CSS

## Databases

- PostgreSQL
- MongoDB

## DevOps

- Docker Compose

---

# Hexagonal Architecture

El proyecto utiliza arquitectura hexagonal.

La arquitectura se divide en:

```text
Domain
Application
Infrastructure

Domain
Contiene las reglas de negocio puras.
No depende de Spring, JPA, MongoDB ni controladores.
Ejemplos
User
Trainer
Training
RecoveryCheckIn
NutritionEntry
WellnessCheckIn

Application
Contiene los casos de uso y los puertos.
Ejemplos
CreateUserUseCase
RegisterTrainingUseCase
ViewProgressSummaryUseCase
ViewDeloadRecommendationUseCase

Infrastructure
Contiene los detalles técnicos externos.
Incluye
REST Controllers
Request/Response DTOs
Persistence adapters
JPA entities
MongoDB documents
Mappers
Configuration
Database implementations


Persistence
El proyecto utiliza persistencia híbrida.

PostgreSQL
Usado para datos estructurados y relacionales.
Persistencia relacional
Users
Trainers
User-Trainer assignments

MongoDB
Usado para registros flexibles e históricos.
Persistencia documental
Training records
Recovery check-ins
Nutrition entries
Wellness check-ins
Main Features

US01 — Create user profile
Permite crear usuarios self-managed o supervised.

US02 — Create trainer profile
Permite registrar entrenadores.

US03 — Assign trainer to supervised user
Permite asignar entrenadores a usuarios supervisados.

US04 — Register training session
Permite registrar entrenamientos.

US05 — Register recovery and fatigue
Permite registrar:
recuperación
sueño
fatiga
molestias musculares

US06 — Register nutrition macros
Permite registrar:
calorías
proteína
carbohidratos
grasas
hidratación

US07 — Register wellness check-in
Permite registrar:
estado físico
estado mental
estrés
motivación
estado emocional

US08 — View progress summary
Muestra métricas resumidas del usuario.

US09 — Trainer views assigned user progress
Permite que el entrenador vea el progreso del usuario asignado.

US10 — Deload and fatigue recommendations
Devuelve recomendaciones básicas de recuperación y descarga.


Main API Endpoints

Users
Create user
POST /api/users

Trainers
Create trainer
POST /api/trainers

Assignment
Assign trainer to user
POST /api/users/{userId}/assign-trainer/{trainerId}

Training
Register training
POST /api/trainings

Recovery
Register recovery check-in
POST /api/recovery-checkins

Nutrition
Register nutrition entry
POST /api/nutrition-entries

Wellness
Register wellness check-in
POST /api/wellness-checkins

Progress Summary
View user progress summary
GET /api/progress-summary/{userId}

Trainer Progress
Trainer views assigned user progress
GET /api/progress-summary/trainers/{trainerId}/users/{userId}

Recommendations
Deload recommendation
GET /api/recommendations/deload/{userId}


Swagger
Swagger está disponible en:
http://localhost:8080/swagger-ui.html
o:
http://localhost:8080/swagger-ui/index.html

Swagger permite:
probar endpoints
validar respuestas
inspeccionar payloads
comprobar integración frontend/backend
Docker Compose

El proyecto utiliza Docker Compose para levantar PostgreSQL y MongoDB.

Ejecutar contenedores
docker compose up -d
Verificar contenedores
docker ps
Run Backend

Desde la raíz del proyecto:

.\mvnw.cmd clean compile
.\mvnw.cmd spring-boot:run
Backend:
http://localhost:8080

Run Frontend
Entrar en la carpeta frontend:
cd frontend
Instalar dependencias:
npm install
Ejecutar frontend:
npm run dev
Frontend:
http://localhost:5173
Frontend Structure
frontend/src
├── components
│   ├── forms
│   └── layout
├── services
├── App.jsx
└── App.css
Layout Components
Dashboard
NavigationTabs
Form Components
UserForm
TrainerForm
AssignmentForm
TrainingForm
RecoveryForm
NutritionForm
WellnessForm
ProgressSummary
TrainerProgressSummary
DeloadRecommendation
AI-assisted Frontend Development

El frontend fue desarrollado con asistencia de IA.

La IA ayudó a:

generar componentes React iniciales
crear formularios simples
utilizar useState
conectar formularios con el backend mediante fetch
reorganizar el frontend en componentes
mejorar la estructura visual del dashboard

Posteriormente se revisó y ajustó manualmente:
payloads
nombres de campos
endpoints
mensajes de usuario
separación de componentes
estructura visual
Technical Decisions

¿Por qué arquitectura hexagonal?
Para separar el dominio de los detalles técnicos.

El dominio no conoce:
Spring
controladores
bases de datos
frontend
Esto permite cambiar infraestructura sin afectar reglas de negocio.

¿Por qué PostgreSQL?
Porque User, Trainer y Assignment son entidades relacionales y estructuradas.

¿Por qué MongoDB?
Porque Training, Recovery, Nutrition y Wellness son registros flexibles e históricos.

¿Por qué no se añadió JWT?
Spring Security y JWT quedan como mejora futura.

El MVP se enfoca en:
arquitectura
persistencia híbrida
integración frontend/backend
flujo end-to-end
Future Improvements
Spring Security
JWT Authentication
USER/TRAINER roles
Login and registration
Advanced trainer dashboard
Progress charts
Historical tracking
Smarter fatigue recommendations
AI-assisted fatigue analysis
More automated tests
Cloud deployment

Conclusion
Este proyecto demuestra un MVP funcional de seguimiento de hipertrofia natural utilizando:
arquitectura hexagonal
persistencia híbrida PostgreSQL + MongoDB
frontend React conectado al backend
Swagger/OpenAPI
Docker Compose

El sistema implementa un flujo end-to-end funcional entre frontend, backend y persistencia, permitiendo registrar y visualizar información relacionada con entrenamiento, recuperación, nutrición y bienestar.