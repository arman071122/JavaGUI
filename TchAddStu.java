package login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class TchAddStu extends JFrame {
	TchAddStu(TchHome hometch, Connection c, int classIn) {
		setTitle("Add Student");
		setSize(750, 500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(null);

		JLabel l1 = new JLabel("ADD STUDENT", JLabel.CENTER);
		l1.setFont(new java.awt.Font("Georgia", java.awt.Font.BOLD, 24));
		l1.setBounds(125, 20, 500, 50);
		add(l1);

		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setBounds(150, 100, 100, 30);
		add(nameLabel);

		JTextField nameField = new JTextField();
		nameField.setBounds(300, 100, 300, 30);
		add(nameField);

		JLabel passLabel = new JLabel("Password:");
		passLabel.setBounds(150, 150, 100, 30);
		add(passLabel);

		JPasswordField passField = new JPasswordField();
		passField.setBounds(300, 150, 300, 30);
		add(passField);

		JLabel confirmPassLabel = new JLabel("Confirm Password:");
		confirmPassLabel.setBounds(150, 200, 150, 30);
		add(confirmPassLabel);

		JPasswordField confirmPassField = new JPasswordField();
		confirmPassField.setBounds(300, 200, 300, 30);
		add(confirmPassField);

		JButton submitButton = new JButton("Submit");
		submitButton.setBounds(300, 260, 150, 40);
		add(submitButton);

		JButton goback = new JButton("Go Back");
		goback.setBounds(300, 310, 150, 40);
		add(goback);

		goback.addActionListener((e) -> {
			hometch.setVisible(true);
			dispose();
		});

		submitButton.addActionListener((e) -> {
			String nameStu = nameField.getText();
			String password = new String(passField.getPassword());
			String Cpassword = new String(confirmPassField.getPassword());

			if (password.equals(Cpassword)) {
				try {
					PreparedStatement add = c
							.prepareStatement("INSERT INTO student(Sname, class, password) VALUES (?, ?, ?)");
					add.setString(1, nameStu);
					add.setInt(2, classIn);
					add.setString(3, password);
					int addRes = add.executeUpdate();

					PreparedStatement getId = c.prepareStatement("SELECT Sid FROM student WHERE Sname=?");
					getId.setString(1, nameStu);
					ResultSet getIdRes = getId.executeQuery();
					getIdRes.next();
					int id = getIdRes.getInt(1);

					if (addRes > 0) {
						JOptionPane.showMessageDialog(this, "Successfully added student " + nameStu + " to class "
								+ classIn + " and his/her id is " + id, "Success", JOptionPane.INFORMATION_MESSAGE);
						hometch.setVisible(true);
						dispose();
					}
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(this, "SQL ERROR", "Error", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(this, "Password Mismatch", "Error", JOptionPane.ERROR_MESSAGE);
			}
		});

		setVisible(true);
	}
}
