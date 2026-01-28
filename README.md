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
├── GUI.java               // Main application logic and UI definitions
├── Main.java              // Entry point of the application
├── Student.java           // Student data model
├── Classes.java           // Course data model
├── Enrolments.java        // Enrollment data model
├── StudentManager.java    // Logic for searching/managing students
├── EnrolmentManager.java  // Logic for filtering enrollments by ID
├── StudentData.java       // Handles JSON parsing for initial data
└── *.json                 // Seed data (Classes.json, Student.json, Enrolments.json)

```

## ⚙️ Installation & Setup

### Prerequisites

* [Java JDK 17](https://www.google.com/search?q=https://www.oracle.com/java/technologies/downloads/%23java17) or higher
* [Maven](https://maven.apache.org/install.html)

### Building the Project

1. **Clone the repository:**
```bash
git clone https://github.com/Gamesiliketoplay/Report-Card-Pro.git

```


2. **Compile and Build with Maven:**
```bash
mvn clean compile assembly:single

```



## 🖥️ Usage

To run the application after building:

```bash
java -cp target/reportcardpro-1.0-SNAPSHOT-jar-with-dependencies.jar project.Main

```

### Controls

* **Left Click:** Select a student.
* **Right Click:** Open context menu (View More).
* **Double Click:** Open the **Modify Marks** dialog to add classes and grades.

## 📝 Configuration

The project uses a `pom.xml` file to manage dependencies. Key dependencies include:

* `javafx-controls` (17.0.2)
* `javafx-fxml` (17.0.2)
* `gson` (2.10.1)

If you are running this on **Replit**, the repository includes a `.replit` configuration file that handles the classpath and run commands automatically.

## 🤝 Contributing

Contributions, issues, and feature requests are welcome!

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 👤 Author

**mufasa334**

* GitHub: [@mufasa334](https://www.google.com/search?q=https://github.com/mufasa334)

---

*This project was created for educational purposes to demonstrate Object-Oriented Programming and GUI development in Java.*

```

```
