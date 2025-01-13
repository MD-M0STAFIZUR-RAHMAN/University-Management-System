import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

class Faculty extends JFrame {
	Faculty() {
        ImageIcon image = new ImageIcon("E:/UMS/image/AIUB.png");
        this.setIconImage(image.getImage());
        this.setTitle("Faculty Panel");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setSize(500, 600);
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        Container c = this.getContentPane();
        c.setBackground(new Color(220, 250, 255));

        this.setVisible(true);
    }
}