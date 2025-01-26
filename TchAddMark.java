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

public class TchAddMark extends JFrame {
	TchAddMark(TchHome hometch, Connection c, int classIn) {
		setTitle("Add Marks");
		setSize(750, 650);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(null);

		JLabel titleLabel = new JLabel("ADD MARKS", JLabel.CENTER);
		titleLabel.setFont(new Font("Georgia", Font.BOLD, 24));
		titleLabel.setBounds(125, 20, 500, 50);
		add(titleLabel);

		JLabel studentIdLabel = new JLabel("Student ID : ");
		studentIdLabel.setBounds(150, 100, 100, 30);
		add(studentIdLabel);

		JTextField studentIdField = new JTextField();
		studentIdField.setBounds(300, 100, 300, 30);
		add(studentIdField);

		JLabel mathLabel = new JLabel("Maths : ");
		mathLabel.setBounds(150, 150, 100, 30);
		add(mathLabel);

		JTextField mathField = new JTextField();
		mathField.setBounds(300, 150, 300, 30);
		add(mathField);

		JLabel scienceLabel = new JLabel("Science : ");
		scienceLabel.setBounds(150, 200, 100, 30);
		add(scienceLabel);

		JTextField scienceField = new JTextField();
		scienceField.setBounds(300, 200, 300, 30);
		add(scienceField);

		JLabel englishLabel = new JLabel("English : ");
		englishLabel.setBounds(150, 250, 100, 30);
		add(englishLabel);

		JTextField englishField = new JTextField();
		englishField.setBounds(300, 250, 300, 30);
		add(englishField);

		JLabel ssLabel = new JLabel("SS : ");
		ssLabel.setBounds(150, 300, 100, 30);
		add(ssLabel);

		JTextField ssField = new JTextField();
		ssField.setBounds(300, 300, 300, 30);
		add(ssField);

		JLabel malayalamLabel = new JLabel("Malayalam : ");
		malayalamLabel.setBounds(150, 350, 100, 30);
		add(malayalamLabel);

		JTextField malayalamField = new JTextField();
		malayalamField.setBounds(300, 350, 300, 30);
		add(malayalamField);

		JButton submitButton = new JButton("Submit");
		submitButton.setBounds(300, 450, 150, 40);
		add(submitButton);

		JButton goback = new JButton("Go Back");
		goback.setBounds(300, 510, 150, 40);
		add(goback);

		goback.addActionListener((e) -> {
			hometch.setVisible(true);
			dispose();
		});

		submitButton.addActionListener((e) -> {
			int Sid = Integer.parseInt(studentIdField.getText());
			int mat = Integer.parseInt(mathField.getText());
			int sci = Integer.parseInt(scienceField.getText());
			int eng = Integer.parseInt(englishField.getText());
			int ss = Integer.parseInt(ssField.getText());
			int mal = Integer.parseInt(malayalamField.getText());

			PreparedStatement s2;
			try {
				s2 = c.prepareStatement("SELECT Sname, class FROM student WHERE Sid=?");
				s2.setInt(1, Sid);

				ResultSet getStuName = s2.executeQuery();
				if (getStuName.next()) {
					String StuName = getStuName.getString(1);
					int Sclass = getStuName.getInt(2);

					if (Sclass == classIn) {
						PreparedStatement isMark = c.prepareStatement("SELECT * FROM marklist WHERE Sid=?");
						isMark.setInt(1, Sid);
						ResultSet isMarkRes = isMark.executeQuery();

						if (!isMarkRes.next()) {

							PreparedStatement addMark = c
									.prepareStatement("INSERT INTO marklist VALUES (?, ?, ?, ?, ?, ?)");
							addMark.setInt(1, Sid);
							addMark.setInt(2, mat);
							addMark.setInt(3, sci);
							addMark.setInt(4, eng);
							addMark.setInt(5, ss);
							addMark.setInt(6, mal);

							int addMarkRes = addMark.executeUpdate();
							if (addMarkRes > 0) {
								JOptionPane.showMessageDialog(this,
										"Successfully added marks to " + Sid + " " + StuName, "Success",
										JOptionPane.INFORMATION_MESSAGE);
								hometch.setVisible(true);
								dispose();
							} else
								JOptionPane.showMessageDialog(this, "Error", "Error", JOptionPane.ERROR_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(this,
									"Marks for student " + Sid + " " + StuName + " already found. Try Again.", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(this,
								"Error: Student " + Sid + " " + StuName + " not from your class. Try again.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(this, "Error: No student found with ID " + Sid + ". Try again.",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			} catch (SQLException ex) {

				ex.printStackTrace();
			}

		});

		setVisible(true);
	}

}
