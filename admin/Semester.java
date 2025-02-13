package admin;

import java.io.*;

public class Semester {
    private static final String FILE_NAME = "..\\UMS\\Data\\semesters.txt";

    public static void saveSemester( String semesterName) throws IOException {
        String semesterData = semesterName ;
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(semesterData);
            writer.newLine();
        }
    }
}
