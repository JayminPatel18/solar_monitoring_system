# Smart Solar Monitoring System ☀️

A full-stack Smart Solar Monitoring System built using Spring Boot and PostgreSQL for backend services, designed to monitor solar panel performance, store real-time sensor readings, calculate energy production, and detect smart panel statuses.

---

# 🚀 Project Overview

This project simulates a real-world solar energy monitoring platform where users can:

* Manage solar panels
* Store real-time sensor readings
* Monitor panel performance
* Calculate generated energy
* Detect panel conditions automatically
* View panel-wise analytics

The project is being developed with industry-level backend architecture using:

* Layered Architecture
* DTO Pattern
* REST APIs
* PostgreSQL Database
* Service Layer Design

---

# 🛠️ Tech Stack

## Backend

* Java 17
* Spring Boot
* Spring Data JPA
* Hibernate
* PostgreSQL
* Maven
* Lombok

## Frontend (Planned)

* React.js
* Chart.js / Recharts
* Axios
* Bootstrap / Tailwind CSS

---

# 📂 Project Structure

```text
src/main/java/com/solar
│
├── controller
├── dto
├── entity
├── repository
├── services
└── SolarMonitoringApplication
```

---

# 🗄️ Database Design

## Entities

### 1. User

Stores user information.

### 2. SolarPanel

Stores panel details such as:

* Panel Name
* Location
* Capacity
* Status

### 3. SensorData

Stores live sensor readings:

* Voltage
* Current
* Power
* Temperature
* Timestamp

---

# 🔗 Entity Relationships

```text
User
  ↓
SolarPanel
  ↓
SensorData
```

### Relationship Mapping

* One User → Many Solar Panels
* One Solar Panel → Many Sensor Readings

---

# 📡 REST APIs

## Solar Panel APIs

| Method | Endpoint    | Description            |
| ------ | ----------- | ---------------------- |
| POST   | /api/panels | Create new solar panel |
| GET    | /api/panels | Get all solar panels   |

---

## Sensor Data APIs

| Method | Endpoint              | Description                     |
| ------ | --------------------- | ------------------------------- |
| POST   | /api/data             | Save sensor data                |
| GET    | /api/data             | Get all sensor data             |
| GET    | /api/data/panel/{id}  | Get panel-wise sensor data      |
| GET    | /api/data/latest/{id} | Get latest sensor reading       |
| GET    | /api/data/total/{id}  | Calculate total generated power |
| GET    | /api/data/status/{id} | Get smart panel status          |

---

# ⚡ Smart Panel Status Logic

The system automatically detects panel condition using sensor values.

| Condition          | Status     |
| ------------------ | ---------- |
| No sensor data     | NO DATA    |
| Power = 0          | FAULT      |
| Temperature > 50°C | OVERHEATED |
| Otherwise          | ACTIVE     |

---

# 🧠 Backend Features Implemented

✅ Layered Architecture

✅ DTO Pattern

✅ Real-time Sensor Data Storage

✅ Custom JPQL Query

✅ Panel-wise Data Filtering

✅ Latest Reading API

✅ Total Power Calculation

✅ Smart Status Detection

✅ PostgreSQL Integration

---

# 📦 Example API Response

## GET /api/data/latest/1

```json
{
  "id": 1,
  "voltage": 220.0,
  "current": 5.0,
  "power": 1100.0,
  "temperature": 36.0,
  "timestamp": "2026-05-15T10:00:00",
  "panelId": 1,
  "panelName": "Main Rooftop Panel"
}
```

---

# ⚙️ How to Run the Project

## 1. Clone Repository

```bash
git clone <repository-url>
```

---

## 2. Open Project

Open project in:

* IntelliJ IDEA
* VS Code
* Eclipse

---

## 3. Configure PostgreSQL

Create database:

```sql
CREATE DATABASE solar_monitoring;
```

---

## 4. Configure application.properties

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/solar_monitoring
spring.datasource.username=postgres
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## 5. Run Spring Boot Application

Run:

```text
SolarMonitoringApplication.java
```

---

# 🧪 API Testing

Use:

* Postman
* Thunder Client
* Swagger (Planned)

Base URL:

```text
http://localhost:8080
```

---

# 📈 Future Enhancements

* JWT Authentication
* Role-Based Access Control
* React Dashboard
* Live Charts & Graphs
* Real-Time WebSocket Updates
* Email Alerts for Fault Detection
* Docker Deployment
* AWS Deployment
* Swagger API Documentation
* Unit Testing & Integration Testing

---

# 🎯 Learning Outcomes

This project demonstrates:

* REST API Development
* Backend Architecture Design
* Database Relationships
* DTO Pattern
* Business Logic Implementation
* Clean Code Practices
* Industry-Level Spring Boot Concepts

---

# 👨‍💻 Author

Developed by Jaymin Patel

---

# 📜 License

This project is developed for learning and educational purposes.
