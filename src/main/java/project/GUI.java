package project;
import javafx.scene.layout.Region;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import java.util.List;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class GUI extends Application {

    private Map<String, Student> studentsMap;
    private ObservableList<Student> studentList;
    private TableView<Student> tableView;
    private Stage primaryStage;
  private GridPane grid;


  private void viewMoreDialog(Student student) {

    Dialog<Void> dialog = new Dialog<>();
    dialog.initOwner(primaryStage);
    dialog.setTitle("View More");


    // Create a GridPane for the report card layout
    GridPane gridPane = new GridPane();
    gridPane.setHgap(10);
    gridPane.setVgap(10);
    gridPane.setPadding(new Insets(20));

    // Add labels with student information
    Label nameLabel = new Label("Student Name:");
    Label nameValueLabel = new Label(student.getFirstName() + " " + student.getLastName());

    Label mathLabel = new Label("Math Grade:");
    Label mathValueLabel = new Label(String.valueOf(student.getMathMarks()));

    Label scienceLabel = new Label("Science Grade:");
    Label scienceValueLabel = new Label(String.valueOf(student.getScienceMarks()));

    Label averageLabel = new Label("Average:");
    Label averageValueLabel = new Label(String.valueOf(student.getAverage()));

    // Add labels to the GridPane
    gridPane.add(nameLabel, 0, 0);
    gridPane.add(nameValueLabel, 1, 0);
    gridPane.add(mathLabel, 0, 1);
    gridPane.add(mathValueLabel, 1, 1);
    gridPane.add(scienceLabel, 0, 2);
    gridPane.add(scienceValueLabel, 1, 2);
    gridPane.add(averageLabel, 0, 3);
    gridPane.add(averageValueLabel, 1, 3);

    // Additional comments section
    Label commentsLabel = new Label("Additional Comments:");
    TextArea commentsTextArea = new TextArea();
    commentsTextArea.setWrapText(true);

    // Add additional comments to the GridPane
    gridPane.add(commentsLabel, 0, 4);
    gridPane.add(commentsTextArea, 1, 4, 2, 1);


    dialog.getDialogPane().setContent(gridPane);

    // Add close button to the dialog header
    Button closeButton = new Button("Close");
    closeButton.setOnAction(event -> dialog.close());
    dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
    dialog.getDialogPane().lookupButton(ButtonType.CLOSE).setVisible(false);


    // Show the dialog
    dialog.showAndWait();


  }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        primaryStage.setTitle("Report Card Software");

        studentsMap = new HashMap<>();
        studentList = FXCollections.observableArrayList();

        loadStudentsData();

        tableView = createTableView();
        GridPane gridPane = createGridPane();

        Scene scene = new Scene(gridPane, 600, 400);

  //    scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private TableView<Student> createTableView() {
    TableView<Student> tableView = new TableView<>();

    TableColumn<Student, String> firstNameCol = createStringTableColumn("First Name", Student::firstNameProperty);
    TableColumn<Student, String> lastNameCol = createStringTableColumn("Last Name", Student::lastNameProperty);
    TableColumn<Student, Double> averageCol = createDoubleTableColumn("Average", Student::averageProperty);
      TableColumn<Student, String> studentID = createStringTableColumn("Student ID", Student::studentIDProperty);
    // Clear existing columns to avoid duplicates
    tableView.getColumns().clear();

    tableView.getColumns().addAll(firstNameCol, lastNameCol, averageCol, studentID);


      setMinWidth(firstNameCol);
      setMinWidth(lastNameCol);
      setMinWidth(averageCol);
      setMinWidth(studentID);


    tableView.setItems(studentList);
    tableView.setEditable(true);



      tableView.setRowFactory(tv -> {
          TableRow<Student> row = new TableRow<>();

    // Right-click handling (show context menu)
    ContextMenu contextMenu = new ContextMenu();
    MenuItem viewMoreItem = new MenuItem("View More");
    viewMoreItem.setOnAction(e -> {
        Student selectedStudent = row.getItem();
        if (selectedStudent != null) {
            viewMoreDialog(selectedStudent);
        }
    });
  //  contextMenu.getItems().add(viewMoreItem);

    row.setOnContextMenuRequested(event -> {
        contextMenu.show(row, event.getScreenX(), event.getScreenY());
    });



contextMenu.getItems().add(viewMoreItem);

row.setOnContextMenuRequested(event -> {
    contextMenu.show(row, event.getScreenX(), event.getScreenY());
});

          // Single-click handling (select student)
          row.setOnMouseClicked(event -> {
              if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) {
                  Student selectedStudent = row.getItem();
                  if (selectedStudent != null) {
                      showStudentDetails(selectedStudent);
                  }
              }
          });

          // Double-click handling (open modify marks window)
          row.setOnMouseClicked(event -> {
              if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                  Student selectedStudent = row.getItem();
                  if (selectedStudent != null) {
                      modifyMarksDialog(selectedStudent);
                  }
              }
          });

          return row;
      });



    return tableView;

}

  private void setMinWidth(TableColumn<?, ?> column) {
      final double MIN_COLUMN_WIDTH = 120.0; // Adjust this value as needed
      column.setMinWidth(MIN_COLUMN_WIDTH);
  }

    private TableColumn<Student, String> createStringTableColumn(String columnName, Function<Student, StringProperty> extractor) {
      TableColumn<Student, String> column = new TableColumn<>(columnName);
      column.setCellValueFactory(cellData -> extractor.apply(cellData.getValue()));
      column.setCellFactory(TextFieldTableCell.forTableColumn());

      column.setEditable(false);


      column.setOnEditCommit(event -> {
            TablePosition<Student, String> position = event.getTablePosition();
            String newValue = event.getNewValue();
            int row = position.getRow();
            Student student = event.getTableView().getItems().get(row);

            if (column == getFirstNameCol()) {
                student.setFirstName(newValue);
            } else if (column == getLastNameCol()) {
                student.setLastName(newValue);
            }

            saveStudentsData();
        });
        return column;
    }



    private TableColumn<Student, Double> createDoubleTableColumn(String columnName, Function<Student, DoubleProperty> extractor) {
      TableColumn<Student, Double> column = new TableColumn<>(columnName);
      column.setCellValueFactory(cellData -> extractor.apply(cellData.getValue()).asObject());
      column.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

      column.setEditable(false);


      column.setOnEditCommit(event -> {
            TablePosition<Student, Double> position = event.getTablePosition();
            Double newValue = event.getNewValue();
            int row = position.getRow();
            Student student = event.getTableView().getItems().get(row);

            if (column == getAverageCol()) {
                student.setAverage(newValue);
            }

            saveStudentsData();
        });


        return column;
    }

    private TableColumn<Student, String> getFirstNameCol() {
        return (TableColumn<Student, String>) tableView.getColumns().get(0);
    }

    private TableColumn<Student, String> getLastNameCol() {
        return (TableColumn<Student, String>) tableView.getColumns().get(1);
    }

    private TableColumn<Student, Double> getAverageCol() {
        return (TableColumn<Student, Double>) tableView.getColumns().get(2);
    }

    private void showStudentDetails(Student student) {
        if (student != null) {
            System.out.println("Student is not null");
            modifyMarksDialog(student);
        }else{
            System.out.println("Student is null");
        }
    }



    private void modifyMarksDialog(Student student) {
                  Dialog<Void> dialog = new Dialog<>();
                  dialog.initOwner(primaryStage);
                  dialog.setTitle("Modify Marks");

                  ButtonType modifyButton = new ButtonType("Modify", ButtonBar.ButtonData.OK_DONE);
                 ButtonType add2NewClassButton = new ButtonType("Add New Class");

                  GridPane grid = new GridPane();
                  grid.setHgap(10);
                  grid.setVgap(10);
                  grid.setPadding(new Insets(20, 150, 10, 10));

                  // Add TextFields for class name and four major assignments
                  TextField classNameField = new TextField();
                  classNameField.setPromptText("Class Name");
                  grid.add(new Label("Class Name:"), 0, 0);
                  grid.add(classNameField, 1, 0);

                  List<List<TextField>> assignmentFieldsList = new ArrayList<>();

                  // Initial set of assignment fields
                  for (int i = 1; i <= 4; i++) {
                      TextField assignmentField = new TextField();
                      assignmentField.setPromptText("Assignment " + i);
                      grid.add(new Label("Assignment " + i + ":"), 0, i);
                      grid.add(assignmentField, 1, i);
                  }

                  assignmentFieldsList.add(createAssignmentFieldsList(grid, 1));

                  dialog.getDialogPane().setContent(grid);

                  dialog.getDialogPane().getButtonTypes().addAll(modifyButton, ButtonType.CANCEL, add2NewClassButton);

      dialog.setResultConverter(dialogButton -> {
        if (dialogButton == modifyButton) {
            String className = classNameField.getText();

            // Validate class name and assignment marks
            if (className.isEmpty()) {
                showAlert("Validation Error", null, "Class Name cannot be empty.");
                return null;
            }

            List<Double> assignmentMarks = new ArrayList<>();
            for (List<TextField> assignmentFields : assignmentFieldsList) {
                for (TextField assignmentField : assignmentFields) {
                    String assignmentText = assignmentField.getText();
                    if (assignmentText.isEmpty() || !isNumeric(assignmentText)) {
                        showAlert("Validation Error", null, "Assignment marks must be a valid number.");
                        return null;
                    }
                    assignmentMarks.add(Double.parseDouble(assignmentText));
                }
            }

            // Update the student's data within the dialog
            updateStudentData(student, className, assignmentMarks);

            // Close the dialog
            dialog.close();
        }

        return null;
      });

      // Handle the "Add New Class" button separately
      Button addNewClassButton = new Button("Add New Class");
        addNewClassButton.setOnAction(event -> {
        // Add a new set of assignment fields
        assignmentFieldsList.add(createAssignmentFieldsList(grid, assignmentFieldsList.size() * 4 + 1));
      });
      dialog.getDialogPane().getChildren().add(addNewClassButton);



        dialog.showAndWait();

      calculateOverallAverages(student);
    }


  private List<TextField> createAssignmentFieldsList(GridPane grid, int rowIndex) {
      List<TextField> assignmentFields = new ArrayList<>();
      for (int i = 1; i <= 4; i++) {
          TextField assignmentField = new TextField();
          assignmentField.setPromptText("Assignment " + i);
          grid.add(new Label("Assignment " + i + ":"), 0, rowIndex);
          grid.add(assignmentField, 1, rowIndex);
          assignmentFields.add(assignmentField);
          rowIndex++;
      }
      return assignmentFields;
  }





  // Helper method to check if a string is numeric
  private boolean isNumeric(String str) {
      try {
          Double.parseDouble(str);
          return true;
      } catch (NumberFormatException e) {
          return false;
      }
  }


  private void updateStudentData(Student student, String className, List<Double> assignmentMarks) {
      // Retrieve the ClassData instance for the modified or new class name
      Student.ClassData modifiedClassData = student.classDataMap.get(className);

      // Update or create ClassData instance if it doesn't exist
      if (modifiedClassData == null) {
          modifiedClassData = new Student.ClassData(className);
          student.addClassData(modifiedClassData);
      }

      // Update assignment marks in the ClassData instance
      for (int i = 0; i < assignmentMarks.size(); i++) {
          modifiedClassData.setAssignmentMarks(i + 1, assignmentMarks.get(i));
      }
  }


  private void calculateOverallAverages(Student student) {
    // Recalculate class averages and overall average
    double overallSum = 0.0;
    int overallCount = 0;

    for (Student.ClassData classData : student.classDataMap.values()) {
        int numberOfAssignments = 4;
        double sum = 0.0;

        for (int i = 1; i <= numberOfAssignments; i++) {
            sum += classData.getAssignmentMarks(i);
        }

        double classAverage = sum / numberOfAssignments;
        classData.setClassAverage(classAverage);

        overallSum += classAverage;
        overallCount++;
    }

    double overallAverage = overallCount > 0 ? overallSum / overallCount : 0.0;

    // Set the overall average for the student
    student.setAverage(overallAverage);
  }

  private void updateOverallAverage(Student student) {
      int numberOfClasses = student.classDataMap.size();
      double sum = 0.0;

      for (Student.ClassData classData : student.classDataMap.values()) {
          sum += classData.getClassAverage();
      }

      double overallAverage = sum / numberOfClasses;

      // Set the overall average for the student
      student.setAverage(overallAverage);
  }










    private GridPane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);

        Button addStudentBtn = createButton("Add Student", e -> addStudentDialog());
        Button removeStudentBtn = createButton("Remove Student", e -> removeStudent());
