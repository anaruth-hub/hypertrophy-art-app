# The Art of Muscle Hypertrophy

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5-green)
![React](https://img.shields.io/badge/React-Vite-blue)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue)
![MongoDB](https://img.shields.io/badge/MongoDB-NoSQL-green)
![Docker](https://img.shields.io/badge/Docker-Compose-blue)

---

#Description

**The Art of Muscular Hypertrophy** is an MVP aimed at tracking natural hypertrophy.

The project aims to provide a tool for recording and accessing information related to:

* Training
* Recovery and fatigue
* Nutrition and macronutrients
* Physical and emotional well-being
* Overall user progress

In addition, the system incorporates a monitoring model where a coach can track assigned users and generate personalized recommendations.

The project was developed using a Hexagonal Architecture, hybrid persistence via PostgreSQL and MongoDB, authentication using JWT, and a React frontend connected to the backend via REST endpoints.

---

#Technologies

##Backend

* Java 21
  *Spring Boot 3.5
  *Spring Web
  *SpringSecurity
  *JWT Authentication
  *Spring Data JPA
* Spring Data MongoDB
  *Maven
  *Swagger/OpenAPI

##Frontend

*React
* Vite
* JavaScript
  *CSS
* Vercel v0 (UI generation assistance)

## Databases

### PostgreSQL

Used for relational information:

* Users
* Trainers
* User-Trainer assignments
* Authentication accounts

### MongoDB

Used for historical records:

* Trainings
* Recovery Check-ins
  *Nutrition Entries
* Wellness Check-ins
  *Recommendations

## DevOps

* Docker Compose

---

#Architecture

The project follows a hexagonal architecture divided into three layers Main components:

```text
Domain
Application
Infrastructure
```

## Domain

Contains the pure business rules.


Examples:

```text
User
Trainer
Training
RecoveryCheckIn
NutritionEntry
WellnessCheckIn
Recommendation
Account
```

The domain layer does not depend on:

* Spring
* JPA
* MongoDB
* REST Controllers

---

## Application

Contains:

* Use Cases
* Inbound Ports
* Outbound Ports

Examples:

```text
CreateUserUseCase
RegisterTrainingUseCase
ViewProgressSummaryUseCase
CreateRecommendationUseCase
ViewMyRecommendationsUseCase
AuthenticateAccountUseCase
```

---

## Infrastructure

Contains the technical details:

```text
REST Controllers
DTOs
Persistence Adapters
JPA Entities
MongoDB Documents
Mappers
Security Configuration
JWT Authentication
```

---

# Authentication and Authorization

The system incorporates authentication using JWT.

## Roles

### USER

Can:

* View their authenticated profile
* View their progress
* Record workouts
* Record recovery
* Record nutrition
* Record wellness
* View received recommendations

### TRAINER

Can:

* View supervised users
* View the progress of assigned users
* Create recommendations for assigned users

> Note: The assignment between users and trainers is done through REST endpoints. Visual trainer selection from the interface is part of future user experience improvements.

---

# Main Features

## US01 — Create User Profile

Allows users to register.

## US02 — Create Trainer Profile

Allows trainers to register.

## US03 — Assign Trainer to User

Allows you to assign a trainer to a supervised user.

## US04 — Register Training Session

Records training sessions.

## US05 — Register Recovery Check-In

Records:

* Fatigue
* Sleep
* Recovery

## US06 — Register Nutrition Entry

Records:

* Calories
* Protein
* Carbohydrates
* Fats
* Hydration

## US07 — Register Wellness Check-In

Records:

* Physical Condition
* Mental State
* Stress
* Motivation
* Emotional State

## US08 — View Progress Summary

Views the progress summary.

## US09 — Trainer Views Assigned User Progress

Allows a trainer to view the progress of supervised users.

## US10 — Recommendations

Allows a trainer to generate recommendations for assigned users, which the users can then review later.

---

# Main API Endpoints

## Authentication

```http
POST /api/auth/register-user
POST /api/auth/register-trainer
POST /api/auth/login
```

##AuthenticatedUser

```http
GET /api/users/me
GET /api/progress-summary/me
GET /api/recommendations/me
```

## Trainer

```http
GET /api/trainers
GET /api/trainers/me/users
GET /api/progress-summary/trainers/me/users/{userId}/progress
POST /api/recommendations/trainers/me/users/{userId}
```

##Assignment

```http
POST /api/users/{userId}/assign-trainer/{trainerId}
```

##Tracking

```http
POST /api/trainings
POST /api/recovery-checkins
POST /api/nutrition-entries
POST /api/wellness-checkins
```

---

#MainUserFlow

```text
Register Trainer 
↓
Register User 
↓
Login 
↓
Assign Trainer 
↓