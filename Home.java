package login;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Home extends JFrame {
	public Home(Connection c) {
		setTitle("Student Management System");
		setSize(750, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setVisible(true);

		JLabel l1 = new JLabel("STUDENT MANAGEMENT SYSTEM", JLabel.CENTER);
		l1.setFont(new Font("Georgia", Font.BOLD, 24));
		l1.setBounds(125, 20, 500, 50);
		add(l1);

		JButton SignUpB = new JButton("SIGN UP");
		SignUpB.setBounds(275, 150, 200, 50);
		add(SignUpB);

		JButton LoginB = new JButton("LOGIN");
		LoginB.setBounds(275, 250, 200, 50);
		add(LoginB);

		JButton ExitB = new JButton("EXIT");
		ExitB.setBounds(275, 350, 200, 50);
		add(ExitB);

		SignUpB.addActionListener((e) -> {
			try {
				new SignUp(c);
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		});

		LoginB.addActionListener((e) -> {
			try {
				new Login(c);
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		});

		;
		ExitB.addActionListener((e) -> {
			System.exit(0);
		});
	}

	public static void main(String[] args) throws SQLException {
		Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/login", "arman", "arman@07");
		new Home(c);
	}
}
