import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



class MyFrame extends JFrame implements ActionListener {
	private JButton button1,button2;

    MyFrame() {
        ImageIcon image = new ImageIcon("E:/UMS/image/AIUB.png");
        this.setIconImage(image.getImage());
        this.setTitle("University Management System.");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setSize(1230, 920);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        Container c = this.getContentPane();
        c.setBackground(Color.WHITE);

        Label();
    }

    private void Label() {
        ImageIcon image = new ImageIcon("E:/UMS/image/AIUB.png");
		ImageIcon image2 = new ImageIcon("E:/UMS/image/AIUB2.jpg");
        Font a = new Font("Ariel", Font.BOLD, 20);

        JLabel label1 = new JLabel("Welcome to AIUB");
        label1.setFont(a);
        label1.setHorizontalAlignment(JLabel.CENTER);
        label1.setBounds(740, 50, 460, 30);

        JLabel label2 = new JLabel();
        label2.setIcon(image);
        label2.setHorizontalTextPosition(JLabel.CENTER);
        label2.setVerticalTextPosition(JLabel.BOTTOM);
        label2.setHorizontalAlignment(JLabel.CENTER);
        label2.setVerticalAlignment(JLabel.CENTER);
        label2.setBounds(740, 100, 460, 230);

        JLabel label3 = new JLabel("American International University-Bangladesh");
        label3.setFont(a);
        label3.setForeground(Color.BLUE);
        label3.setHorizontalAlignment(JLabel.CENTER);
        label3.setBounds(740, 420, 460, 30);
		
		JLabel label4 = new JLabel();
        label4.setIcon(image2);
        label4.setHorizontalTextPosition(JLabel.CENTER);
        label4.setVerticalTextPosition(JLabel.BOTTOM);
        label4.setHorizontalAlignment(JLabel.CENTER);
        label4.setVerticalAlignment(JLabel.CENTER);
        label4.setBounds(10, 10, 720, 850);

        button1 = new JButton("Log In");
        button1.setBounds(890, 550, 160, 30);
        button1.setBackground(new Color(0, 102, 204));
        button1.setForeground(Color.WHITE);
        button1.addActionListener(this);

        button2 = new JButton("Create Account");
        button2.setBounds(890, 590, 160, 30);
        button2.setBackground(new Color(0, 102, 204));
        button2.setForeground(Color.WHITE);
        button2.addActionListener(this);

        this.add(label1);
        this.add(label2);
        this.add(label3);
		this.add(label4);
        this.add(button1);
        this.add(button2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            new Login();
        } else if (e.getSource() == button2) {
            new CreateAccount();
        }
    }
}


class Login extends JFrame {
    JLabel label1;
    JComboBox<String> userRuleComboBox;
    JTextField idField;
    JPasswordField passwordField;
    JButton submitButton;

    Login() {
        ImageIcon image = new ImageIcon("E:/UMS/image/AIUB.png");
        this.setIconImage(image.getImage());
        this.setTitle("Login.....");
        this.setSize(600, 800);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        label1 = new JLabel("User Rule:");
        label1.setBounds(50, 50, 200, 30);
        String[] userRule = {"Admin", "Faculty", "Student"};
        userRuleComboBox = new JComboBox<>(userRule);
        userRuleComboBox.setBounds(200, 50, 200, 30);

        JLabel label2 = new JLabel("ID:");
        label2.setBounds(50, 100, 200, 30);
        idField = new JTextField();
        idField.setBounds(200, 100, 200, 30);

        JLabel label3 = new JLabel("Password:");
        label3.setBounds(50, 150, 200, 30);
        passwordField = new JPasswordField();
        passwordField.setBounds(200, 150, 200, 30);

        submitButton = new JButton("Submit");
        submitButton.setBounds(200, 200, 200, 30);
        submitButton.addActionListener(e -> idAndPassCheck());

        this.add(label1);
        this.add(userRuleComboBox);
        this.add(label2);
        this.add(idField);
        this.add(label3);
        this.add(passwordField);
        this.add(submitButton);

        this.setVisible(true);
    }

