# 🚀 Java & OOP Course Notes & Practices  

This repository contains Java code examples, OOP implementations, and laboratory exercises developed during my university coursework and personal study sessions.

> ⚠️ **Note:** The code in this repository documents my ongoin g learning journey in Object-Oriented Programming. The primary goal is to master OOP principles, understand JVM internals, and establish solid software architecture fundamentals.
 
---

### 📁 Repository Structure

- **`Hafta-01` : Foundations & JVM Architecture**
  - Compilation & execution workflow (`javac`, `java`), `src/` and `bin/` structure.
  - Structural transition from C to Java: reference mechanics, pointers vs. references, `null` safety.
  - Paradigms: Procedural approach vs. Class & Object abstractions.

- **`Hafta-02` : Encapsulation & Object Lifecycle**
  - Data hiding and encapsulation (`private` fields, read-only getters, guarded setters).
  - Constructor design: Full, short, and copy constructors with constructor chaining (`this(...)`).
  - JVM memory model: Zero-initialization phase, Heap allocation vs. Stack references.
  - Object collections: Reference arrays (`Ogrenci[]`), enhanced `for-each` traversal, and formatted representation (`toString()`).

- **`Hafta-03` : The Four Pillars of OOP & Polymorphic Design**
  - **Architectural Anti-Patterns:** Transitioning from fragile type-code and repetitive `switch` statements (`SekilKotu`) to extensible class hierarchies; eliminating dead fields and silent runtime calculation failures.
  - **True Encapsulation & Domain Invariants:** Enforcing business rules alongside data via domain methods (`yatir()`, `cek()`) while restricting arbitrary state modification (`BankaHesabi`).
  - **Abstraction & Contract-First Design:** Decoupling the "what" from the "how" using `abstract class` (`Sekil`) and `interface` (`Odeme`); programming against abstract contracts rather than concrete implementations (`Kasa`).
  - **Inheritance & Dynamic Binding:** Establishing `IS-A` hierarchies with `extends` and `super(...)` constructor chaining; runtime polymorphism, static (declared) vs. dynamic (actual) reference types (`Sekil s = new Daire(...)`), and safe downcasting with `instanceof`.

- **`Hafta-04` : Deep Encapsulation, Immutability & Access Control**
  - **Mindless Mutators vs. Guarded Invariants:** Moving beyond naive getter/setter pairs; eliminating public fields (`KapsullemeYok`), establishing guarded mutators (`setYas()`) and constructor-enforced invariants (`Ogrenci`).
  - **Derived Getters & Single Source of Truth:** Eliminating data duplication by calculating derived values dynamically (`getFahrenheit()`, `getKelvin()`) from a single canonical state (`celsius`) without state drift (`Sicaklik`).
  - **Immutable Value Objects:** Designing thread-safe immutable classes (`ParaBirimi`) via `final class`, `private final` fields, defensive constructor checks, and functional copy-on-write methods (`topla()`, `cikar()`); preventing floating-point inaccuracies using integer cents (`long kurus`).
  - **Domain Operations vs. Naked Setters:** Restricting arbitrary state modification by replacing raw setter access with encapsulated transactional operations (`yatir()`, `cek()`) enforcing daily limits and overdraft rules (`BankaHesabi`).
  - **Access Modifiers & Package Visibility:** Demystifying the four accessibility levels (`private`, default/package-private, `protected`, `public`) across modular package boundaries (`okul` vs. `disarida`) and inheritance contexts.
    
---

## 🛠️ Methodology & Study Workflow

The Java source code, domain models, and theoretical notes across this repository originate directly from my university laboratory coursework, lecture materials, and structured technical study sessions.

To maintain high software engineering standards and establish deep conceptual mastery:

* **Core Logic & Hands-on Implementation:** All domain entities, class hierarchies, lab assignment tasks, boundary validations, and driver test suites are designed, coded, and debugged personally during active study and laboratory sessions.
* **AI-Assisted Architectural Review & Documentation:** AI tooling is utilized as an interactive engineering mentor and editorial companion—used to review code against strict OOP principles (encapsulation levels, invariant enforcement, dynamic dispatch), detect anti-patterns (such as exposed mutable state or inappropriate `static` usage), and format bilingual technical documentation and study guides.

> In essence, AI serves as an architectural reviewer and pair-programming sounding board; the core problem-solving, debugging decisions, architectural evolution, and learning milestones represent my personal academic effort.

---

## 💻 Development Environment

* **IDE:** Visual Studio Code
* **JDK Version:** OpenJDK 21
* **Build / Runtime:** javac / JVM
* **Version Control:** Git & GitHub
