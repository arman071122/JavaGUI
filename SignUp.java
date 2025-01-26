package gui_pack;

import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class SignUp extends JFrame {
	String name, gender, username, password, confirm;
	int age;

	SignUp() {
		setSize(500, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setVisible(true);

		JLabel l1 = new JLabel("SIGN UP", JLabel.CENTER);
		l1.setFont(new Font("Georgia", Font.BOLD, 24));
		l1.setBounds(125, 20, 200, 50);
		add(l1);

		JLabel l2 = new JLabel("Name:");
		l2.setBounds(50, 100, 150, 30);
		add(l2);

		JTextField nameField = new JTextField();
		nameField.setBounds(200, 100, 200, 30);
		add(nameField);

		JLabel l3 = new JLabel("Age:");
		l3.setBounds(50, 150, 150, 30);
		add(l3);

		JTextField ageField = new JTextField();
		ageField.setBounds(200, 150, 200, 30);
		add(ageField);

		JLabel l4 = new JLabel("Gender:");
		l4.setBounds(50, 200, 150, 30);
		add(l4);

		JRadioButton maleB = new JRadioButton("Male");
		maleB.setBounds(200, 200, 100, 30);
		add(maleB);

		JRadioButton femaleB = new JRadioButton("Female");
		femaleB.setBounds(300, 200, 100, 30);
		add(femaleB);

		ButtonGroup genderGroup = new ButtonGroup();
		genderGroup.add(maleB);
		genderGroup.add(femaleB);

		JLabel l5 = new JLabel("Username:");
		l5.setBounds(50, 250, 150, 30);
		add(l5);

		JTextField usernameField = new JTextField();
		usernameField.setBounds(200, 250, 200, 30);
		add(usernameField);

		JLabel l6 = new JLabel("Password:");
		l6.setBounds(50, 300, 150, 30);
		add(l6);

		JTextField passwordField = new JPasswordField();
		passwordField.setBounds(200, 300, 200, 30);
		add(passwordField);

		JLabel l7 = new JLabel("Confirm Password:");
		l7.setBounds(50, 350, 150, 30);
		add(l7);

		JTextField conPasswordField = new JPasswordField();
		conPasswordField.setBounds(200, 350, 200, 30);
		add(conPasswordField);

		JButton submit = new JButton("Submit");
		submit.setBounds(170, 420, 100, 30);
		add(submit);

		submit.addActionListener((e) -> {
			name = nameField.getText();
			age = Integer.parseInt(ageField.getText());

			if (maleB.isSelected()) {
				gender = "Male";
				name = "Mr. " + name;
			}

			else if (femaleB.isSelected()) {
				gender = "Female";
				name = "Mrs. " + name;
			}

			username = usernameField.getText();
			password = passwordField.getText();
			confirm = conPasswordField.getText();

			if (password.equals(confirm)) {
				JOptionPane.showMessageDialog(this, "User Created successfull", "Info",
						JOptionPane.INFORMATION_MESSAGE);
				new Login(name, age, gender, username, password);
			} else {
				JOptionPane.showMessageDialog(this, "Password Mismatch", "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
	}

	public static void main(String[] args) {
		new SignUp();
	}
}
