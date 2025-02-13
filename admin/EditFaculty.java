package admin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class EditFaculty extends JFrame implements ActionListener {
    private JTextField idTF, passwordTF, fullNameTF, dobTF, phoneTF, emailTF;
    private JRadioButton maleRB, femaleRB, otherRB;
    private JComboBox<String> nationalityComboBox, departmentComboBox;
    private JButton saveButton, cancelButton;
    private int rowIndex;
    private DefaultTableModel tableModel;

    public EditFaculty(DefaultTableModel model, int row) {
        this.rowIndex = row;
        this.tableModel = model;

        setTitle("Edit Faculty");
		ImageIcon image = new ImageIcon("..\\UMS\\image\\AIUB.png");
        this.setIconImage(image.getImage());
        setSize(500, 600);
		this.setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel formPanel = new JPanel(new GridLayout(10, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        idTF = new JTextField(model.getValueAt(row, 0).toString());
        passwordTF = new JTextField(model.getValueAt(row, 1).toString());
        fullNameTF = new JTextField(model.getValueAt(row, 2).toString());
        dobTF = new JTextField(model.getValueAt(row, 5).toString());
        phoneTF = new JTextField(model.getValueAt(row, 6).toString());
        emailTF = new JTextField(model.getValueAt(row, 7).toString());

        maleRB = new JRadioButton("Male");
        femaleRB = new JRadioButton("Female");
        otherRB = new JRadioButton("Other");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRB);
        genderGroup.add(femaleRB);
        genderGroup.add(otherRB);
        
        String gender = model.getValueAt(row, 3).toString();
        if (gender.equals("Male")) maleRB.setSelected(true);
        else if (gender.equals("Female")) femaleRB.setSelected(true);
        else otherRB.setSelected(true);

        nationalityComboBox = new JComboBox<>(new String[]{"Bangladeshi", "Foreign"});
        nationalityComboBox.setSelectedItem(model.getValueAt(row, 4).toString());

        departmentComboBox = new JComboBox<>(Methods.readFile("..\\\\UMS\\\\Data\\\\departments.txt"));
        departmentComboBox.setSelectedItem(model.getValueAt(row, 8).toString());

        formPanel.add(new JLabel("ID:"));
        formPanel.add(idTF);
        formPanel.add(new JLabel("Password:"));
        formPanel.add(passwordTF);
        formPanel.add(new JLabel("Full Name:"));
        formPanel.add(fullNameTF);
        formPanel.add(new JLabel("Date of Birth:"));
        formPanel.add(dobTF);
        formPanel.add(new JLabel("Gender:"));

        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderPanel.add(maleRB);
        genderPanel.add(femaleRB);
        genderPanel.add(otherRB);
        formPanel.add(genderPanel);

        formPanel.add(new JLabel("Nationality:"));
        formPanel.add(nationalityComboBox);
        formPanel.add(new JLabel("Department:"));
        formPanel.add(departmentComboBox);
        formPanel.add(new JLabel("Phone:"));
        formPanel.add(phoneTF);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailTF);

        JPanel buttonPanel = new JPanel();
        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");
        saveButton.addActionListener(this);
        cancelButton.addActionListener(this);
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            tableModel.setValueAt(idTF.getText(), rowIndex, 0);
            tableModel.setValueAt(passwordTF.getText(), rowIndex, 1);
            tableModel.setValueAt(fullNameTF.getText(), rowIndex, 2);
            tableModel.setValueAt(dobTF.getText(), rowIndex, 5);
            tableModel.setValueAt(maleRB.isSelected() ? "Male" : femaleRB.isSelected() ? "Female" : "Other", rowIndex, 3);
            tableModel.setValueAt(nationalityComboBox.getSelectedItem(), rowIndex, 4);
            tableModel.setValueAt(departmentComboBox.getSelectedItem(), rowIndex, 8);
            tableModel.setValueAt(phoneTF.getText(), rowIndex, 6);
            tableModel.setValueAt(emailTF.getText(), rowIndex, 7);

            JOptionPane.showMessageDialog(this, "Faculty details updated!");
            dispose();
        } else if (e.getSource() == cancelButton) {
            dispose();
        }
    }
}
