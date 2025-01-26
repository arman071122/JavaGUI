package login;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class TchStuPersEdit extends JFrame {
	public TchStuPersEdit(TchModStu tchModStu, Connection c, int classIn, int sid, String modName, int modClass) {
		setTitle("Modify Student");
		setSize(750, 500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(null);

		JLabel l1 = new JLabel("MODIFY STUDENT", JLabel.CENTER);
		l1.setFont(new Font("Georgia", Font.BOLD, 24));
		l1.setBounds(125, 20, 500, 50);
		add(l1);

		JLabel nameL = new JLabel("Enter new name:");
		nameL.setBounds(150, 150, 150, 30);
		add(nameL);

		JTextField nameField = new JTextField();
		nameField.setBounds(300, 150, 300, 30);
		add(nameField);

		JLabel classL = new JLabel("Enter new class:");
		classL.setBounds(150, 200, 150, 30);
		add(classL);

		JTextField classField = new JTextField();
		classField.setBounds(300, 200, 300, 30);
		add(classField);

		JButton submit = new JButton("Submit");
		submit.setBounds(300, 300, 150, 40);
		add(submit);

		JButton goBack = new JButton("Go Back");
		goBack.setBounds(300, 350, 150, 40);
		goBack.addActionListener((e) -> {
			tchModStu.setVisible(true);
			dispose();
		});
		add(goBack);

		submit.addActionListener((e) -> {
			String newname = nameField.getText();
			int newclass = Integer.parseInt(classField.getText());

			PreparedStatement mod1;
			try {
				mod1 = c.prepareStatement("UPDATE student SET Sname=?, Class=? WHERE Sid=?");
				mod1.setString(1, newname);
				mod1.setInt(2, newclass);
				mod1.setInt(3, sid);

				int mod1Res = mod1.executeUpdate();
				if (mod1Res > 0) {
					JOptionPane
							.showMessageDialog(this,
									"Successfully updated details of " + modName + " class " + modClass + " to "
											+ newname + " class " + newclass,
									"Success", JOptionPane.INFORMATION_MESSAGE);
					tchModStu.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(this, "Error", "Error", JOptionPane.ERROR_MESSAGE);
				}

			} catch (SQLException ex) {

				ex.printStackTrace();
			}

		});

		setVisible(true);
	}
}
