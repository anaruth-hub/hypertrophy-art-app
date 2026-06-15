# The Art of Muscle Hypertrophy - Frontend

React/Vite frontend for the hypertrophy tracking MVP.

## Current Status

This frontend connects to the Spring Boot backend at:

```text
http://localhost:8080
```

It supports the current working demo flow:

- Register USER with password.
- Register TRAINER with password.
- Login with email and password.
- Store JWT authentication data in `localStorage`.
- USER dashboard for tracking, assignment, progress and recommendations.
- TRAINER dashboard for supervised users, assigned user progress and recommendation creation.

## Tech Stack

- React
- Vite
- JavaScript
- CSS
- Fetch API
- JWT stored in localStorage

## Requirements

- Node.js
- npm
- Backend running on `http://localhost:8080`
- PostgreSQL and MongoDB running for the backend

## Install Dependencies

From this directory:

```bash
npm install
```

If `node_modules` already exists, installation is not required before building or running.

## Run Development Server

```bash
npm run dev
```

Default Vite URL:

```text
http://localhost:5173
```

## Build

On Windows PowerShell, use `npm.cmd` if `npm.ps1` is blocked by execution policy:

```bash
npm.cmd run build
```

Standard command:

```bash
npm run build
```

## Authentication

The app has three public auth views:

- Login
- Register User
- Register Trainer

After login or registration, the backend returns auth data and the frontend stores:

```text
localStorage
token
role
id
name
email
```

Authenticated API requests send:

```http
Authorization: Bearer <token>
```

## USER Dashboard

A USER can access:

- Training form
- Recovery form
- Nutrition form
- Wellness form
- My Progress
- Recommendations
- Assign Trainer

The USER tracking forms no longer show or send `userId`. The backend gets the authenticated user id from the JWT.

### Assignment

The assignment form loads trainers from:

```http
GET /api/trainers
```

The user selects a trainer from the list and the frontend calls:

```http
POST /api/users/me/assign-trainer/{trainerId}
```

## TRAINER Dashboard

A TRAINER can:

- Load supervised users.
- Select one supervised user.
- View that user's progress.
- Create a recommendation for that assigned user.

Used endpoints:

```http
GET /api/trainers/me/users
GET /api/progress-summary/trainers/me/users/{userId}/progress
POST /api/recommendations/trainers/me/users/{userId}
```

## Backend Endpoints Used

### Auth

```http
POST /api/auth/register-user
POST /api/auth/register-trainer
POST /api/auth/login
```

### User

```http
GET /api/users/me
POST /api/users/me/assign-trainer/{trainerId}
GET /api/progress-summary/me
GET /api/recommendations/me
```

### Tracking

```http
POST /api/trainings
POST /api/recovery-checkins
POST /api/nutrition-entries
POST /api/wellness-checkins
```

### Trainer

```http
GET /api/trainers
GET /api/trainers/me/users
GET /api/progress-summary/trainers/me/users/{userId}/progress
POST /api/recommendations/trainers/me/users/{userId}
```

## Recommended Manual UI Demo

1. Start backend and databases.

2. Open:

```text
http://localhost:5173
```

3. Register a trainer.

4. Logout if needed, then register a USER with mode `SUPERVISED`.

5. As USER, open `Assign Trainer` and select the trainer from the list.

6. Register training, recovery, nutrition and wellness data.

7. Open `My Progress` and load the summary.

8. Logout and login as TRAINER.

9. Select the supervised user.

10. View assigned user progress.

11. Create a recommendation.

12. Logout and login as USER.

13. Open `Recommendations` to view received recommendations.

## Known Limitations / Future Improvements

- The UI is intentionally basic for the MVP.
- No charts are implemented.
- No profile editing screen exists.
- No admin dashboard exists.
- No notification system exists.
- Recommendation management is limited to creating and reading recommendations.
- The frontend stores JWT data in localStorage for simplicity.
