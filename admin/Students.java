package admin;

import java.io.*;


public class Students {

    private static final String FILE_NAME = "..\\UMS\\Data\\Students.txt";

    public static void saveStudent(String id, String studentName, String fathersName, String mothersName, String nationality, String dob, String level, String department, String email, String phoneNumber) 
        throws IOException {
        String studentData = id + "," + studentName + "," + fathersName + "," + mothersName + "," + nationality + "," + dob + "," + level + "," + department + "," + email + "," + phoneNumber;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(studentData);
            writer.newLine();
        }
    }
}
