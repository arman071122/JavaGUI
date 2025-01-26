package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class viewGui extends JFrame {
	JLabel maths, sci, eng, ss, mal;

	public viewGui(Connection c) {
		Student s = new Student();

		setTitle("View Marks of Student");
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setVisible(true);

		JLabel l2 = new JLabel("VIEW MARKS");
		l2.setBounds(150, 30, 150, 30);
		add(l2);

		JLabel l1 = new JLabel("Enter the id of student : ");
		l1.setBounds(30, 100, 150, 30);
		add(l1);

		JTextField f1 = new JTextField();
		f1.setBounds(200, 100, 130, 30);
		add(f1);

		JButton b1 = new JButton("Submit");
		b1.setBounds(150, 170, 100, 30);
		add(b1);

		maths = new JLabel("Maths     : ");
		maths.setBounds(150, 230, 300, 30);
		add(maths);

		sci = new JLabel("Science   : ");
		sci.setBounds(150, 260, 300, 30);
		add(sci);

		eng = new JLabel("English   : ");
		eng.setBounds(150, 290, 300, 30);
		add(eng);

		ss = new JLabel("SS          : ");
		ss.setBounds(150, 320, 300, 30);
		add(ss);

		mal = new JLabel("Malayalam : ");
		mal.setBounds(150, 350, 300, 30);
		add(mal);

		JLabel message = new JLabel();
		message.setBounds(120, 400, 500, 30);
		add(message);

		b1.addActionListener((e) -> {
			int id = Integer.parseInt(f1.getText());
			try {
				int marks[] = s.ViewMark(c, id);

				if (marks == null) {
					maths.setText("Maths     : ");
					sci.setText("Science   : ");
					eng.setText("English   : ");
					ss.setText("SS          : ");
					mal.setText("Malayalam : ");
					message.setText("Marks not published for " + id);
				} else {
					maths.setText("Maths   :  " + marks[0]);
					sci.setText("Science   :  " + marks[1]);
					eng.setText("English   :  " + marks[2]);
					ss.setText("SS         :  " + marks[3]);
					mal.setText("Malayalam :  " + marks[4]);
					message.setText("Successfully retrieved marks of student id " + id);
				}
			} catch (SQLException ex) {
				message.setText(ex.getMessage());
			}
		});
	}

	public static void main(String[] args) throws SQLException {
		Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/login", "arman", "arman@07");

		new viewGui(c);
	}
}
