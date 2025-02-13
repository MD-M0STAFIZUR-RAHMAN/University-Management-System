package admin;

import java.awt.*;
import java.io.*;
import javax.swing.*;

public class AddStudent extends JFrame {
    JTextField idField, passwordField, studentNameField, fathersNameField, mothersNameField, dobField, emailField, phoneField;
    JComboBox<String> levelComboBox, departmentComboBox, nationalityComboBox;
    JButton submitButton , backButton;

    public AddStudent() {
        ImageIcon image = new ImageIcon("..\\UMS\\image\\AIUB.png");
        this.setIconImage(image.getImage());
        this.setTitle("Create Account...");
        this.setSize(600, 900);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(240, 248, 255));
		this.setVisible(true);
        
        JLabel titleLabel = new JLabel("Create Student Account");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setForeground(new Color(0, 102, 204));
        titleLabel.setBounds(150, 50, 300, 30);
        
        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(50, 100, 200, 30);
        idField = new JTextField();
        idField.setBounds(200, 100, 200, 30);
        
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 150, 200, 30);
        passwordField = new JPasswordField();
        passwordField.setBounds(200, 150, 200, 30);
        
        JLabel levelLabel = new JLabel("Level:");
        levelLabel.setBounds(50, 200, 200, 30);
        String[] levels = {"Undergraduate", "Postgraduate"};
        levelComboBox = new JComboBox<>(levels);
        levelComboBox.setBounds(200, 200, 200, 30);
        
        JLabel departmentLabel = new JLabel("Department:");
        departmentLabel.setBounds(50, 250, 200, 30);
        String[] departments = Methods.readFile("..\\\\UMS\\\\Data\\\\departments.txt");
        departmentComboBox = new JComboBox<>(departments);
        departmentComboBox.setBounds(200, 250, 200, 30);
        
        JLabel studentNameLabel = new JLabel("Student Name:");
        studentNameLabel.setBounds(50, 300, 200, 30);
        studentNameField = new JTextField();
        studentNameField.setBounds(200, 300, 200, 30);
        
        JLabel fathersNameLabel = new JLabel("Father's Name:");
        fathersNameLabel.setBounds(50, 350, 200, 30);
        fathersNameField = new JTextField();
        fathersNameField.setBounds(200, 350, 200, 30);
        
        JLabel mothersNameLabel = new JLabel("Mother's Name:");
        mothersNameLabel.setBounds(50, 400, 200, 30);
        mothersNameField = new JTextField();
        mothersNameField.setBounds(200, 400, 200, 30);
        
        JLabel nationalityLabel = new JLabel("Nationality:");
        nationalityLabel.setBounds(50, 450, 200, 30);
        String[] nationalities = {"Bangladeshi", "Foreigner"};
        nationalityComboBox = new JComboBox<>(nationalities);
        nationalityComboBox.setBounds(200, 450, 200, 30);

        JLabel dobLabel = new JLabel("Date of Birth:");
        dobLabel.setBounds(50, 500, 200, 30);
        dobField = new JTextField("DD/MM/YYYY");
        dobField.setBounds(200, 500, 200, 30);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 550, 200, 30);
        emailField = new JTextField();
        emailField.setBounds(200, 550, 200, 30);

        JLabel phoneLabel = new JLabel("Phone No:");
        phoneLabel.setBounds(50, 600, 200, 30);
        phoneField = new JTextField();
        phoneField.setBounds(200, 600, 200, 30);

        submitButton = new JButton("Submit");
        submitButton.setBounds(200, 650, 200, 30);
        submitButton.setBackground(new Color(100, 150, 250));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFont(new Font("Arial", Font.BOLD, 14));
        submitButton.setFocusPainted(false);
        submitButton.addActionListener(e -> addStudent());
		
		backButton = new JButton("Back");
        backButton.setBackground(new Color(100, 150, 250));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setFocusPainted(false);
        backButton.setBounds(200, 700, 200, 30);
        backButton.addActionListener(e -> {
            new Admin();
            this.dispose();
        });
        
        this.add(titleLabel);
        this.add(idLabel);
        this.add(idField);
        this.add(passwordLabel);
        this.add(passwordField);
        this.add(levelLabel);
        this.add(levelComboBox);
        this.add(departmentLabel);
        this.add(departmentComboBox);
        this.add(studentNameLabel);
        this.add(studentNameField);
        this.add(fathersNameLabel);
        this.add(fathersNameField);
        this.add(mothersNameLabel);
        this.add(mothersNameField);
        this.add(nationalityLabel);
        this.add(nationalityComboBox);
        this.add(dobLabel);
        this.add(dobField);
        this.add(emailLabel);
        this.add(emailField);
        this.add(phoneLabel);
        this.add(phoneField);
        this.add(submitButton);
		this.add (backButton);
        
    }

    private void addStudent() {
        String id = idField.getText();
        String password = passwordField.getText();
        String userRole ="Student";
        String studentName = studentNameField.getText();
        String fathersName = fathersNameField.getText();
        String mothersName = mothersNameField.getText();
        String nationality = (String) nationalityComboBox.getSelectedItem();
        String dob = dobField.getText();
        String email = emailField.getText();
        String phoneNumber = phoneField.getText();
        String level = (String) levelComboBox.getSelectedItem();
        String department = (String) departmentComboBox.getSelectedItem();

        if (id.isEmpty() || password.isEmpty()|| studentName.isEmpty() || fathersName.isEmpty()|| mothersName.isEmpty()|| nationality.isEmpty()|| dob.isEmpty() || email.isEmpty() || phoneNumber.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.");
        } else {
            try {
                User.saveUser(userRole, id, password);
                Students.saveStudent(id, studentName, fathersName, mothersName, nationality, dob, level, department, email, phoneNumber);
                JOptionPane.showMessageDialog(this, "Account created successfully!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving to the database.");
            }
        }
    }
}
