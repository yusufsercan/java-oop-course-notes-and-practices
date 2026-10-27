# 📌 Week 01 — Introduction to Object-Oriented Programming

This module marks the transition from procedural programming habits to the Object-Oriented Programming (OOP) paradigm in Java. It covers class-object relationships, state and behavior encapsulation, constructor logic, the `this` keyword, and the separation of concerns via Model-Driver architecture.

---

## 🎯 Key Learning Objectives

* **Procedural vs. Object-Oriented Paradigm:** Moving from dispersed state/functions to unified classes combining data and behavior.
* **Model & Driver (Demo) Architecture:** Adhering to the *Separation of Concerns* principle by decoupling domain logic from execution entry points (`main` methods).
* **Constructors & Object Initialization:** Ensuring objects in Heap memory instantiate with valid, consistent initial states.
* **Encapsulation Foundations:** Protecting internal object state against arbitrary mutation through controlled methods.

---

## 📂 Source Code Index

### Core & Practice Implementations
| File | Description & Engineering Focus |
| :--- | :--- |
| **`Merhaba.java`** | Basic Java syntax, class declaration, and standard console stream operations.[cite: 9, 10] |
| **`GirdiOrnegi.java`** | User input handling, stream scanning (`Scanner`), and primitive type parsing.[cite: 9, 10] |
| **`YapisalYaklasim.java`** | Procedural banking simulation demonstrating the risks of exposed global state and manual synchronisation.[cite: 9, 10] |
| **`NesneYaklasimi.java`** | Initial OOP approach bundling balance state and financial transactions within a single entity.[cite: 9, 10] |
| **`Hesap.java`** | Encapsulated bank account model with validation rules for deposits and withdrawals.[cite: 9, 10] |
| **`Ogrenci.java` & `OgrenciDemo.java`** | Student model and test driver illustrating constructor initialization and field shadowing resolution using `this`.[cite: 9, 10] |
| **`Dikdortgen.java` & `DikdortgenDemo.java`** | Rectangle geometry model computing perimeter and area while maintaining dimension validity.[cite: 9, 10] |
| **`Car.java` & `CarDemo.java`** | Automotive entity modeling lifecycle, constructor instantiation, and state inspection.[cite: 9, 10] |

### 🧪 Laboratory Tasks
| File | Problem Statement & Task Focus |
| :--- | :--- |
| **`Gorev1_Kimlik.java`** | Formatted string output, validation pipeline, and basic identity verification logic.[cite: 9, 10] |
| **`Gorev2_NotIstatistik.java`** | Array processing, min/max/average calculation algorithms, and boundary condition checks.[cite: 9, 10] |
| **`Kitap.java` & `Gorev3_KitapDemo.java`** | Bookstore catalog simulation utilizing constructor overloading and batch object management.[cite: 9, 10] |

---

## ⚙️ Compilation & Execution

All classes can be compiled to a dedicated `bin/` directory and executed via terminal:

```bash
# From within the Hafta-01 directory:
# 1. Compile (e.g., OgrenciDemo and its dependency Ogrenci)
javac -d ./bin ./src/Ogrenci.java ./src/OgrenciDemo.java

# 2. Run the driver class
java -cp ./bin OgrenciDemo
