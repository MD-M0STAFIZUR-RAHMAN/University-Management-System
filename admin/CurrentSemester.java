package admin;

import java.io.*;

public class CurrentSemester {
    private static final String FILE_NAME = "..\\\\UMS\\\\Data\\\\current_semester.txt";

    public static void saveSemester(String semesterName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write(semesterName);
        }
    }
}
