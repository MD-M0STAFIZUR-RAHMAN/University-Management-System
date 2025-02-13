package admin;

import java.io.*;


public class FacultyIdName  {
    private static final String FILE_NAME = "..\\\\UMS\\\\Data\\\\FacultyIdName.txt";

    public static void saveFacultyIdName(String id, String name) throws IOException {
        StringBuilder facultyData = new StringBuilder(id + "," + name );
		
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(facultyData.toString());
            writer.newLine();
        }
    }
}
