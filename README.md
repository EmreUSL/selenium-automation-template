# Selenium Automation Template

![Java](https://img.shields.io/badge/Java-21-orange)
![Selenium](https://img.shields.io/badge/Selenium-4.x-green)
![Build](https://img.shields.io/badge/Build-Maven-blue)
![Status](https://img.shields.io/badge/Status-In%20Progress-yellow)

A scalable and modular Selenium automation framework template built with Java.  
The project is designed to evolve step by step, starting from a robust and thread-safe driver management layer.

---

## 🚗 Driver Management

This framework provides a **centralized and thread-safe WebDriver management solution**.  
The main goal is to make driver handling **reusable, maintainable, and CI/CD friendly** while keeping test classes clean and focused on test logic only.

---

## 🧵 Thread-Safe Execution

WebDriver instances are managed using **ThreadLocal**, enabling safe parallel test execution.

Each test thread:
- Gets its **own isolated WebDriver instance**
- Avoids shared state and race conditions
- Can run independently without interfering with other tests

This design makes the framework **ready for parallel execution and future Selenium Grid integration**.

---

## 🌐 Browser Selection with Enums

Browser selection is handled using a **type-safe enum** instead of raw strings.

Benefits:
- Prevents invalid browser values at runtime
- Enables fail-fast behavior
- Makes browser support easier to extend
- Improves overall code readability and safety

---

## ⚙️ WebDriver Binary Management

WebDriver binaries are managed automatically using **Bonigarcia WebDriverManager**.

This approach:
- Eliminates manual driver setup
- Automatically resolves compatible driver versions
- Works seamlessly across different operating systems
- Is fully compatible with CI/CD pipelines

---

## 🧠 Architecture Overview

