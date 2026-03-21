# TaskFlow 🚀

A robust, full-stack collaborative task management platform designed for efficiency and modern workflows.

## 🏗️ Project Architecture

TaskFlow is built with a modern multi-module architecture:

- **[backend/](./backend/)**: A high-performance Kotlin API built with [Micronaut](https://micronaut.io/) and **Hibernate JPA**.
- **[frontend/](./frontend/)**: A responsive web application built with [React](https://reactjs.org/), [TypeScript](https://www.typescriptlang.org/), and [Vite](https://vitejs.dev/).
- **[android/](./android/)**: A native Android application written in Kotlin for mobile productivity.
- **[Infrastructure](./docker-compose.yml)**: Fully containerized local development environment using Docker and PostgreSQL.

## ✨ Features

- **Real-time Task Tracking**: Manage your workflow with ease.
- **Collaborative Workspaces**: Work together with your team seamlessly.
- **Cross-platform**: Access your tasks via Web or Android.
- **Scalable Backend**: Built on Micronaut for low-latency and high-performance API calls.
- **Rich Data Model**: Comprehensive tracking of projects, tasks, comments, labels, and activity logs.

## 📋 Prerequisites

Ensure you have the following installed:
- **JDK 17 or 21** (Backend supports JDK 21)
- **Node.js 18+** & **npm/yarn** (for Frontend)
- **Docker & Docker Compose** (for Infrastructure)
- **Android Studio & SDK** (for Android development)

## 🚀 Getting Started
### Quick Start with Docker
The easiest way to get the entire stack running is using Docker Compose:

```bash
docker compose up --build
```
- **Frontend**: [http://localhost](http://localhost) (Port 80)
- **Backend API**: [http://localhost:8080](http://localhost:8080)
- **Database**: PostgreSQL on `localhost:5432`

---

## ⚙️ Backend Technology Stack

The backend has been modernized to use:
- **Kotlin 1.9.23**: Latest stable version for improved performance and compiler stability.
- **Micronaut 4.2.1**: A modern, JVM-based, full-stack framework for building modular, easily testable microservice and serverless applications.
- **Hibernate JPA**: Robust Object-Relational Mapping (ORM) for complex data relationships.
- **PostgreSQL**: Industry-standard relational database.
- **Flyway**: Automated database migrations.

---

## ⚙️ CI/CD

This project uses **GitHub Actions** for Continuous Integration. Every push and pull request to the `main` branch triggers:
- **Backend**: Build and Micronaut tests (JUnit 5).
- **Frontend**: Build and Vitest tests.
- **Android**: Build (assembleDebug) and unit tests.

The configuration is located in [`.github/workflows/ci.yml`](./.github/workflows/ci.yml).

### Database & Migrations

We use **Flyway** for database migrations.
- **Schema & Seed Data**: Located in `backend/src/main/resources/db/migration`.
- **Naming**: Flyway migrations follow the naming convention `V<Version>__<Description>.sql` (e.g., `V1__Initial_Schema.sql`, `V3__Seed_Data.sql`).

To run migrations for local testing:
```bash
./gradlew :backend:flywayMigrate
```

The Micronaut application also runs migrations automatically on startup using the settings in `backend/src/main/resources/application.yml`.

---

### Manual Development Setup

#### 1. Backend
The backend requires **JDK 17 or 21**.
```bash
./gradlew :backend:run
```
The backend will be available at `http://localhost:8080`.

#### 2. Frontend
The frontend is built with **Vite** and **React**.
```bash
cd frontend
npm install
npm run dev
```
The frontend will be available at `http://localhost:5173`.

#### 3. Android
The Android application has been updated to use **Compose 1.5.4** and **AndroidX**.
1. Open the root directory in **Android Studio**.
2. Sync Project with Gradle Files.
3. Run on an Emulator or Physical Device.

## 🧪 Testing

### Unified Testing
To run all tests (Backend, Frontend, and Android) with a single command from the root:
```bash
./test.sh
```

### Backend
We use [Micronaut Test](https://micronaut-projects.github.io/micronaut-test/latest/guide/) and [JUnit 5](https://junit.org/junit5/) with [MockK](https://mockk.io/) for backend testing.
```bash
./gradlew :backend:test
```

### Frontend
We use [Vitest](https://vitest.dev/) and [React Testing Library](https://testing-library.com/docs/react-testing-library/intro/) for frontend testing.
```bash
cd frontend
npm test
```

### Android
We use [JUnit](https://junit.org/junit4/) for unit tests and [AndroidX Test](https://developer.android.com/training/testing) for instrumented tests.
```bash
./gradlew :android:testDebugUnitTest
./gradlew :android:connectedDebugAndroidTest
```
