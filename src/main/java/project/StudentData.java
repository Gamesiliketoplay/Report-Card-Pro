package project;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class StudentData {
    public ArrayList<Classes> GetCourses() {
        String classesFile = "src/main/java/project/Classes.json";// if running in replit chat to "Classes.json"
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(classesFile)) {
            Type listType = new TypeToken<ArrayList<Classes>>() {
            }.getType();

            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public ArrayList<Enrolments> getGrades() {
        String enrolmentsFile = "src/main/java/project/Enrolments.json";// if running in replit chat to
                                                                        // "Enrolments.json"
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(enrolmentsFile)) {
            Type listType = new TypeToken<ArrayList<Enrolments>>() {
            }.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<>(); // Return an empty list in case of an error
    }

    public ArrayList<Student> GetStundentInfo() {
        String studentFile = "src/main/java/project/Student.json";// if running in replit chat to "Student.json"
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(studentFile)) {
            Type listType = new TypeToken<ArrayList<Student>>() {
            }.getType();

            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
