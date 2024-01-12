package project;

import java.io.Serializable;

public class Enrolments implements Serializable {
    private String StudentID;
    private String CourseCode;
    private int[] Marks;

    // Getters and setters for the fields

    public String getStudentID() {
        return StudentID;
    }

    public void setStudentID(String StudentID) {
        this.StudentID = StudentID;
    }

    public String getCourseCode() {
        return CourseCode;
    }

    public void setCourseCode(String CourseCode) {
        this.CourseCode = CourseCode;
    }

    public int[] getMarks() {
        return Marks;
    }

    public void setMarks(int[] Marks) {
        this.Marks = Marks;
    }
}