import org.omg.CORBA.PRIVATE_MEMBER;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

/**
 * Created by CTWLPC on 2017/5/5.
 */
public class SwingTest extends JPanel {
    private static final Integer WIDTH = 600;
    private static final Integer HEIGHT = 300;
    private JFrame jFrame;

    private void add(Component c, GridBagConstraints constraints, int x, int y, int w, int h) {
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = w;
        constraints.gridheight = h;
        add(c, constraints);
    }

    public SwingTest() {
        jFrame = new JFrame("swingtest");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
        jFrame.add(this, BorderLayout.WEST);
        jFrame.setSize(WIDTH, HEIGHT);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int width = screenSize.width;
        int heght = screenSize.height;
        int x = (width - WIDTH) / 2;
        int y = (heght - HEIGHT) / 2;
        jFrame.setLocation(x, y);
        JButton ok = new JButton("login");
        JButton cancel = new JButton("cancel");
        JLabel title = new JLabel("welcome");
        JLabel name = new JLabel("name");
        JLabel password = new JLabel("password");
        final JTextField nameInput = new JTextField(15);
        final JTextField passwordInput = new JTextField(15);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.EAST;
        constraints.weightx = 3;
        constraints.weighty = 4;
        add(title, constraints, 0, 0, 4, 1);
        add(name, constraints, 0, 1, 1, 1);
        add(password, constraints, 0, 2, 1, 1);
        add(nameInput, constraints, 2, 1, 1, 1);
        add(passwordInput, constraints, 2, 2, 1, 1);
        add(ok, constraints, 0, 3, 1, 1);
        add(cancel, constraints, 2, 3, 1, 1);
        jFrame.setResizable(false);
        jFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingTest swingTest = new SwingTest();
    }
}
