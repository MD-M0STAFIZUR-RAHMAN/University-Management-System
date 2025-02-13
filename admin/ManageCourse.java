package admin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ManageCourse extends JFrame implements ActionListener {
    private JComboBox<String> departmentComboBox, facultyComboBox, creditsComboBox;
    private JTextField courseNameField, sectionField;
    private JTable courseTable;
    private DefaultTableModel tableModel;
    private JButton addButton, deleteButton, backButton , newSemesterButton, statusButton ;
	public boolean status ;


    public ManageCourse() {
        this.setTitle("Manage Courses");
        this.setSize(1000, 900);
        ImageIcon image = new ImageIcon("..\\UMS\\image\\AIUB.png");
        this.setIconImage(image.getImage());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null);

        JLabel departmentLabel = new JLabel("Department:");
        departmentLabel.setBounds(30, 30, 100, 25);
        this.add(departmentLabel);

        departmentComboBox = new JComboBox<>(Methods.readFile("..\\\\UMS\\\\Data\\\\departments.txt"));
        departmentComboBox.setBounds(150, 30, 200, 25);
        this.add(departmentComboBox);

        JLabel courseNameLabel = new JLabel("Course Name:");
        courseNameLabel.setBounds(30, 70, 100, 25);
        this.add(courseNameLabel);

        courseNameField = new JTextField();
        courseNameField.setBounds(150, 70, 200, 25);
        this.add(courseNameField);

        JLabel creditsLabel = new JLabel("Credits:");
        creditsLabel.setBounds(30, 110, 100, 25);
        this.add(creditsLabel);

        creditsComboBox = new JComboBox<>(new String[]{"1", "2", "3"});
        creditsComboBox.setBounds(150, 110, 200, 25);
        this.add(creditsComboBox);

        JLabel sectionLabel = new JLabel("Section:");
        sectionLabel.setBounds(30, 150, 100, 25);
        this.add(sectionLabel);

        sectionField = new JTextField();
        sectionField.setBounds(150, 150, 200, 25);
        this.add(sectionField);

        JLabel facultyLabel = new JLabel("Faculty:");
        facultyLabel.setBounds(30, 190, 100, 25);
        this.add(facultyLabel);

        facultyComboBox = new JComboBox<>(Methods.readFile("..\\\\UMS\\\\Data\\\\FacultyIdName.txt"));
        facultyComboBox.setBounds(150, 190, 200, 25);
        this.add(facultyComboBox);

        addButton = new JButton("Add Course");
        addButton.setBounds(400, 80, 150, 30);
		addButton.setFont(new Font("Arial", Font.BOLD, 14));
		addButton.setBackground(new Color(100, 150, 250));
		addButton.setForeground(Color.WHITE);
		addButton.setFocusPainted(false);
        this.add(addButton);
        addButton.addActionListener(this);

        deleteButton = new JButton("Delete Course");
        deleteButton.setBounds(400, 130, 150, 30);
		deleteButton.setFont(new Font("Arial", Font.BOLD, 14));
		deleteButton.setBackground(new Color(100, 150, 250));
		deleteButton.setForeground(Color.WHITE);
		deleteButton.setFocusPainted(false);
        this.add(deleteButton);
        deleteButton.addActionListener(this);

        JLabel semesterLabel;
        try {
            semesterLabel = new JLabel("Semester : "+readSemester());
        } catch (IOException e) {
            semesterLabel = new JLabel("Error reading semester");
        }
		semesterLabel.setBounds(450, 10, 300, 50);
		semesterLabel.setFont(new Font("Arial", Font.BOLD, 20));
		semesterLabel.setForeground(new Color(70, 70, 150));
		this.add(semesterLabel);
		
		newSemesterButton = new JButton("New Semester");
		newSemesterButton.setBounds(400, 180, 150, 30);
		newSemesterButton.setFont(new Font("Arial", Font.BOLD, 14));
		newSemesterButton.setBackground(new Color(100, 150, 250));
		newSemesterButton.setForeground(Color.WHITE);
		newSemesterButton.setFocusPainted(false);
        newSemesterButton.addActionListener(this);
		this.add(newSemesterButton);
		
		JLabel enableCourseLabel = new JLabel("Add Course for Students:");
		enableCourseLabel.setFont(new Font("Arial", Font.BOLD, 20));
		enableCourseLabel.setForeground(new Color(70, 70, 150));
		enableCourseLabel.setBounds(600, 120, 250, 30);
		this.add(enableCourseLabel);

		JButton statusButton = new JButton("Enable");
		statusButton.setBounds(600, 160, 100, 30);
		statusButton.setFont(new Font("Arial", Font.BOLD, 14));
		statusButton.setBackground(new Color(100, 150, 250));
		statusButton.setForeground(Color.WHITE);
		statusButton.setFocusPainted(false);
        statusButton.addActionListener(this);
		this.add(statusButton);

		status = Methods.readStatus();
		statusButton.addActionListener(e -> {
            status = !status;
            statusButton.setText(status ? "Disable" : "Enable");
            saveStatus(status);
        });


        backButton = new JButton("Back");
        backButton.setBackground(new Color(100, 150, 250));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setFocusPainted(false);
        backButton.setBounds(800, 810, 150, 30);
        this.add(backButton);
        backButton.addActionListener(e -> { new Admin(); this.dispose(); });

        tableModel = new DefaultTableModel(new String[]{"Department", "Course Name", "Credits", "Section", "Faculty ID", "Faculty Name"}, 0);
        courseTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(courseTable);
        scrollPane.setBounds(30, 250, 920, 550);
        this.add(scrollPane);

        loadCoursesIntoTable();


        this.getContentPane().setBackground(new Color(230, 240, 255));
        this.setVisible(true);
    }
	
	private void saveStatus(boolean status) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("..\\\\UMS\\\\Data\\\\status.txt"))) {
            writer.write(Boolean.toString(status));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readSemester() throws IOException {
        String semesterName;

        try (BufferedReader reader = new BufferedReader(new FileReader("..\\\\UMS\\\\Data\\\\current_semester.txt"))) {
            semesterName = reader.readLine();
        }

        return semesterName;
    }

    private void loadCoursesIntoTable() {
        tableModel.setRowCount(0);
        String[][] courses = Courses.loadCourses();
        for (String[] course : courses) {
            tableModel.addRow(course);
        }
    }

    private void addCourse() {
        String department = (String) departmentComboBox.getSelectedItem();
        String courseName = courseNameField.getText().trim();
        int credits = Integer.parseInt((String) creditsComboBox.getSelectedItem());
        String section = sectionField.getText().trim();
        String selectedFaculty = (String) facultyComboBox.getSelectedItem();
		String semester = " ";
		
		try {
			semester = readSemester();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Error reading semester: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}

        String[] facultyDetails = selectedFaculty.split(",");
        String facultyId = facultyDetails[0].trim();
        String facultyName = facultyDetails[1].trim();

        if (!courseName.isEmpty() && !section.isEmpty()) {
            try {
                Courses.saveCourse(department, courseName, section, facultyId, facultyName, credits,semester);
                tableModel.addRow(new Object[]{department, courseName, credits, section, facultyId, facultyName});
                JOptionPane.showMessageDialog(this, "Course added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                courseNameField.setText("");
                sectionField.setText("");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error saving course: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteCourse() {
        int selectedRow = courseTable.getSelectedRow();
        if (selectedRow >= 0) {
            tableModel.removeRow(selectedRow);
            saveTableToFile();
            JOptionPane.showMessageDialog(this, "Course deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a course to delete.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveTableToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Courses.FILE_NAME))) {
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                writer.write(tableModel.getValueAt(i, 0) + "," +
                             tableModel.getValueAt(i, 1) + "," +
                             tableModel.getValueAt(i, 2) + "," +
                             tableModel.getValueAt(i, 3) + "," +
                             tableModel.getValueAt(i, 4) + "," +
                             tableModel.getValueAt(i, 5));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == newSemesterButton) {
			new NewSemesterFrame();
		} else if (e.getSource() == addButton) {
			addCourse();
		} else if (e.getSource() == deleteButton) {
			deleteCourse();
		}
	}
}
