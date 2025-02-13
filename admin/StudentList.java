package admin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class StudentList extends JFrame implements ActionListener {
    private JTable studentTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;
    private JComboBox<String> departmentFilter;
    private JButton updateButton, removeButton, searchButton, filterButton, refreshButton , backButton;

    public StudentList() {
		ImageIcon image = new ImageIcon("..\\UMS\\image\\AIUB.png");
        this.setIconImage(image.getImage());
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
		Button(searchButton, new Color(70, 130, 180), Color.WHITE);
		searchButton.setBounds(280, 320, 100, 30);
		searchButton.addActionListener(this);
		add(searchButton);

        JLabel filterLabel = new JLabel("Filter by Department:");
        filterLabel.setBounds(10, 360, 150, 30);
        add(filterLabel);

        String[] departments = Methods.readFile("..\\UMS\\Data\\departments.txt");
        departmentFilter = new JComboBox<>(departments);
        departmentFilter.setBounds(160, 360, 150, 30);
        add(departmentFilter);

		filterButton = new JButton("Filter");
		Button(filterButton, new Color(34, 139, 34), Color.WHITE);
		filterButton.setBounds(320, 360, 100, 30);
		filterButton.addActionListener(this);
		add(filterButton);

		updateButton = new JButton("Update");
		Button(updateButton, new Color(255, 165, 0), Color.BLACK);
		updateButton.setBounds(10, 400, 100, 30);
		updateButton.addActionListener(this);
		add(updateButton);

		removeButton = new JButton("Remove");
		Button(removeButton, new Color(220, 20, 60), Color.WHITE);
		removeButton.setBounds(120, 400, 100, 30);
		removeButton.addActionListener(this);
		add(removeButton);

		refreshButton = new JButton("Refresh");
		Button(refreshButton, new Color(0, 191, 255), Color.BLACK);
		refreshButton.setBounds(230, 400, 100, 30);
		refreshButton.addActionListener(this);
		add(refreshButton);
		
		backButton = new JButton("Back");
		Button(backButton, new Color(100, 150, 250), Color.WHITE);
		backButton.setBounds(600, 500, 100, 30);
		backButton.addActionListener(e -> { new Admin(); this.dispose(); });
		add(backButton);

        loadStudents();
        setVisible(true);
    }
	
	private void Button(JButton button, Color bgColor, Color fgColor) {
		button.setBackground(bgColor);
		button.setForeground(fgColor);
		button.setFont(new Font("Arial", Font.BOLD, 14));
		button.setFocusPainted(false);
		button.setBorderPainted(false);
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}


    private void loadStudents() {
        tableModel.setRowCount(0);
        try (BufferedReader reader = new BufferedReader(new FileReader("..\\UMS\\Data\\Students.txt"))) {
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
        try (BufferedReader reader = new BufferedReader(new FileReader("..\\UMS\\Data\\Students.txt"))) {
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

        try (BufferedReader reader = new BufferedReader(new FileReader("..\\UMS\\Data\\Students.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("..\\UMS\\Data\\Students_temp.txt"))) {

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

        new File("..\\UMS\\Data\\Students.txt").delete();
        new File("..\\UMS\\Data\\Students_temp.txt").renameTo(new File("..\\UMS\\Data\\Students.txt"));
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
           new UpdateStudent();
        } else if (e.getSource() == refreshButton) {
           new StudentList();
		   this.dispose ();
        }
    }
}
