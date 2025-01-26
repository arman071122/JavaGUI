package login;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class StuChPass extends JFrame {
	public StuChPass(StuHome homestu, Connection c, int sid, String stuName) {
		setTitle("Change Password");
		setSize(750, 500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(null);

		JLabel l1 = new JLabel("CHANGE PASSWORD", JLabel.CENTER);
		l1.setFont(new Font("Georgia", Font.BOLD, 24));
		l1.setBounds(125, 20, 500, 50);
		add(l1);

		JLabel pass = new JLabel("New Password: ");
		pass.setBounds(150, 125, 200, 30);
		add(pass);

		JPasswordField newPasstf = new JPasswordField();
		newPasstf.setBounds(300, 125, 300, 30);
		add(newPasstf);

		JLabel conPass = new JLabel("Confirm Password: ");
		conPass.setBounds(150, 175, 200, 30);
		add(conPass);

		JPasswordField confirmPasstf = new JPasswordField();
		confirmPasstf.setBounds(300, 175, 300, 30);
		add(confirmPasstf);

		JButton submit = new JButton("Submit");
		submit.setBounds(300, 250, 150, 40);
		add(submit);

		JButton goback = new JButton("Go Back");
		goback.setBounds(300, 300, 150, 40);
		add(goback);

		submit.addActionListener((e) -> {
			String newPass = new String(newPasstf.getPassword());
			String confirmPass = new String(confirmPasstf.getPassword());

			if (newPass.equals(confirmPass)) {
				PreparedStatement chP;
				try {
					chP = c.prepareStatement("UPDATE student SET password=? WHERE Sid=?");
					chP.setString(1, newPass);
					chP.setInt(2, sid);

					int chPRes = chP.executeUpdate();
					if (chPRes > 0) {
						JOptionPane.showMessageDialog(this, "Changed password of " + stuName + ".\nPlease login again",
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

		goback.addActionListener((e) -> {
			homestu.setVisible(true);
			dispose();
		});

		setVisible(true);
	}

}
