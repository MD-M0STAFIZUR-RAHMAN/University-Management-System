package admin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Admin extends JFrame implements ActionListener {

    JButton manageDepartmentButton, addAdminButton, addFacultyButton, manageCourseButton, addStudentButton, studentListButton, backButton ;

    public Admin() {
        ImageIcon image = new ImageIcon("..\\UMS\\image\\AIUB.png");
        this.setIconImage(image.getImage());
        this.setTitle("Admin Dashboard");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
		this.setVisible(true);

        getContentPane().setBackground(new Color(240, 255, 255));

        JLabel dashBoard = createLabel("Admin Dashboard", 300, 30);

        ImageIcon manageDepartmentIcon = new ImageIcon("..\\UMS\\image\\departmnent.png");
        ImageIcon addAdminIcon = new ImageIcon("..\\UMS\\image\\add_Admin.png");
        ImageIcon addFacultyIcon = new ImageIcon("..\\UMS\\image\\add_faculty.png");
        ImageIcon manageCourseIcon = new ImageIcon("..\\UMS\\image\\manage_course.png");
        ImageIcon addStudentIcon = new ImageIcon("..\\UMS\\image\\add_student.png");
        ImageIcon studentListIcon = new ImageIcon("..\\UMS\\image\\student_list.png");

        manageDepartmentButton = createButton(manageDepartmentIcon, 100, 100);
        JLabel manageDepartment = createLabel("Manage Department", 50, 210);
        
        addAdminButton = createButton(addAdminIcon, 100, 300);
        JLabel addAdmin = createLabel("Add Admin", 50, 410);
        
        addFacultyButton = createButton(addFacultyIcon, 350, 100);
        JLabel addFaculty = createLabel("Manage Faculty", 300, 210);
        
        manageCourseButton = createButton(manageCourseIcon, 350, 300);
        JLabel manageCourse = createLabel("Manage Course", 300, 410);
        
        addStudentButton = createButton(addStudentIcon, 600, 100);
        JLabel addStudent = createLabel("Add Student", 550, 210);
        
        studentListButton = createButton(studentListIcon, 600, 300);
        JLabel studentList = createLabel("Student List", 550, 410);
		
		backButton = new JButton("Back");
        backButton.setBackground(new Color(100, 150, 250));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setFocusPainted(false);
        backButton.setBounds(550, 460, 200, 30);
        backButton.addActionListener(this);
		this.add(backButton);
		
        
    }
    
    private JLabel createLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setForeground(new Color(0, 51, 102));
        label.setBounds(x, y, 200, 40);
        label.setHorizontalAlignment(JLabel.CENTER);
        this.add(label);
        return label;
    }

    private JButton createButton(ImageIcon icon, int x, int y) {
        JButton button = new JButton(icon);
        button.setBounds(x, y, 100, 100);
        button.setBackground(new Color(255, 255, 255));
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.CENTER);
        button.setFocusPainted(false);
        button.addActionListener(this);
        this.add(button);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == manageDepartmentButton) {
            new ManageDepartment();
			this.dispose();
        } else if (e.getSource() == addAdminButton) {
            new AddAdmin();
			this.dispose();
        } else if (e.getSource() == addFacultyButton) {
            new AddFaculty();
			this.dispose();
        } else if (e.getSource() == manageCourseButton) {
            new ManageCourse();
			this.dispose();
        } else if (e.getSource() == addStudentButton) {
            new AddStudent();
			this.dispose();
        } else if (e.getSource() == studentListButton) {
            new StudentList();
			this.dispose();
        } else if (e.getSource() == backButton) {
            new Login();
			this.dispose();
        } 
    }
}