//view more button must be implemented

        gridPane.add(addStudentBtn, 0, 0);
        gridPane.add(removeStudentBtn, 1, 0);
        gridPane.add(tableView, 0, 1, 3, 1);

        return gridPane;
    }

    private Button createButton(String text, javafx.event.EventHandler<javafx.event.ActionEvent> handler) {
        Button button = new Button(text);
        button.setMinWidth(Region.USE_PREF_SIZE);
        button.setOnAction(handler);
        return button;
    }

    private void addStudentDialog() {
        Dialog<Student> dialog = new Dialog<>();
        dialog.initOwner(primaryStage);
        dialog.setTitle("Add Student");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField firstName = new TextField();
        firstName.setPromptText("First Name");
        TextField lastName = new TextField();
        lastName.setPromptText("Last Name");

        grid.add(new Label("First Name:"), 0, 0);
        grid.add(firstName, 1, 0);
        grid.add(new Label("Last Name:"), 0, 1);
        grid.add(lastName, 1, 1);

        dialog.getDialogPane().setContent(grid);

        ButtonType addButton = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButton) {
                String fName = firstName.getText();
                String lName = lastName.getText();
                Student student = new Student(fName, lName);
                studentsMap.put(student.getLastName(), student);
                studentList.add(student);

                saveStudentsData();
            }
            return null;
        });

        dialog.showAndWait();
    }

    private void removeStudent() {
        TableView.TableViewSelectionModel<Student> selectionModel = tableView.getSelectionModel();
        int selectedIndex = selectionModel.getSelectedIndex();

        if (selectedIndex >= 0) {
            Student studentToRemove = selectionModel.getSelectedItem();
            studentsMap.remove(studentToRemove.getLastName());
            studentList.remove(studentToRemove);

            saveStudentsData();
        } else {
            showAlert("No Selection", "No Student Selected", "Please select a student in the table.");
        }
    }

    private void printReportCard() {
        Student selectedStudent = tableView.getSelectionModel().getSelectedItem();

        if (selectedStudent != null) {
            // Implement report card printing logic here
            // You may use JavaFX Printing API or other methods
            System.out.println("Printing report card for: " + selectedStudent);
        } else {
            showAlert("Information", null, "Please select a student to print the report card.");
        }
    }

    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void loadStudentsData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("students.ser"))) {
            if (ois.available() > 0) { // Check if the file has content
                studentsMap = (HashMap<String, Student>) ois.readObject();
                studentList = FXCollections.observableArrayList(studentsMap.values());
                tableView.setItems(studentList);
            } else {
                // Handle the case when the file is empty
                System.out.println("No existing data found. Starting with an empty dataset.");
                studentsMap = new HashMap<>();
                studentList = FXCollections.observableArrayList();
            }
        } catch (FileNotFoundException e) {
            System.out.println("No existing data file found. Starting with an empty dataset.");
            studentsMap = new HashMap<>();
            studentList = FXCollections.observableArrayList();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        saveStudentsData();
    }

    private void saveStudentsData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("students.ser"))) {
            oos.writeObject(studentsMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class Student implements Serializable {
        private StringProperty firstName;
        private StringProperty lastName;
        private DoubleProperty average;
        private DoubleProperty mathMarks;
        private DoubleProperty scienceMarks;
        private StringProperty studentID;
        private Map<String, ClassData> classDataMap;

        public Student(String firstName, String lastName) {
            this.firstName = new SimpleStringProperty(firstName);
            this.lastName = new SimpleStringProperty(lastName);
            this.average = new SimpleDoubleProperty(0.0);
            this.mathMarks = new SimpleDoubleProperty(0.0);
            this.scienceMarks = new SimpleDoubleProperty(0.0);
            this.studentID = new SimpleStringProperty();
          classDataMap = new HashMap<>();


        }
      public ClassData getClassData(String className) {
          return classDataMap.get(className);
      }

      public void addClassData(ClassData classData) {
          classDataMap.put(classData.getClassName(), classData);
      }



      public static class ClassData implements Serializable {
              private String className;
              private Map<Integer, Double> assignmentMarks;
              private Double classAverage;

              public ClassData(String className) {
                  this.className = className;
                  this.assignmentMarks = new HashMap<>();
              }

              public String getClassName() {
                  return className;
              }

        public Double getAssignmentMarks(int assignmentNumber) {
          return assignmentMarks.getOrDefault(assignmentNumber, 0.0);
        }

        public void setAssignmentMarks(int assignmentNumber, double marks) {
          assignmentMarks.put(assignmentNumber, marks);
        }

        public Double getClassAverage() {
          return classAverage;
        }

        public void setClassAverage(Double classAverage) {
          this.classAverage = classAverage;
              }
          }


        public String getFirstName() {
            return firstName.get();
        }

        public StringProperty firstNameProperty() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName.set(firstName);
        }

        public String getLastName() {
            return lastName.get();
        }

        public StringProperty lastNameProperty() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName.set(lastName);
        }

        public double getAverage() {
            return average.get();
        }

      public DoubleProperty averageProperty() {
          return average;
      }

      public StringProperty studentIDProperty(){
        return studentID;
      }


        public void setAverage(double average) {
            this.average.set(average);
        }

        public double getMathMarks() {
            return mathMarks.get();
        }

        public DoubleProperty mathMarksProperty() {
            return mathMarks;
        }

        public void setMathMarks(double mathMarks) {
            this.mathMarks.set(mathMarks);
            calculateAverage();
        }

        public double getScienceMarks() {
            return scienceMarks.get();
        }

        public DoubleProperty scienceMarksProperty() {
            return scienceMarks;
        }

        public void setScienceMarks(double scienceMarks) {
            this.scienceMarks.set(scienceMarks);
            calculateAverage();
        }

        public void calculateAverage() {
            double avg = (mathMarks.get() + scienceMarks.get()) / 2.0;
            average.set(avg);
        }

        @Override
        public String toString() {
            return "Student{" +
                    "firstName=" + firstName +
                    ", lastName=" + lastName +
                    ", average=" + average +
                    '}';
        }
    }

}