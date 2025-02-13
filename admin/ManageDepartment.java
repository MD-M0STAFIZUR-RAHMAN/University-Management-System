package admin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

public class ManageDepartment extends JFrame implements ActionListener {
    private JComboBox<String> departmentComboBox;
    private JTextField departmentTextField;
    private ArrayList<String> departments;
    private JButton backButton;

    public ManageDepartment() {
        ImageIcon image = new ImageIcon("..\\UMS\\image\\AIUB.png");
        this.setIconImage(image.getImage());
        this.setTitle("Manage Department");
        this.setSize(500, 360);
        this.setLayout(null);
		this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getContentPane().setBackground(new Color(240, 248, 255)); 
        this.setVisible(true);
        
        this.departments = new ArrayList<>();

        JLabel titleLabel = new JLabel("Manage Departments");
        titleLabel.setBounds(150, 10, 300, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(0, 102, 204));
        this.add(titleLabel);

        JLabel nameLabel = new JLabel("Department Name:");
        nameLabel.setBounds(30, 60, 150, 25);
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        this.add(nameLabel);

        departmentTextField = new JTextField();
        departmentTextField.setBounds(170, 60, 200, 25);
        departmentTextField.setFont(new Font("Arial", Font.PLAIN, 14));
        this.add(departmentTextField);

        JButton addButton = new JButton("Add Department");
        addButton.setBounds(170, 100, 200, 30);
        addButton.setFont(new Font("Arial", Font.BOLD, 14));
        addButton.setBackground(new Color(0, 153, 76)); 
        addButton.setForeground(Color.WHITE);
        this.add(addButton);
        
        JButton deleteButton = new JButton("Delete Department");
        deleteButton.setBounds(170, 200, 200, 30);
        deleteButton.setFont(new Font("Arial", Font.BOLD, 14));
        deleteButton.setBackground(new Color(204, 0, 0)); 
        deleteButton.setForeground(Color.WHITE);
        this.add(deleteButton);
		
        JLabel comboBoxLabel = new JLabel("Existing Departments:");
        comboBoxLabel.setBounds(30, 150, 200, 25);
        comboBoxLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        this.add(comboBoxLabel);
         
        String[] departments = Methods.readFile("..\\\\UMS\\\\Data\\\\departments.txt");
        departmentComboBox = new JComboBox<>(departments);
        departmentComboBox.setBounds(170, 150, 200, 25);
        departmentComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        this.add(departmentComboBox);
		
		backButton = new JButton("Back");
        backButton.setBackground(new Color(100, 150, 250));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setFocusPainted(false);
        backButton.setBounds(170, 250, 200, 30);
        backButton.addActionListener(this);
		this.add(backButton);

        addButton.addActionListener(e -> addDepartment());
        deleteButton.addActionListener(e -> deleteDepartment());
		backButton.addActionListener(e -> { new Admin(); this.dispose(); });
    }

    private void addDepartment() {
        String department = departmentTextField.getText().trim();
        if (!department.isEmpty()) {
            departments.add(department);
            saveInFile(department);
            departmentTextField.setText("");
            departmentComboBox.addItem(department);
            JOptionPane.showMessageDialog(this, "Department added successfully!", 
                "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a department name.", 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void deleteDepartment() {
        String selectedDepartment = (String) departmentComboBox.getSelectedItem();
        if (selectedDepartment != null) {
            departments.remove(selectedDepartment);
            updateFile();
            departmentComboBox.removeItem(selectedDepartment);
            JOptionPane.showMessageDialog(this, "Department deleted successfully!", 
                "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a department to delete.", 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveInFile(String department) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("..\\\\UMS\\\\Data\\\\departments.txt", true))) {
            writer.write(department);
            writer.newLine();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving department: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("..\\UMS\\Data\\departments.txt"))) {
            for (String department : departments) {
                writer.write(department);
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error updating department file: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
