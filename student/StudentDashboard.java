package student;

import javax.swing.*;

public class StudentDashboard extends JFrame implements ActionListener {
    private String studentId;
    private JButton updateButton;
import javax.swing.*;

    public StudentDashboard(String studentId) {
        this.studentId = studentId;

        this.setTitle("Student Dashboard");
        this.setSize(600, 600);
		
		ImageIcon image = new ImageIcon("..\\UMS\\image\\AIUB.png");
        this.setIconImage(image.getImage());
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
        try (BufferedReader reader = new BufferedReader(new FileReader("..\\UMS\\Data\\Students.txt"))) {
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
        try (BufferedReader reader = new BufferedReader(new FileReader("..\\UMS\\Data\\Students.txt"))) {
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

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("..\\UMS\\Data\\Students.txt"))) {
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
