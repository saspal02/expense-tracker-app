# expense-tracker-app

# 💸 Expense Tracker App

**Expense Tracker App** is a microservices-based Android backend that helps users track their expenses automatically **without manual entry**. It leverages **LLMs and NLP techniques** to extract key information like **amount**, **currency**, and **merchant** from SMS messages, and stores them securely. The system is designed with a mobile-first mindset, aiming to simplify expense tracking for users who receive transactional updates via SMS.

### 💡 Key Highlights:
- No manual expense entry required — just read and process SMS
- Authenticated user access via JWT-based AuthService
- Asynchronous communication using Kafka
- Data stored in MySQL across multiple services
- Kong API Gateway with custom plugins for secure API management
- Dockerized microservices for easy deployment and local development

This project demonstrates real-world integration of **microservices**, **message queues**, and **container orchestration**, all geared toward seamless personal finance management on mobile.

---

## 🧩 Architecture Overview

**Services:**

| Service               | Description                                                |
|-----------------------|------------------------------------------------------------|
| `AuthService`         | Handles user login, registration, and JWT authentication   |
| `Userservice`         | Stores and manages user profile data                       |
| `Expense-service`     | Receives processed expenses and stores them                |
| `Datascience-service` | Extracts merchant, amount, and currency from SMS messages  |

**Communication:**
- Services communicate via **Kafka**
- API traffic is managed by **Kong Gateway**
- Data is persisted in **MySQL**

---

## 🗂 Project Structure


