package login;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class TchModStu extends JFrame {
	int sid, modClass;
	String modName;

	public TchModStu(TchHome hometch, Connection c, int classIn) {
		setTitle("Modify Student");
		setSize(750, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(null);

		JLabel l1 = new JLabel("MODIFY STUDENT", JLabel.CENTER);
		l1.setFont(new Font("Georgia", Font.BOLD, 24));
		l1.setBounds(125, 20, 500, 50);
		add(l1);

		JLabel enterIdLabel = new JLabel("Enter Student ID: ");
		enterIdLabel.setBounds(150, 100, 150, 30);
		add(enterIdLabel);

		JTextField studentIdField = new JTextField();
		studentIdField.setBounds(300, 100, 300, 30);
		add(studentIdField);

		JButton submitButton = new JButton("Submit");
		submitButton.setBounds(300, 170, 150, 40);
		add(submitButton);

		JLabel message = new JLabel("Message", JLabel.CENTER);
		message.setBounds(150, 230, 450, 30);
		add(message);

		submitButton.addActionListener((e) -> {
			sid = Integer.parseInt(studentIdField.getText());
			try {
				PreparedStatement getStu = c.prepareStatement("SELECT Sname, Class FROM student WHERE Sid = ?");
				getStu.setInt(1, sid);
				ResultSet stuRes = getStu.executeQuery();

				if (stuRes.next()) {
					modName = stuRes.getString("Sname");
					modClass = stuRes.getInt("Class");

					if (modClass == classIn) {

						message.setText("Modifying student: " + modName + ", Class: " + modClass);
					} else {
						message.setText("Student: " + modName + " not from your class");
					}
				} else {
					message.setText("Student with " + sid + " not found");
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}

		});

		JButton infoB = new JButton("PERSONAL INFO");
		infoB.setBounds(150, 300, 200, 50);
		add(infoB);

		infoB.addActionListener((e) -> {
			new TchStuPersEdit(this, c, classIn, sid, modName, modClass);
			setVisible(false);

		});

		JButton markB = new JButton("MARKS");
		markB.setBounds(400, 300, 200, 50);
		add(markB);

		markB.addActionListener((e) -> {
			new TchStuMarksEdit(this, c, classIn, sid, modName, modClass);
			setVisible(false);
		});

		JButton goBack = new JButton("GO BACK");
		goBack.setBounds(275, 450, 200, 50);
		goBack.addActionListener((e) -> {
			hometch.setVisible(true);
			dispose();
		});
		add(goBack);

		setVisible(true);
	}

}