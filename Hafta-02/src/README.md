# 📌 Week 02 — Memory Model, References & Object Lifecycle

This module focuses on the inner workings of Java's runtime environment, specifically dissecting the distinction between **Stack** and **Heap** memory, reference mechanics, constructor chaining via `this()`, variable shadowing, default constructors, and equality semantics (`==` vs. `.equals()`).

---

## 🎯 Key Learning Objectives

* **Java Memory Architecture:** Understanding how primitive values and object references reside on the **Stack**, while concrete object instances occupy the **Heap**.
* **Reference Mechanics & Equality:** Differentiating between identity comparison (`==` comparing memory addresses) and structural value comparison (`.equals()` inspecting object state).
* **Variable Shadowing & `this`:** Resolving ambiguities when method parameters share the same identifier as instance fields.
* **Constructor Chaining (`this(...)`):** Reusing constructor logic within the same class to reduce redundancy and enforce single-point validation.
* **Default vs. No-Arg Constructors:** Examining compiler behavior when explicit constructors are omitted versus when custom constructors are introduced.
* **Object Lifecycle & Garbage Collection:** Tracking an object from instantiation to reference disconnection and eventual GC reclamation.

---

## 📂 Source Code Index

### Core & Concept Implementations
| File | Description & Engineering Focus |
| :--- | :--- |
| **`referanslar.java`** | Demonstration of object references in Stack/Heap, comparing memory address identity (`==`) against deep value equality (`.equals()`). |
| **`YasamDongusu.java`** | Tracking object instantiation, reference reassignment, scope exit, and eligibility for Garbage Collection (GC). |
| **`GolgelemeOrnegi.java`** | Demonstrating variable shadowing where local method arguments overshadow instance variables, solved via `this`. |
| **`VarsayilanYapici.java`** | Inspecting the default zero-argument constructor injected by `javac` and what happens when custom constructors override it. |
| **`Nokta.java` & `NoktaDemo.java`** | 2D Point model implementing constructor chaining (`this(x, y)`), distance calculations, and coordinate manipulation. |
| **`Dikdortgen.java`** | Geometric model enforcing positive dimension boundaries and calculating perimeter/area. |
| **`Ogrenci.java` & `OgrenciDemo.java`** | Student entity management handling constructor overloading and managing an array of custom object references. |

### 🧪 Laboratory Tasks
| File | Problem Statement & Task Focus |
| :--- | :--- |
| **`Kitap.java`** | Encapsulated book domain model enforcing invariant checks, constructor validation, and mutation prevention. |
| **`Gorev1_KitapDemo.java`** | Driver application testing catalog operations, edge cases (e.g., negative page counts/prices), and state integrity. |

---

## ⚙️ Compilation & Execution

All classes can be compiled using `javac` into an output binary directory (`bin/`) and executed via the terminal:

```bash
# From within the Hafta-02 directory:
# 1. Compile the demo and its model dependencies
javac -d ./bin ./src/Nokta.java ./src/NoktaDemo.java

# 2. Run the compiled driver class
java -cp ./bin NoktaDemo
