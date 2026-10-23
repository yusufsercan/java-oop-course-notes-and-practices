# 🚀 Java & OOP Course Notes & Practices  

This repository contains Java code examples, OOP implementations, and laboratory exercises developed during my university coursework and personal study sessions.

> ⚠️ **Note:** The code in this repository documents my ongoin g learning journey in Object-Oriented Programming. The primary goal is to master OOP principles, understand JVM internals, and establish solid software architecture fundamentals.
 
---

## 📁 Repository Structure

* `Hafta-01` : **Foundations & JVM Architecture**
  * Compilation & execution workflow (`javac`, `java`), `src/` and `bin/` structure.
  * Structural transition from C to Java: reference mechanics, pointers vs. references, `null` safety.
  * Paradigms: Procedural approach vs. Class & Object abstractions.

* `Hafta-02` : **Encapsulation & Object Lifecycle**
  * Data hiding and encapsulation (`private` fields, read-only getters, guarded setters).
  * Constructor design: Full, short, and copy constructors with constructor chaining (`this(...)`).
  * JVM memory model: Zero-initialization phase, Heap allocation vs. Stack references.
  * Object collections: Reference arrays (`Ogrenci[]`), enhanced `for-each` traversal, and formatted representation (`toString()`).
 
 * `Hafta-03` : **The Four Pillars of OOP & Polymorphic Design**
  * Architectural Anti-Patterns: Transitioning from fragile type-code and repetitive `switch` statements (`SekilKotu`) to extensible class hierarchies; eliminating dead fields and silent runtime calculation failures.
  * True Encapsulation & Domain Invariants: Enforcing business rules alongside data via domain methods (`yatir()`, `cek()`) while restricting arbitrary state modification (`BankaHesabi`).
  * Abstraction & Contract-First Design: Decoupling the "what" from the "how" using `abstract class` (`Sekil`) and `interface` (`Odeme`); programming against abstract contracts rather than concrete implementations (`Kasa`).
  * Inheritance & Dynamic Binding: Establishing `IS-A` hierarchies with `extends` and `super(...)` constructor chaining; runtime polymorphism, static (declared) vs. dynamic (actual) reference types (`Sekil s = new Daire(...)`), and safe downcasting with `instanceof`.
    
---

## 💻 Development Environment

* **IDE:** Visual Studio Code
* **JDK Version:** OpenJDK 21
* **Build / Runtime:** javac / JVM
* **Version Control:** Git & GitHub
