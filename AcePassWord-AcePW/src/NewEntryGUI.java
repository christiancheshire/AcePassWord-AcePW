
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * Copyright 2020
 * 3 Musketeers Tech (Michael Arcangel, Caleb Cheshire, Christian Cheshire)
 * AcePW - ICS 427 Project
 */

/*
 * NewEntryGUI implements a basic GUI for the user to input new passwords and usernames, along with the website for which each will be used
 */

public class NewEntryGUI {
	private JFrame frame;
	private JPanel panel;

	private JLabel instructionsLabel;

	private JLabel websiteLabel, websiteLabelWarning;
	private JLabel usernameLabel, usernameLabelWarning;
	private JLabel passwordLabel, passwordLabelWarning;

	private JTextField websiteTextField;
	private JTextField usernameTextField;
	private JTextField passwordTextField;

	private JButton submitButton;

	private Boolean submitButtonHit = new Boolean(false);
	private String nameOfUser = System.getProperty("user.name");

	public NewEntryGUI() {
		frame = new JFrame();
		frame.setTitle("Enter New Information");
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setSize(350, 500);
		frame.getContentPane();

		panel = new JPanel(null);
		panel.setBounds(0, 0, 300, 500);

		frame.add(panel);

		instructionsLabel = new JLabel("Enter Account Information");

		websiteLabel = new JLabel("Website Name");
		websiteLabelWarning = new JLabel("<html>*Please enter a " + "   website.<html>");
		websiteLabelWarning.setForeground(Color.RED);
		websiteLabelWarning.setVisible(false);
		websiteTextField = new JTextField();

		usernameLabel = new JLabel("Username");
		usernameLabelWarning = new JLabel("<html>*Please enter a " + "  username.<html>");
		usernameLabelWarning.setForeground(Color.RED);
		usernameLabelWarning.setVisible(false);
		usernameTextField = new JTextField();

		passwordLabel = new JLabel("Password");
		passwordLabelWarning = new JLabel("<html>*Please enter a " + "  password.<html>");
		passwordLabelWarning.setForeground(Color.RED);
		passwordLabelWarning.setVisible(false);
		passwordTextField = new JTextField();

		submitButton = new JButton("Submit");

		instructionsLabel.setBounds(5, 0, 200, 50);

		websiteLabel.setBounds(110, 35, 100, 50);
		websiteLabelWarning.setBounds(250, 35, 100, 50);
		websiteTextField.setBounds(70, 80, 170, 40);

		usernameLabel.setBounds(120, 120, 70, 50);
		usernameLabelWarning.setBounds(250, 120, 100, 50);
		usernameTextField.setBounds(70, 180, 170, 40);

		passwordLabel.setBounds(120, 230, 70, 50);
		passwordLabelWarning.setBounds(250, 230, 100, 50);
		passwordTextField.setBounds(70, 290, 170, 40);

		submitButton.setBounds(100, 370, 100, 40);
		submitButton.addActionListener(new SubmitButtonPressed());

		panel.add(instructionsLabel);
		panel.add(websiteLabel);
		panel.add(websiteLabelWarning);
		panel.add(websiteTextField);
		panel.add(usernameLabel);
		panel.add(usernameLabelWarning);
		panel.add(usernameTextField);
		panel.add(passwordLabel);
		panel.add(passwordLabelWarning);
		panel.add(passwordTextField);
		panel.add(submitButton);
	}

	public String getWebsite() {
		return websiteTextField.getText();
	}

	public String getUsername() {
		return usernameTextField.getText();
	}

	public String getPassword() {
		return passwordTextField.getText();
	}

	public Boolean getSubmitButtonHit() {
		return submitButtonHit;
	}

	public void writeNewEntryToFile() {
		String operatingSystem = System.getProperty("os.name");
		if (operatingSystem.equals("Windows 7") || operatingSystem.equals("Windows 8") || operatingSystem.equals("Windows 10")) {
			try {
				FileWriter outFile = new FileWriter("C:/Users/" + nameOfUser + "/Documents/AcePW Info.txt",
						true);
				/* true for appending/add elements to existing file */
				PrintWriter printOutFile = new PrintWriter(outFile);

				printOutFile.printf(getWebsite() + "\n" + getUsername() + "\n" + getPassword() + "\r\n");
				printOutFile.close();
			} catch (IOException ex) {
				System.err.format("ERROR: " + ex);
			}
		} else if (operatingSystem.equals("Windows XP")) {
			try {
				FileWriter outFile = new FileWriter(
						"C:/Documents and Settings/" + nameOfUser + "/My Documents/AcePW Info.txt", true);
				PrintWriter printOutFile = new PrintWriter(outFile);

				printOutFile.printf(getWebsite() + "\n" + getUsername() + "\n" + getPassword() + "\r\n");
				printOutFile.close();
			} catch (IOException ex) {
				System.err.format("ERROR: " + ex);
			}
		} else if (operatingSystem.equals("Mac OS X")) {
			try {
				FileWriter outFile = new FileWriter("/Users/" + nameOfUser + "/Documents/AcePW Info.txt", true);
				PrintWriter printOutFile = new PrintWriter(outFile);

				printOutFile.printf(getWebsite() + "\n" + getUsername() + "\n" + getPassword());
				printOutFile.close();
			} catch (IOException ex) {
				System.err.format("ERROR: " + ex);
			}
		}
	}

	public class SubmitButtonPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (getWebsite().equals("") && getUsername().equals("") && getPassword().equals("")) {
				websiteLabelWarning.setVisible(true);
				usernameLabelWarning.setVisible(true);
				passwordLabelWarning.setVisible(true);
			} else if (getWebsite().equals("")) {
				websiteLabelWarning.setVisible(true);
			} else if (getUsername().equals("")) {
				usernameLabel.setVisible(true);
			} else if (getPassword().equals("")) {
				passwordLabelWarning.setVisible(true);
			} else if (getWebsite().equals("") && getUsername().equals("")) {
				websiteLabelWarning.setVisible(true);
				usernameLabelWarning.setVisible(true);
			} else if (getUsername().equals("") && getPassword().equals("")) {
				usernameLabelWarning.setVisible(true);
				passwordLabelWarning.setVisible(true);
			} else if (getPassword().equals("") && getWebsite().equals("")) {
				passwordLabelWarning.setVisible(true);
				websiteLabelWarning.setVisible(true);
			} else {
				writeNewEntryToFile();
				frame.setVisible(false);
			}
		}
	}
}
