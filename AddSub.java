package gui_pack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddSub extends JFrame {
	JLabel l1, l2, l3, sumShow;
	JTextField tf1, tf2;
	JButton add, sub;

	AddSub() {
		setSize(470, 400);
		setLayout(null);

		l1 = new JLabel("ADDER AND SUBTRACTOR");
		l1.setBounds(155, 20, 200, 30);
		add(l1);

		l2 = new JLabel("Enter number 1:");
		l2.setBounds(100, 70, 150, 30);
		add(l2);

		tf1 = new JTextField(20);
		tf1.setBounds(200, 70, 150, 30);
		add(tf1);

		l3 = new JLabel("Enter number 2:");
		l3.setBounds(100, 120, 150, 30);
		add(l3);

		tf2 = new JTextField(20);
		tf2.setBounds(200, 120, 150, 30);
		add(tf2);

		add = new JButton("Sum");
		add.setBounds(50, 200, 150, 30);
		add.addActionListener(e -> {
			int a = Integer.parseInt(tf1.getText());
			int b = Integer.parseInt(tf2.getText());
			int sum = a + b;
			sumShow.setText(sum + "");
		});
		add(add);

		sub = new JButton("Difference");
		sub.setBounds(250, 200, 150, 30);
		sub.addActionListener(e -> {
			int a = Integer.parseInt(tf1.getText());
			int b = Integer.parseInt(tf2.getText());
			int diff = a - b;
			sumShow.setText(diff + "");
		});
		add(sub);

		sumShow = new JLabel("Answer will be displayed here");
		sumShow.setBounds(150, 250, 200, 30);
		add(sumShow);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new AddSub();
	}
}
