package faculty;

import admin.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Faculty extends JFrame implements ActionListener {
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
    private JComboBox<String> courseComboBox, sectionComboBox;
    private JButton backButton, addMarksButton, dashboardButton, filterButton , editButton, refrashButton;
    private JTable studentTable;
    private DefaultTableModel tableModel;
    private String facultyId;
    private static final String FILE_NAME = "..\\UMS\\Data\\Courses.txt";

    public Faculty(String facultyId) {
        this.facultyId = facultyId;
        setTitle("Faculty Dashboard");
		ImageIcon image = new ImageIcon("..\\UMS\\image\\AIUB.png");
        this.setIconImage(image.getImage());
		
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setVisible(true);
        setSize(800, 650);

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
        dashboardButton.setIcon(new ImageIcon("..\\UMS\\image\\dashboard.png"));
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
		}else if (e.getSource() == refrashButton) {
			new Faculty(facultyId);
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
		
		try (BufferedReader studentReader = new BufferedReader(new FileReader("..\\UMS\\Data\\StudentCourses.txt"));
			 BufferedReader marksReader = new BufferedReader(new FileReader("..\\UMS\\Data\\StudentMarks.txt"))) {
			
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

		final String selectedCourse = (String) courseComboBox.getSelectedItem();
		final String selectedSection = (String) sectionComboBox.getSelectedItem();
		final String semester;

		try {
			semester = ManageCourse.readSemester(); 
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Error reading semester: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (selectedCourse == null || selectedSection == null) {
			JOptionPane.showMessageDialog(this, "Please select a course and section.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		File marksFile = new File("..\\UMS\\Data\\StudentMarks.txt");
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

			boolean alreadyExists = existingRecords.stream().anyMatch(record -> {
				String[] parts = record.split(",");
				return parts.length == 12 && parts[0].equals(studentId) && parts[1].equals(selectedCourse)
						&& parts[2].equals(selectedSection) && parts[11].equals(semester);
			});

			if (alreadyExists) {
				JOptionPane.showMessageDialog(this, "Marks already added for Student ID: " + studentId +
						" in Course: " + selectedCourse + ". Use the Edit button to modify.", "Error", JOptionPane.ERROR_MESSAGE);
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

				writer.write(studentId + "," + selectedCourse + "," + selectedSection + "," +
						attendance + "," + quiz1 + "," + quiz2 + "," + assignment + "," +
						midExam + "," + finalExam + "," + totalMarks + "," + cgpa + "," + semester);
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
			JOptionPane.showMessageDialog(this, "Please select a student to edit marks.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		String selectedCourse = (String) courseComboBox.getSelectedItem();
		String selectedSection = (String) sectionComboBox.getSelectedItem();
		String semester;

		try {
			semester = ManageCourse.readSemester();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Error reading semester: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (selectedCourse == null || selectedSection == null) {
			JOptionPane.showMessageDialog(this, "Please select a course and section.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		String studentId = (String) tableModel.getValueAt(selectedRow, 0);
		File marksFile = new File("..\\UMS\\Data\\StudentMarks.txt");
		List<String> updatedRecords = new ArrayList<>();
		boolean recordFound = false;

		try (BufferedReader reader = new BufferedReader(new FileReader(marksFile))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(",");
				if (parts.length == 12 && parts[0].equals(studentId) && parts[1].equals(selectedCourse) 
						&& parts[2].equals(selectedSection) && parts[11].equals(semester)) {
					
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

					String updatedRecord = studentId + "," + selectedCourse + "," + selectedSection + "," +
							attendance + "," + quiz1 + "," + quiz2 + "," + assignment + "," +
							midExam + "," + finalExam + "," + totalMarks + "," + cgpa + "," + semester;
					
					updatedRecords.add(updatedRecord);
					recordFound = true;
				} else {
					updatedRecords.add(line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error reading marks data.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (!recordFound) {
			JOptionPane.showMessageDialog(this, "No existing marks found for the selected student.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(marksFile))) {
			for (String record : updatedRecords) {
				writer.write(record);
				writer.newLine();
			}
			JOptionPane.showMessageDialog(this, "Marks updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error saving updated marks.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}



    public static String GetName(String facultyId) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("..\\UMS\\Data\\Faculties.txt"))) {
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
		if (value == null || value.trim().isEmpty()) {
			return "0";
		}
		try {
			int num = Integer.parseInt(value.trim());
			return String.valueOf(num);
		} catch (NumberFormatException e) {
			return "0";
		}
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
        try (BufferedReader reader = new BufferedReader(new FileReader("..\\UMS\\Data\\Students.txt"))) {
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
