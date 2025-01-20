import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.Border;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

class Methods {
	
	public static String[] readFile(String fileName) {
        List<String> List = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                List.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return List.toArray(new String[0]);
    }
}

class User {
    private static final String FILE_NAME = "user.txt";

    public static void saveUser(String userRole, String id, String password) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(userRole + "," + id + "," + password);
            writer.newLine();
        }
    }

    public static boolean verifyUser(String userRole, String id, String password) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
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

class Students {

    private static final String FILE_NAME = "Students.txt";

    public static void saveStudent(String id, String studentName, String fathersName, String mothersName, String nationality, String dob, String level, String department, String email, String phoneNumber) 
        throws IOException {
        String studentData = id + "," + studentName + "," + fathersName + "," + mothersName + "," + nationality + "," + dob + "," + level + "," + department + "," + email + "," + phoneNumber;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(studentData);
            writer.newLine();
        }
    }
}

class Faculties {
    private static final String FILE_NAME = "Faculties.txt";

    public static void saveFaculty(String id, String password, String name, String gender, String nationality, String dob, String phone, String email, String[] programs, String[] cgpas, String department) throws IOException {
        StringBuilder facultyData = new StringBuilder(id + "," + password + "," + name + "," + gender + "," + nationality + "," + dob + "," + phone + "," + email + "," + department);
        for (int i = 0; i < programs.length; i++) {
            facultyData.append(",").append(programs[i]).append(",").append(cgpas[i]);
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(facultyData.toString());
            writer.newLine();
        }
    }
}

class FacultyIdName  {
    private static final String FILE_NAME = "FacultyIdName.txt";

    public static void saveFacultyIdName(String id, String name) throws IOException {
        StringBuilder facultyData = new StringBuilder(id + "," + name );
		
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(facultyData.toString());
            writer.newLine();
        }
    }
}

class Courses {
    public static final String FILE_NAME = "Courses.txt";

    public static void saveCourse(String department, String courseName, String section, String facultyId, String facultyName, int credits) throws IOException {
        String courseData = department + "," + courseName + "," + credits + "," + section + "," + facultyId + "," + facultyName;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(courseData);
            writer.newLine();
        }
    }

    public static String[][] loadCourses() {
        ArrayList<String[]> courseList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                courseList.add(line.split(","));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return courseList.toArray(new String[0][0]);
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
			this.dispose();
        }
    }
}

class Login extends JFrame {
    JLabel label1;
    JComboBox<String> userRoleComboBox;
    JTextField idField;
    JPasswordField passwordField;
    JButton submitButton, showButton, backButton;
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

        backButton = new JButton("Back");
        backButton.setBackground(new Color(100, 150, 250));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setFocusPainted(false);
        backButton.setBounds(200, 250, 200, 30);
        backButton.addActionListener(e -> {
            new MyFrame();
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
		} else if (userRole.matches("Faculty")) {
			new Faculty(idField.getText()); // Pass faculty ID
		} else if (userRole.matches("Student")) {
			new Student(idField.getText()); // Pass student ID
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
                        JOptionPane.showMessageDialog(this, "Invalid credentials.");
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error accessing the database.");
                }
            }
        } else {
            if (id.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields.");
            } else if (id.length() != 5 || !id.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "Invalid ID. Please enter a 5-digit number.");
            } else if (password.length() < 6 || password.length() > 10 || !password.matches("[a-zA-Z0-9]+")) {
                JOptionPane.showMessageDialog(this, "Password must be 6 to 10 characters long and contain only a-z, A-Z, and 0-9.");
            } else {
                try {
                    if (User.verifyUser(userRole, id, password)) {
                        JOptionPane.showMessageDialog(this, "Login successful as " + userRole);
                        userclass();
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid credentials.");
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error accessing the database.");
                }
            }
        }
    }
}

class Admin extends JFrame implements ActionListener {

    JButton manageDepartmentButton, addAdminButton, addFacultyButton, manageCourseButton, addStudentButton, studentListButton, backButton;

