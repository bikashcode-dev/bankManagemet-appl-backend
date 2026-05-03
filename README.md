<div align="center">

# 🏦 Console Banking System

**Core Java · OOP · Collections · Custom Exceptions**

![Java](https://img.shields.io/badge/Java-Core%20Java-ED8B00?style=flat-square&logo=openjdk&logoColor=white)
![Architecture](https://img.shields.io/badge/Architecture-Layered-007396?style=flat-square)
![Status](https://img.shields.io/badge/Status-Complete-brightgreen?style=flat-square)

> A console-based banking application built to demonstrate real-world use of Core Java through clean, layered architecture — no frameworks, no shortcuts.

</div>

---

## 📝 Note

> This project was originally developed during my Core Java learning phase.
> Due to loss of access to my previous GitHub account, it has been migrated and re-uploaded here.

---

## 💡 Why I Built This

Before jumping to frameworks like Spring Boot, I wanted to deeply understand what happens underneath — how data flows through layers, how exceptions communicate errors, and how OOP principles translate into actual working code.

This project is that foundation. Every design decision here mirrors patterns used in real enterprise Java applications.

---

## 📁 Project Structure

```
ConsoleBankingSystem/
│
├── app/              →  Entry point (Main class, menu-driven console loop)
├── domain/           →  Core entities: Account, Customer, Transaction
├── repository/       →  In-memory data handling using Collections
├── services/         →  Business logic behind interfaces
├── exceptions/       →  Custom exception classes per failure case
└── util/             →  Input validation and helper utilities
```

---

## 🧠 Key Concepts Applied

| Concept | How It's Used |
|---|---|
| **Encapsulation** | Domain models use private fields with getters/setters |
| **Abstraction** | Service layer built on interfaces — logic hidden behind contracts |
| **Polymorphism** | Implementations swappable without changing business logic |
| **Custom Exceptions** | Each failure case has its own exception class |
| **ArrayList / HashMap** | Repository layer for all in-memory CRUD operations |
| **Interface-first Design** | Every service defines a contract before implementation |

---

## ✅ Features

- Create customer and bank account
- Deposit and withdraw funds
- Transfer amount between accounts
- Search and view account details
- Transaction history per account
- Input validation with custom exceptions

---
