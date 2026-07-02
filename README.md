# WalletManagement_Backend
# Wallet Management System

A full-stack **Wallet Management System** developed using **Spring Boot**, **React (Vite)**, **PostgreSQL**, and **JWT Authentication**.

The application allows customers to register, log in securely, create multiple wallets, perform credit/debit operations, transfer money between wallets, and view transaction history.

---

# Technologies Used

## Backend

* Java 17
* Spring Boot
* Spring Security
* JWT Authentication
* Spring Data JPA
* PostgreSQL
* Maven

## Frontend

* React
* Vite
* Tailwind CSS
* Axios
* React Router DOM

---

# Features

* User Registration
* User Login
* JWT Authentication
* Create Multiple Wallets
* Credit Wallet
* Debit Wallet
* Transfer Money
* Transaction History
* Audit Logs
* Dashboard
* Protected Routes

---

# Prerequisites

Install the following software:

* Java 17+
* Maven
* PostgreSQL
* Node.js (18+)
* npm

---

# Database Setup

## Step 1: Create Database

Open PostgreSQL and execute:

```sql
CREATE DATABASE wallet_management;
```

Connect to database:

```sql
\c wallet_management;
```

---

# Create Tables

## Customer Table

```sql
CREATE TABLE customer (
    customer_id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);
```

---

## Wallet Table

```sql
CREATE TABLE wallet (
    wallet_id BIGSERIAL PRIMARY KEY,
    wallet_name VARCHAR(100) NOT NULL,
    balance NUMERIC(19,2) DEFAULT 0,
    status VARCHAR(20),
    customer_id BIGINT NOT NULL,
    CONSTRAINT fk_wallet_customer
    FOREIGN KEY (customer_id)
    REFERENCES customer(customer_id)
);
```

---

## Transaction Table

> **Note:** `transaction` is a reserved keyword in PostgreSQL. Use double quotes.

```sql
CREATE TABLE "transaction" (
    txn_id BIGSERIAL PRIMARY KEY,
    txn_type VARCHAR(30),
    amount NUMERIC(19,2),
    created_date TIMESTAMP,
    wallet_id BIGINT NOT NULL,
    CONSTRAINT fk_transaction_wallet
    FOREIGN KEY (wallet_id)
    REFERENCES wallet(wallet_id)
);
```

---

## Audit Log Table

```sql
CREATE TABLE audit_log (
    audit_id BIGSERIAL PRIMARY KEY,
    customer_id BIGINT,
    action VARCHAR(100),
    created_date TIMESTAMP,
    CONSTRAINT fk_audit_customer
    FOREIGN KEY (customer_id)
    REFERENCES customer(customer_id)
);
```

---

# Recommended Database Configuration

Instead of manually creating tables, configure Hibernate to generate them automatically.

Open:

```
src/main/resources/application.properties
```

Configure:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/wallet_management
spring.datasource.username=postgres
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

Hibernate will automatically create and update tables based on your entity classes.

---

# Backend Setup

Navigate to backend project:

```bash
cd walletmanagement
```

Build project:

```bash
mvn clean install
```

Run application:

```bash
mvn spring-boot:run
```

Or run the main class directly from Eclipse:

```
WalletManagementApplication.java
```

Backend URL:

```
http://localhost:8080
```

---

# Frontend Setup

Navigate to React project:

```bash
cd wallet-ui
```

Install dependencies:

```bash
npm install
```

Run project:

```bash
npm run dev
```

Frontend URL:

```
http://localhost:5173
```

---

# Authentication Flow

```
Register
      │
      ▼
Login
      │
      ▼
JWT Token Generated
      │
      ▼
Stored in Local Storage
      │
      ▼
Token Sent in Authorization Header
```

Example Header:

```
Authorization: Bearer <JWT_TOKEN>
```

---

# Application Flow

```
User Registration
        │
        ▼
User Login
        │
        ▼
Dashboard
        │
        ├── Create Wallet
        ├── Credit Wallet
        ├── Debit Wallet
        ├── Transfer Money
        └── View Transaction History
```

---

# API Endpoints

## Authentication

| Method | Endpoint          |
| ------ | ----------------- |
| POST   | /auth/register    |
| POST   | /auth/login       |
| GET    | /auth/getCustomer |

---

## Wallet APIs

| Method | Endpoint                  |
| ------ | ------------------------- |
| POST   | /wallet/create            |
| GET    | /wallet/all               |
| PUT    | /wallet/credit/{walletId} |
| PUT    | /wallet/debit/{walletId}  |
| POST   | /wallet/transfer          |

---

## Transaction APIs

| Method | Endpoint                    |
| ------ | --------------------------- |
| GET    | /transactions?page=0&size=8 |

---

# Project Structure

```
wallet-management
│
├── walletmanagement (Spring Boot)
│   ├── config
│   ├── controller
│   ├── dto
│   ├── model
│   ├── repository
│   ├── services
│   ├── util
│   └── WalletManagementApplication.java
│
└── wallet-ui (React)
    ├── api
    ├── assets
    ├── components
    ├── layouts
    ├── pages
    ├── services
    ├── App.jsx
    └── main.jsx
```

---

# Future Enhancements

* Payment Gateway Integration
* UPI Payments
* Bank Account Integration
* Email Notifications
* SMS Notifications
* Wallet Freeze / Unfreeze
* Admin Dashboard
* Reports & Analytics
* Docker Support
* AWS Deployment
* Kubernetes Deployment

---

# Author

**Saurabh Kadam**

This project was developed as a full-stack learning project using Spring Boot, React, PostgreSQL, and JWT Authentication.

