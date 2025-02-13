package admin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;

public class FrontPage extends JFrame implements ActionListener {
    private JButton button1, button2;

    public FrontPage() {
        ImageIcon image = new ImageIcon("..\\UMS\\image\\AIUB.png");
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
        ImageIcon image = new ImageIcon("..\\UMS\\image\\AIUB.png");
        ImageIcon image2 = new ImageIcon("..\\UMS\\image\\AIUB2.jpg");

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

        button2 = new JButton("Play Video");
        button2.setBounds(890, 600, 160, 30);
        button2.setBackground(new Color(0, 153, 76));
        button2.setForeground(Color.WHITE);
        button2.setFont(buttonFont);
        button2.setFocusPainted(false);
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
            this.dispose();
        } else if (e.getSource() == button2) {
            playVideo();
        }
    }

    private void playVideo() {
        try {
            File videoFile = new File("..\\UMS\\image\\AIUB.mp4");
            if (videoFile.exists()) {
                Desktop.getDesktop().open(videoFile);
            } else {
                JOptionPane.showMessageDialog(this, "Video file not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Cannot open video file!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
