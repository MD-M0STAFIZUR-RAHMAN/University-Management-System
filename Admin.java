import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

class user {
    private static final String FILE_PATH = "user.txt";

    public static void saveUser(String userRole, String id, String password) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(userRole + "," + id + "," + password);
            writer.newLine();
        }
    }

    public static boolean verifyUser(String userRole, String id, String password) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3 && parts[0].equals(userRole) && parts[1].equals(id) && parts[2].equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }
}



class MyFrame extends JFrame implements ActionListener {
    private JButton button1, button2;

    MyFrame() {
        ImageIcon image = new ImageIcon("E:/UMS/image/AIUB.png");
        this.setIconImage(image.getImage());
        this.setTitle("University Management System");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setSize(1230, 920);
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        Container c = this.getContentPane();
        c.setBackground(Color.WHITE);

        add();
        this.setVisible(true);
    }

    private void add() {
        ImageIcon image = new ImageIcon("E:/UMS/image/AIUB.png");
        ImageIcon image2 = new ImageIcon("E:/UMS/image/AIUB2.jpg");
		
        Font titleFont = new Font("Arial", Font.BOLD, 22);
        Font buttonFont = new Font("Arial", Font.BOLD, 16);
		
		JLabel label1 = new JLabel();
        label1.setIcon(image);
        label1.setHorizontalTextPosition(JLabel.CENTER);
        label1.setVerticalTextPosition(JLabel.BOTTOM);
        label1.setHorizontalAlignment(JLabel.CENTER);
        label1.setVerticalAlignment(JLabel.CENTER);
        label1.setBounds(740, 100, 460, 230);

        JLabel label2 = new JLabel("Welcome to AIUB");
        label2.setFont(titleFont);
        label2.setHorizontalAlignment(JLabel.CENTER);
        label2.setForeground(new Color(0, 51, 102));
        label2.setBounds(740, 50, 460, 30);

        JLabel label3 = new JLabel("American International University-Bangladesh");
        label3.setFont(titleFont);
        label3.setForeground(new Color(0, 51, 102)); 
        label3.setHorizontalAlignment(JLabel.CENTER);
        label3.setBounds(725, 420, 500, 30);

        JLabel label4 = new JLabel();
        label4.setIcon(image2);
        label4.setHorizontalTextPosition(JLabel.CENTER);
        label4.setVerticalTextPosition(JLabel.BOTTOM);
        label4.setHorizontalAlignment(JLabel.CENTER);
        label4.setVerticalAlignment(JLabel.CENTER);
        label4.setBounds(10, 10, 720, 850);

        button1 = new JButton("Log In");
        button1.setBounds(890, 550, 160, 30);
        button1.setBackground(new Color(100, 150, 250));
        button1.setForeground(Color.WHITE);
        button1.setFont(buttonFont);
		button1.setFocusPainted(false);
        button1.addActionListener(this);


        this.add(label1);
        this.add(label2);
        this.add(label3);
        this.add(label4);
        this.add(button1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            new Login();
        }
    }
}

class Login extends JFrame {
    JLabel label1;
    JComboBox<String> userRoleComboBox;
    JTextField idField;
    JPasswordField passwordField;
    JButton submitButton;
    JButton showButton;
    boolean isPasswordVisible = false;

