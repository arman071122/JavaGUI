package login;

import java.awt.Font;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class TchHome extends JFrame {
	public TchHome(Connection c, String name, int tid, int classIn) {
		setTitle("Teacher Home");
		setSize(750, 760);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

		JLabel l1 = new JLabel("Welcome Teacher, " + name, JLabel.CENTER);
		l1.setFont(new Font("Georgia", Font.BOLD, 24));
		l1.setBounds(125, 20, 500, 50);
		add(l1);

		JButton addStudentB = new JButton("ADD STUDENT");
		addStudentB.setBounds(275, 90, 200, 50);
		add(addStudentB);

		JButton addMarksB = new JButton("ADD MARKS");
		addMarksB.setBounds(275, 160, 200, 50);
		add(addMarksB);

		JButton viewClassB = new JButton("VIEW CLASS");
		viewClassB.setBounds(275, 230, 200, 50);
		add(viewClassB);

		JButton viewStudentB = new JButton("VIEW STUDENT");
		viewStudentB.setBounds(275, 300, 200, 50);
		add(viewStudentB);

		JButton deleteStudentB = new JButton("DELETE STUDENT");
		deleteStudentB.setBounds(275, 370, 200, 50);
		add(deleteStudentB);

		JButton modifyStudentB = new JButton("MODIFY STUDENT");
		modifyStudentB.setBounds(275, 440, 200, 50);
		add(modifyStudentB);

		JButton personalDetailsB = new JButton("PERSONAL DETAILS");
		personalDetailsB.setBounds(275, 510, 200, 50);
		add(personalDetailsB);

		JButton editPersonalDetailsB = new JButton("EDIT PERSONAL DETAILS");
		editPersonalDetailsB.setBounds(275, 580, 200, 50);
		add(editPersonalDetailsB);

		JButton logoutB = new JButton("LOGOUT");
		logoutB.setBounds(275, 650, 200, 50);
		add(logoutB);

		addStudentB.addActionListener((e) -> {
			new TchAddStu(this, c, classIn);
			setVisible(false);
		});

		addMarksB.addActionListener((e) -> {
			new TchAddMark(this, c, classIn);
			setVisible(false);
		});

		viewClassB.addActionListener((e) -> {

			new TchViewClass(this, c, classIn);
			setVisible(false);
		});

		viewStudentB.addActionListener((e) -> {

			new TchViewStu(this, c, classIn);
			setVisible(false);
		});

		deleteStudentB.addActionListener((e) -> {

			new TchRemStu(this, c, classIn);
			setVisible(false);
		});

		modifyStudentB.addActionListener((e) -> {
			new TchModStu(this, c, classIn);
			setVisible(false);
		});

		personalDetailsB.addActionListener((e) -> {
			new TchInfo(this, c, tid, classIn, name);
			setVisible(false);
		});

		editPersonalDetailsB.addActionListener((e) -> {
			new TchInfoEdit(this, c, tid);
			setVisible(false);
		});

		logoutB.addActionListener((e) -> {
			dispose();
		});

		setVisible(true);
	}

}
