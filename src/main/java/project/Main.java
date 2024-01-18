package project;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        StudentData reader = new StudentData();
        StudentManager sm = new StudentManager();
        EnrolmentManager em = new EnrolmentManager();
        ArrayList<Classes> courses = reader.GetCourses();
        ArrayList<Enrolments> enrolment = reader.getGrades();
        ArrayList<Student> students = reader.GetStundentInfo();

        for (Classes course : courses) {
            System.out.print(String.format("Code: %s, Name: %s, Assignments: %s%n",
                    course.getCode(), course.getName(), course.getAssignments()));
        }

        System.out.println(""); // Separate the output for better readability

        for (Student info : students) {
            System.out.print(String.format("First Name: %s, Last Name: %s, Student ID: %s%n",
                    info.getfirstName(), info.getlastName(), info.getStudentID()));
        }

        System.out.println(""); // Separate the output for better readability
        Scanner sc = new Scanner(System.in);
        System.out.println("Which Student would you like to see?");
        String searchLastName = sc.next();
        Student result = sm.studentSearch(students, searchLastName);
        System.out.println(result);
        ArrayList<Enrolments> result2 = em.IdSearch(enrolment, result);
        // Printing the results
        System.out.println(result2);
    }
}
