# Opencart Hybrid TestNG UI Automation Framework

This is a hybrid UI automation framework for OpenCart applications built using **Selenium WebDriver**, **TestNG**, **Java**, and **Maven**. It supports **multi-environment**, **Cross-browser testing**, **Docker integration**, and **Jenkins CI/CD** pipelines.

---

## ✨ Features

- ✅ Hybrid Automation Framework (Page Object + Data-Driven + Modular)
- ✅ Multi-Environment Support (Dev, QA, Stage, etc.)
- ✅ TestNG-based execution with parallel testing capabilities
- ✅ Maven build tool for managing dependencies
- ✅ Docker-ready for containerized execution
- ✅ Jenkinsfile for CI/CD integration

---

## ⚙️ Multi-Environment Configuration

Environment-specific properties are maintained under `src/test/resources/config/`.  
You can switch between environments (e.g., QA, Dev, Stage) using a system property:

### Example Command:

```bash
mvn test -Denv=stage
```

This will load `stage.properties`.

### Default Fallback:

If no environment is provided, the framework loads `qa.properties` by default.

---

## 🚀 Getting Started

### 🔧 Prerequisites

- Java 11 or above

- Maven 3.x

- Chrome, Edge or Firefox browser

- Docker (Optional)


### 📥 Clone the Repository
```bash
git clone https://github.com/jeevan-sidhu/Opencart_Hybrid_TestNG_UI_Automation_Framework.git
cd Opencart_Hybrid_TestNG_UI_Automation_Framework
```

### 📦 Install Dependencies
```bash
mvn clean install
```

### 🧪 Running Tests
#### Locally:
```bash
mvn test -Denv=qa
```
#### Using Docker:

Set `remote=true` in config properties.
```bash
docker-compose up
mvn test -Denv=qa
```

### 🔁 CI/CD with Jenkins
This framework includes a `Jenkinsfile` for pipeline setup. You can:

1. Set up a Jenkins pipeline project.
2. Connect it to your GitHub repo.
3. Jenkinsfile will handle build + test stages.



