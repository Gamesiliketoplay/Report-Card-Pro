package project;

import java.util.ArrayList;

import javax.swing.SwingUtilities;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Panel());
        StudentData reader = new StudentData();
        ArrayList<Classes> courses = reader.GetCourses();
        ArrayList<Enrolments> grades = reader.getGrades();
        ArrayList<Student> infos = reader.GetStundentInfo();

        for (Classes course : courses) {
            System.out.print(String.format("Code: %s, Name: %s, Assignments: %s%n",
                    course.getCode(), course.getName(), course.getAssignments()));
        }

        System.out.println(""); // Separate the output for better readability

        for (Enrolments grade : grades) {
            System.out.print(String.format("StudentID: %s, CourseCode: %s, Marks: %s%n",
                    grade.getStudentID(), grade.getCourseCode(), grade.getMarks()));
        }

        System.out.println(""); // Separate the output for better readability

        for (Student info : infos) {
            System.out.print(String.format("First Name: %s, Last Name: %s, Student ID: %s%n",
                    info.getfirstName(), info.getlastName(), info.getStudentID()));
        }

    }
}
