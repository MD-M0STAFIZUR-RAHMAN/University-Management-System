package admin;

import java.io.*;

public class User {
    private static final String FILE_NAME = "..\\UMS\\Data\\user.txt";

    public static void saveUser(String userRole, String id, String password) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(userRole + "," + id + "," + password);
            writer.newLine();
        }
    }

    public static boolean verifyUser(String userRole, String id, String password) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3 && parts[0].equals(userRole) && parts[1].equals(id) && parts[2].equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }
}
