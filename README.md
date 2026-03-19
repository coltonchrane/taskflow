# TaskFlow 🚀

A robust, full-stack collaborative task management platform designed for efficiency and modern workflows.

## 🏗️ Project Architecture

TaskFlow is built with a modern multi-module architecture:

- **[backend/](./backend/)**: A high-performance Kotlin API built with [Micronaut](https://micronaut.io/).
- **[frontend/](./frontend/)**: A responsive web application built with [React](https://reactjs.org/), [TypeScript](https://www.typescriptlang.org/), and [Vite](https://vitejs.dev/).
- **[android/](./android/)**: A native Android application written in Kotlin for mobile productivity.
- **[Infrastructure](./docker-compose.yml)**: Fully containerized local development environment using Docker and PostgreSQL.

## ✨ Features

- **Real-time Task Tracking**: Manage your workflow with ease.
- **Collaborative Workspaces**: Work together with your team seamlessly.
- **Cross-platform**: Access your tasks via Web or Android.
- **Scalable Backend**: Built on Micronaut for low-latency and high-performance API calls.

## 📋 Prerequisites

Ensure you have the following installed:
- **JDK 17+** (for Backend and Android)
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

## ⚙️ CI/CD

This project uses **GitHub Actions** for Continuous Integration. Every push and pull request to the `main` branch triggers:
- **Backend**: Build and Spock tests.
- **Frontend**: Build and Vitest tests.
- **Android**: Build (assembleDebug) and unit tests.

The configuration is located in [`.github/workflows/ci.yml`](./.github/workflows/ci.yml).

### Database & Migrations
We use **Flyway** for database migrations.
- **Schema**: Located in `backend/src/main/resources/db/migration`.
- **Seed Data**: Located in `backend/src/main/resources/db/seed/seed_data.sql`.

To run migrations with seed data for local testing:
```bash
./gradlew flywayMigrate -Dflyway.locations=classpath:db/migration,classpath:db/seed
```
(Note: You may need to configure your local DB connection in `backend/build.gradle` or via environment variables).

---

### Manual Development Setup

#### 1. Backend
The backend uses **Micronaut 4.2.1** and requires **JDK 17**.
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
The frontend will be available at `http://localhost:5173` (standard Vite port, though Docker maps it to 3000).

#### 3. Android
The Android application has been updated to use **Compose 1.5.4** and **AndroidX**.
1. Open the root directory in **Android Studio**.
2. Sync Project with Gradle Files.
3. Run on an Emulator or Physical Device.

*Note: Ensure `gradle.properties` in the root directory contains `android.useAndroidX=true` and `android.enableJetifier=true`.*

## 🧪 Testing

### Backend
We use [Spock Framework](https://spockframework.org/) for backend testing.
```bash
./gradlew :backend:test
```
Tests are located in `backend/src/test/groovy/com/taskflow/controller/`.

### Frontend
We use [Vitest](https://vitest.dev/) and [React Testing Library](https://testing-library.com/docs/react-testing-library/intro/) for frontend testing.
```bash
cd frontend
npm test
```
Tests are co-located with components (e.g., `frontend/src/components/TaskList.test.tsx`).

### Android
We have both local unit tests and instrumented tests.
- **Unit Tests** (Run on JVM):
  ```bash
  ./gradlew :android:testDebugUnitTest
  ```
- **Instrumented Tests** (Run on device/emulator):
  ```bash
  ./gradlew :android:connectedDebugAndroidTest
  ```
Tests are located in `android/src/test/` and `android/src/androidTest/`.

## 🤝 Contributing
We welcome contributions! Please see our [CONTRIBUTING.md](./CONTRIBUTING.md) for guidelines on how to get started.
