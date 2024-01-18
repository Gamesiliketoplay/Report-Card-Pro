package project;

import java.util.ArrayList;

public class StudentManager {

    public Student studentSearch(ArrayList<Student> students, String searchLastName) {
        for (Student student : students) {
            if (student.getlastName().equals(searchLastName)) {
                return student;
            }
        }
        return null;
    }

}
