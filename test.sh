#!/bin/bash

# Exit immediately if a command exits with a non-zero status
set -e

# Colors for better output
GREEN='\033[0;32m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

echo -e "${BLUE}🚀 Starting TaskFlow Test Suite...${NC}"

# 1. Backend Tests
echo -e "\n${BLUE}🧪 Running Backend Tests (Kotlin/Micronaut)...${NC}"
./gradlew :backend:test
echo -e "${GREEN}✅ Backend tests passed!${NC}"

# 2. Frontend Tests
echo -e "\n${BLUE}🧪 Running Frontend Tests (React/Vitest)...${NC}"
cd frontend
npm test -- --run
cd ..
echo -e "${GREEN}✅ Frontend tests passed!${NC}"

# 3. Android Tests
echo -e "\n${BLUE}🧪 Running Android Unit Tests (Kotlin/JUnit)...${NC}"
./gradlew :android:testDebugUnitTest
echo -e "${GREEN}✅ Android tests passed!${NC}"

echo -e "\n${GREEN}🎉 All test suites completed successfully!${NC}"
