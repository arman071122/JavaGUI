package login;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TchViewClass extends JFrame {
	TchViewClass(TchHome hometch, Connection c, int classIn) {
		setTitle("View Class");
		setSize(750, 500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(null);

		JLabel heading = new JLabel("Class " + classIn, JLabel.CENTER);
		heading.setFont(new Font("Georgia", Font.BOLD, 24));
		heading.setBounds(150, 20, 500, 50);
		add(heading);

		String[] columnNames = { "SID", "Name", "Maths", "Science", "English", "SS", "Malayalam" };
		Object[][] data = fetchClassData(c, classIn);

		JTable table = new JTable(data, columnNames);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(50, 80, 650, 300);
		add(scrollPane);

		JButton goBackButton = new JButton("Go Back");
		goBackButton.setBounds(300, 400, 150, 40);
		goBackButton.addActionListener(e -> {
			hometch.setVisible(true);
			dispose();
		});
		add(goBackButton);

		setVisible(true);
	}

	public Object[][] fetchClassData(Connection c, int classIn) {
		ArrayList<Object[]> rows = new ArrayList();
		String query = "SELECT s.Sid, s.Sname, m.Maths, m.Sci, m.Eng, m.SS, m.Mal FROM student s JOIN markList m ON s.Sid = m.Sid WHERE s.class = (SELECT Class_In_Charge FROM teacher WHERE Class_In_Charge = ?)";

		try (PreparedStatement stmt = c.prepareStatement(query)) {
			stmt.setInt(1, classIn);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					rows.add(new Object[] { rs.getInt("Sid"), rs.getString("Sname"), rs.getInt("Maths"),
							rs.getInt("Sci"), rs.getInt("Eng"), rs.getInt("SS"), rs.getInt("Mal") });
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return rows.toArray(new Object[rows.size()][]);
	}
}
