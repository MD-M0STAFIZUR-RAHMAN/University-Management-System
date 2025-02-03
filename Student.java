import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;

class Student extends JFrame implements ActionListener { 
    private JComboBox<String> courseComboBox, sectionComboBox;
    private JTable marksTable;
    private DefaultTableModel tableModel;
    private JButton addCourseButton, backButton, dashboardButton;
    private String studentId;
	public boolean status ;

    public Student(String studentId) {
        this.studentId = studentId;

        setTitle("Student Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
		
		status = Methods.readStatus();

        String studentName = "Unknown";
        try {
            studentName = GetName(studentId);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Unable to load student name.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        JLabel WelcomeLabel = new JLabel("Welcome " + studentName);
        WelcomeLabel.setBounds(0, 20, 800, 30);
        WelcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        WelcomeLabel.setFont(new Font("Arial", Font.BOLD, 20)); 
        WelcomeLabel.setForeground(new Color(70, 70, 150));
        add(WelcomeLabel);
        
		JLabel semesterLabel;
        try {
            semesterLabel = new JLabel("Semester : "+ManageCourse.readSemester());
        } catch (IOException e) {
            semesterLabel = new JLabel("Error reading semester");
        }
		semesterLabel.setBounds(50, 50, 300, 50);
		semesterLabel.setFont(new Font("Arial", Font.BOLD, 20));
		semesterLabel.setForeground(new Color(70, 70, 150));
		this.add(semesterLabel);
		
        dashboardButton = new JButton();
        dashboardButton.setBounds(720, 30, 30, 30);
        dashboardButton.setIcon(new ImageIcon("E:/UMS/image/dashboard.png"));
        dashboardButton.setFocusPainted(false);
        dashboardButton.addActionListener(this);
        add(dashboardButton);

        addCourseButton = new JButton("Add Course");
        addCourseButton.setBounds(50, 150, 150, 30);
        addCourseButton.addActionListener(this);
        add(addCourseButton);

        tableModel = new DefaultTableModel(new String[]{"Course", "Section", "Attendance Mark", "Quiz 1", "Quiz 2", "Assignment", "Mid Exam", "Final Exam", "Total Marks", "CGPA"}, 0);
        marksTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(marksTable);
        scrollPane.setBounds(50, 200, 700, 300);
        add(scrollPane);

        backButton = new JButton("Back");
        backButton.setBounds(350, 520, 150, 30);
        backButton.addActionListener(e -> {new Login(); dispose();});
        add(backButton);
        
        loadMarks(studentId);
        
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addCourseButton) {
            if (status) {
                new AddCourse(studentId);
            } else {
                JOptionPane.showMessageDialog(this, "Course Management is disabled!", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        } else if (e.getSource() == dashboardButton) {
            new StudentDashboard(studentId);
        }
    }
    
    public static String GetName(String studentId) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("Students.txt"))) { 
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 10 && parts[0].equals(studentId)) { 
                    return parts[1]; 
                }
            }
        }
        return null;
    }

    private void addCourseToStudent(String course, String section) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("StudentCourses.txt", true))) {
            writer.write(studentId + "," + course + "," + section);
            writer.newLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void loadMarks(String studentId) {
        tableModel.setRowCount(0); 

        try (BufferedReader reader = new BufferedReader(new FileReader("StudentMarks.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 11 && data[0].equals(studentId)) {  
                    String[] rowData = Arrays.copyOfRange(data, 1, data.length);
                    tableModel.addRow(rowData);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class AddCourse extends JFrame implements ActionListener {
    private JComboBox<String> courseComboBox, sectionComboBox;
    private JButton addButton, deleteButton, cancelButton;
    private JTable courseTable;
    private DefaultTableModel tableModel;
    private String studentId;

    public AddCourse(String studentId) {
        this.studentId = studentId;

        setTitle("Add Course");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel courseLabel = new JLabel("Course:");
        courseLabel.setBounds(30, 30, 100, 30);
        add(courseLabel);

        courseComboBox = new JComboBox<>(getCourses());
        courseComboBox.setBounds(100, 30, 200, 30);
        courseComboBox.addActionListener(e ->  ());
        add(courseComboBox);

        JLabel sectionLabel = new JLabel("Section:");
        sectionLabel.setBounds(30, 70, 100, 30);
        add(sectionLabel);

        sectionComboBox = new JComboBox<>();
        sectionComboBox.setBounds(100, 70, 200, 30);
        updateSections();
        add(sectionComboBox);

        addButton = new JButton("Add Course");
        addButton.setBounds(320, 30, 150, 30);
        addButton.addActionListener(this);
        add(addButton);

        deleteButton = new JButton("Delete Course");
        deleteButton.setBounds(320, 70, 150, 30);
        deleteButton.addActionListener(this);
        add(deleteButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(320, 110, 150, 30);
        cancelButton.addActionListener(e -> dispose());
        add(cancelButton);

        tableModel = new DefaultTableModel(new String[]{"Student ID", "Course", "Section"}, 0);
        courseTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(courseTable);
        scrollPane.setBounds(30, 150, 540, 200);
        add(scrollPane);

        loadStudentCourses();
        setVisible(true);
    }

    private void loadMarks() {
        tableModel.setRowCount(0); 

        try (BufferedReader reader = new BufferedReader(new FileReader("StudentMarks.txt"))) {
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

        try (BufferedReader reader = new BufferedReader(new FileReader("current_semester.txt"))) {
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
		
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("StudentCourses.txt", true))) {
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
        File inputFile = new File("StudentCourses.txt");
        File tempFile = new File("StudentCourses_temp.txt");
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
        try (BufferedReader reader = new BufferedReader(new FileReader("StudentCourses.txt"))) {
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

class StudentDashboard extends JFrame implements ActionListener {
    private String studentId;
    private JTextField nameField, fathersNameField, mothersNameField, nationalityField, dobField, levelField, departmentField, emailField, phoneField;
    private JButton updateButton;

    public StudentDashboard(String studentId) {
        this.studentId = studentId;

        this.setTitle("Student Dashboard");
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null);

        JLabel titleLabel = new JLabel("Student Dashboard");
        titleLabel.setBounds(200, 20, 200, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        this.add(titleLabel);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 80, 150, 25);
        this.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(200, 80, 300, 25);
        this.add(nameField);

        JLabel fathersNameLabel = new JLabel("Father's Name:");
        fathersNameLabel.setBounds(50, 120, 150, 25);
        this.add(fathersNameLabel);

        fathersNameField = new JTextField();
        fathersNameField.setBounds(200, 120, 300, 25);
        this.add(fathersNameField);

        JLabel mothersNameLabel = new JLabel("Mother's Name:");
        mothersNameLabel.setBounds(50, 160, 150, 25);
        this.add(mothersNameLabel);

        mothersNameField = new JTextField();
        mothersNameField.setBounds(200, 160, 300, 25);
        this.add(mothersNameField);

        JLabel nationalityLabel = new JLabel("Nationality:");
        nationalityLabel.setBounds(50, 200, 150, 25);
        this.add(nationalityLabel);

        nationalityField = new JTextField();
        nationalityField.setBounds(200, 200, 300, 25);
        this.add(nationalityField);

        JLabel dobLabel = new JLabel("Date of Birth:");
        dobLabel.setBounds(50, 240, 150, 25);
        this.add(dobLabel);

        dobField = new JTextField();
        dobField.setBounds(200, 240, 300, 25);
        this.add(dobField);

        JLabel levelLabel = new JLabel("Level:");
        levelLabel.setBounds(50, 280, 150, 25);
        this.add(levelLabel);

        levelField = new JTextField();
        levelField.setBounds(200, 280, 300, 25);
        this.add(levelField);

        JLabel departmentLabel = new JLabel("Department:");
        departmentLabel.setBounds(50, 320, 150, 25);
        this.add(departmentLabel);

        departmentField = new JTextField();
        departmentField.setBounds(200, 320, 300, 25);
        this.add(departmentField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 360, 150, 25);
        this.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(200, 360, 300, 25);
        this.add(emailField);

        JLabel phoneLabel = new JLabel("Phone Number:");
        phoneLabel.setBounds(50, 400, 150, 25);
        this.add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(200, 400, 300, 25);
        this.add(phoneField);

        updateButton = new JButton("Update");
        updateButton.setBounds(250, 450, 100, 30);
        updateButton.addActionListener(this);
        this.add(updateButton);

        loadStudentData();

        this.setVisible(true);
    }

    private void loadStudentData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Students.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(studentId)) {
                    nameField.setText(data[1]);
                    fathersNameField.setText(data[2]);
                    mothersNameField.setText(data[3]);
                    nationalityField.setText(data[4]);
                    dobField.setText(data[5]);
                    levelField.setText(data[6]);
                    departmentField.setText(data[7]);
                    emailField.setText(data[8]);
                    phoneField.setText(data[9]);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateStudentData() {
        ArrayList<String> fileData = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("Students.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(studentId)) {
                    data[1] = nameField.getText();
                    data[2] = fathersNameField.getText();
                    data[3] = mothersNameField.getText();
                    data[4] = nationalityField.getText();
                    data[5] = dobField.getText();
                    data[6] = levelField.getText();
                    data[7] = departmentField.getText();
                    data[8] = emailField.getText();
                    data[9] = phoneField.getText();
                    line = String.join(",", data);
                }
                fileData.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Students.txt"))) {
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
            updateStudentData();
        }
    }
}


