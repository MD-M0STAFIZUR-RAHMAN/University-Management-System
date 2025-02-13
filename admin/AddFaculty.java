package admin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AddFaculty extends JFrame implements ActionListener {
    private JLabel idLabel, passwordLabel, fullNameLabel, dobLabel, genderLabel, nationalityLabel, departmentLabel, educationLabel, phoneLabel, emailLabel;
    private JTextField idTF, passwordTF, fullNameTF, dobTF, phoneTF, emailTF, searchTF;
    private JComboBox<String> nationalityComboBox, departmentComboBox;
    private JRadioButton maleRB, femaleRB, otherRB;
    private ButtonGroup genderGroup;
    private JTextField[] programTF, cgpaTF;
    private JButton submitButton, resetButton, editButton, deleteButton, searchButton, backButton;
    private JTable table;
    private DefaultTableModel tableModel;
    private JPanel inputPanel, tablePanel, buttonPanel;

    public AddFaculty() {
        this.setTitle("Manage Faculty");
		ImageIcon image = new ImageIcon("..\\UMS\\image\\AIUB.png");
		this.setIconImage(image.getImage());
		this.setSize(1300, 850);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
        getContentPane().setBackground(Color.WHITE);
		
		String[] departments = Methods.readFile("..\\UMS\\Data\\departments.txt");
		departmentComboBox = new JComboBox<>(departments);
		
		inputPanel = new JPanel();
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
			inputPanel.add(programTF[i]);
			cgpaTF[i] = new JTextField();
			cgpaTF[i].setBounds(230, 390 + i * 40, 120, 30);
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
		
		submitButton.setBackground(new Color(100, 150, 250));
		submitButton.setForeground(Color.WHITE);
		submitButton.setFont(new Font("Arial", Font.BOLD, 14));
		submitButton.setFocusPainted(false);
		submitButton.setBounds(20, 590, 100, 30);
		submitButton.addActionListener(this);
		inputPanel.add(submitButton);
		
		resetButton = new JButton("Reset");
		resetButton.setBackground(new Color(100, 150, 250));
		resetButton.setForeground(Color.WHITE);
		resetButton.setFont(new Font("Arial", Font.BOLD, 14));
		resetButton.setFocusPainted(false);
		resetButton.setBounds(150, 590, 100, 30);
		resetButton.addActionListener(this);
		inputPanel.add(resetButton);
		
		tablePanel = new JPanel();
		tablePanel.setLayout(new BorderLayout());
		tablePanel.setBounds(470, 10, 800, 600);
		tablePanel.setBorder(BorderFactory.createTitledBorder("Faculty List"));
		this.add(tablePanel);
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(new String[]{"ID", "Password", "Full Name", "Gender", "Nationality", "DOB", "Phone", "Email", "Department", "Program 1", "CGPA 1", "Program 2", "CGPA 2", "Program 3", "CGPA 3"});
		table = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(table);
		tablePanel.add(scrollPane, BorderLayout.CENTER);
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.setBounds(470, 620, 800, 50);
		buttonPanel.setBorder(BorderFactory.createTitledBorder("Actions"));
		this.add(buttonPanel);
		
		editButton = new JButton("Edit");
		editButton.addActionListener(this);
		buttonPanel.add(editButton);
		
		deleteButton = new JButton("Delete");
		deleteButton.setBackground(new Color(100, 150, 250));
		deleteButton.setForeground(Color.WHITE);
		deleteButton.setFont(new Font("Arial", Font.BOLD, 14));
		deleteButton.setFocusPainted(false);
		deleteButton.addActionListener(this);
		buttonPanel.add(deleteButton);
		
		searchTF = new JTextField(15);
		buttonPanel.add(searchTF);
		
		searchButton = new JButton("Search by ID");
		searchButton.addActionListener(this);
		searchButton.setBackground(new Color(100, 150, 250));
		searchButton.setForeground(Color.WHITE);
		searchButton.setFont(new Font("Arial", Font.BOLD, 14));
		searchButton.setFocusPainted(false);
		buttonPanel.add(searchButton);
		
		backButton = new JButton("Back");
		backButton.setBackground(new Color(100, 150, 250));
		backButton.setForeground(Color.WHITE);
		backButton.setFont(new Font("Arial", Font.BOLD, 14));
		backButton.setFocusPainted(false);
		backButton.setBounds(170, 250, 200, 30);
		backButton.addActionListener(e -> { new Admin(); this.dispose(); });
		buttonPanel.add(backButton);

		
		readFile();

        
    }
	
	private void readFile() {
		try (BufferedReader reader = new BufferedReader(new FileReader("..\\UMS\\Data\\Faculties.txt"))) {
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
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(this, "Please select a row to edit.");
			return;
		}
		new EditFaculty(tableModel, selectedRow);
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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("..\\UMS\\Data\\Faculties.txt"))) {
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
