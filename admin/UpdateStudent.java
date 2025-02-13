package admin;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class UpdateStudent extends JFrame {
    private JTextField idField, nameField, fatherField, motherField, nationalityField, dobField, levelField, departmentField, emailField, phoneField;
    private JButton searchButton, updateButton, cancelButton;

    public UpdateStudent() {
		ImageIcon image = new ImageIcon("..\\UMS\\image\\AIUB.png");
        this.setIconImage(image.getImage());
        setTitle("Update Student");
        setSize(400, 500);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel idLabel = new JLabel("Enter ID:");
        idLabel.setBounds(20, 20, 100, 30);
        add(idLabel);

        idField = new JTextField();
        idField.setBounds(130, 20, 200, 30);
        add(idField);

        searchButton = new JButton("Search");
        searchButton.setBounds(130, 60, 100, 30);
        searchButton.addActionListener(e -> searchStudent());
        add(searchButton);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 100, 100, 30);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(130, 100, 200, 30);
        add(nameField);

        JLabel fatherLabel = new JLabel("Father:");
        fatherLabel.setBounds(20, 140, 100, 30);
        add(fatherLabel);

        fatherField = new JTextField();
        fatherField.setBounds(130, 140, 200, 30);
        add(fatherField);

        JLabel motherLabel = new JLabel("Mother:");
        motherLabel.setBounds(20, 180, 100, 30);
        add(motherLabel);

        motherField = new JTextField();
        motherField.setBounds(130, 180, 200, 30);
        add(motherField);

        JLabel nationalityLabel = new JLabel("Nationality:");
        nationalityLabel.setBounds(20, 220, 100, 30);
        add(nationalityLabel);

        nationalityField = new JTextField();
        nationalityField.setBounds(130, 220, 200, 30);
        add(nationalityField);

        JLabel dobLabel = new JLabel("DOB:");
        dobLabel.setBounds(20, 260, 100, 30);
        add(dobLabel);

        dobField = new JTextField();
        dobField.setBounds(130, 260, 200, 30);
        add(dobField);

        JLabel levelLabel = new JLabel("Level:");
        levelLabel.setBounds(20, 300, 100, 30);
        add(levelLabel);

        levelField = new JTextField();
        levelField.setBounds(130, 300, 200, 30);
        add(levelField);

        JLabel departmentLabel = new JLabel("Department:");
        departmentLabel.setBounds(20, 340, 100, 30);
        add(departmentLabel);

        departmentField = new JTextField();
        departmentField.setBounds(130, 340, 200, 30);
        add(departmentField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(20, 380, 100, 30);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(130, 380, 200, 30);
        add(emailField);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(20, 420, 100, 30);
        add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(130, 420, 200, 30);
        add(phoneField);

        updateButton = new JButton("Update");
        updateButton.setBounds(60, 460, 100, 30);
        updateButton.addActionListener(e -> updateStudent());
        add(updateButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(200, 460, 100, 30);
        cancelButton.addActionListener(e -> dispose());
        add(cancelButton);

        setVisible(true);
    }

    private void searchStudent() {
        String id = idField.getText();
        try (BufferedReader reader = new BufferedReader(new FileReader("..\\UMS\\Data\\Students.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(id)) {
                    nameField.setText(data[1]);
                    fatherField.setText(data[2]);
                    motherField.setText(data[3]);
                    nationalityField.setText(data[4]);
                    dobField.setText(data[5]);
                    levelField.setText(data[6]);
                    departmentField.setText(data[7]);
                    emailField.setText(data[8]);
                    phoneField.setText(data[9]);
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Student not found.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void updateStudent() {
        String id = idField.getText();
        List<String> students = new ArrayList<>();
        boolean found = false;
        try (BufferedReader reader = new BufferedReader(new FileReader("..\\UMS\\Data\\Students.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(id)) {
                    String updatedData = id + "," + nameField.getText() + "," + fatherField.getText() + "," + motherField.getText() + "," +
                            nationalityField.getText() + "," + dobField.getText() + "," + levelField.getText() + "," + departmentField.getText() + "," +
                            emailField.getText() + "," + phoneField.getText();
                    students.add(updatedData);
                    found = true;
                } else {
                    students.add(line);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        if (found) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("..\\UMS\\Data\\Students.txt"))) {
                for (String student : students) {
                    writer.write(student);
                    writer.newLine();
                }
                JOptionPane.showMessageDialog(this, "Student details updated successfully.");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Student ID not found.");
        }
    }
}
