package project;

import java.io.Serializable;

public class Classes implements Serializable {
    private String code;
    private String name;
    private String assignments;

    // Getters and setters for the fields

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAssignments() {
        return assignments;
    }

    public void setAssignments(String assignments) {
        this.assignments = assignments;
    }
}
