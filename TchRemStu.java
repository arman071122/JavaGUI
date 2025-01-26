package login;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class TchRemStu extends JFrame {
	TchRemStu(TchHome hometch, Connection c, int classIn) {
		setTitle("Remove Student");
		setSize(750, 500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(null);

		JLabel headingLabel = new JLabel("REMOVE STUDENT", JLabel.CENTER);
		headingLabel.setFont(new Font("Georgia", Font.BOLD, 24));
		headingLabel.setBounds(125, 20, 500, 50);
		add(headingLabel);

		JLabel enterIdLabel = new JLabel("Enter Student ID : ");
		enterIdLabel.setBounds(150, 120, 150, 30);
		add(enterIdLabel);

		JTextField studentIdField = new JTextField();
		studentIdField.setBounds(300, 120, 300, 30);
		add(studentIdField);

		JButton submitButton = new JButton("Submit");
		submitButton.setBounds(300, 180, 150, 40);
		add(submitButton);

		JButton goBackButton = new JButton("Go Back");
		goBackButton.setBounds(300, 240, 150, 40);
		goBackButton.addActionListener((e) -> {
			hometch.setVisible(true);
			dispose();
		});
		add(goBackButton);

		submitButton.addActionListener((e) -> {
			int sid = Integer.parseInt(studentIdField.getText());

			PreparedStatement getStu;
			try {
				getStu = c.prepareStatement("SELECT Sname, Class FROM student WHERE Sid = ?");
				getStu.setInt(1, sid);
				ResultSet stuRes = getStu.executeQuery();

				if (stuRes.next()) {
					String delName = stuRes.getString("Sname");
					int delClass = stuRes.getInt("Class");

					if (delClass == classIn) {

						int response = JOptionPane.showConfirmDialog(this,
								"Are you sure you want to delete " + delName + " of " + delClass + "?", "Confirm",
								JOptionPane.YES_NO_OPTION);

						if (response == JOptionPane.YES_OPTION) {
							PreparedStatement delStu = c.prepareStatement("DELETE FROM student WHERE Sid = ?");
							PreparedStatement delMark = c.prepareStatement("DELETE FROM marklist WHERE Sid = ?");
							delStu.setInt(1, sid);
							delMark.setInt(1, sid);

							int delSRes = delStu.executeUpdate();
							int delMRes = delMark.executeUpdate();

							JOptionPane.showMessageDialog(this, "Deleted " + delSRes + " row(s) from student and "
									+ delMRes + " row(s) from marklist.", "Success", JOptionPane.INFORMATION_MESSAGE);
							hometch.setVisible(true);
							dispose();

						} else {
							return;
						}

					} else {
						JOptionPane.showMessageDialog(this,
								"Error: Student " + sid + " is not from your class. You cannot delete this student.",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(this, "Error: No student found with ID " + sid + ". Try again.",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			} catch (SQLException ex) {

				ex.printStackTrace();
			}

		});

		setVisible(true);
	}
}