    Login() {
        ImageIcon image = new ImageIcon("E:/UMS/image/AIUB.png");
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

        showButton = new JButton(new ImageIcon("E:/UMS/image/lock.png"));
        showButton.setBounds(410, 150, 30, 30);
        showButton.setFocusPainted(false);
        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isPasswordVisible = !isPasswordVisible;
                if (isPasswordVisible) {
                    passwordField.setEchoChar((char) 0);
                    showButton.setIcon(new ImageIcon("E:/UMS/image/unlock.png"));
                } else {
                    passwordField.setEchoChar('*');
                    showButton.setIcon(new ImageIcon("E:/UMS/image/lock.png"));
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

        this.add(label1);
        this.add(userRoleComboBox);
        this.add(label2);
        this.add(idField);
        this.add(label3);
        this.add(passwordField);
        this.add(showButton);
        this.add(submitButton);

        this.setVisible(true);
    }

    private void idAndPassCheck() {
        String id = idField.getText();
        String userRole = (String) userRoleComboBox.getSelectedItem();
        String password = new String(passwordField.getPassword());

        if (id.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.");
        } else if (id.length() != 5 || !id.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Invalid ID. Please enter a 5-digit number.");
        } else if (password.length() < 6 || password.length() > 10 || !password.matches("[a-zA-Z0-9]+")) {
            JOptionPane.showMessageDialog(this, "Password must be 6 to 10 characters long and contain only a-z, A-Z, and 0-9.");
        } else {
            try {
                if (user.verifyUser(userRole, id, password)) {
                    JOptionPane.showMessageDialog(this, "Login successful as " + userRole);
					new Admin();
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid credentials.");
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error accessing the database.");
            }
        }
    }
}

class Admin extends JFrame implements ActionListener {

    JButton addCourseButton, courseListButton, addFacultyButton, facultyListButton, addStudentButton, studentListButton;

    Admin() {
        ImageIcon image = new ImageIcon("E:/UMS/image/AIUB.png");
        this.setIconImage(image.getImage());
        this.setTitle("Admin Dashboard");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        getContentPane().setBackground(new Color(240, 240, 255));

        JLabel dashBoard = createLabel("Admin Dashboard", 300, 30);

        ImageIcon addCourseIcon = new ImageIcon("E:/UMS/image/add_course.png");
        ImageIcon courseListIcon = new ImageIcon("E:/UMS/image/course_list.png");
        ImageIcon addFacultyIcon = new ImageIcon("E:/UMS/image/add_faculty.png");
        ImageIcon facultyListIcon = new ImageIcon("E:/UMS/image/faculty_list.png");
        ImageIcon addStudentIcon = new ImageIcon("E:/UMS/image/add_student.png");
        ImageIcon studentListIcon = new ImageIcon("E:/UMS/image/student_list.png");

        addCourseButton = createButton(addCourseIcon, 50, 100);
        JLabel addCourseLabel = createLabel("Add Course", 50, 260);
        
        courseListButton = createButton(courseListIcon, 50, 350);
        JLabel courseListLabel = createLabel("Course List", 50, 510);
        
        addFacultyButton = createButton(addFacultyIcon, 300, 100);
        JLabel addFacultyLabel = createLabel("Add Faculty", 300, 260);
        
        facultyListButton = createButton(facultyListIcon, 300, 350);
        JLabel facultyListLabel = createLabel("Faculty List", 300, 510);
        
        addStudentButton = createButton(addStudentIcon, 550, 100);
        JLabel addStudentLabel = createLabel("Add Student", 550, 260);
        
        studentListButton = createButton(studentListIcon, 550, 350);
        JLabel studentListLabel = createLabel("Student List", 550, 510);

        this.setVisible(true);
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
        button.setBounds(x, y, 200, 150);
        button.setBackground(new Color(204, 255, 255));
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.CENTER);
        button.setFocusPainted(false);
        button.addActionListener(this);
        this.add(button);
        
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addCourseButton) {
            new AddCourse();
        } else if (e.getSource() == courseListButton) {
            new CourseList();
        } else if (e.getSource() == addFacultyButton) {
            new AddFaculty();
        } else if (e.getSource() == facultyListButton) {
            new FacultyList();
        } else if (e.getSource() == addStudentButton) {
            new AddStudent();
        } else if (e.getSource() == studentListButton) {
            new StudentList();
        }
    }
}

class AddCourse {
	
}

class CourseList {
	
}

class AddFaculty {
	
}

class FacultyList {
	
}

class AddStudent  extends JFrame {
    JTextField idField, passwordField, studentNameField, fathersNameField, mothersNameField, nationalityField, dobField;
    JComboBox<String> levelComboBox, departmentComboBox;
    JButton submitButton;

    AddStudent() {
        ImageIcon image = new ImageIcon("E:/UMS/image/AIUB.png");
        this.setIconImage(image.getImage());
        this.setTitle("Create Account...");
        this.setSize(600, 800);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(240, 248, 255));
        
