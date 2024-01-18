package project;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentManager {
    private int x;

    public Student studentSearch(ArrayList<Student> students, String searchLastName) {

        for (Student student : students) {
            if (student.getlastName().equals(searchLastName)) {
                return student;
            }
            x++;
        }
        return null;
    }
}
