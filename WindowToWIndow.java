package gui_pack;

import javax.swing.JButton;
import javax.swing.JFrame;

public class WindowToWIndow extends JFrame {
	public WindowToWIndow() {

		setSize(600, 600);
		setLayout(null);

		JButton b1 = new JButton("Click here to view Adder");
		b1.setBounds(50, 50, 200, 30);
		add(b1);

		b1.addActionListener((e) -> {
			new Adder_lambda();
		});

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}

	public static void main(String[] args) {
		new WindowToWIndow();
	}
}
