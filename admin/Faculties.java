package admin;

import java.io.*;


public class Faculties {
    private static final String FILE_NAME = "..\\\\UMS\\\\Data\\\\Faculties.txt";

    public static void saveFaculty(String id, String password, String name, String gender, String nationality, String dob, String phone, String email, String[] programs, String[] cgpas, String department) throws IOException {
        StringBuilder facultyData = new StringBuilder(id + "," + password + "," + name + "," + gender + "," + nationality + "," + dob + "," + phone + "," + email + "," + department);
        for (int i = 0; i < programs.length; i++) {
            facultyData.append(",").append(programs[i]).append(",").append(cgpas[i]);
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(facultyData.toString());
            writer.newLine();
        }
    }
}
