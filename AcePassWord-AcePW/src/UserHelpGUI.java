
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/*
 * Copyright 2020
 * 3 Musketeers Tech (Michael Arcangel, Caleb Cheshire, Christian Cheshire)
 * AcePW - ICS 427 Project
 */

public class UserHelpGUI {
    private static final Logger LOGGER = Logger.getLogger(UserHelpGUI.class.getName());
    private JFrame frame;
    private JTextArea textArea;
    private JScrollPane scroll;
    private JButton button;

    public UserHelpGUI() {
        this.frame = new JFrame("Help");
        this.textArea = new JTextArea(5, 20);
        this.scroll = new JScrollPane(textArea);
        this.button = new JButton("Button");
        this.initFrame();
        this.initTextArea();
        this.frame.add(scroll);
        this.scroll.setBounds(0, 0, 350, 370);
        this.button.addActionListener(new Action());
    }

    private void initFrame() {
        this.frame.setSize(350, 370);
        this.frame.setVisible(true);
        this.frame.getContentPane();
    }

    private void initTextArea() {
        this.textArea.setText("User Help \n\nAcePassWord (AcePW) is a password management application.\n\n"
        		+ "To add a new password, "
        		+ "use the File dropdown and select New. In the new window, fill in the blank fields and click Submit."
        		+ " The password and other information will then be saved to the password file.\n\n"
        		+ "To view the recently added password along with all the others, go to the File dropdown and select Load."
        		+ " This will refresh the screen and display all the passwords and information which has been saved.\n\n"
        		+ "For additional help, visit the AcePassWord webpage on GitHub at https://github.com/christiancheshire/AcePassWord-AcePW.");
        this.textArea.setFont(new Font("Comic Sans", Font.PLAIN, 15));
        this.textArea.setWrapStyleWord(true);
        this.textArea.setLineWrap(true);
        this.textArea.setEditable(false);
    }

    private static class Action implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            LOGGER.log(Level.INFO, "Help Button Clicked");
            JFrame frame2 = new JFrame("Clicked!");
            frame2.setVisible(true);
            frame2.setSize(200, 200);
            JLabel label2 = new JLabel("Button has been clicked");
            JPanel panel2 = new JPanel();
            frame2.add(panel2);
            panel2.add(label2);
        }
    }
}
