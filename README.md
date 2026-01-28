# Report-Card-Pro 📊

**Report-Card-Pro** is a Java-based desktop application designed to streamline the management of student enrollments, grades, and report card generation. Built with **JavaFX** and **Maven**, it features a graphical user interface (GUI) that allows educators or administrators to easily add students, modify grades, and calculate averages automatically.

## 🚀 Features

* **Interactive GUI:** Built with JavaFX, featuring a responsive table view for managing student data.
* **Student Management:**
    * **Add Students:** Easy input forms to register new students.
    * **Remove Students:** Delete student records directly from the interface.
    * **View Details:** Context menus to view specific student information.
* **Grade Management:**
    * **Modify Marks:** Double-click any student to open a dialog for editing class names and assignment scores.
    * **Dynamic Calculations:** Automatically calculates class averages and overall student averages based on assignment inputs.
* **Data Persistence:**
    * Uses Java Serialization (`students.ser`) to save and load student data locally, ensuring data isn't lost when the application closes.
    * Includes JSON integration (via GSON) for parsing initial course and enrollment data.

## 🛠️ Tech Stack

* **Language:** Java 17
* **Build Tool:** Maven
* **GUI Framework:** JavaFX (Controls & FXML)
* **Data Handling:** GSON (Google JSON), Java Serialization
* **IDE Support:** Optimized for Replit and VS Code

## 📂 Project Structure

```text
src/main/java/project/
├── GUI.java              // Main application logic and UI definitions
├── Main.java             // Entry point of the application
├── Student.java          // Student data model
├── Classes.java          // Course data model
├── Enrolments.java       // Enrollment and grade data model
├── StudentManager.java   // Logic for searching and managing students
├── StudentData.java      // Handles JSON parsing for initial data
└── *.json                // Seed data for Students, Classes, and Enrollments
