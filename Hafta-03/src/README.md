# 📌 Week 03 — The Four Pillars of OOP, Abstraction & Polymorphism

This module covers the core architectural foundations of Object-Oriented Programming (OOP) in Java. It explores the four fundamental pillars: **Encapsulation**, **Abstraction**, **Inheritance**, and **Polymorphism**, demonstrating how to refactor procedural anti-patterns into extensible, polymorphic domain models.

---

## 🎯 Key Learning Objectives

* **Encapsulation (Level 3):** Safeguarding state by eliminating direct public access, discarding unneeded mutators (`setters`), and enforcing domain invariants inside operational methods.
* **Inheritance (IS-A Relationship):** Factoring out shared state and behavior into an abstract base class (`Sekil`) to achieve code reuse without duplication.
* **Polymorphism & Dynamic Dispatch:** Decoupling call sites from concrete implementations by invoking uniform contracts (`@Override`) across polymorphic collections.
* **Abstraction & Interface Contracts (CAN-DO Relationship):** Designing lightweight specifications (`interface`) to achieve loose coupling between calling code and business implementations.
* **Anti-Pattern Refactoring:** Replacing rigid conditional logic (`switch-case` checking types) with polymorphic dispatch.

---

## 📂 Source Code Index

### Core & Domain Models
| File | Description & Architectural Focus |
| :--- | :--- |
| **`Sekil.java`** | Abstract base class defining common shape attributes (`ad`, `renk`) and declaring abstract geometric contracts (`alan()`, `cevre()`). |
| **`Daire.java`** | Concrete shape subclass implementing radius bounds and circular area/perimeter calculations. |
| **`Kare.java`** | Concrete shape subclass modeling quadrilateral geometry and validating side lengths. |
| **`Ucgen.java`** | Concrete shape subclass enforcing triangle inequality rules and custom geometric formulas. |
| **`SekilKotu.java`** | Anti-pattern implementation illustrating rigid, procedural code relying on type flags instead of polymorphism. |
| **`SekilDemo.java`** | Polymorphic driver demonstrating uniform processing of heterogeneous shape objects via upcasting (`Sekil[]`). |
| **`Odeme.java` & `OdemeDemo.java`** | Interface contract modeling financial transaction workflows across different payment provider implementations. |
| **`BankaHesabi.java` & `KapsullemeDemo.java`** | Encapsulated balance management validating transactional boundaries and preventing arbitrary state corruption. |

### 🧪 Laboratory Tasks
| File | Problem Statement & Task Focus |
| :--- | :--- |
| **`Gorev1_IlkeTespiti.java`** | Diagnostic task identifying the 4 core OOP pillars across distinct code regions using key structural markers (`private`, `interface`, `extends`, `@Override`). |
| **`Sensor.java`** | Refactored environmental sensor class resolving encapsulation flaws, enforcing invariant bounds (`[-50.0, 150.0]`), and preventing identity mutation (`final`). |
| **`Gorev2_SensorDemo.java`** | Test driver evaluating valid and out-of-bounds sensor readings, operational return flags, and formatted reporting. |

---

## ⚙️ Compilation & Execution

All classes can be compiled using `javac` into a dedicated binary output directory (`bin/`) and executed via terminal:

```bash
# From within the Hafta-03 directory:
# 1. Compile all source files
javac -d ./bin ./src/*.java

# 2. Run the polymorphic Shape simulation
java -cp ./bin SekilDemo

# 3. Run the Sensor encapsulation test suite
java -cp ./bin Gorev2_
