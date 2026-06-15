# The Art of Muscle Hypertrophy

Backend and frontend MVP for natural hypertrophy tracking with user and trainer flows.

## Current Status

The backend is a Spring Boot application using Java 21 and a hexagonal architecture:

```text
domain
application
infrastructure
```

The current working flow supports:

- User and trainer registration with password.
- Login with JWT.
- Protected endpoints using `Authorization: Bearer <token>`.
- USER role for profile, tracking, progress and received recommendations.
- TRAINER role for supervised users, assigned user progress and recommendation creation.
- User-trainer assignment without manually copying the current user's ID.
- PostgreSQL for users, trainers, authentication data and user-trainer assignment.
- MongoDB for training, recovery, nutrition, wellness and recommendations.

## Tech Stack

- Java 21
- Spring Boot 3.5
- Spring Web
- Spring Security
- JWT
- Spring Data JPA
- PostgreSQL
- Spring Data MongoDB
- MongoDB
- Docker Compose
- Swagger / OpenAPI
- Maven

## Persistence

### PostgreSQL

PostgreSQL stores relational and authentication-related data:

- Users
- Trainers
- Password hashes
- Roles
- User mode (`SELF_MANAGED` / `SUPERVISED`)
- User-trainer assignment

### MongoDB

MongoDB stores historical/user activity documents:

- Training sessions
- Recovery check-ins
- Nutrition entries
- Wellness check-ins
- Trainer recommendations

## Authentication and Roles

Authentication is JWT-based.

Auth endpoints:

```http
POST /api/auth/register-user
POST /api/auth/register-trainer
POST /api/auth/login
```

After registration or login, the backend returns:

- JWT token
- role
- id
- name
- email

Protected requests must include:

```http
Authorization: Bearer <token>
```

### USER

A USER can:

- View their profile.
- Assign themselves to a trainer.
- Register training, recovery, nutrition and wellness data.
- View their progress summary.
- View recommendations created for them.

The authenticated USER no longer sends `userId` manually in tracking payloads. The backend takes the user id from the JWT.

### TRAINER

A TRAINER can:

- View their supervised users.
- View progress for users assigned to them.
- Create recommendations for users assigned to them.

## Main Endpoints

### Authentication

```http
POST /api/auth/register-user
POST /api/auth/register-trainer
POST /api/auth/login
```

### User

```http
GET /api/users/me
POST /api/users/me/assign-trainer/{trainerId}
```

Legacy assignment endpoint still exists, but it is protected and only allows the authenticated user to assign their own profile:

```http
POST /api/users/{userId}/assign-trainer/{trainerId}
```

### Tracking

All tracking endpoints require a USER JWT. Do not send `userId` in the request body.

```http
POST /api/trainings
POST /api/recovery-checkins
POST /api/nutrition-entries
POST /api/wellness-checkins
```

### Progress

```http
GET /api/progress-summary/me
GET /api/progress-summary/trainers/me/users/{userId}/progress
```

Legacy ID-based progress endpoints still exist but are protected by role and identity checks:

```http
GET /api/progress-summary/{userId}
GET /api/progress-summary/trainers/{trainerId}/users/{userId}
```

### Trainers

```http
GET /api/trainers
GET /api/trainers/me/users
```

`GET /api/trainers` is public so users can select a trainer. Trainer-specific endpoints require a TRAINER JWT.

### Recommendations

```http
GET /api/recommendations/me
POST /api/recommendations/trainers/me/users/{userId}
GET /api/recommendations/deload/{userId}
```

`GET /api/recommendations/me` requires USER. Creating recommendations requires TRAINER and the target user must be assigned to that trainer.

## Requirements

- Java 21
- Maven
- Docker Desktop or Docker Compose
- PostgreSQL and MongoDB via `docker-compose.yml`
- Optional: Node.js if running the frontend

## Start Databases

From the project root:

```bash
docker compose up -d
```

This starts:

- PostgreSQL on `localhost:5432`
- MongoDB on `localhost:27018`

Database configuration is in:

```text
src/main/resources/application.properties
```

## Run Backend

Recommended command:

```bash
mvn spring-boot:run
```

Run tests:

```bash
.\\mvnw.cmd clean test
```

If you already have Maven installed, this also works:

```bash
mvn clean test
```

## Swagger

Start the backend and open:

```text
http://localhost:8080/swagger-ui.html
```

## Using Swagger With JWT

1. Register a trainer:

```http
POST /api/auth/register-trainer
```

2. Register a user:

```http
POST /api/auth/register-user
```

3. Or login:

```http
POST /api/auth/login
```

4. Copy the returned `token`.

5. In Swagger, click `Authorize`.

6. Paste the token with the Bearer prefix:

```text
Bearer <token>
```

7. Test protected endpoints such as:

```http
GET /api/users/me
POST /api/trainings
GET /api/progress-summary/me
GET /api/trainers/me/users
POST /api/recommendations/trainers/me/users/{userId}
```

## Recommended Manual Demo Flow

1. Start PostgreSQL and MongoDB:

```bash
docker compose up -d
```

2. Start backend:

```bash
mvn spring-boot:run
```

3. Register a trainer using `/api/auth/register-trainer`.

4. Register a supervised user using `/api/auth/register-user` with:

```json
{
  "name": "User Demo",
  "email": "user.demo@test.com",
  "password": "password123",
  "mode": "SUPERVISED"
}
```

5. Login as the user or use the returned user token.

6. List trainers:

```http
GET /api/trainers
```

7. Assign the user to a trainer:

```http
POST /api/users/me/assign-trainer/{trainerId}
```

8. Register user tracking data with the USER token:

```http
POST /api/trainings
POST /api/recovery-checkins
POST /api/nutrition-entries
POST /api/wellness-checkins
```

Do not include `userId` in these request bodies.

9. View user progress:

```http
GET /api/progress-summary/me
```

10. Login as the trainer.

11. View supervised users:

```http
GET /api/trainers/me/users
```

12. View assigned user progress:

```http
GET /api/progress-summary/trainers/me/users/{userId}/progress
```

13. Create a recommendation:

```http
POST /api/recommendations/trainers/me/users/{userId}
```

14. Login as the user again and view recommendations:

```http
GET /api/recommendations/me
```

## Verification

Backend verified with:

```bash
.\\mvnw.cmd clean test
```

Result:

```text
Tests run: 7, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

Frontend verified from `frontend-official` with:

```bash
npm.cmd run build
```

Result:

```text
vite build completed successfully
```

## Known Limitations / Future Improvements

- Legacy endpoints for creating users/trainers without real auth still exist (`POST /api/users`, `POST /api/trainers`) and should not be used for the demo login flow.
- Spring Data logs repository scanning warnings because JPA and Mongo repositories coexist in the same application. The app still starts and tests pass.
- There is no admin role.
- There is no profile editing flow.
- Progress summary is basic and computed from the latest records.
- Recommendation management is simple: trainers create recommendations and users read them.
