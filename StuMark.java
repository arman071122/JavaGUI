package login;

import java.awt.Font;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class StuMark extends JFrame {
	private JLabel mathsValue, sciValue, engValue, ssValue, malValue;

	public StuMark(StuHome homestu, Connection c, int maths, int sci, int eng, int ss, int mal) {
		setTitle("Student Marks");
		setSize(750, 500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(null);

		JLabel l1 = new JLabel("STUDENT MARKS", JLabel.CENTER);
		l1.setFont(new Font("Georgia", Font.BOLD, 24));
		l1.setBounds(125, 20, 500, 50);
		add(l1);

		JLabel mathsL = new JLabel("Maths:");
		mathsL.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mathsL.setBounds(250, 100, 200, 30);
		add(mathsL);

		mathsValue = new JLabel(String.valueOf(maths));
		mathsValue.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mathsValue.setBounds(400, 100, 250, 30);
		add(mathsValue);

		JLabel sciL = new JLabel("Science:");
		sciL.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		sciL.setBounds(250, 150, 200, 30);
		add(sciL);

		sciValue = new JLabel(String.valueOf(sci));
		sciValue.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		sciValue.setBounds(400, 150, 250, 30);
		add(sciValue);

		JLabel engL = new JLabel("English:");
		engL.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		engL.setBounds(250, 200, 200, 30);
		add(engL);

		engValue = new JLabel(String.valueOf(eng));
		engValue.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		engValue.setBounds(400, 200, 250, 30);
		add(engValue);

		JLabel ssL = new JLabel("Social Studies:");
		ssL.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		ssL.setBounds(250, 250, 200, 30);
		add(ssL);

		ssValue = new JLabel(String.valueOf(ss));
		ssValue.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		ssValue.setBounds(400, 250, 250, 30);
		add(ssValue);

		JLabel malL = new JLabel("Malayalam:");
		malL.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		malL.setBounds(250, 300, 200, 30);
		add(malL);

		malValue = new JLabel(String.valueOf(mal));
		malValue.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		malValue.setBounds(400, 300, 250, 30);
		add(malValue);

		JButton goBack = new JButton("Go Back");
		goBack.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		goBack.setBounds(300, 400, 150, 40);
		goBack.addActionListener((e) -> {
			homestu.setVisible(true);
			dispose();
		});
		add(goBack);

		setVisible(true);
	}
}
