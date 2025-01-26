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

public class TchViewStu extends JFrame {
	TchViewStu(TchHome hometch, Connection c, int classIn) {
		setTitle("View Student");
		setSize(750, 700);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(null);

		JLabel headingLabel = new JLabel("VIEW STUDENT DETAILS", JLabel.CENTER);
		headingLabel.setFont(new Font("Georgia", Font.BOLD, 24));
		headingLabel.setBounds(125, 20, 500, 50);
		add(headingLabel);

		JLabel enterIdLabel = new JLabel("Enter Student ID: ");
		enterIdLabel.setBounds(150, 120, 150, 30);
		add(enterIdLabel);

		JTextField studentIdField = new JTextField();
		studentIdField.setBounds(300, 120, 300, 30);
		add(studentIdField);

		JButton submitButton = new JButton("Submit");
		submitButton.setBounds(300, 180, 150, 40);
		add(submitButton);

		JLabel nameLabel = new JLabel("Name : ");
		nameLabel.setBounds(250, 260, 100, 30);
		add(nameLabel);

		JLabel nameValueLabel = new JLabel("");
		nameValueLabel.setBounds(400, 260, 300, 30);
		add(nameValueLabel);

		JLabel classLabel = new JLabel("Class : ");
		classLabel.setBounds(250, 300, 100, 30);
		add(classLabel);

		JLabel classValueLabel = new JLabel("");
		classValueLabel.setBounds(400, 300, 300, 30);
		add(classValueLabel);

		JLabel mathsLabel = new JLabel("Maths :");
		mathsLabel.setBounds(250, 340, 100, 30);
		add(mathsLabel);

		JLabel mathsValueLabel = new JLabel("");
		mathsValueLabel.setBounds(400, 340, 100, 30);
		add(mathsValueLabel);

		JLabel scienceLabel = new JLabel("Science :");
		scienceLabel.setBounds(250, 380, 100, 30);
		add(scienceLabel);

		JLabel scienceValueLabel = new JLabel("");
		scienceValueLabel.setBounds(400, 380, 100, 30);
		add(scienceValueLabel);

		JLabel englishLabel = new JLabel("English :");
		englishLabel.setBounds(250, 420, 100, 30);
		add(englishLabel);

		JLabel englishValueLabel = new JLabel("");
		englishValueLabel.setBounds(400, 420, 100, 30);
		add(englishValueLabel);

		JLabel ssLabel = new JLabel("SS :");
		ssLabel.setBounds(250, 460, 100, 30);
		add(ssLabel);

		JLabel ssValueLabel = new JLabel("");
		ssValueLabel.setBounds(400, 460, 100, 30);
		add(ssValueLabel);

		JLabel malayalamLabel = new JLabel("Malayalam :");
		malayalamLabel.setBounds(250, 500, 100, 30);
		add(malayalamLabel);

		JLabel malayalamValueLabel = new JLabel("");
		malayalamValueLabel.setBounds(400, 500, 100, 30);
		add(malayalamValueLabel);

		JButton goBack = new JButton("Go Back");
		goBack.setBounds(300, 560, 150, 40);
		goBack.addActionListener((e) -> {
			hometch.setVisible(true);
			dispose();
		});
		add(goBack);

		submitButton.addActionListener((e) -> {
			int sId = Integer.parseInt(studentIdField.getText());

			try {
				PreparedStatement vSid;
				vSid = c.prepareStatement(
						"SELECT s.sname, s.class, m.maths, m.sci, m.eng, m.ss, m.mal FROM student s JOIN marklist m ON s.sid = m.sid WHERE s.sid = ?");
				vSid.setInt(1, sId);
				ResultSet vSidRes = vSid.executeQuery();

				if (vSidRes.next()) {

					nameValueLabel.setText(vSidRes.getString("Sname"));
					classValueLabel.setText(String.valueOf(vSidRes.getInt("Class")));
					mathsValueLabel.setText(String.valueOf(vSidRes.getInt("Maths")));
					scienceValueLabel.setText(String.valueOf(vSidRes.getInt("Sci")));
					englishValueLabel.setText(String.valueOf(vSidRes.getInt("Eng")));
					ssValueLabel.setText(String.valueOf(vSidRes.getInt("SS")));
					malayalamValueLabel.setText(String.valueOf(vSidRes.getInt("Mal")));

				} else {
					nameValueLabel.setText("");
					classValueLabel.setText("");
					mathsValueLabel.setText("");
					scienceValueLabel.setText("");
					englishValueLabel.setText("");
					ssValueLabel.setText("");
					malayalamValueLabel.setText("");
					JOptionPane.showMessageDialog(this, "Error: No student found with ID " + sId + ". Try again.",
							"Error", JOptionPane.ERROR_MESSAGE);
				}

			} catch (SQLException ex) {
				ex.printStackTrace();
			}

		});

		setVisible(true);
	}
}
