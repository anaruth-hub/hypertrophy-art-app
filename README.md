# El arte de la hipertrofia muscular

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5-green)
![React](https://img.shields.io/badge/React-Vite-blue)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue)
![MongoDB](https://img.shields.io/badge/MongoDB-NoSQL-green)
![Docker](https://img.shields.io/badge/Docker-Compose-blue)

---

# Description

**El arte de la hipertrofia muscular** es un MVP orientado al seguimiento de hipertrofia natural.

El objetivo del proyecto es proporcionar una herramienta que permita registrar y consultar información relacionada con:

* Entrenamientos
* Recuperación y fatiga
* Nutrición y macronutrientes
* Bienestar físico y emocional
* Progreso general del usuario

Además, el sistema incorpora un modelo de supervisión donde un entrenador puede realizar seguimiento de usuarios asignados y generar recomendaciones personalizadas.

El proyecto ha sido desarrollado utilizando **Arquitectura Hexagonal**, persistencia híbrida **PostgreSQL + MongoDB**, autenticación mediante **JWT** y un frontend React conectado al backend mediante endpoints REST.

---

# Technologies

## Backend

* Java 21
* Spring Boot 3.5
* Spring Web
* Spring Security
* JWT Authentication
* Spring Data JPA
* Spring Data MongoDB
* Maven
* Swagger / OpenAPI

## Frontend

* React
* Vite
* JavaScript
* CSS
* Vercel v0 (UI generation assistance)

## Databases

### PostgreSQL

Utilizada para información relacional:

* Users
* Trainers
* User-Trainer assignments

### MongoDB

Utilizada para registros históricos:

* Trainings
* Recovery Check-ins
* Nutrition Entries
* Wellness Check-ins
* Recommendations

## DevOps

* Docker Compose

---

# Architecture

El proyecto sigue una arquitectura hexagonal dividida en tres capas principales:

```text
Domain
Application
Infrastructure
```

## Domain

Contiene las reglas de negocio puras.

Ejemplos:

```text
User
Trainer
Training
RecoveryCheckIn
NutritionEntry
WellnessCheckIn
Recommendation
```

La capa de dominio no depende de:

* Spring
* JPA
* MongoDB
* Controladores REST

---

## Application

Contiene:

* Casos de uso
* Puertos de entrada
* Puertos de salida

Ejemplos:

```text
CreateUserUseCase
RegisterTrainingUseCase
ViewProgressSummaryUseCase
CreateRecommendationUseCase
ViewMyRecommendationsUseCase
```

---

## Infrastructure

Contiene los detalles técnicos:

```text
REST Controllers
DTOs
Persistence Adapters
JPA Entities
Mongo Documents
Mappers
Security Configuration
JWT Authentication
```

---

# Authentication and Authorization

El sistema incorpora autenticación mediante JWT.

## Roles

### USER

Puede:

* Consultar su perfil autenticado
* Consultar su progreso
* Consultar recomendaciones recibidas

### TRAINER

Puede:

* Consultar usuarios supervisados
* Consultar progreso de usuarios asignados
* Crear recomendaciones para usuarios asignados

> Nota: En la versión actual del MVP, la asignación entre usuarios y entrenadores se realiza mediante el endpoint de asignación existente. La selección visual de entrenadores desde la interfaz se considera una mejora futura.

---

# Main Features

## US01 — Create User Profile

Permite registrar usuarios.

## US02 — Create Trainer Profile

Permite registrar entrenadores.

## US03 — Assign Trainer to User

Permite asignar un entrenador a un usuario supervisado.

## US04 — Register Training Session

Registro de entrenamientos.

## US05 — Register Recovery Check-In

Registro de:

* Fatiga
* Sueño
* Recuperación

## US06 — Register Nutrition Entry

Registro de:

* Calorías
* Proteína
* Carbohidratos
* Grasas
* Hidratación

## US07 — Register Wellness Check-In

Registro de:

* Estado físico
* Estado mental
* Estrés
* Motivación
* Estado emocional

## US08 — View Progress Summary

Consulta del resumen de progreso.

## US09 — Trainer Views Assigned User Progress

Permite que un entrenador consulte el progreso de usuarios supervisados.

## US10 — Recommendations

Permite que un entrenador genere recomendaciones para usuarios asignados y que los usuarios las consulten posteriormente.

---

# Main API Endpoints

## Authentication

```http
POST /api/auth/register-user
POST /api/auth/register-trainer
POST /api/auth/login
```

## Authenticated User

```http
GET /api/users/me
GET /api/progress-summary/me
GET /api/recommendations/me
```

## Trainer

```http
GET /api/trainers/me/users
GET /api/progress-summary/trainers/me/users/{userId}/progress
POST /api/recommendations/trainers/me/users/{userId}
```

## Assignment

```http
POST /api/users/{userId}/assign-trainer/{trainerId}
```

## Tracking

```http
POST /api/trainings
POST /api/recovery-checkins
POST /api/nutrition-entries
POST /api/wellness-checkins
```

---

# Main User Flow

```text
Trainer Register
        ↓
Trainer Login
        ↓
User Register
        ↓
Assign Trainer
        ↓
User Login
        ↓
User Dashboard
        ↓
Trainer Dashboard
        ↓
Create Recommendation
        ↓
User Reads Recommendation
```

---

# Swagger

Swagger está disponible en:

```text
http://localhost:8080/swagger-ui/index.html
```

Permite:

* Probar endpoints
* Validar respuestas
* Inspeccionar payloads
* Verificar integración frontend/backend

---

# Docker

Levantar contenedores:

```bash
docker compose up -d
```

Verificar:

```bash
docker ps
```

---

# Run Backend

Desde la raíz del proyecto:

```bash
./mvnw clean compile
./mvnw spring-boot:run
```

Backend:

```text
http://localhost:8080
```

---

# Run Frontend

Entrar en la carpeta frontend:

```bash
cd frontend
```

Instalar dependencias:

```bash
npm install
```

Ejecutar:

```bash
npm run dev
```

Frontend:

```text
http://localhost:5173 
http://localhost:3000
```

---
## Frontend

La carpeta `frontend-official` contiene la versión utilizada para la demostración y evaluación del proyecto.

Otras versiones o prototipos de interfaz pueden mantenerse en carpetas separadas únicamente con fines de experimentación y aprendizaje.

# Frontend

El frontend proporciona una interfaz mínima para:

## User Dashboard

* Ver perfil autenticado
* Ver progreso personal
* Ver recomendaciones recibidas

## Trainer Dashboard

* Ver usuarios supervisados
* Consultar progreso de usuarios asignados
* Crear recomendaciones

---

# Frontend Development

El frontend fue generado inicialmente utilizando Vercel v0 como herramienta de asistencia para la creación rápida de interfaces React.

Posteriormente fue adaptado e integrado con los endpoints reales del backend para implementar los flujos de autenticación, consulta de progreso y gestión de recomendaciones.

El frontend tiene como objetivo proporcionar una interfaz mínima para demostrar el funcionamiento del sistema y la integración con la API REST.

El desarrollo del backend, la arquitectura hexagonal, el modelo de dominio, la persistencia híbrida PostgreSQL + MongoDB, la autenticación JWT y los casos de uso constituyen el núcleo funcional del proyecto.

---

# Technical Decisions

## Why Hexagonal Architecture?

Para desacoplar las reglas de negocio de la infraestructura.

Permite modificar:

* Base de datos
* Framework
* Interfaces de usuario

sin afectar la lógica de negocio.

## Why PostgreSQL?

Porque User, Trainer y Assignment son entidades relacionales y estructuradas.

## Why MongoDB?

Porque los registros de entrenamiento, recuperación, nutrición, bienestar y recomendaciones son información histórica y flexible.

## Why JWT?

Para autenticar usuarios y entrenadores mediante tokens sin mantener sesiones en servidor.

---

# Future Improvements

* Selección visual de entrenadores desde la interfaz
* Gestión completa de asignaciones sin uso directo de identificadores
* Dashboard avanzado para entrenadores
* Gráficas de progreso y evolución
* Histórico ampliado de métricas
* Recomendaciones más avanzadas basadas en datos históricos
* Mayor cobertura de pruebas automatizadas
* Despliegue cloud
* Mejora de la experiencia de usuario del frontend

---

# Conclusion

Este proyecto implementa un MVP funcional para el seguimiento de hipertrofia natural utilizando:

* Arquitectura Hexagonal
* Java 21
* Spring Boot
* PostgreSQL
* MongoDB
* JWT Authentication
* React + Vite
* Swagger/OpenAPI
* Docker Compose

El sistema permite registrar información relacionada con entrenamiento, recuperación, nutrición y bienestar, así como gestionar un flujo básico de supervisión entre usuarios y entrenadores mediante autenticación y recomendaciones personalizadas.
