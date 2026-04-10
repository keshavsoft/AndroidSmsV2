# 📱 SMS Architecture Demo App

---

## 🧠 Overview

This project is a modern Android SMS application focused on **clean architecture and controlled evolution**.

It is built with a **requirement-driven approach**, not a copy-paste or template-based structure.

Every screen and layer is designed specifically for the problem it solves.

---

## ⚠️ Important Principle

> ❌ We are NOT blindly following Jetpack Compose patterns
> ❌ We are NOT doing copy-paste architecture
> ✅ We are building **exact-fit architecture for our use case**

---

## 🧩 What This Project Demonstrates

* Clear separation between **Incoming, Outgoing, and All Messages**
* Controlled evolution using **V8 → V9 approach**
* Repositories designed per **actual need**, not theory
* UI built based on **data behavior**, not generic templates
* Minimal abstraction — only where needed

---

## 🔄 Version Strategy

### V8 (Focused Implementation)

* Handles **Incoming SMS only**
* Simple, feature-specific
* Fast to build, easy to understand

---

### V9 (Refined Implementation)

* Handles **All SMS (Incoming + Outgoing)**
* Introduces **conversation-level thinking**
* Removes duplication from V8
* Moves toward reusable but **still controlled design**

---

## 🧱 Project Structure Philosophy

```id="s0d91k"
ui/
 ├── incoming/        # Feature-specific logic stays here
 │    ├── v8/         # Simple version
 │    ├── v9/         # Improved version
 │    ├── logic/
 │    ├── model/
 │    └── conversation/
 │
 └── common/          # Only truly reusable things go here
      └── conversation/
```

👉 Rule:

* If deletion of a feature should not break others → keep it local
* If reused across features → move to `common`

---

## 🧵 Conversation Design Insight

* Thread screen is **not inherently "incoming"**
* It depends on **data source**, not UI type

So:

* UI → should be reusable
* Data → should control behavior

---

## 📦 Repository Design

### SmsReaderRepository

* Provides:

  * Inbox messages
  * Sent messages
  * All messages (V9 key)

👉 Repository exposes **data variations**, not UI logic

---

### SmsSenderRepository

* Handles sending SMS only
* Completely isolated responsibility

---

## 🎯 Design Principles

* Build **only what is needed**
* Avoid over-generalization early
* Keep features **deletable and independent**
* Prefer **clarity over abstraction**
* Evolve architecture **step-by-step (V8 → V9 → next)**

---

## 🚀 Direction

* Improve chat UI alignment (incoming vs outgoing)
* Gradually extract only **proven reusable components**
* Keep control — avoid premature "common" dumping

---

## 💡 Summary

This project is about:

* Thinking before structuring
* Structuring based on reality, not trends
* Evolving architecture instead of over-designing it

---

✨ Built with intention, not imitation.
