package login;

import java.awt.Font;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class StuInfo extends JFrame {
	private JLabel sidValue, snameValue, classValue, classInChargeValue;

	public StuInfo(StuHome homestu, Connection c, int sid, String sname, int sclass, String Tname) {
		setTitle("Student Info");
		setSize(750, 500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(null);

		JLabel l1 = new JLabel("STUDENT INFO", JLabel.CENTER);
		l1.setFont(new Font("Georgia", Font.BOLD, 24));
		l1.setBounds(125, 20, 500, 50);
		add(l1);

		JLabel sidL = new JLabel("ID  :");
		sidL.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		sidL.setBounds(250, 100, 200, 30);
		add(sidL);

		sidValue = new JLabel(String.valueOf(sid));
		sidValue.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		sidValue.setBounds(400, 100, 250, 30);
		add(sidValue);

		JLabel snameL = new JLabel("Name  :");
		snameL.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		snameL.setBounds(250, 150, 200, 30);
		add(snameL);

		snameValue = new JLabel(sname);
		snameValue.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		snameValue.setBounds(400, 150, 250, 30);
		add(snameValue);

		JLabel classL = new JLabel("Class  :");
		classL.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		classL.setBounds(250, 200, 200, 30);
		add(classL);

		classValue = new JLabel(String.valueOf(sclass));
		classValue.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		classValue.setBounds(400, 200, 250, 30);
		add(classValue);

		JLabel classInChargeL = new JLabel("Class In-Charge  :");
		classInChargeL.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		classInChargeL.setBounds(250, 250, 200, 30);
		add(classInChargeL);

		classInChargeValue = new JLabel(Tname);
		classInChargeValue.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		classInChargeValue.setBounds(400, 250, 250, 30);
		add(classInChargeValue);

		JButton goBack = new JButton("Go Back");
		goBack.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		goBack.setBounds(300, 350, 150, 40);
		goBack.addActionListener((e) -> {
			homestu.setVisible(true);
			dispose();
		});
		add(goBack);

		setVisible(true);
	}
}
