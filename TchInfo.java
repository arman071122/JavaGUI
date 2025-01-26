package login;

import java.awt.Font;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class TchInfo extends JFrame {
	private JLabel tidValue, tnameValue, classInChargeValue;

	public TchInfo(TchHome hometch, Connection c, int tid, int classIn, String name) {
		setTitle("Teacher Info");
		setSize(750, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

		JLabel l1 = new JLabel("TEACHER INFO", JLabel.CENTER);
		l1.setFont(new Font("Georgia", Font.BOLD, 24));
		l1.setBounds(125, 20, 500, 50);
		add(l1);

		JLabel tidL = new JLabel("ID:");
		tidL.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tidL.setBounds(250, 130, 200, 30);
		add(tidL);

		tidValue = new JLabel(String.valueOf(tid));
		tidValue.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tidValue.setBounds(400, 130, 250, 30);
		add(tidValue);

		JLabel tnameL = new JLabel("Name:");
		tnameL.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tnameL.setBounds(250, 180, 200, 30);
		add(tnameL);

		tnameValue = new JLabel(name);
		tnameValue.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tnameValue.setBounds(400, 180, 250, 30);
		add(tnameValue);

		JLabel classInChargeL = new JLabel("Class In-Charge:");
		classInChargeL.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		classInChargeL.setBounds(250, 230, 200, 30);
		add(classInChargeL);

		classInChargeValue = new JLabel(String.valueOf(classIn));
		classInChargeValue.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		classInChargeValue.setBounds(400, 230, 250, 30);
		add(classInChargeValue);

		JButton goBack = new JButton("Go Back");
		goBack.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		goBack.setBounds(300, 350, 150, 40);
		goBack.addActionListener((e) -> {
			hometch.setVisible(true);
			dispose();
		});
		add(goBack);

		setVisible(true);
	}
}
