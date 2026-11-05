# 📌 Week 04 — Encapsulation, Immutability & Access Control

This module covers the core architectural mechanics of state protection, data integrity, and visibility boundaries in Java. It explores the transition from vulnerable public fields to Level 3 encapsulation, defensive mutation, derived single-source-of-truth getters, thread-safe immutable value objects, and package-level access control.

---

### 🎯 Key Learning Objectives

* **Encapsulation (Level 3 & Invariant Protection):** Eliminating direct field exposure, preventing silent delayed bugs (`KapsullemeYok`), and guarding state mutations via centralized validation (`Ogrenci`).
* **Derived Getters & Single Source of Truth:** Preventing synchronization drift by calculating dependent state dynamically on demand (`Sicaklik`) rather than maintaining redundant mutable fields.
* **Immutable Value Object Pattern:** Enforcing thread safety and side-effect-free state sharing via `final class`, `private final` fields, and return-new-instance transformations (`ParaBirimi`).
* **Precision in Financial Modeling:** Preventing IEEE 754 floating-point rounding errors by utilizing integer minor units (`long kurus`).
* **Domain Operations vs. Anemic Setters:** Replacing raw field reassignment (`setBakiye`) with rule-enforcing transactional business methods (`yatir`, `cek`) managing balances and daily ceilings (`BankaHesabi`).
* **Access Control Matrix & Package Boundaries:** Mastering the four accessibility tiers (`private`, package-private, `protected`, `public`) across modular package boundaries (`okul` vs. `disarida`).

---

### 📁 Source Code Index

#### Core & Domain Models

| File | Description & Architectural Focus |
| :--- | :--- |
| **`KapsullemeYok.java`** | Anti-pattern demonstration illustrating unprotected `public` fields, compiler blind spots, and delayed runtime defects. |
| **`Ogrenci.java` & `OgrenciDemo.java`** | Fully encapsulated entity featuring guarded mutators (`setAd`, `setYas`), read-only immutable identity (`ogrenciNo`), and derived grade averaging. |
| **`Sicaklik.java` & `SicaklikDemo.java`** | Thermodynamic domain model enforcing absolute zero bounds ($\ge -273.15^\circ\text{C}$) and dynamically deriving Fahrenheit/Kelvin from a single canonical state. |
| **`ParaBirimi.java` & `ParaBirimiDemo.java`** | Immutable value object eliminating floating-point errors via integer cents (`long kurus`) and providing thread-safe arithmetic methods (`topla`, `cikar`). |
| **`BankaHesabi.java` & `BankaHesabiDemo.java`** | Business-driven encapsulation replacing naked setters with guarded transactional operations (`yatir`, `cek`) enforcing overdraft rules and daily limits. |
| **`okul/Kayit.java` & `okul/PaketIciDemo.java`** | Package-level access modifier reference demonstrating package-private, protected, and public member visibility inside the source package. |
| **`disarida/PaketDisiDemo.java`** | Cross-package and inheritance test driver demonstrating cross-package restriction and subclass access to `protected` members via `extends`. |

#### 🧪 Laboratory Tasks

| File | Problem Statement & Task Focus |
| :--- | :--- |
| **`Urun.java` & `Gorev1_UrunDemo.java`** | Product inventory entity enforcing positive price bounds, guarded stock increment/decrement rules, and discounted pricing workflows. |
| **`KanBasinci.java` & `Gorev2_KanBasinciDemo.java`** | Clinical diagnostic model enforcing the invariant rule ($\text{systolic} > \text{diastolic}$) and mapping dynamic blood pressure categories. |
| **`Uye.java` & `Gorev3_UyeDemo.java`** | Automated membership registration model featuring sequential static ID generation, email validation, and loyalty point transactions. |

---

### ⚙️ Compilation & Execution

All classes can be compiled using `javac` into a dedicated binary output directory (`bin/`) and executed via terminal:

```bash
# From within the Hafta-04 directory:
# 1. Compile all source files into bin directory
javac -d ./bin ./src/*.java ./src/okul/*.java ./src/disarida/*.java

# 2. Run core lecture drivers
java -cp ./bin KapsullemeYok
java -cp ./bin OgrenciDemo
java -cp ./bin SicaklikDemo
java -cp ./bin ParaBirimiDemo
java -cp ./bin BankaHesabiDemo

# 3. Run package boundary tests
java -cp ./bin okul.PaketIciDemo
java -cp ./bin disarida.PaketDisiDemo

# 4. Run laboratory task test suites
java -cp ./bin Gorev1_UrunDemo
java -cp ./bin Gorev2_KanBasinciDemo
java -cp ./bin Gorev3_UyeDemo