package project;

import java.io.Serializable;
import java.util.List;

public class Classes implements Serializable {
    private String code;
    private String name;
    private List<String> assignments;

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

    public List<String> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<String> assignments) {
        this.assignments = assignments;
    }
}
