package project;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class StudentManager {
    public List<Classes> GetCourses() {
        String classesFile = "src/main/java/project/Classes.json";
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(classesFile)) {
            Type listType = new TypeToken<List<Classes>>() {
            }.getType();

            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Enrolments> GetGrades() {
        String enrolmentsFile = "src/main/java/project/Enrolments.json";
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(enrolmentsFile)) {
            Type listType = new TypeToken<List<Enrolments>>() {
            }.getType();

            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Student> GetStundentInfo() {
        String studentFile = "src/main/java/project/Student.json";
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(studentFile)) {
            Type listType = new TypeToken<List<Student>>() {
            }.getType();

            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
