package gui_pack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Adder_lambda extends JFrame {
	JLabel l1, l2, l3, sumShow;
	JTextField tf1, tf2;
	JButton b1;

	Adder_lambda() {
		setSize(600, 600);
		setLayout(null);

		l1 = new JLabel("ADDER");
		l1.setBounds(175, 20, 100, 30);
		add(l1);

		l2 = new JLabel("Enter number 1:");
		l2.setBounds(50, 70, 150, 30);
		add(l2);

		tf1 = new JTextField(20);
		tf1.setBounds(200, 70, 150, 30);
		add(tf1);

		l3 = new JLabel("Enter number 2:");
		l3.setBounds(50, 120, 150, 30);
		add(l3);

		tf2 = new JTextField(20);
		tf2.setBounds(200, 120, 150, 30);
		add(tf2);

		b1 = new JButton("Sum");
		b1.setBounds(100, 200, 150, 30);
		b1.addActionListener(e -> {
			int a = Integer.parseInt(tf1.getText());
			int b = Integer.parseInt(tf2.getText());
			int sum = a + b;
			sumShow.setText(sum + "");
		});
		add(b1);

		sumShow = new JLabel("Sum will be displayed here");
		sumShow.setBounds(100, 250, 200, 30);
		add(sumShow);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Adder_lambda();
	}
}
