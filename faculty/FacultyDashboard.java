package faculty;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

public class FacultyDashboard extends JFrame implements ActionListener {
    private String facultyId;
    private JTextField nameField, genderField, nationalityField, dobField, phoneField, emailField, departmentField;
    private JButton updateButton;

    public FacultyDashboard(String facultyId) {
        this.facultyId = facultyId;

        this.setTitle("Faculty Dashboard");
        this.setSize(500, 500);
        ImageIcon image = new ImageIcon("..\\UMS\\image\\AIUB.png");
        this.setIconImage(image.getImage());
		
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setVisible(true);

        JLabel titleLabel = new JLabel("Faculty Dashboard");
        titleLabel.setBounds(150, 20, 200, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        this.add(titleLabel);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 80, 100, 25);
        this.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(150, 80, 200, 25);
        this.add(nameField);

        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(50, 120, 100, 25);
        this.add(genderLabel);

        genderField = new JTextField();
        genderField.setBounds(150, 120, 200, 25);
        this.add(genderField);

        JLabel nationalityLabel = new JLabel("Nationality:");
        nationalityLabel.setBounds(50, 160, 100, 25);
        this.add(nationalityLabel);

        nationalityField = new JTextField();
        nationalityField.setBounds(150, 160, 200, 25);
        this.add(nationalityField);

        JLabel dobLabel = new JLabel("Date of Birth:");
        dobLabel.setBounds(50, 200, 100, 25);
        this.add(dobLabel);

        dobField = new JTextField();
        dobField.setBounds(150, 200, 200, 25);
        this.add(dobField);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(50, 240, 100, 25);
        this.add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(150, 240, 200, 25);
        this.add(phoneField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 280, 100, 25);
        this.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(150, 280, 200, 25);
        this.add(emailField);

        JLabel departmentLabel = new JLabel("Department:");
        departmentLabel.setBounds(50, 320, 100, 25);
        this.add(departmentLabel);

        departmentField = new JTextField();
        departmentField.setBounds(150, 320, 200, 25);
        this.add(departmentField);

        updateButton = new JButton("Update");
        updateButton.setBounds(180, 380, 100, 30);
        updateButton.addActionListener(this);
        this.add(updateButton);

        loadFacultyData();
    }

    private void loadFacultyData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("..\\UMS\\Data\\Faculties.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(facultyId)) { 
                    nameField.setText(data[2]);
                    genderField.setText(data[3]);
                    nationalityField.setText(data[4]);
                    dobField.setText(data[5]);
                    phoneField.setText(data[6]);
                    emailField.setText(data[7]);
                    departmentField.setText(data[8]);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateFacultyData() {
        ArrayList<String> fileData = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("..\\UMS\\Data\\Faculties.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(facultyId)) {
                    data[2] = nameField.getText();
                    data[3] = genderField.getText();
                    data[4] = nationalityField.getText();
                    data[5] = dobField.getText();
                    data[6] = phoneField.getText();
                    data[7] = emailField.getText();
                    data[8] = departmentField.getText();
                    line = String.join(",", data);
                }
                fileData.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("..\\UMS\\Data\\Faculties.txt"))) {
            for (String updatedLine : fileData) {
                writer.write(updatedLine);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        JOptionPane.showMessageDialog(this, "Information updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateButton) {
            updateFacultyData();
        }
    }
}


