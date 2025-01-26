package gui_pack;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame {
	JLabel namel, agel, genderl, message;

	Login(String name, int age, String gender, String username, String password) {
		setSize(500, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setVisible(true);

		JLabel l1 = new JLabel("LOGIN", JLabel.CENTER);
		l1.setFont(new Font("Georgia", Font.BOLD, 24));
		l1.setBounds(125, 20, 200, 50);
		add(l1);

		JLabel l2 = new JLabel("Username:");
		l2.setBounds(50, 100, 150, 30);
		add(l2);

		JTextField nameField = new JTextField();
		nameField.setBounds(200, 100, 200, 30);
		add(nameField);

		JLabel l3 = new JLabel("Password:");
		l3.setBounds(50, 150, 150, 30);
		add(l3);

		JTextField passwordField = new JPasswordField();
		passwordField.setBounds(200, 150, 200, 30);
		add(passwordField);

		JButton submit = new JButton("Submit");
		submit.setBounds(170, 230, 100, 30);
		add(submit);

		message = new JLabel();
		message.setBounds(175, 300, 200, 30);
		add(message);

		namel = new JLabel();
		namel.setFont(new Font("Arial", Font.BOLD, 15));
		namel.setBounds(160, 350, 200, 30);
		add(namel);

		agel = new JLabel();
		agel.setFont(new Font("Arial", Font.BOLD, 15));
		agel.setBounds(160, 370, 200, 30);
		add(agel);

		genderl = new JLabel();
		genderl.setFont(new Font("Arial", Font.BOLD, 15));
		genderl.setBounds(160, 390, 200, 30);
		add(genderl);

		submit.addActionListener((e) -> {
			String userFromField = nameField.getText();
			String passFromField = passwordField.getText();

			if (userFromField.equals(username) && passFromField.equals(password)) {
				message.setText("Login Success!");
				namel.setText("Name      :  " + name);
				agel.setText("Age         :  " + String.valueOf(age));
				genderl.setText("Gender  :  " + gender);
			} else {
				JOptionPane.showMessageDialog(this, "Invalid Credentials", "Error", JOptionPane.ERROR_MESSAGE);
			}

		});
	}

	public static void main(String[] args) {
		SignUp s = new SignUp();
		new Login(s.name, s.age, s.gender, s.username, s.password);
	}
}
