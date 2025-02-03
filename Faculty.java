import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Faculty extends JFrame implements ActionListener {
    private JComboBox<String> courseComboBox, sectionComboBox;
    private JButton backButton, addMarksButton, dashboardButton, filterButton , editButton, refrashButton;
    private JTable studentTable;
    private DefaultTableModel tableModel;
    private String facultyId;
    private static final String FILE_NAME = "Courses.txt";

    public Faculty(String facultyId) {
        this.facultyId = facultyId;
        setTitle("Faculty Dashboard");
        setSize(800, 650);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        String facultyName = null;
        try {
            facultyName = GetName(facultyId);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JLabel WelcomeLabel = new JLabel("Welcome " + facultyName);
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
        dashboardButton.setBounds(720, 80, 30, 30);
        dashboardButton.setIcon(new ImageIcon("../image/dashboard.png"));
        dashboardButton.setFocusPainted(false);
        dashboardButton.addActionListener(this);
        add(dashboardButton);

        JLabel courseLabel = new JLabel("Select Course:");
        courseLabel.setBounds(50, 100, 100, 30);
        add(courseLabel);

        courseComboBox = new JComboBox<>(getCourses(facultyId));
        courseComboBox.setBounds(150, 100, 200, 30);
        add(courseComboBox);

        JLabel sectionLabel = new JLabel("Select Section:");
        sectionLabel.setBounds(50, 150, 100, 30);
        add(sectionLabel);

        sectionComboBox = new JComboBox<>(getSections(facultyId));
        sectionComboBox.setBounds(150, 150, 200, 30);
        add(sectionComboBox);

        filterButton = new JButton("Filter Students");    
		button(filterButton, 400, 120);

        addMarksButton = new JButton("Add Marks");
        button(addMarksButton, 50, 200);
		
		editButton = new JButton("Edit Marks");
		button(editButton, 250, 200);
        

        tableModel = new DefaultTableModel(new String[]{"Student ID", "Student Name", "Attendance", "Quiz 1", "Quiz 2", "Assignment", "Mid Exam", "Final Exam", "Total Marks", "CGPA"}, 0);
        studentTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(studentTable);
        scrollPane.setBounds(50, 250, 700, 300);
        add(scrollPane);

        backButton = new JButton("Back");
		button(backButton, 50, 570);
        
		
		refrashButton = new JButton("Refresh");
		button(refrashButton, 450, 200);

        setVisible(true);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addMarksButton) {
			addMarks();
		} else if (e.getSource() == editButton) {
			editMarks();
		} else if (e.getSource() == dashboardButton) {
			new FacultyDashboard(facultyId);
		}else if (e.getSource() == dashboardButton) {
			new Faculty(facultyId);
			this.dispose();
		}else if (e.getSource() == filterButton) {
			loadStudents(); 
		}else if (e.getSource() == backButton) {
			new Login();
			this.dispose();
		}
	}
	
	private void button(JButton button, int x, int y) {
		button.setBounds(x, y,150 ,30);  
		button.setFont(new Font("Arial", Font.BOLD, 14)); 
		button.setBackground(new Color(100, 150, 250));  
		button.setForeground(Color.WHITE); 
		button.setFocusPainted(false); 
		button.addActionListener(this);
		add(button);
	}
	
	
	private void loadStudents() {
		tableModel.setRowCount(0);
		
		String selectedCourse = (String) courseComboBox.getSelectedItem();
		String selectedSection = (String) sectionComboBox.getSelectedItem();
		String currentSemester = "";
		
		try {
			currentSemester = ManageCourse.readSemester();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (selectedCourse == null || selectedSection == null) {
			JOptionPane.showMessageDialog(this, "Please select both course and section.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		try (BufferedReader studentReader = new BufferedReader(new FileReader("StudentCourses.txt"));
			 BufferedReader marksReader = new BufferedReader(new FileReader("StudentMarks.txt"))) {
			
			List<String[]> studentData = new ArrayList<>();
			String studentLine;
			while ((studentLine = studentReader.readLine()) != null) {
				String[] data = studentLine.split(",");
				if (data.length >= 4 && data[1].equals(selectedCourse) && data[2].equals(selectedSection) && data[3].equals(currentSemester)) {
					studentData.add(data);
				}
			}
			
			List<String[]> marksData = new ArrayList<>();
			String marksLine;
			while ((marksLine = marksReader.readLine()) != null) {
				String[] data = marksLine.split(",");
				if (data.length == 12) {
					marksData.add(data);
				}
			}
			
			boolean dataFound = false;
			for (String[] student : studentData) {
				String studentId = student[0];
				String studentName = getStudentName(studentId);
				String[] marks = null;
				for (String[] mark : marksData) {
					if (mark[0].equals(studentId) && mark[1].equals(selectedCourse) && mark[2].equals(selectedSection) && mark[11].equals(currentSemester)) {
						marks = mark;
						break;
					}
				}
				if (marks != null) {
					tableModel.addRow(new Object[]{
							studentId, studentName, marks[3], marks[4], marks[5], marks[6],
							marks[7], marks[8], marks[9], marks[10]
					});
				} else {
					tableModel.addRow(new Object[]{
							studentId, studentName, "", "", "", "", "", "", "", ""
					});
				}
				dataFound = true;
			}
			
			if (!dataFound) {
				JOptionPane.showMessageDialog(this, "No students found for the selected course and section.", "Info", JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error reading student or marks data.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}


	
	private void addMarks() {
		int selectedRow = studentTable.getSelectedRow();
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(this, "Please select a student for adding marks.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		String selectedCourse = (String) courseComboBox.getSelectedItem();
		String selectedSection = (String) sectionComboBox.getSelectedItem();
		
		String semester = " ";
		
		try {
			semester = ManageCourse.readSemester();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Error reading semester: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}

		if (selectedCourse == null || selectedSection == null) {
			JOptionPane.showMessageDialog(this, "Please select a course and section.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		File marksFile = new File("StudentMarks.txt");
		List<String> existingRecords = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(marksFile))) {
			String line;
			while ((line = reader.readLine()) != null) {
				existingRecords.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(marksFile, true))) {
			String studentId = (String) tableModel.getValueAt(selectedRow, 0);
			boolean alreadyExists = existingRecords.stream().anyMatch(record -> record.startsWith(studentId + ","));

			if (alreadyExists) {
				JOptionPane.showMessageDialog(this, "Marks already added for Student ID: " + studentId + 
						". Use the Edit button to modify.", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				String attendance = getValidNumber((String) tableModel.getValueAt(selectedRow, 2));
				String quiz1 = getValidNumber((String) tableModel.getValueAt(selectedRow, 3));
				String quiz2 = getValidNumber((String) tableModel.getValueAt(selectedRow, 4));
				String assignment = getValidNumber((String) tableModel.getValueAt(selectedRow, 5));
				String midExam = getValidNumber((String) tableModel.getValueAt(selectedRow, 6));
				String finalExam = getValidNumber((String) tableModel.getValueAt(selectedRow, 7));

				int totalMarks = Integer.parseInt(attendance) + Integer.parseInt(quiz1) +
						Integer.parseInt(quiz2) + Integer.parseInt(assignment) +
						Integer.parseInt(midExam) + Integer.parseInt(finalExam);

				double cgpa = calculateCGPA(totalMarks);

				writer.write(studentId + "," + selectedCourse + "," + selectedSection + "," + attendance + "," +
						quiz1 + "," + quiz2 + "," + assignment + "," + midExam + 
						"," + finalExam + "," + totalMarks + "," + cgpa + "," + semester);
				writer.newLine();

				JOptionPane.showMessageDialog(this, "Marks added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error saving marks.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}


	
	private void editMarks() {
		int selectedRow = studentTable.getSelectedRow();
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(this, "Please select a student for editing marks.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		String studentId = (String) tableModel.getValueAt(selectedRow, 0);
		String selectedCourse = (String) courseComboBox.getSelectedItem();
		String selectedSection = (String) sectionComboBox.getSelectedItem();
		
		if (selectedCourse == null || selectedSection == null) {
			JOptionPane.showMessageDialog(this, "Please select a course and section.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		File marksFile = new File("StudentMarks.txt");
		List<String> fileContent = new ArrayList<>();
		boolean studentExist = false;
		
		try (BufferedReader reader = new BufferedReader(new FileReader(marksFile))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] data = line.split(",");
				if (data.length == 11 && data[0].equals(studentId) && data[1].equals(selectedCourse) && data[2].equals(selectedSection)) {
					studentExist = true;
					
					String attendance = getValidNumber((String) tableModel.getValueAt(selectedRow, 2));
					String quiz1 = getValidNumber((String) tableModel.getValueAt(selectedRow, 3));
					String quiz2 = getValidNumber((String) tableModel.getValueAt(selectedRow, 4));
					String assignment = getValidNumber((String) tableModel.getValueAt(selectedRow, 5));
					String midExam = getValidNumber((String) tableModel.getValueAt(selectedRow, 6));
					String finalExam = getValidNumber((String) tableModel.getValueAt(selectedRow, 7));
					
					int totalMarks = Integer.parseInt(attendance) + Integer.parseInt(quiz1) +
							Integer.parseInt(quiz2) + Integer.parseInt(assignment) +
							Integer.parseInt(midExam) + Integer.parseInt(finalExam);
					
					double cgpa = calculateCGPA(totalMarks);
					
					fileContent.add(studentId + "," + selectedCourse + "," + selectedSection + "," +
							attendance + "," + quiz1 + "," + quiz2 + "," + assignment + "," +
							midExam + "," + finalExam + "," + totalMarks + "," + cgpa);
				} else {
					fileContent.add(line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error reading marks data.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if (!studentExist) {
			JOptionPane.showMessageDialog(this, "No existing marks found for this student. Use Add Marks instead.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(marksFile))) {
			for (String record : fileContent) {
				writer.write(record);
				writer.newLine();
			}
			JOptionPane.showMessageDialog(this, "Marks updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error updating marks.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}



    public static String GetName(String facultyId) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("Faculties.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3 && parts[0].equals(facultyId)) {
                    return parts[2];
                }
            }
        } catch (FileNotFoundException e) {
            throw e;
        }
        return null;
    }

	

	private String getValidNumber(String value) {
		return (value == null || value.trim().isEmpty()) ? "0" : value.trim();
	}
	
	private double calculateCGPA(int totalMarks) {
		
		double marks = totalMarks;
		
		if (marks >= 90) return 4.0;
		else if (marks >= 80) return 3.5;
		else if (marks >= 70) return 3.0;
		else if (marks >= 60) return 2.5;
		else if (marks >= 50) return 2.0;
		else return 0.0; 
	}


	public static String[] getCourses(String facultyId) {
		List<String> courses = new ArrayList<>();
		String currentSemester = "";
		
		try {
			currentSemester = ManageCourse.readSemester();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] data = line.split(",");
				if (data.length >= 7 && data[4].equals(facultyId) && data[6].equals(currentSemester)) {
					if (!courses.contains(data[1])) {
						courses.add(data[1]);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return courses.toArray(new String[0]);
	}

	private String[] getSections(String facultyId) {
		List<String> sections = new ArrayList<>();
		String currentSemester = "";

		
		try {
			currentSemester = ManageCourse.readSemester();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] courseData = line.split(",");
				if (courseData.length >= 7 && courseData[4].equals(facultyId) && courseData[6].equals(currentSemester)) {
					sections.add(courseData[3]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sections.toArray(new String[0]);
	}
	
    private String getStudentName(String studentId) {
        try (BufferedReader reader = new BufferedReader(new FileReader("Students.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(studentId)) {
                    return data[1];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Unknown";
    }

}


class FacultyDashboard extends JFrame implements ActionListener {
    private String facultyId;
    private JTextField nameField, genderField, nationalityField, dobField, phoneField, emailField, departmentField;
    private JButton updateButton;

    public FacultyDashboard(String facultyId) {
        this.facultyId = facultyId;

        this.setTitle("Faculty Dashboard");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null);

        JLabel titleLabel = new JLabel("Faculty Dashboard");
        titleLabel.setBounds(150, 20, 200, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        this.add(titleLabel);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 80, 100, 25);
        this.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(150, 80, 200, 25);
        this.add(nameField);

        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(50, 120, 100, 25);
        this.add(genderLabel);

        genderField = new JTextField();
        genderField.setBounds(150, 120, 200, 25);
        this.add(genderField);

        JLabel nationalityLabel = new JLabel("Nationality:");
        nationalityLabel.setBounds(50, 160, 100, 25);
        this.add(nationalityLabel);

        nationalityField = new JTextField();
        nationalityField.setBounds(150, 160, 200, 25);
        this.add(nationalityField);

        JLabel dobLabel = new JLabel("Date of Birth:");
        dobLabel.setBounds(50, 200, 100, 25);
        this.add(dobLabel);

        dobField = new JTextField();
        dobField.setBounds(150, 200, 200, 25);
        this.add(dobField);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(50, 240, 100, 25);
        this.add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(150, 240, 200, 25);
        this.add(phoneField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 280, 100, 25);
        this.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(150, 280, 200, 25);
        this.add(emailField);

        JLabel departmentLabel = new JLabel("Department:");
        departmentLabel.setBounds(50, 320, 100, 25);
        this.add(departmentLabel);

        departmentField = new JTextField();
        departmentField.setBounds(150, 320, 200, 25);
        this.add(departmentField);

        updateButton = new JButton("Update");
        updateButton.setBounds(180, 380, 100, 30);
        updateButton.addActionListener(this);
        this.add(updateButton);

        loadFacultyData();

        this.setVisible(true);
    }

    private void loadFacultyData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Faculties.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(facultyId)) { 
                    nameField.setText(data[2]);
                    genderField.setText(data[3]);
                    nationalityField.setText(data[4]);
                    dobField.setText(data[5]);
                    phoneField.setText(data[6]);
                    emailField.setText(data[7]);
                    departmentField.setText(data[8]);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateFacultyData() {
        ArrayList<String> fileData = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("Faculties.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(facultyId)) {
                    data[2] = nameField.getText();
                    data[3] = genderField.getText();
                    data[4] = nationalityField.getText();
                    data[5] = dobField.getText();
                    data[6] = phoneField.getText();
                    data[7] = emailField.getText();
                    data[8] = departmentField.getText();
                    line = String.join(",", data);
                }
                fileData.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Faculties.txt"))) {
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
            updateFacultyData();
        }
    }
}

