import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DisplayArea extends JFrame {
    private JPanel contentPane;
    private JTextArea textArea;

    public DisplayArea() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 192, 203));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setTitle("Simulation");

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBackground(new Color(255, 255, 255));
        scrollPane.setBounds(10, 11, 416, 241);
        contentPane.add(scrollPane);

        textArea = new JTextArea();
        textArea.setBackground(new Color(255, 240, 245));
        scrollPane.setViewportView(textArea);
    }

    public void setText(String s){
        textArea.setText(s);
    }
}