
# Java & Object-Oriented Programming — Course Notes & Practices

A collection of Java programming exercises, laboratory assignments, and study notes developed while studying Object-Oriented Programming as part of my Software Engineering coursework.

This repository documents my progress in understanding Java fundamentals, object-oriented design principles, and practical software development through hands-on exercises.

## Repository Structure

The repository is organized by course week. Each directory contains Java source files, practical examples, and explanations related to the topics covered during that week.

```text
java-oop-course-notes-and-practices/
├── Hafta-01/
│   └── src/
├── Hafta-02/
│   └── src/
├── Hafta-03/
│   └── src/
├── Hafta-04/
│   └── src/
├── Hafta-05/
│   └── src/
├── Hafta-06/
│   └── src/
├── Hafta-07/
│   └── src/
├── .gitignore
└── README.md
```

## Weekly Topics

### Week 01 — Java Fundamentals & OOP Introduction

- Java compilation and execution using `javac`, `java`, and the JVM.
- Basic class and object definitions, fields, methods, and constructors.
- Object references, `null`, and differences between primitive and reference types.
- Introduction to encapsulation and object-oriented programming concepts.

### Week 02 — Constructors & Object Lifecycle

- Constructor overloading and constructor chaining with `this(...)`.
- Default, parameterized, and copy constructors.
- Object references versus independent object copies.
- Field initialization and basic object lifecycle concepts.
- Encapsulation through private fields, getters, and setters.
- Object arrays, enhanced `for` loops, and `toString()`.

### Week 03 — The Four Pillars of Object-Oriented Programming

- **Encapsulation:** Protecting object state and enforcing validation through methods.
- **Abstraction:** Defining common behaviors using interfaces and abstract classes.
- **Inheritance:** Reusing and extending class functionality with `extends` and `super(...)`.
- **Polymorphism:** Using parent-class or interface references with different object implementations.
- Method overriding with `@Override` and dynamic method dispatch.
- Understanding `implements`, `instanceof`, and type casting.
- Identifying design problems in procedural and poorly encapsulated code.

### Week 04 — Encapsulation, Immutability & Access Control

- Access modifiers: `public`, `private`, `protected`, and package-private.
- Package visibility and accessibility across different packages.
- Constructor validation and controlled state modification.
- Immutable objects using `final` classes and fields.
- Domain-specific methods instead of unrestricted setters.
- Defensive validation and maintaining consistent object state.
- Practical examples involving bank accounts, temperature, and monetary values.

### Week 05 — Advanced Constructors & Object Initialization

- Constructor chaining and overloaded constructor design.
- Copy constructors and shallow versus deep copying.
- Object creation and initialization order.
- Static factory methods and alternative object creation patterns.
- Static and instance initialization concepts.
- Resource management with `AutoCloseable` and try-with-resources.

### Week 06 — Static Members, Final & Initialization Order

- Differences between static and instance fields and methods.
- Class-level state versus object-specific state.
- Static initialization blocks and instance initialization blocks.
- Understanding initialization order during object creation.
- Constants, `final` fields, and mutable array references.
- Utility classes and controlled object creation.
- Introduction to the Singleton design pattern.

### Week 07 — Inheritance, Composition & Polymorphism

- Building class hierarchies using `extends`.
- Constructor chaining with `super(...)` and initialization order across inheritance levels.
- Method overriding and runtime polymorphism.
- Upcasting, downcasting, and `instanceof`.
- The difference between **IS-A** and **HAS-A** relationships.
- Composition and delegation as alternatives to inheritance.
- Common inheritance pitfalls, including field hiding and constructor-related design issues.

---

## Study Methodology

This repository follows a practice-oriented learning approach based on university lecture materials, laboratory assignments, and additional exercises.

My study workflow includes:

1. **Theory Review:** Studying the lecture materials and understanding the underlying Java and OOP concepts.
2. **Code Practice:** Writing, executing, and modifying Java examples to observe their behavior.
3. **Debugging & Analysis:** Investigating compilation errors, unexpected outputs, and object behavior.
4. **Documentation:** Adding detailed Turkish explanations to source files, covering execution flow, important concepts, and common mistakes.
5. **Knowledge Reinforcement:** Practicing output prediction, code tracing, error analysis, and short programming exercises.

AI-assisted explanations and code reviews are used as supplementary learning resources to clarify concepts, examine alternative implementations, and improve documentation.

The repository is maintained as an educational record of my coursework and ongoing learning rather than a production software project.

## Development Environment

| Tool | Usage |
|---|---|
| Java / OpenJDK 21 | Programming language and development kit |
| Visual Studio Code | Code editor and Java development environment |
| Java Compiler (`javac`) | Compiling Java source code |
| Java Virtual Machine (JVM) | Running compiled Java programs |
| Git & GitHub | Version control and progress tracking |

## Working with the Examples

Each `Hafta-XX` directory is intended to be studied independently.

Some weekly exercises use similar class names or different package structures. For this reason, opening and running one week's folder at a time in Visual Studio Code is recommended.

The source files contain examples, demonstrations, and exercises designed to reinforce individual concepts.

## Learning Objectives

Through this repository, I aim to:

- Develop a strong understanding of Java and object-oriented programming.
- Write maintainable code using encapsulation, abstraction, inheritance, and polymorphism.
- Understand object creation, references, memory behavior, and initialization.
- Improve debugging, code-reading, and problem-solving skills.
- Build a foundation for more advanced software engineering and secure programming practices.

---

**Status:** Ongoing academic learning and practice.

**Focus:** Java • Object-Oriented Programming • Software Design • Problem Solving

