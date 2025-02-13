package admin;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Methods {
	public static String[] readFile(String fileName) {
        List<String> List = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                List.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return List.toArray(new String[0]);
    }
	
	public static boolean readStatus() { 
        File file = new File("..\\UMS\\Data\\status.txt");
        if (!file.exists()) return false;
		
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return Boolean.parseBoolean(reader.readLine().trim());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}