    Admin() {
        ImageIcon image = new ImageIcon("E:/UMS/image/AIUB.png");
        this.setIconImage(image.getImage());
        this.setTitle("Admin Dashboard");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
		this.setVisible(true);

        getContentPane().setBackground(new Color(240, 255, 255));

        JLabel dashBoard = createLabel("Admin Dashboard", 300, 30);

        ImageIcon manageDepartmentIcon = new ImageIcon("E:/UMS/image/departmnent.png");
        ImageIcon addAdminIcon = new ImageIcon("E:/UMS/image/add_Admin.png");
        ImageIcon addFacultyIcon = new ImageIcon("E:/UMS/image/add_faculty.png");
        ImageIcon manageCourseIcon = new ImageIcon("E:/UMS/image/manage_course.png");
        ImageIcon addStudentIcon = new ImageIcon("E:/UMS/image/add_student.png");
        ImageIcon studentListIcon = new ImageIcon("E:/UMS/image/student_list.png");

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
        backButton.setBounds(550, 510, 200, 30);
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

class ManageDepartment extends JFrame implements ActionListener {
    private JComboBox<String> departmentComboBox;
    private JTextField departmentTextField;
    private ArrayList<String> departments;
    private JButton backButton;

    public ManageDepartment() {
        ImageIcon image = new ImageIcon("E:/UMS/image/AIUB.png");
        this.setIconImage(image.getImage());
        this.setTitle("Manage Department");
        this.setLayout(null);
        this.setSize(500, 360);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getContentPane().setBackground(new Color(240, 248, 255)); 
        this.setVisible(true);
        
        this.departments = new ArrayList<>();

        JLabel titleLabel = new JLabel("Manage Departments");
        titleLabel.setBounds(150, 10, 300, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(0, 102, 204));
        this.add(titleLabel);

        JLabel nameLabel = new JLabel("Department Name:");
        nameLabel.setBounds(30, 60, 150, 25);
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        this.add(nameLabel);

        departmentTextField = new JTextField();
        departmentTextField.setBounds(170, 60, 200, 25);
        departmentTextField.setFont(new Font("Arial", Font.PLAIN, 14));
        this.add(departmentTextField);

        JButton addButton = new JButton("Add Department");
        addButton.setBounds(170, 100, 200, 30);
        addButton.setFont(new Font("Arial", Font.BOLD, 14));
        addButton.setBackground(new Color(0, 153, 76)); 
        addButton.setForeground(Color.WHITE);
        this.add(addButton);
        
        JButton deleteButton = new JButton("Delete Department");
        deleteButton.setBounds(170, 200, 200, 30);
        deleteButton.setFont(new Font("Arial", Font.BOLD, 14));
        deleteButton.setBackground(new Color(204, 0, 0)); 
        deleteButton.setForeground(Color.WHITE);
        this.add(deleteButton);
		
        JLabel comboBoxLabel = new JLabel("Existing Departments:");
        comboBoxLabel.setBounds(30, 150, 200, 25);
        comboBoxLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        this.add(comboBoxLabel);
         
        String[] departments = Methods.readFile("departments.txt");
        departmentComboBox = new JComboBox<>(departments);
        departmentComboBox.setBounds(170, 150, 200, 25);
        departmentComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        this.add(departmentComboBox);
		
		backButton = new JButton("Back");
        backButton.setBackground(new Color(100, 150, 250));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setFocusPainted(false);
        backButton.setBounds(170, 250, 200, 30);
        backButton.addActionListener(this);
		this.add(backButton);

        addButton.addActionListener(e -> addDepartment());
        deleteButton.addActionListener(e -> deleteDepartment());
		backButton.addActionListener(e -> { new Admin(); this.dispose(); });
    }

    private void addDepartment() {
        String department = departmentTextField.getText().trim();
        if (!department.isEmpty()) {
            departments.add(department);
            saveInFile(department);
            departmentTextField.setText("");
            departmentComboBox.addItem(department);
            JOptionPane.showMessageDialog(this, "Department added successfully!", 
                "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a department name.", 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void deleteDepartment() {
        String selectedDepartment = (String) departmentComboBox.getSelectedItem();
        if (selectedDepartment != null) {
            departments.remove(selectedDepartment);
            updateFile();
            departmentComboBox.removeItem(selectedDepartment);
            JOptionPane.showMessageDialog(this, "Department deleted successfully!", 
                "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a department to delete.", 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveInFile(String department) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("departments.txt", true))) {
            writer.write(department);
            writer.newLine();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving department: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("departments.txt"))) {
            for (String department : departments) {
                writer.write(department);
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error updating department file: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}

class AddAdmin extends JFrame {
    private JTextField idField;
    private JPasswordField passwordField;

    public AddAdmin() {
        this.setTitle("Add Admin");
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        
        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(50, 50, 100, 30);
        this.add(idLabel);

        idField = new JTextField();
        idField.setBounds(150, 50, 200, 30);
        this.add(idField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 100, 100, 30);
        this.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 100, 200, 30);
        this.add(passwordField);

        JButton saveButton = new JButton("Save");
        saveButton.setBounds(150, 150, 100, 30);
        this.add(saveButton);
        
        JButton backButton = new JButton("Back");
        backButton.setBackground(new Color(100, 150, 250));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setFocusPainted(false);
        backButton.setBounds(150, 200, 150, 30);
        backButton.addActionListener(e -> {
            new Admin();
            this.dispose();
        });
        this.add(backButton);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String password = new String(passwordField.getPassword());

                if (id.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Both fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    addAdmin();
                }
            }
        });

        this.setVisible(true);
    }

    private void addAdmin() {
        String id = idField.getText();
        String password = new String(passwordField.getPassword());
        String userRole = "Admin";

        if (id.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.");
        } else {
            try {
                User.saveUser(userRole, id, password);
                JOptionPane.showMessageDialog(this, "Account created successfully!");
                idField.setText("");
                passwordField.setText("");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving to the database.");
            }
        }
    }
}

class AddFaculty extends JFrame implements ActionListener {
    private JLabel idLabel, passwordLabel, fullNameLabel, dobLabel, genderLabel, nationalityLabel, phoneLabel, emailLabel, educationLabel, departmentLabel;
    private JTextField idTF, passwordTF, fullNameTF, dobTF, phoneTF, emailTF, searchTF;
    private JTextField[] programTF, cgpaTF;
    private JRadioButton maleRB, femaleRB, otherRB;
    private ButtonGroup genderGroup;
    private JComboBox<String> nationalityComboBox, departmentComboBox;
    private JButton submitButton, resetButton, editButton, deleteButton, searchButton;
    private JTable table;
    private DefaultTableModel tableModel;

    public AddFaculty() {
        this.setTitle("Manage Faculty");
        this.setSize(1300, 850);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
		this.setVisible(true);

        String[] departments = Methods.readFile("departments.txt");
        departmentComboBox = new JComboBox<>(departments);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(null);
        inputPanel.setBounds(10, 10, 450, 650);
        inputPanel.setBorder(BorderFactory.createTitledBorder("Add Faculty Details"));
        this.add(inputPanel);

        idLabel = new JLabel("ID:");
        idLabel.setBounds(20, 30, 100, 30);
        inputPanel.add(idLabel);
        idTF = new JTextField();
        idTF.setBounds(150, 30, 200, 30);
        inputPanel.add(idTF);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(20, 70, 100, 30);
        inputPanel.add(passwordLabel);
        passwordTF = new JTextField();
        passwordTF.setBounds(150, 70, 200, 30);
        inputPanel.add(passwordTF);

        fullNameLabel = new JLabel("Full Name:");
        fullNameLabel.setBounds(20, 110, 100, 30);
        inputPanel.add(fullNameLabel);
        fullNameTF = new JTextField();
        fullNameTF.setBounds(150, 110, 200, 30);
        inputPanel.add(fullNameTF);

        dobLabel = new JLabel("Date of Birth:");
        dobLabel.setBounds(20, 150, 100, 30);
        inputPanel.add(dobLabel);
        dobTF = new JTextField();
        dobTF.setBounds(150, 150, 200, 30);
        inputPanel.add(dobTF);

        genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(20, 190, 100, 30);
        inputPanel.add(genderLabel);
        maleRB = new JRadioButton("Male");
        maleRB.setBounds(150, 190, 70, 30);
        femaleRB = new JRadioButton("Female");
        femaleRB.setBounds(230, 190, 80, 30);
        otherRB = new JRadioButton("Other");
        otherRB.setBounds(320, 190, 80, 30);
        genderGroup = new ButtonGroup();
        genderGroup.add(maleRB);
        genderGroup.add(femaleRB);
        genderGroup.add(otherRB);
        inputPanel.add(maleRB);
        inputPanel.add(femaleRB);
        inputPanel.add(otherRB);

        nationalityLabel = new JLabel("Nationality:");
        nationalityLabel.setBounds(20, 230, 100, 30);
        inputPanel.add(nationalityLabel);
        nationalityComboBox = new JComboBox<>(new String[]{"Bangladeshi", "Foreign"});
        nationalityComboBox.setBounds(150, 230, 200, 30);
        inputPanel.add(nationalityComboBox);

        departmentLabel = new JLabel("Department:");
        departmentLabel.setBounds(20, 270, 100, 30);
        inputPanel.add(departmentLabel);
        departmentComboBox.setBounds(150, 270, 200, 30);
        inputPanel.add(departmentComboBox);

        educationLabel = new JLabel("Education Qualification:");
        educationLabel.setBounds(20, 350, 200, 30);
        inputPanel.add(educationLabel);
        programTF = new JTextField[3];
        cgpaTF = new JTextField[3];
        for (int i = 0; i < 3; i++) {
			programTF[i] = new JTextField();
			programTF[i].setBounds(20, 390 + i * 40, 200, 30);
			programTF[i].setText("");
			inputPanel.add(programTF[i]);
			cgpaTF[i] = new JTextField();
			cgpaTF[i].setBounds(230, 390 + i * 40, 120, 30);
			cgpaTF[i].setText("");
			inputPanel.add(cgpaTF[i]);
		}

        phoneLabel = new JLabel("Phone Number:");
        phoneTF = new JTextField();
		phoneLabel.setBounds(20, 510, 120, 30);
		phoneTF.setBounds(150, 510, 200, 30);
		inputPanel.add(phoneLabel);
		inputPanel.add(phoneTF);

        emailLabel = new JLabel("Email Address:");
        emailTF = new JTextField();
		emailLabel.setBounds(20, 550, 120, 30);
		emailTF.setBounds(150, 550, 200, 30);
		inputPanel.add(emailLabel);
		inputPanel.add(emailTF);

        submitButton = new JButton("Submit");
        submitButton.setBounds(20, 590, 100, 30);
        submitButton.addActionListener(this);
        inputPanel.add(submitButton);
        resetButton = new JButton("Reset");
        resetButton.setBounds(140, 590, 100, 30);
        resetButton.addActionListener(this);
        inputPanel.add(resetButton);

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setBounds(470, 10, 800, 600);
        tablePanel.setBorder(BorderFactory.createTitledBorder("Faculty List"));
        this.add(tablePanel);

        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{"ID", "Password", "Full Name", "DOB", "Gender", "Nationality", "Phone", "Email", "Department", "Program 1", "CGPA 1", "Program 2", "CGPA 2", "Program 3", "CGPA 3"});
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBounds(470, 620, 800, 50);
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Actions"));
        this.add(buttonPanel);
		
		editButton = new JButton("Edit");
        editButton.addActionListener(this);
        buttonPanel.add(editButton);
		
		deleteButton = new JButton("Delete");
        deleteButton.addActionListener(this);
        buttonPanel.add(deleteButton);
		
		searchTF = new JTextField(15);
        buttonPanel.add(searchTF);
		
		searchButton = new JButton("Search by ID");
        searchButton.addActionListener(this);
        buttonPanel.add(searchButton);
		
		readFile();

        
    }
	
	private void readFile() {
		try (BufferedReader reader = new BufferedReader(new FileReader("Faculties.txt"))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] fields = line.split(",");
				Object[] rowData = new Object[15];
				System.arraycopy(fields, 0, rowData, 0, Math.min(fields.length, 15));
				tableModel.addRow(rowData);
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Error loading existing data: " + e.getMessage());
		}
	}

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
			submit();
        } else if (e.getSource() == resetButton) {
            resetFields();
        } else if (e.getSource() == editButton) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a row to edit.");
                return;
            }
            update(selectedRow);
        } else if (e.getSource() == deleteButton) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a row to delete.");
                return;
            }
            delete(selectedRow);
        } else if (e.getSource() == searchButton) {
            String searchId = searchTF.getText().trim();
            searchById(searchId);
        }
    }
	
	private void submit() {
		String userRole = "Faculty";
		String id = idTF.getText();
		String password = passwordTF.getText();
		String name = fullNameTF.getText();
		String dob = dobTF.getText();
		String gender = maleRB.isSelected() ? "Male" : femaleRB.isSelected() ? "Female" : "Other";
		String nationality = (String) nationalityComboBox.getSelectedItem();
		String department = (String) departmentComboBox.getSelectedItem();
		String phone = phoneTF.getText();
		String email = emailTF.getText();
		String[] programs = new String[3];
		String[] cgpas = new String[3];
		for (int i = 0; i < 3; i++) {
			programs[i] = programTF[i].getText();
			cgpas[i] = cgpaTF[i].getText();
		}

		if (id.isEmpty() || password.isEmpty() || name.isEmpty() || dob.isEmpty() || phone.isEmpty() || email.isEmpty()) {
			JOptionPane.showMessageDialog(this, "All fields must be filled!");
			return;
		}

		try {
			FacultyIdName.saveFacultyIdName( id, name);
			User.saveUser(userRole, id, password);
			Faculties.saveFaculty(id, password, name, gender, nationality, dob, phone, email, programs, cgpas, department);
			tableModel.addRow(new Object[]{id, password, name, dob, gender, nationality, phone, email, department, programs[0], cgpas[0], programs[1], cgpas[1], programs[2], cgpas[2]});
			JOptionPane.showMessageDialog(this, "Faculty saved successfully!");
			resetFields();
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(this, "Error saving faculty data: " + ex.getMessage());
		}
	}


    private void update(int selectedRow) {
        String id = (String) tableModel.getValueAt(selectedRow, 0);
        String updatedDetails = JOptionPane.showInputDialog(this, "Update details for ID: " + id, tableModel.getDataVector().elementAt(selectedRow));
        if (updatedDetails != null) {
            String[] fields = updatedDetails.split(",");
            for (int i = 0; i < fields.length; i++) {
                tableModel.setValueAt(fields[i], selectedRow, i);
            }
            updateFile();
        }
    }

    private void delete(int selectedRow) {
        String id = (String) tableModel.getValueAt(selectedRow, 0);
        tableModel.removeRow(selectedRow);
        updateFile();
        JOptionPane.showMessageDialog(this, "Deleted record for ID: " + id);
    }

    private void searchById(String id) {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (tableModel.getValueAt(i, 0).equals(id)) {
                table.setRowSelectionInterval(i, i);
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "No record found for ID: " + id);
    }

    private void updateFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Faculties.txt"))) {
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                for (int j = 0; j < tableModel.getColumnCount(); j++) {
                    writer.write((String) tableModel.getValueAt(i, j));
                    if (j < tableModel.getColumnCount() - 1) writer.write(",");
                }
                writer.newLine();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error updating file: " + ex.getMessage());
        }
    }


    private void resetFields() {
        new AddFaculty();
        this.dispose();
    }
}

