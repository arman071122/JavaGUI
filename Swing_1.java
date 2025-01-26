package gui_pack;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Swing_1 extends JFrame {
	Swing_1() {
		setSize(600, 600);
		setLayout(null);

		JButton b1 = new JButton("Button 1");
		b1.setBounds(50, 50, 120, 30);
		add(b1);

		JLabel l1 = new JLabel("Label 1");
		l1.setBounds(50, 100, 120, 30);
		add(l1);

		JTextField tf1 = new JTextField("Text Field 1");
		tf1.setBounds(50, 150, 200, 30);
		add(tf1);

		JTextArea ta1 = new JTextArea("This is TextArea");
		ta1.setBounds(50, 200, 200, 60);
		add(ta1);

		JCheckBox cb1 = new JCheckBox("This is Checkbox 1");
		cb1.setBounds(50, 280, 200, 30);
		add(cb1);

		JCheckBox cb2 = new JCheckBox("This is Checkbox 2");
		cb2.setBounds(50, 320, 200, 30);
		add(cb2);

		JCheckBox cb3 = new JCheckBox("This is Checkbox 3");
		cb3.setBounds(50, 360, 200, 30);
		add(cb3);

		JCheckBox cb4 = new JCheckBox("This is Checkbox 4");
		cb4.setBounds(50, 400, 200, 30);
		add(cb4);

		String choices[] = { "Choice 1", "Choice 2", "Choice 3", "Choice 4" };
		JComboBox<String> c1 = new JComboBox<>(choices);
		c1.setBounds(50, 440, 150, 30);
		add(c1);

		String listSet[] = { "item 1", "item 2", "item 3", "item 4" };
//		JList<String> list = new JList<>(listSet);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Swing_1();
	}
}
