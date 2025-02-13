package admin;

import java.awt.*;
import java.io.*;
import javax.swing.*;

public class AddAdmin extends JFrame {
    private JTextField idField;
    private JPasswordField passwordField;

    public AddAdmin() {
        this.setTitle("Add Admin");
        ImageIcon image = new ImageIcon("..\\UMS\\image\\AIUB.png");
        this.setIconImage(image.getImage());
        this.setSize(450, 350);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(240, 248, 255));

        JLabel titleLabel = new JLabel("Add New Admin");
        titleLabel.setBounds(140, 10, 200, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(70, 70, 150));
        this.add(titleLabel);

        JLabel idLabel = new JLabel("Admin ID:");
        idLabel.setBounds(50, 60, 100, 30);
        this.add(idLabel);

        idField = new JTextField();
        idField.setBounds(150, 60, 200, 30);
        this.add(idField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 110, 100, 30);
        this.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 110, 200, 30);
        this.add(passwordField);

        JButton saveButton = new JButton("Save");
        button(saveButton, 150, 160 , 100, 150, 250);
        saveButton.addActionListener(e -> addAdmin());
        this.add(saveButton);

        JButton viewAdminsButton = new JButton("View Admins");
        button(viewAdminsButton, 150, 210 ,100, 150, 250 );
        viewAdminsButton.addActionListener(e -> new ViewAdmins());
        this.add(viewAdminsButton);

        JButton backButton = new JButton("Back");
        button(backButton, 150, 260, 100, 150, 250);
        backButton.addActionListener(e -> {
            new Admin();
            this.dispose();
        });
        this.add(backButton);

        this.setVisible(true);
    }

    private void button(JButton button, int x, int y , int a, int b ,int c) {
        button.setBounds(x, y, 200, 30);
        button.setBackground(new Color(a, b, c));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
    }

    private void addAdmin() {
        String id = idField.getText();
        String password = new String(passwordField.getPassword());
        String userRole = "Admin";

        if (id.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                User.saveUser(userRole, id, password);
                JOptionPane.showMessageDialog(this, "Account created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                idField.setText("");
                passwordField.setText("");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving to the database.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
