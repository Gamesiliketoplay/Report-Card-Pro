# Report Card Pro

## Overview
Report Card Pro is a comprehensive Student Management System (SMS) built with **Java** and **JavaFX**. It allows educators and administrators to manage student enrollments, track academic performance across different courses, and generate dynamic report cards.

The system utilizes a hybrid data approach, parsing static course data from **JSON** while maintaining runtime state via Java Serialization.

## Key Features
* **Student Dashboard:** A responsive `TableView` interface to view, sort, and manage the student roster in real-time.
* **Course Management:** Dynamic handling of courses (Math, Science, etc.) and specific assignment weighting.
* **Grade Entry System:** Interactive dialogs for modifying marks with built-in input validation.
* **Data Persistence:**
    * **Loading:** utilizing `Google Gson` to parse initial student/course data from JSON.
    * **Saving:** Implements `Serializable` to persist user changes and application state between sessions.
* **Dynamic UI:** Context-aware menus (Right-click to "View More") and modal dialogs for adding/removing students.

## Technical Architecture
* **Language:** Java 17+
* **Frontend:** JavaFX (Stage, Scene, Nodes, Event Handling)
* **Data Handling:** Google Gson (JSON Parsing), Java IO (Serialization)
* **Build Tool:** Maven

## My Contributions (GUI & UX Architecture)
As the **Lead UI Developer**, I was responsible for the entire visual layer and user interaction flow:
* **Designed the Main Interface:** Built a robust `GridPane` layout containing specific action controls and a dynamic data table.
* **Event-Driven Programming:** Implemented listeners for mouse interactions, including double-click to edit and right-click context menus.
* **Data Binding:** Leveraged `ObservableList` and `Property` bindings to ensure the UI stays completely in sync with the backend data model.
* **Custom Dialog Components:** Created reusable `Dialog<T>` classes for complex tasks like "Modify Marks," ensuring valid data entry before updating the model.

## How to Run
1.  Clone the repository.
2.  Ensure Maven is installed to handle the `Gson` dependency.
3.  Run `GUI.java` as the entry point.
