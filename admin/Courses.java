package admin;

import java.io.*;
import java.util.ArrayList;


public class Courses {
    public static final String FILE_NAME = "..\\\\UMS\\\\Data\\\\Courses.txt";

    public static void saveCourse(String department, String courseName, String section, String facultyId, String facultyName, int credits,String semester ) throws IOException {
        String courseData = department + "," + courseName + "," + credits + "," + section + "," + facultyId + "," + facultyName + "," + semester;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(courseData);
            writer.newLine();
        }
    }

    public static String[][] loadCourses() {
        ArrayList<String[]> courseList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                courseList.add(line.split(","));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return courseList.toArray(new String[0][0]);
    }
}