class ManageCourse extends JFrame implements ActionListener {
    private JComboBox<String> departmentComboBox, facultyComboBox, creditsComboBox;
    private JTextField courseNameField, sectionField;
    private JTable courseTable;
    private DefaultTableModel tableModel;
	private JButton backButton;
		

    public ManageCourse() {
        this.setTitle("Manage Courses");
        this.setSize(1000, 900);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null);

        JLabel departmentLabel = new JLabel("Department:");
        departmentLabel.setBounds(30, 30, 100, 25);
        this.add(departmentLabel);

        departmentComboBox = new JComboBox<>(Methods.readFile("departments.txt"));
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

        creditsComboBox = new JComboBox<>(new String[]{"1", "2", "3", "4", "5"});
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

        facultyComboBox = new JComboBox<>(Methods.readFile("FacultyIdName.txt"));
        facultyComboBox.setBounds(150, 190, 200, 25);
        this.add(facultyComboBox);

        JButton addButton = new JButton("Add Course");
        addButton.setBounds(400, 70, 150, 30);
        this.add(addButton);

        JButton deleteButton = new JButton("Delete Course");
        deleteButton.setBounds(400, 110, 150, 30);
        this.add(deleteButton);
		
		backButton = new JButton("Back");
        backButton.setBackground(new Color(100, 150, 250));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setFocusPainted(false);
        backButton.setBounds(650, 70, 150, 30);
        backButton.addActionListener(this);
		this.add(backButton);
		backButton.addActionListener(e -> { new Admin(); this.dispose(); });