        JLabel titleLabel = new JLabel("Create Student Account");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setForeground(new Color(0, 102, 204));
        titleLabel.setBounds(150, 50, 300, 30);
		
		JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(50, 100, 200, 30);
        idField = new JTextField();
        idField.setBounds(200, 100, 200, 30);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 150, 200, 30);
        passwordField = new JPasswordField();
        passwordField.setBounds(200, 150, 200, 30);
        
        JLabel levelLabel = new JLabel("Level:");
        levelLabel.setBounds(50, 200, 200, 30);
        String[] levels = {"Undergraduate", "Postgraduate"};
        levelComboBox = new JComboBox<>(levels);
        levelComboBox.setBounds(200, 200, 200, 30);

        JLabel departmentLabel = new JLabel("Department:");
        departmentLabel.setBounds(50, 250, 200, 30);
        String[] departments = {"CSE", "EEE", "BBA", "LAW"};
        departmentComboBox = new JComboBox<>(departments);
        departmentComboBox.setBounds(200, 250, 200, 30);

        JLabel studentNameLabel = new JLabel("Student Name:");
        studentNameLabel.setBounds(50, 300, 200, 30);
        studentNameField = new JTextField();
        studentNameField.setBounds(200, 300, 200, 30);

        JLabel fathersNameLabel = new JLabel("Father's Name:");
        fathersNameLabel.setBounds(50, 350, 200, 30);
        fathersNameField = new JTextField();
        fathersNameField.setBounds(200, 350, 200, 30);

        JLabel mothersNameLabel = new JLabel("Mother's Name:");
        mothersNameLabel.setBounds(50, 400, 200, 30);
        mothersNameField = new JTextField();
        mothersNameField.setBounds(200, 400, 200, 30);

        JLabel nationalityLabel = new JLabel("Nationality:");
        nationalityLabel.setBounds(50, 450, 200, 30);
        nationalityField = new JTextField();
        nationalityField.setBounds(200, 450, 200, 30);

        JLabel dobLabel = new JLabel("Date of Birth:");
        dobLabel.setBounds(50, 500, 200, 30);
        dobField = new JTextField();
        dobField.setBounds(200, 500, 200, 30);

        submitButton = new JButton("Submit");
        submitButton.setBounds(200, 550, 200, 30);
		submitButton.setBackground(new Color(100, 150, 250));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFont(new Font("Arial", Font.BOLD, 14));
        submitButton.setFocusPainted(false);
        submitButton.addActionListener(e -> addStudent());
        
        this.add(titleLabel);
		this.add(idLabel);
        this.add(idField);
        this.add(passwordLabel);
        this.add(passwordField);
        this.add(levelLabel);
        this.add(levelComboBox);
        this.add(departmentLabel);
        this.add(departmentComboBox);
        this.add(studentNameLabel);
        this.add(studentNameField);
        this.add(fathersNameLabel);
        this.add(fathersNameField);
        this.add(mothersNameLabel);
        this.add(mothersNameField);
        this.add(nationalityLabel);
        this.add(nationalityField);
        this.add(dobLabel);
        this.add(dobField);
        this.add(submitButton);

        this.setVisible(true);
    }
	
	private void addStudent() {
        String id = idField.getText();
        String password = passwordField.getText();
        String userRole ="Student";
		String studentName = studentNameField.getText();
		String fathersName = fathersNameField.getText();
		String mothersName = mothersNameField.getText();
		String nationality = nationalityField.getText();
		String dob = dobField.getText();

        if (id.isEmpty() || password.isEmpty()|| studentName.isEmpty() || fathersName.isEmpty()|| mothersName.isEmpty()|| nationality.isEmpty()|| dob.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.");
        } else {
            try {
                user.saveUser(userRole, id, password);
                JOptionPane.showMessageDialog(this, "Account created successfully!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving to the database.");
            }
        }
		
    }
}

class StudentList {
	
}


class Main {
    public static void main(String[] args) {
        new MyFrame();
    }
}
