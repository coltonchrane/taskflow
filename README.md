# TaskFlow

A collaborative task management platform.

## Project Structure

- `backend/`: Kotlin Micronaut API.
- `frontend/`: React + Vite web application.
- `android/`: Kotlin Android application.
- `docker-compose.yml`: Local development setup with PostgreSQL.

## Getting Started

### Backend
To run the backend locally (requires JDK 11+ and PostgreSQL):
```bash
cd backend
./gradlew run
```

### Frontend
To run the frontend locally (requires Node.js):
```bash
cd frontend
npm install
npm run dev
```

### Android
Open the `android/` directory in Android Studio.

### Docker
To spin up the entire stack:
```bash
docker-compose up
```

## API Example
The project includes a sample controller and service:
- `com.taskflow.controller.TaskController`
- `com.taskflow.service.TaskService`

You can access the example API at `http://localhost:8080/tasks`.
