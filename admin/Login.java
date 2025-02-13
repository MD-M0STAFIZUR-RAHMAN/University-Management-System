package admin;

import faculty.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;
import student.*;


public class Login extends JFrame {
    JLabel label1;
    JComboBox<String> userRoleComboBox;
    JTextField idField;
    JPasswordField passwordField;
    JButton submitButton, showButton, backButton;
    boolean isPasswordVisible = false;

    public Login() {
        ImageIcon image = new ImageIcon("..\\image\\AIUB.png");
        this.setIconImage(image.getImage());
        this.setTitle("Login");
        this.setSize(600, 400);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        getContentPane().setBackground(new Color(240, 240, 255));

        label1 = new JLabel("User Role:");
        label1.setBounds(50, 50, 200, 30);
        label1.setFont(new Font("Arial", Font.BOLD, 14));
        label1.setForeground(new Color(70, 70, 150));

        String[] userRole = {"Admin", "Faculty", "Student"};
        userRoleComboBox = new JComboBox<>(userRole);
        userRoleComboBox.setBounds(200, 50, 200, 30);
        userRoleComboBox.setBackground(Color.WHITE);
        userRoleComboBox.setForeground(new Color(50, 50, 50));

        JLabel label2 = new JLabel("ID:");
        label2.setBounds(50, 100, 200, 30);
        label2.setFont(new Font("Arial", Font.BOLD, 14));
        label2.setForeground(new Color(70, 70, 150));

        idField = new JTextField();
        idField.setBounds(200, 100, 200, 30);
        idField.setBackground(new Color(250, 250, 255));
        idField.setForeground(new Color(50, 50, 50));

        JLabel label3 = new JLabel("Password:");
        label3.setBounds(50, 150, 200, 30);
        label3.setFont(new Font("Arial", Font.BOLD, 14));
        label3.setForeground(new Color(70, 70, 150));

        passwordField = new JPasswordField();
        passwordField.setBounds(200, 150, 200, 30);
        passwordField.setBackground(new Color(250, 250, 255));
        passwordField.setForeground(new Color(50, 50, 50));

        showButton = new JButton(new ImageIcon("..\\UMS\\image\\lock.png"));
        showButton.setBounds(410, 150, 30, 30);
        showButton.setFocusPainted(false);
        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isPasswordVisible = !isPasswordVisible;
                if (isPasswordVisible) {
                    passwordField.setEchoChar((char) 0);
                    showButton.setIcon(new ImageIcon("..\\UMS\\image\\unlock.png"));
                } else {
                    passwordField.setEchoChar('*');
                    showButton.setIcon(new ImageIcon("..\\UMS\\image\\lock.png"));
                }
            }
        });

        submitButton = new JButton("Submit");
        submitButton.setBounds(200, 200, 200, 30);
        submitButton.setBackground(new Color(100, 150, 250));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFont(new Font("Arial", Font.BOLD, 14));
        submitButton.setFocusPainted(false);
        submitButton.addActionListener(e -> idAndPassCheck());

        backButton = new JButton("Back");
        backButton.setBackground(new Color(100, 150, 250));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setFocusPainted(false);
        backButton.setBounds(200, 250, 200, 30);
        backButton.addActionListener(e -> {
            new FrontPage();
            this.dispose();
        });

        this.add(label1);
        this.add(userRoleComboBox);
        this.add(label2);
        this.add(idField);
        this.add(label3);
        this.add(passwordField);
        this.add(showButton);
        this.add(submitButton);
        this.add(backButton);
        this.setVisible(true);
    }

    public void userclass() {
		String userRole = (String) userRoleComboBox.getSelectedItem();
		if (userRole.matches("Admin")) {
			new Admin();
			this.dispose();
		} else if (userRole.matches("Faculty")) {
			new Faculty(idField.getText());
			this.dispose();			
		} else if (userRole.matches("Student")) {
			new Student(idField.getText());
			this.dispose();
		}
	}


    private void idAndPassCheck() {
        String id = idField.getText();
        String userRole = (String) userRoleComboBox.getSelectedItem();
        String password = new String(passwordField.getPassword());

        if (userRole.equals("Admin")) {
            if (id.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields.");
            } else {
                try {
                    if (User.verifyUser(userRole, id, password)) {
                        JOptionPane.showMessageDialog(this, "Login successful as " + userRole);
                        userclass();
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid ID or Password.");
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error accessing the database.");
                }
            }
        } else {
            if (id.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields.");
            } else if (id.length() != 5 || !id.matches("[0-8]+")) {
                JOptionPane.showMessageDialog(this, "Invalid ID. Please enter a 5-digit number.");
            } else if (password.length() < 6 || password.length() > 10 || !password.matches("[a-zA-Z0-9]+")) {
                JOptionPane.showMessageDialog(this, "Password must be 6 to 10 characters long and contain only a-z, A-Z, and 0-9.");
            } else {
                try {
                    if (User.verifyUser(userRole, id, password)) {
                        JOptionPane.showMessageDialog(this, "Login successful as " + userRole);
                        userclass();
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid ID or Password.");
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error accessing the database.");
                }
            }
        }
    }
}
