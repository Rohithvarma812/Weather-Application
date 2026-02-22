# Weather Intelligence Platform (Java Full Stack)

Production-style full-stack weather platform with reactive backend, JWT security, Redis caching, hyper-local forecasts, health-sensitive alerts, and explainable AI recommendations.

## Tech Stack

- **Backend:** Java 17, Spring Boot 3, Spring WebFlux, Spring Security, JWT, R2DBC, Redis, OpenAPI
- **Frontend:** React 18 + TypeScript + Vite, Tailwind CSS, responsive mobile-first UI
- **Data:** PostgreSQL + Redis
- **External APIs:** OpenWeather API (configured)

## Features

- Hyper-local current weather (`/api/weather/current?lat=&lon=`)
- AI weather decision assistant with explainable output (`/api/weather/assistant`)
- Health-sensitive and severe weather alert generation
- JWT auth endpoint (`/api/auth/login`)
- Redis caching on weather API calls
- Swagger docs at `/swagger-ui.html`
- Dark/light mode responsive UI

## Project Structure

```
backend/   # Spring Boot reactive API
frontend/  # React responsive app
```

## Setup

### 1) Backend

```bash
cd backend
mvn spring-boot:run
```

Environment values are in `backend/src/main/resources/application.yml`, including OpenWeather key:
`57da890b121c43dba683a7c57dcf96a1`

### 2) Frontend

```bash
cd frontend
npm install
npm run dev
```

Copy `.env.example` to `.env` when needed.

## Database Schema

Schema file: `backend/src/main/resources/schema.sql`

Main table:
- `user_profiles` with personalized heat/pollution thresholds and asthma flag

## Sample Test Data

- Coordinates: `12.9716, 77.5946`
- Decision purposes: `commute`, `laundry`, `workout`, `travel`

## API Notes

- Current weather: `GET /api/weather/current?lat=12.97&lon=77.59`
- Assistant: `POST /api/weather/assistant?lat=12.97&lon=77.59`
  ```json
  {
    "purpose": "commute",
    "healthCondition": "asthma",
    "heatSensitivity": 33,
    "pollutionSensitivity": 110
  }
  ```

## Production Hardening Suggestions

- Add full JWT authentication filter and refresh tokens
- Add push notification channel (FCM/APNs/Web Push)
- Move secrets to vault (AWS Secrets Manager/HashiCorp Vault)
- Add CI/CD and container orchestration (Docker + Kubernetes)
