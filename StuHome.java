package login;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class StuHome extends JFrame {

	StuHome(Connection c, String stuName, int sclass, int sid) {
		setTitle("Student Home");
		setSize(750, 500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(null);

		JLabel l1 = new JLabel("Welcome Student, " + stuName, JLabel.CENTER);
		l1.setFont(new Font("Georgia", Font.BOLD, 24));
		l1.setBounds(125, 20, 500, 50);
		add(l1);

		JButton infoB = new JButton("PERSONAL INFO");
		infoB.setBounds(275, 120, 200, 50);
		add(infoB);

		JButton markB = new JButton("VIEW MARKS");
		markB.setBounds(275, 200, 200, 50);
		add(markB);

		JButton chPassB = new JButton("CHANGE PASSWORD");
		chPassB.setBounds(275, 280, 200, 50);
		add(chPassB);

		JButton logoutB = new JButton("LOGOUT");
		logoutB.setBounds(275, 360, 200, 50);
		add(logoutB);

		infoB.addActionListener((e) -> {
			String Tname = null;
			try {
				PreparedStatement view = c.prepareStatement(
						"SELECT t.Tname FROM teacher t JOIN student s ON s.Class = t.Class_In_Charge WHERE s.Sid = ?");
				view.setInt(1, sid);
				ResultSet viewRes = view.executeQuery();
				if (viewRes.next()) {
					Tname = viewRes.getString(1);
				}
				new StuInfo(this, c, sid, stuName, sclass, Tname);
				setVisible(false);
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		});

		markB.addActionListener((e) -> {
			try {
				PreparedStatement viewMark = c
						.prepareStatement("SELECT Maths, Sci, Eng, SS, Mal FROM markList WHERE Sid = ?");
				viewMark.setInt(1, sid);
				ResultSet viewMarkRes = viewMark.executeQuery();
				if (viewMarkRes.next()) {
					int maths = viewMarkRes.getInt(1);
					int sci = viewMarkRes.getInt(2);
					int eng = viewMarkRes.getInt(3);
					int ss = viewMarkRes.getInt(4);
					int mal = viewMarkRes.getInt(5);
					new StuMark(this, c, maths, sci, eng, ss, mal);
					setVisible(false);
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		});

		chPassB.addActionListener((e) -> {
			new StuChPass(this, c, sid, stuName);
			setVisible(false);
		});

		logoutB.addActionListener((e) -> {
			dispose();
		});

		setVisible(true);
	}
}
