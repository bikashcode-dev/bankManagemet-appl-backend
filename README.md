<div align="center">

# 🏦 Console Banking System

![Java](https://img.shields.io/badge/Java-Core%20Java-ED8B00?style=flat-square&logo=openjdk&logoColor=white)
![OOP](https://img.shields.io/badge/OOP-Principles-blue?style=flat-square)
![Status](https://img.shields.io/badge/Status-Complete-brightgreen?style=flat-square)

**Built from scratch — no frameworks, no shortcuts. Just Core Java, clean architecture, and real OOP.**

</div>

---

> **Note —** Originally developed during my Core Java learning phase. Migrated here due to loss of access to my previous GitHub account.

---

## 💡 Why I Built This

Before jumping to Spring Boot, I wanted to understand what happens underneath — how layers communicate, how exceptions carry meaning, and how OOP applies to real problems. This project is that foundation.

---

## 🧠 OOP Concepts Applied

| Concept | How It's Used |
|---|---|
| **Encapsulation** | Private fields with getters/setters in all domain models |
| **Abstraction** | Service layer built on interfaces |
| **Polymorphism** | Implementations swappable without changing business logic |
| **Collections** | `ArrayList`, `HashMap` for in-memory CRUD |
| **Custom Exceptions** | Domain-specific error class per failure case |
| **Validation** | Centralized in the `util` package |

---

## 📁 Project Structure

```
ConsoleBankingSystem/
├── app/          →  Entry point (Main class)
├── domain/       →  Account, Customer, Transaction
├── repository/   →  In-memory data handling
├── services/     →  Interface-driven business logic
├── exceptions/   →  Custom exception types
└── util/         →  Validation helpers
```

---

## ✅ Features

- Create customer and bank account
- Deposit and withdraw funds
- Transfer between accounts
- View account details and transaction history
- Input validation with custom exceptions

---

## 🛠️ Tech Stack

`Java (Core)` · `Java Collections Framework` · `Git & GitHub`

---

## 🚀 How to Run

```bash
git clone https://github.com/yourusername/ConsoleBankingSystem.git
cd ConsoleBankingSystem
javac -d out src/**/*.java
java -cp out app.Main
```

---

## 🔭 What's Next

- [ ] JDBC + MySQL — persistent storage
- [ ] JUnit — unit tests for all services  
- [ ] Spring Boot — convert to REST API

---

<div align="center">

*Built to learn the foundation — before standing on it.*

</div>
