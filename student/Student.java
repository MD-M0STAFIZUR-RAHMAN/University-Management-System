package student;

import admin.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;



public class Student extends JFrame implements ActionListener { 
    private JComboBox<String> courseComboBox, sectionComboBox;
    private JTable marksTable;
    private DefaultTableModel tableModel;
    private JButton addCourseButton, backButton, dashboardButton, viewResultsButton;
    private String studentId;
    public boolean status;

    public Student(String studentId) {
        this.studentId = studentId;

        setTitle("Student Dashboard");
		
		ImageIcon image = new ImageIcon("..\\UMS\\image\\AIUB.png");
        this.setIconImage(image.getImage());
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE); 
        
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
        WelcomeLabel.setFont(new Font("Arial", Font.BOLD, 24)); 
        WelcomeLabel.setForeground(new Color(70, 70, 150)); 
        add(WelcomeLabel);
        
        JLabel semesterLabel;
        try {
            semesterLabel = new JLabel("Semester: " + ManageCourse.readSemester());
        } catch (IOException e) {
            semesterLabel = new JLabel("Error reading semester");
        }
        semesterLabel.setBounds(50, 50, 300, 50);
        semesterLabel.setFont(new Font("Arial", Font.BOLD, 18));
        semesterLabel.setForeground(new Color(70, 70, 150));
        this.add(semesterLabel);

        dashboardButton = new JButton();
        dashboardButton.setBounds(720, 30, 30, 30);
        dashboardButton.setIcon(new ImageIcon("..\\UMS\\image\\dashboard.png"));
        dashboardButton.setFocusPainted(false);
        dashboardButton.addActionListener(this);
        dashboardButton.setBackground(new Color(200, 200, 255)); 
        add(dashboardButton);

        addCourseButton = new JButton("Add Course");
        addCourseButton.setBounds(50, 150, 150, 30);
        addCourseButton.setFont(new Font("Arial", Font.BOLD, 14));
        addCourseButton.setBackground(new Color(100, 180, 255)); 
        addCourseButton.setForeground(Color.WHITE);
        addCourseButton.addActionListener(this);
        add(addCourseButton);

        viewResultsButton = new JButton("View Results");
        viewResultsButton.setBounds(220, 150, 150, 30);
        viewResultsButton.setFont(new Font("Arial", Font.BOLD, 14));
        viewResultsButton.setBackground(new Color(100, 180, 255)); 
        viewResultsButton.setForeground(Color.WHITE);
        viewResultsButton.addActionListener(this);
        add(viewResultsButton);

        tableModel = new DefaultTableModel(new String[]{"Course", "Section", "Attendance Mark", "Quiz 1", "Quiz 2", "Assignment", "Mid Exam", "Final Exam", "Total Marks", "CGPA"}, 0);
        marksTable = new JTable(tableModel);
        marksTable.setBackground(new Color(255, 255, 255)); 
        marksTable.setForeground(new Color(70, 70, 150));
        JScrollPane scrollPane = new JScrollPane(marksTable);
        scrollPane.setBounds(50, 200, 700, 300);
        add(scrollPane);

        backButton = new JButton("Back");
        backButton.setBounds(600, 520, 150, 30);
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(new Color(100,150, 250));
        backButton.addActionListener(e -> { new Login(); this.dispose(); });
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
        } else if (e.getSource() == viewResultsButton) {
            new ViewResultsFrame(studentId);
        }
    }
    
    public static String GetName(String studentId) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("..\\UMS\\Data\\Students.txt"))) { 
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

    private void loadMarks(String studentId) {
        tableModel.setRowCount(0); 

        String semester;
        try {
            semester = ManageCourse.readSemester();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading semester.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader("..\\UMS\\Data\\StudentMarks.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 12 && data[0].equals(studentId) && data[11].equals(semester)) {  
                    String[] rowData = Arrays.copyOfRange(data, 1, data.length - 1);
                    tableModel.addRow(rowData);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
