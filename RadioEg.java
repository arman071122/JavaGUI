package gui_pack;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class RadioEg extends JFrame {
	String name;

	public RadioEg() {
		setTitle("Radio Button Example");
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

		JTextField tf1 = new JTextField(20);
		tf1.setBounds(50, 50, 150, 30);
		add(tf1);

		JRadioButton r1 = new JRadioButton("Male");
		r1.setBounds(70, 100, 150, 30);
		add(r1);

		JRadioButton r2 = new JRadioButton("Female");
		r2.setBounds(70, 135, 150, 30);
		add(r2);

		ButtonGroup bGroup = new ButtonGroup();
		bGroup.add(r1);
		bGroup.add(r2);

		JLabel l = new JLabel();
		l.setBounds(50, 300, 300, 30);
		add(l);

		JCheckBox c1 = new JCheckBox("Player");
		c1.setBounds(50, 170, 300, 30);
		add(c1);

		c1.addItemListener((e) -> {
			name = tf1.getText();
			name = name + " is a player";
		});

		JCheckBox c2 = new JCheckBox("Singer");
		c2.setBounds(50, 200, 300, 30);
		add(c2);

		c2.addItemListener((e) -> {
			name = tf1.getText();
			name = name + " is a singer";
		});

		JButton b1 = new JButton("Submit");
		b1.setBounds(70, 250, 150, 30);
		add(b1);

		b1.addActionListener((e) -> {

			if (r1.isSelected()) {
				name = "Mr. " + name;
				l.setText(name);
			}

			else if (r2.isSelected()) {
				name = "Mrs. " + name;
				l.setText(name);
			}

		});
		setVisible(true);
	}

	public static void main(String[] args) {
		new RadioEg();
	}
}
