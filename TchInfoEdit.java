package login;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class TchInfoEdit extends JFrame {
	public TchInfoEdit(TchHome hometch, Connection c, int tid) {
		setTitle("Edit Teacher Info");
		setSize(750, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

		JLabel l1 = new JLabel("EDIT TEACHER INFO", JLabel.CENTER);
		l1.setFont(new Font("Georgia", Font.BOLD, 24));
		l1.setBounds(125, 20, 500, 50);
		add(l1);

		JLabel nameL = new JLabel("Name:");
		nameL.setBounds(150, 100, 200, 30);
		add(nameL);

		JTextField nameField = new JTextField();
		nameField.setBounds(350, 100, 250, 30);
		add(nameField);

		JLabel classInChargeL = new JLabel("Class In-Charge:");
		classInChargeL.setBounds(150, 150, 200, 30);
		add(classInChargeL);

		String[] classes = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
		JComboBox classDropdown = new JComboBox(classes);
		classDropdown.setBounds(350, 150, 250, 30);
		add(classDropdown);

		JLabel passwordL = new JLabel("Password:");
		passwordL.setBounds(150, 200, 200, 30);
		add(passwordL);

		JPasswordField passwordField = new JPasswordField();
		passwordField.setBounds(350, 200, 250, 30);
		add(passwordField);

		JLabel confirmPasswordL = new JLabel("Confirm Password:");
		confirmPasswordL.setBounds(150, 250, 200, 30);
		add(confirmPasswordL);

		JPasswordField confirmPasswordField = new JPasswordField();
		confirmPasswordField.setBounds(350, 250, 250, 30);
		add(confirmPasswordField);

		JButton submit = new JButton("Submit");
		submit.setBounds(300, 350, 150, 40);
		add(submit);

		JButton goBack = new JButton("Go Back");
		goBack.setBounds(300, 420, 150, 40);
		goBack.addActionListener((e) -> {
			hometch.setVisible(true);
			dispose();
		});
		add(goBack);

		submit.addActionListener((e) -> {
			String newname = nameField.getText();
			String newclass = (String) classDropdown.getSelectedItem();
			String newpass = new String(passwordField.getPassword());
			String conpass = new String(confirmPasswordField.getPassword());

			if (newpass.equals(conpass)) {
				PreparedStatement upTc;
				try {
					upTc = c.prepareStatement("UPDATE teacher SET Tname=?, Class_In_Charge=?, password=? WHERE Tid=?");
					upTc.setString(1, newname);
					upTc.setInt(2, Integer.valueOf(newclass));
					upTc.setString(3, newpass);
					upTc.setInt(4, tid);

					int upTcRes = upTc.executeUpdate();
					if (upTcRes > 0) {
						JOptionPane.showMessageDialog(this,
								"Personal details have been modified. \nYou will be logged out for security reasons. \nPlease login again.",
								"Info", JOptionPane.INFORMATION_MESSAGE);
						dispose();
						new Login(c);
					}
				} catch (SQLException ex) {

					ex.printStackTrace();
				}

			} else {
				JOptionPane.showMessageDialog(this, "Password Mismatch", "Error", JOptionPane.ERROR_MESSAGE);
			}

		});

		setVisible(true);
	}
}
