# Hulk Store

## Description

This is an inventory management application built with Spring Boot 3 and Java 17. The application allows users to manage an inventory of products such as comics, t-shirts, and more. The project uses Maven for build automation and dependency management. It includes unit and integration tests, integrates with SonarQube for code quality analysis, and is containerized using Docker.

## Technologies Used

- **Java 17**
- **Spring Boot 3**
- **Maven**
- **PostgresSQL**
- **JUnit** for testing
- **SonarQube** for code quality analysis
- **Docker** for containerization

## Getting Started

### Prerequisites

- Java 17
- Maven 3.6+
- Docker
- SonarQube (optional, for code quality analysis)

### Installation

1. **Clone the repository:**

   ```bash
   git clone https://github.com/mayandor/hulkStoreTest.git
   cd hulkStore

2. **Build the application:**

   ```bash
    mvn clean install

### Exec Sonar

   ```bash
    mvn clean verify sonar:sonar \   -Dsonar.projectKey=hulkstore \   -Dsonar.projectName='hulkstore' \   -Dsonar.host.url=http://localhost:9000 \  -Dsonar.token=sqp_3f7a8d03b30913f4f87225a3144131de0e01acf0
```

### Running with Docker

1. **Build image:**
   ```bash
    docker build -t softumxpartan/hulkstore:latest .

2. **Run container:**
   ```bash
    docker run --net=host -it --name=hulkstore -d softumxpartan/hulkstore:latest