        tableModel = new DefaultTableModel(new String[]{"Department", "Course Name", "Credits", "Section", "Faculty ID", "Faculty Name"}, 0);
        courseTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(courseTable);
        scrollPane.setBounds(30, 250, 920, 550);
        this.add(scrollPane);

        loadCoursesIntoTable();

        addButton.addActionListener(e -> addCourse());
        deleteButton.addActionListener(e -> deleteCourse());

        this.getContentPane().setBackground(new Color(230, 240, 255));
        this.setVisible(true);
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

        String[] facultyDetails = selectedFaculty.split(",");
        String facultyId = facultyDetails[0].trim();
        String facultyName = facultyDetails[1].trim();

        if (!courseName.isEmpty() && !section.isEmpty()) {
            try {
                Courses.saveCourse(department, courseName, section, facultyId, facultyName, credits);
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
    }
}
class AddStudent extends JFrame {
    JTextField idField, passwordField, studentNameField, fathersNameField, mothersNameField, dobField, emailField, phoneField;
    JComboBox<String> levelComboBox, departmentComboBox, nationalityComboBox;
    JButton submitButton;

    AddStudent() {
        ImageIcon image = new ImageIcon("E:/UMS/image/AIUB.png");
        this.setIconImage(image.getImage());
        this.setTitle("Create Account...");
        this.setSize(600, 900);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(240, 248, 255));
		this.setVisible(true);
        
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
        String[] departments = Methods.readFile("departments.txt");
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
        String[] nationalities = {"Bangladeshi", "Foreigner"};
        nationalityComboBox = new JComboBox<>(nationalities);
        nationalityComboBox.setBounds(200, 450, 200, 30);

