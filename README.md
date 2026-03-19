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
- **Android Studio** (for Android development)

## 🚀 Getting Started

### Quick Start with Docker
The easiest way to get the entire stack running is using Docker Compose:

```bash
docker-compose up --build
```
- **Frontend**: [http://localhost:3000](http://localhost:3000)
- **Backend API**: [http://localhost:8080](http://localhost:8080)
- **Database**: PostgreSQL on `localhost:5432`

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
```bash
cd backend
./gradlew run
```
The backend will be available at `http://localhost:8080`.

#### 2. Frontend
```bash
cd frontend
npm install
npm run dev
```
The frontend will be available at `http://localhost:5173` (standard Vite port, though Docker maps it to 3000).

#### 3. Android
1. Open the `/android` directory in **Android Studio**.
2. Sync Project with Gradle Files.
3. Run on an Emulator or Physical Device.

## 🧪 Testing

### Backend
```bash
cd backend
./gradlew test
```

### Frontend
```bash
cd frontend
npm test
```

## 🤝 Contributing
We welcome contributions! Please see our [CONTRIBUTING.md](./CONTRIBUTING.md) for guidelines on how to get started.
