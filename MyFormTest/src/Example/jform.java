package Example;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class jform extends JFrame {

    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JFrame iFrame = new JFrame();
                    JButton btnButton = new JButton("ok");
                    iFrame.setLayout(null);
                    iFrame.setBounds(0, 0, 100, 200);
                    btnButton.setBounds(20, 20, 50, 50);
                    iFrame.setBackground(Color.black);
                    iFrame.setName("test");
                    iFrame.setForeground(Color.white);
                    iFrame.setTitle("fffwefffffffff");
                    iFrame.getContentPane().add(btnButton);
                    JLabel label = creatLabel("fefe");
                    iFrame.getContentPane().add(label);
                    iFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static JLabel creatLabel(String title) {
        JLabel label = new JLabel(title);
        label.setBounds(80, 20, 100, 40);
        return label;
    }

    /**
     * Create the frame.
     */
    public jform() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
    }

}