        JLabel dobLabel = new JLabel("Date of Birth:");
        dobLabel.setBounds(50, 500, 200, 30);
        dobField = new JTextField("DD/MM/YYYY");
        dobField.setBounds(200, 500, 200, 30);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 550, 200, 30);
        emailField = new JTextField();
        emailField.setBounds(200, 550, 200, 30);

        JLabel phoneLabel = new JLabel("Phone No:");
        phoneLabel.setBounds(50, 600, 200, 30);
        phoneField = new JTextField();
        phoneField.setBounds(200, 600, 200, 30);

        submitButton = new JButton("Submit");
        submitButton.setBounds(200, 650, 200, 30);
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
        this.add(nationalityComboBox);
        this.add(dobLabel);
        this.add(dobField);
        this.add(emailLabel);
        this.add(emailField);
        this.add(phoneLabel);
        this.add(phoneField);
        this.add(submitButton);
        
    }

    private void addStudent() {
        String id = idField.getText();
        String password = passwordField.getText();
        String userRole ="Student";
        String studentName = studentNameField.getText();
        String fathersName = fathersNameField.getText();
        String mothersName = mothersNameField.getText();
        String nationality = (String) nationalityComboBox.getSelectedItem();
        String dob = dobField.getText();
        String email = emailField.getText();
        String phoneNumber = phoneField.getText();
        String level = (String) levelComboBox.getSelectedItem();
        String department = (String) departmentComboBox.getSelectedItem();

        if (id.isEmpty() || password.isEmpty()|| studentName.isEmpty() || fathersName.isEmpty()|| mothersName.isEmpty()|| nationality.isEmpty()|| dob.isEmpty() || email.isEmpty() || phoneNumber.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.");
        } else {
            try {
                User.saveUser(userRole, id, password);
                Students.saveStudent(id, studentName, fathersName, mothersName, nationality, dob, level, department, email, phoneNumber);
                JOptionPane.showMessageDialog(this, "Account created successfully!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving to the database.");
            }
        }
    }
}

