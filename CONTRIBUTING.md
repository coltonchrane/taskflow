# Contributing to TaskFlow 🤝

Thank you for your interest in contributing to TaskFlow! We appreciate your support in building a better collaborative task management platform.

## 🌈 Our Community

We aim to build an inclusive and welcoming community. Please be respectful and helpful to others when contributing.

## 🛠️ Development Setup

TaskFlow is a multi-module project. Follow these steps to get your local environment ready:

### 1. Fork and Clone
```bash
git clone https://github.com/[your-username]/taskflow.git
cd taskflow
```

### 2. Choose Your Module
Depending on what you want to work on, you'll need different setups:

- **Backend (Kotlin/Micronaut)**: 
  - Install JDK 17+.
  - `cd backend && ./gradlew build` to verify the setup.
- **Frontend (React/Vite)**: 
  - Install Node.js 18+.
  - `cd frontend && npm install` to install dependencies.
- **Android**: 
  - Install Android Studio.
  - Open the `/android` folder as a new project.

### 3. Running Locally
Refer to the [README.md](./README.md) for detailed instructions on running each component locally or via Docker.

## 📝 Contribution Workflow

1. **Find an Issue**: Search our issues or open a new one to discuss your proposed change.
2. **Create a Branch**: Use a descriptive branch name:
   - `feature/your-feature-name`
   - `fix/your-bug-fix-name`
   - `docs/your-doc-update`
3. **Commit with Care**: Write clear, concise commit messages. Use the [Conventional Commits](https://www.conventionalcommits.org/) format if possible.
4. **Test Your Changes**: Ensure all tests pass before submitting your PR.
5. **Submit a Pull Request**: Provide a detailed description of your changes and link any relevant issues.

## 🧪 Testing & Quality

- **Backend**: Run tests with `./gradlew test`.
- **Frontend**: Run tests with `npm test` and linting with `npm run lint`.
- **Code Style**: 
  - Use [ktlint](https://github.com/pinterest/ktlint) for Kotlin.
  - Use [ESLint](https://eslint.org/) and [Prettier](https://prettier.io/) for TypeScript/React.

## 📬 Communication
If you have any questions or need help, feel free to:
- Open an issue for technical discussions.
- Use GitHub Discussions for general questions or feedback.

---

Thank you for contributing to TaskFlow! 🚀
