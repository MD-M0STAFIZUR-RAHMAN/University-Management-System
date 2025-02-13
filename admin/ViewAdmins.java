package admin;

import java.awt.*;
import java.io.*;
import javax.swing.*;

public class ViewAdmins extends JFrame {
    private DefaultListModel<String> adminListModel;
    private JList<String> adminList;

    public ViewAdmins() {
        this.setTitle("Admin Accounts");
        ImageIcon image = new ImageIcon("..\\UMS\\image\\AIUB.png");
        this.setIconImage(image.getImage());
        this.setSize(500, 400);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(240, 248, 255));

        JLabel titleLabel = new JLabel("Admin Accounts", JLabel.CENTER);
        titleLabel.setBounds(100, 10, 300, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(70, 70, 150));
        this.add(titleLabel);

        adminListModel = new DefaultListModel<>();
        adminList = new JList<>(adminListModel);
        JScrollPane scrollPane = new JScrollPane(adminList);
        scrollPane.setBounds(50, 60, 400, 200);
        this.add(scrollPane);

        JButton backButton = new JButton("Back");
        backButton.setBounds(150, 280, 200, 30);
        backButton.setBackground(new Color(100, 150, 250));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> this.dispose());
        this.add(backButton);

        loadAdminAccounts();
        this.setVisible(true);
    }

    private void loadAdminAccounts() {
        File file = new File("..\\UMS\\Data\\user.txt");

        if (!file.exists()) {
            JOptionPane.showMessageDialog(this, "Error: users.txt file not found.", "File Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean foundAdmins = false;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                if (data.length == 3 && "Admin".equalsIgnoreCase(data[0])) {
                    adminListModel.addElement("ID: " + data[1]);
                    foundAdmins = true;
                }
            }

            if (!foundAdmins) {
                adminListModel.addElement("No Admin accounts found.");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading admin accounts: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
