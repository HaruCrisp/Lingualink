# LinguaLink

LinguaLink is a translator software built in **Java** (VS Code friendly).  
This is **Sprint 1** of the project: a console-based app that allows translation, quizzes, and mini-lessons.

---

## ✨ Features (Sprint 1)

- **Word & Phrase Translation**  
  Translate words and short phrases between English (EN) and Spanish (ES).  
  Uses a small built-in dictionary plus basic context rules (e.g., idioms).

- **Context-Aware Translations**  
  Goes beyond word-for-word to provide more natural meaning in some cases.

- **Interactive Quiz Mode**  
  Multiple-choice quiz to practice translations.

- **Learning Section**  
  Flashcard-style lessons grouped by topics (greetings, classroom, politeness).

- **Console Menu**  
  Simple text menu to navigate features.

---

## 📂 Project Structure

LinguaLink/
├─ src/main/java/
│ └─ app/
│ ├─ Main.java # Entry point (console app)
│ ├─ core/ # Interfaces & models
│ ├─ engine/ # Translation logic
│ ├─ features/ # Quiz & learning modules
│ └─ util/ # Console helper
└─ README.md

---

## ▶️ Running the App

### Option A: Run directly with Java (no build tools)

1. Open terminal in project root:
   ```powershell
   cd C:\Users\Haruki\Desktop\Project\LinguaLink
   ```
2. Compile into a bin folder:
   ```powershell
   javac -d bin src/main/java/app/**/*.java src/main/java/app/core/*.java src/main/java/app/engine/*.java src/main/java/app/features/*.java src/main/java/app/util/*.java
   ```
3. Run:
   ```powershell
   java -cp bin app.Main
   ```

### Option B: Run inside VS Code
- Install the Java Extension Pack.
- Open the project folder.
- Right-click Main.java → Run Java.

---

## 🛠 Roadmap

**Sprint 2:**
- Add external dictionary files (JSON/CSV).
- Expand to more languages (FR, JP, etc.).

**Sprint 3:**
- Add API endpoints (REST) for integration with frontend/web.
- Support text input/output files.

**Future Goals:**
- GUI (JavaFX or Web UI).
- Speech input/output.
- Smarter context-aware engine.

---

## 👥 Team (Sprint 1)
- Bryan Perez
- Christian Mendez
- Haruki Horiuchi
- Pedro Camods
