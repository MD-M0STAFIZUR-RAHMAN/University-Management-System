package student;

import admin.*;
import faculty.*;.

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;

public class AddCourse extends JFrame implements ActionListener {
    private JComboBox<String> courseComboBox, sectionComboBox;
    private JButton addButton, deleteButton, cancelButton;
    private JTable courseTable;
    private DefaultTableModel tableModel;
    private String studentId;

    public AddCourse(String studentId) {
        this.studentId = studentId;

        setTitle("Add Course");
        setSize(600, 400);
		
		ImageIcon image = new ImageIcon("..\\UMS\\image\\AIUB.png");
        this.setIconImage(image.getImage());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel courseLabel = new JLabel("Course:");
		courseLabel.setBounds(30, 30, 100, 30);
		courseLabel.setFont(new Font("Arial", Font.BOLD, 14)); 
		courseLabel.setForeground(new Color(50, 50, 50));
		add(courseLabel);

		courseComboBox = new JComboBox<>(getCourses());
		courseComboBox.setBounds(100, 30, 200, 30);
		courseComboBox.setBackground(new Color(255, 255, 255)); 
		courseComboBox.setFont(new Font("Arial", Font.PLAIN, 12)); 
		courseComboBox.setForeground(new Color(50, 50, 50)); 
		courseComboBox.addActionListener(e -> {});
		add(courseComboBox);

		JLabel sectionLabel = new JLabel("Section:");
		sectionLabel.setBounds(30, 70, 100, 30);
		sectionLabel.setFont(new Font("Arial", Font.BOLD, 14)); 
		sectionLabel.setForeground(new Color(50, 50, 50));
		add(sectionLabel);

		sectionComboBox = new JComboBox<>();
		sectionComboBox.setBounds(100, 70, 200, 30);
		sectionComboBox.setBackground(new Color(255, 255, 255)); 
		sectionComboBox.setFont(new Font("Arial", Font.PLAIN, 12)); 
		sectionComboBox.setForeground(new Color(50, 50, 50)); 
		updateSections();
		add(sectionComboBox);

		addButton = new JButton("Add Course");
		addButton.setBounds(320, 30, 150, 30);
		addButton.setBackground(new Color(0, 123, 255)); 
		addButton.setForeground(Color.WHITE); 
		addButton.setFont(new Font("Arial", Font.BOLD, 14));
		addButton.setBorderPainted(false); 
		addButton.setFocusPainted(false); 
		addButton.addActionListener(this);
		add(addButton);

		deleteButton = new JButton("Delete Course");
		deleteButton.setBounds(320, 70, 150, 30);
		deleteButton.setBackground(new Color(255, 0, 0)); 
		deleteButton.setForeground(Color.WHITE); 
		deleteButton.setFont(new Font("Arial", Font.BOLD, 14)); 
		deleteButton.setBorderPainted(false);
		deleteButton.setFocusPainted(false);
		deleteButton.addActionListener(this);
		add(deleteButton);

		cancelButton = new JButton("Cancel");
		cancelButton.setBounds(320, 110, 150, 30);
		cancelButton.setBackground(new Color(169, 169, 169)); 
		cancelButton.setForeground(Color.WHITE); 
		cancelButton.setFont(new Font("Arial", Font.BOLD, 14)); 
		cancelButton.setBorderPainted(false);
		cancelButton.setFocusPainted(false);
		cancelButton.addActionListener(e -> dispose());
		add(cancelButton);

		tableModel = new DefaultTableModel(new String[]{"Student ID", "Course", "Section"}, 0);
		courseTable = new JTable(tableModel);
		courseTable.setFont(new Font("Arial", Font.PLAIN, 12)); 
		courseTable.setForeground(new Color(50, 50, 50)); 
		courseTable.setSelectionBackground(new Color(0, 123, 255)); 
		courseTable.setSelectionForeground(Color.WHITE); 
		JScrollPane scrollPane = new JScrollPane(courseTable);
		scrollPane.setBounds(30, 150, 540, 200);
		add(scrollPane);

        loadStudentCourses();
        setVisible(true);
    }

    private void loadMarks() {
        tableModel.setRowCount(0); 

        try (BufferedReader reader = new BufferedReader(new FileReader("..\\UMS\\Data\\StudentMarks.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == tableModel.getColumnCount() && data[0].equals(studentId)) {
                    tableModel.addRow(data);
                }
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Marks file not found.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateSections() {
        sectionComboBox.removeAllItems();
        String selectedCourse = (String) courseComboBox.getSelectedItem();
        if (selectedCourse != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(Courses.FILE_NAME))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data[1].equals(selectedCourse)) {
                        sectionComboBox.addItem(data[3]);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String course = (String) courseComboBox.getSelectedItem();
            String section = (String) sectionComboBox.getSelectedItem();
            if (!isCourseAlreadyAdded(course, section)) {
                addCourseToStudent(course, section);
                JOptionPane.showMessageDialog(this, "Course added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "You have already added this course.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == deleteButton) {
            deleteSelectedCourse();
        }
    }
	
	public static String readSemester() throws IOException {
        String semesterName;

        try (BufferedReader reader = new BufferedReader(new FileReader("..\\UMS\\Data\\current_semester.txt"))) {
            semesterName = reader.readLine();
        }

        return semesterName;
    }

    private boolean isCourseAlreadyAdded(String course, String section) {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (tableModel.getValueAt(i, 1).equals(course) && tableModel.getValueAt(i, 2).equals(section)) {
                return true;
            }
        }
        return false;
    }

    private void addCourseToStudent(String course, String section) {
		String semester = " ";
		
		try {
			semester = ManageCourse.readSemester();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Error reading semester: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("..\\UMS\\Data\\StudentCourses.txt", true))) {
            writer.write(studentId + "," + course + "," + section + "," + semester);
            writer.newLine();
            tableModel.addRow(new Object[]{studentId, course, section});
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void deleteSelectedCourse() {
        int selectedRow = courseTable.getSelectedRow();
        if (selectedRow >= 0) {
            String studentId = (String) tableModel.getValueAt(selectedRow, 0);
            String course = (String) tableModel.getValueAt(selectedRow, 1);
            String section = (String) tableModel.getValueAt(selectedRow, 2);
            tableModel.removeRow(selectedRow);
            deleteCourseFromFile(studentId, course, section);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a course to delete.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteCourseFromFile(String studentId, String course, String section) {
        File inputFile = new File("..\\UMS\\Data\\StudentCourses.txt");
        File tempFile = new File("..\\UMS\\Data\\StudentCourses_temp.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (!(data[0].equals(studentId) && data[1].equals(course) && data[2].equals(section))) {
                    writer.write(line);
                    writer.newLine();
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        inputFile.delete();
        tempFile.renameTo(inputFile);
    }

    private void loadStudentCourses() {
        try (BufferedReader reader = new BufferedReader(new FileReader("..\\UMS\\Data\\StudentCourses.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(studentId)) {
                    tableModel.addRow(data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String[] getCourses() {
        ArrayList<String> courses = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(Courses.FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (!courses.contains(data[1])) {
                    courses.add(data[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return courses.toArray(new String[0]);
    }
}