    private void idAndPassCheck() {
        String id = idField.getText();
        String userRule = (String) userRuleComboBox.getSelectedItem();
        String password = new String(passwordField.getPassword());
        
            if (id.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields.");
            } else if (id.length() != 5 || !id.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "Invalid ID. Please enter a 5-digit number.");
            } else if (password.length() < 6 || password.length() > 10 || !password.matches("[a-zA-Z0-9]+")) {
                JOptionPane.showMessageDialog(this, "Password must be 6 to 10 characters long and contain only a-z, A-Z, and 0-9.");
            } else {
                JOptionPane.showMessageDialog(this, "Logged in as " + userRule);
            }
        
    }
}

class CreateAccount extends JFrame {
    JTextField idField, studentNameField, fathersNameField, mothersNameField, nationalityField, dobField;
    JComboBox<String> departmentComboBox;
    JButton submitButton;

    CreateAccount() {
        ImageIcon image = new ImageIcon("E:/UMS/image/AIUB.png");
        this.setIconImage(image.getImage());
        this.setTitle("Create Account...");
        this.setSize(600, 800);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        JLabel levelLabel = new JLabel("Level:");
        levelLabel.setBounds(50, 50, 200, 30);
        String[] levels = {"Undergraduate", "Postgraduate"};
        JComboBox<String> levelComboBox = new JComboBox<>(levels);
        levelComboBox.setBounds(200, 50, 200, 30);

        JLabel departmentLabel = new JLabel("Department:");
        departmentLabel.setBounds(50, 100, 200, 30);
        String[] departments = {"CSE", "EEE", "BBA", "LAW"};
        departmentComboBox = new JComboBox<>(departments);
        departmentComboBox.setBounds(200, 100, 200, 30);

        JLabel idLabel = new JLabel("ID (5-digit):");
        idLabel.setBounds(50, 150, 200, 30);
        idField = new JTextField();
        idField.setBounds(200, 150, 200, 30);

        JLabel studentNameLabel = new JLabel("Student Name:");
        studentNameLabel.setBounds(50, 200, 200, 30);
        studentNameField = new JTextField();
        studentNameField.setBounds(200, 200, 200, 30);

        JLabel fathersNameLabel = new JLabel("Father's Name:");
        fathersNameLabel.setBounds(50, 250, 200, 30);
        fathersNameField = new JTextField();
        fathersNameField.setBounds(200, 250, 200, 30);

        JLabel mothersNameLabel = new JLabel("Mother's Name:");
        mothersNameLabel.setBounds(50, 300, 200, 30);
        mothersNameField = new JTextField();
        mothersNameField.setBounds(200, 300, 200, 30);

        JLabel nationalityLabel = new JLabel("Nationality:");
        nationalityLabel.setBounds(50, 350, 200, 30);
        nationalityField = new JTextField();
        nationalityField.setBounds(200, 350, 200, 30);

        JLabel dobLabel = new JLabel("Date of Birth:");
        dobLabel.setBounds(50, 400, 200, 30);
        dobField = new JTextField();
        dobField.setBounds(200, 400, 200, 30);

        submitButton = new JButton("Submit");
        submitButton.setBounds(200, 450, 200, 30);
        submitButton.addActionListener(e -> showInfo());

        this.add(levelLabel);
        this.add(levelComboBox);
        this.add(departmentLabel);
        this.add(departmentComboBox);
        this.add(idLabel);
        this.add(idField);
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

    private void showInfo() {
        String info = "ID: " + idField.getText() + "\n" +
                      "Student Name: " + studentNameField.getText() + "\n" +
                      "Father's Name: " + fathersNameField.getText() + "\n" +
                      "Mother's Name: " + mothersNameField.getText() + "\n" +
                      "Nationality: " + nationalityField.getText() + "\n" +
                      "Date of Birth: " + dobField.getText() + "\n" +
                      "Department: " + departmentComboBox.getSelectedItem();
        JOptionPane.showMessageDialog(this, info, "Account Information", JOptionPane.INFORMATION_MESSAGE);
    }
}

class Main {
    public static void main(String[] args) {
        new MyFrame();
    }
}