class StudentList extends JFrame implements ActionListener {
    private JTable studentTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;
    private JComboBox<String> departmentFilter;
    private JButton updateButton, removeButton, searchButton, filterButton, refreshButton;

    public StudentList() {
        setTitle("Student Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        tableModel = new DefaultTableModel(new String[]{
                "ID", "Name", "Father", "Mother", "Nationality", "DOB", "Level", "Department", "Email", "Phone"
        }, 0);
        studentTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(studentTable);
        scrollPane.setBounds(10, 10, 760, 300);
        add(scrollPane);

        JLabel searchLabel = new JLabel("Search by ID:");
        searchLabel.setBounds(10, 320, 100, 30);
        add(searchLabel);

        searchField = new JTextField();
        searchField.setBounds(120, 320, 150, 30);
        add(searchField);

        searchButton = new JButton("Search");
        searchButton.setBounds(280, 320, 100, 30);
        searchButton.addActionListener(this);
        add(searchButton);

        JLabel filterLabel = new JLabel("Filter by Department:");
        filterLabel.setBounds(10, 360, 150, 30);
        add(filterLabel);

        String[] departments = Methods.readFile("departments.txt");
        departmentFilter = new JComboBox<>(departments);
        departmentFilter.setBounds(160, 360, 150, 30);
        add(departmentFilter);

