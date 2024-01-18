package project;

import java.util.ArrayList;

public class EnrolmentManager {
    public ArrayList<Enrolments> IdSearch(ArrayList<Enrolments> enrolmentsList, Student student) {
        ArrayList<Enrolments> matchingEnrolments = new ArrayList<>();

        for (Enrolments enrolment : enrolmentsList) {
            if (enrolment.getStudentID().equals(student.getStudentID())) {
                matchingEnrolments.add(enrolment);
            }
        }

        return matchingEnrolments;
    }
}
