import javax.swing.*;
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

class Students {

    private static final String FILE_NAME = "Students.txt";

    public static void saveStudent(String id, String password, String studentName, String fathersName, String mothersName, String nationality, String dob, String level, String department) 
	throws IOException {	
        String studentData = String.join(id, password, studentName, fathersName, mothersName, nationality, dob, level, department );

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(studentData);
            writer.newLine();
        }
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
            new Faculty();
        } else if (userRole.matches("Student")) {
            new Student();
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
                    if (user.verifyUser(userRole, "", password)) {
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
                    if (user.verifyUser(userRole, id, password)) {
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

    JButton manageDepartmentButton, addAdminButton, addFacultyButton, facultyListButton, addStudentButton, studentListButton, backButton;

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
        ImageIcon facultyListIcon = new ImageIcon("E:/UMS/image/faculty_list.png");
        ImageIcon addStudentIcon = new ImageIcon("E:/UMS/image/add_student.png");
        ImageIcon studentListIcon = new ImageIcon("E:/UMS/image/student_list.png");

        manageDepartmentButton = createButton(manageDepartmentIcon, 100, 100);
        JLabel manageDepartment = createLabel("Manage Department", 50, 210);
        
        addAdminButton = createButton(addAdminIcon, 100, 300);
        JLabel addAdmin = createLabel("Add Admin", 50, 410);
        
        addFacultyButton = createButton(addFacultyIcon, 350, 100);
        JLabel addFaculty = createLabel("Add Faculty", 300, 210);
        
        facultyListButton = createButton(facultyListIcon, 350, 300);
        JLabel facultyList = createLabel("Faculty List", 300, 410);
        
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
        } else if (e.getSource() == facultyListButton) {
            new FacultyList();
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

class ManageDepartment extends JFrame {
    private JComboBox<String> departmentComboBox;
    private JTextField departmentTextField;
    private ArrayList<String> departments;

    public ManageDepartment() {
        ImageIcon image = new ImageIcon("E:/UMS/image/AIUB.png");
        this.setIconImage(image.getImage());
        this.setTitle("Manage Department");
        this.setLayout(null);
        this.setSize(500, 300);
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

        addButton.addActionListener(e -> addDepartment());
        deleteButton.addActionListener(e -> deleteDepartment());
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
                user.saveUser(userRole, id, password);
                JOptionPane.showMessageDialog(this, "Account created successfully!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving to the database.");
            }
        }
    }
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
		String level = (String) levelComboBox.getSelectedItem();
		String department = (String) departmentComboBox.getSelectedItem();
		
		

        if (id.isEmpty() || password.isEmpty()|| studentName.isEmpty() || fathersName.isEmpty()|| mothersName.isEmpty()|| nationality.isEmpty()|| dob.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.");
        } else {
            try {
                user.saveUser(userRole, id, password);
				Students.saveStudent(id, password, studentName, fathersName, mothersName, nationality, dob, level, department);
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