        filterButton = new JButton("Filter");
        filterButton.setBounds(320, 360, 100, 30);
        filterButton.addActionListener(this);
        add(filterButton);

        updateButton = new JButton("Update");
        updateButton.setBounds(10, 400, 100, 30);
        updateButton.addActionListener(this);
        add(updateButton);

        removeButton = new JButton("Remove");
        removeButton.setBounds(120, 400, 100, 30);
        removeButton.addActionListener(this);
        add(removeButton);

        refreshButton = new JButton("Refresh");
        refreshButton.setBounds(230, 400, 100, 30);
        refreshButton.addActionListener(this);
        add(refreshButton);

        loadStudents();
        setVisible(true);
    }

    private void loadStudents() {
        tableModel.setRowCount(0);
        try (BufferedReader reader = new BufferedReader(new FileReader("Students.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] studentData = line.split(",");
                tableModel.addRow(studentData);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading student data.");
        }
    }

    private void searchStudent(String id) {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (tableModel.getValueAt(i, 0).equals(id)) {
                studentTable.setRowSelectionInterval(i, i);
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Student not found.");
    }

    private void filterStudents(String department) {
        tableModel.setRowCount(0); 
        try (BufferedReader reader = new BufferedReader(new FileReader("Students.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] studentData = line.split(",");
                if (studentData[7].equals(department)) {
                    tableModel.addRow(studentData);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error filtering students.");
        }
    }

    private void removeStudent() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "No student selected.");
            return;
        }

        String id = (String) tableModel.getValueAt(selectedRow, 0);
        tableModel.removeRow(selectedRow);

        try (BufferedReader reader = new BufferedReader(new FileReader("Students.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("Students_temp.txt"))) {

            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith(id + ",")) {
                    writer.write(line);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error removing student.");
        }

        new File("Students.txt").delete();
        new File("Students_temp.txt").renameTo(new File("Students.txt"));
    }

    private void updateStudent() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a student to update.");
            return;
        }

        String id = tableModel.getValueAt(selectedRow, 0).toString();
        String studentName = tableModel.getValueAt(selectedRow, 1).toString();
        String fathersName = tableModel.getValueAt(selectedRow, 2).toString();
        String mothersName = tableModel.getValueAt(selectedRow, 3).toString();
        String nationality = tableModel.getValueAt(selectedRow, 4).toString();
        String dob = tableModel.getValueAt(selectedRow, 5).toString();
        String level = tableModel.getValueAt(selectedRow, 6).toString();
        String department = tableModel.getValueAt(selectedRow, 7).toString();
        String email = tableModel.getValueAt(selectedRow, 8).toString();
        String phoneNumber = tableModel.getValueAt(selectedRow, 9).toString();

        String updatedStudentData = id + "," + studentName + "," + fathersName + "," + mothersName + "," + nationality + "," + dob + "," + level + "," + department + "," + email + "," + phoneNumber;

        try {
            List<String> lines = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader("Students.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data[0].equals(id)) {
                        lines.add(updatedStudentData);
                    } else {
                        lines.add(line);
                    }
                }
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("Students.txt", false))) {
                for (String line : lines) {
                    writer.write(line);
                    writer.newLine();
                }
            }

            JOptionPane.showMessageDialog(null, "Student information updated successfully!");

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error updating student information: " + e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            searchStudent(searchField.getText());
        } else if (e.getSource() == filterButton) {
            filterStudents((String) departmentFilter.getSelectedItem());
        } else if (e.getSource() == removeButton) {
            removeStudent();
        } else if (e.getSource() == updateButton) {
            updateStudent();
        } else if (e.getSource() == refreshButton) {
            loadStudents();
        }
    }
}

class Main {
    public static void main(String[] args) {
        new MyFrame();
    }
}
