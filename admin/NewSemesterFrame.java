package admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.Border;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class NewSemesterFrame extends JFrame {
	
    private JTextField semesterTextField;

    public NewSemesterFrame() {
        this.setTitle("New Semester");
        this.setSize(300, 150);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new FlowLayout());
		this.setVisible(true);

        JLabel semesterLabel = new JLabel("Enter Semester Name:");
        semesterTextField = new JTextField(20);
        JButton saveButton = new JButton("Save");

        this.add(semesterLabel);
        this.add(semesterTextField);
        this.add(saveButton);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String semesterName = semesterTextField.getText().trim();
                if (!semesterName.isEmpty()) {
                    try {
                        Semester.saveSemester(semesterName);
                        CurrentSemester.saveSemester(semesterName);
                        JOptionPane.showMessageDialog(NewSemesterFrame.this, "Semester saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(NewSemesterFrame.this, "Error saving semester.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(NewSemesterFrame.this, "Please enter a valid semester name.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }
}
