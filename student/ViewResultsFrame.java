package student;

import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ViewResultsFrame extends JFrame {
    private JTable resultsTable;
    private DefaultTableModel tableModel;
    private JComboBox<String> semesterComboBox;
    private String studentId;
	private JButton backButton ;

    public ViewResultsFrame(String studentId) {
        this.studentId = studentId;

        setTitle("View Results");
        setSize(800, 600);
		ImageIcon image = new ImageIcon("..\\UMS\\image\\AIUB.png");
        this.setIconImage(image.getImage());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        semesterComboBox = new JComboBox<>();
        semesterComboBox.setBounds(50, 20, 200, 30);
        loadSemesters();
        semesterComboBox.addActionListener(e -> loadResults(studentId));
        add(semesterComboBox);

        tableModel = new DefaultTableModel(new String[]{"Course", "Total Marks", "CGPA"}, 0);
        resultsTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(resultsTable);
        scrollPane.setBounds(50, 70, 700, 400);
        add(scrollPane);
		
		backButton = new JButton("Back");
        backButton.setBounds(600, 520, 150, 30);
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(new Color(100,150, 250));
        backButton.addActionListener(e -> { this.dispose(); });
        add(backButton);

        loadResults(studentId);

        setVisible(true);
    }

    private void loadSemesters() {
        try (BufferedReader reader = new BufferedReader(new FileReader("..\\UMS\\Data\\semesters.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                semesterComboBox.addItem(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadResults(String studentId) {
        tableModel.setRowCount(0);

        String selectedSemester = (String) semesterComboBox.getSelectedItem();

        try (BufferedReader reader = new BufferedReader(new FileReader("..\\UMS\\Data\\StudentMarks.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 12 && data[0].equals(studentId) && data[11].equals(selectedSemester)) {
                    String course = data[1];
                    String totalMarks = data[9];
                    String cgpa = data[10];
                    tableModel.addRow(new String[]{course, totalMarks, cgpa});
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


