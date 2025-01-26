package login;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class TchStuMarksEdit extends JFrame {
	public TchStuMarksEdit(TchModStu tchModStu, Connection c, int classIn, int sid, String modName, int modClass) {
		setTitle("Modify Marks");
		setSize(750, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(null);

		JLabel l1 = new JLabel("MODIFY MARKS", JLabel.CENTER);
		l1.setFont(new Font("Georgia", Font.BOLD, 24));
		l1.setBounds(125, 20, 500, 50);
		add(l1);

		JLabel infoLabel = new JLabel("Enter -1 to denote no change for the subject", JLabel.CENTER);
		infoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		infoLabel.setBounds(125, 70, 500, 30);
		add(infoLabel);

		JLabel mathsL = new JLabel("Maths:");
		mathsL.setBounds(150, 120, 200, 30);
		add(mathsL);

		JTextField mathsField = new JTextField();
		mathsField.setBounds(350, 120, 250, 30);
		add(mathsField);

		JLabel mathsPreExisting = new JLabel("Existing: N/A");
		mathsPreExisting.setBounds(610, 120, 100, 30);
		add(mathsPreExisting);

		JLabel scienceL = new JLabel("Science:");
		scienceL.setBounds(150, 180, 200, 30);
		add(scienceL);

		JTextField scienceField = new JTextField();
		scienceField.setBounds(350, 180, 250, 30);
		add(scienceField);

		JLabel sciencePreExisting = new JLabel("Existing: N/A");
		sciencePreExisting.setBounds(610, 180, 100, 30);
		add(sciencePreExisting);

		JLabel englishL = new JLabel("English:");
		englishL.setBounds(150, 240, 200, 30);
		add(englishL);

		JTextField englishField = new JTextField();
		englishField.setBounds(350, 240, 250, 30);
		add(englishField);

		JLabel englishPreExisting = new JLabel("Existing: N/A");
		englishPreExisting.setBounds(610, 240, 100, 30);
		add(englishPreExisting);

		JLabel ssL = new JLabel("SS:");
		ssL.setBounds(150, 300, 200, 30);
		add(ssL);

		JTextField ssField = new JTextField();
		ssField.setBounds(350, 300, 250, 30);
		add(ssField);

		JLabel ssPreExisting = new JLabel("Existing: N/A");
		ssPreExisting.setBounds(610, 300, 100, 30);
		add(ssPreExisting);

		JLabel malayalamL = new JLabel("Malayalam:");
		malayalamL.setBounds(150, 360, 200, 30);
		add(malayalamL);

		JTextField malayalamField = new JTextField();
		malayalamField.setBounds(350, 360, 250, 30);
		add(malayalamField);

		JLabel malayalamPreExisting = new JLabel("Existing: N/A");
		malayalamPreExisting.setBounds(610, 360, 100, 30);
		add(malayalamPreExisting);

		JButton submit = new JButton("Submit");
		submit.setBounds(300, 440, 150, 40);
		add(submit);

		JButton goBack = new JButton("Go Back");
		goBack.setBounds(300, 500, 150, 40);
		goBack.addActionListener((e) -> {
			tchModStu.setVisible(true);
			dispose();
		});
		add(goBack);

		PreparedStatement isMark;
		try {
			isMark = c.prepareStatement("SELECT * FROM marklist WHERE Sid=?");
			isMark.setInt(1, sid);
			ResultSet isMarkRes = isMark.executeQuery();

			if (isMarkRes.next()) {

				mathsPreExisting.setText("Existing: " + isMarkRes.getInt("Maths"));
				sciencePreExisting.setText("Existing: " + isMarkRes.getInt("Sci"));
				englishPreExisting.setText("Existing: " + isMarkRes.getInt("Eng"));
				ssPreExisting.setText("Existing: " + isMarkRes.getInt("SS"));
				malayalamPreExisting.setText("Existing: " + isMarkRes.getInt("Mal"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		submit.addActionListener((e) -> {

			String sub[] = { "Maths", "Sci", "Eng", "SS", "Mal" };
			int marks[] = new int[sub.length];
			boolean hasChanges = false;

			marks[0] = Integer.parseInt(mathsField.getText());
			marks[1] = Integer.parseInt(scienceField.getText());
			marks[2] = Integer.parseInt(englishField.getText());
			marks[3] = Integer.parseInt(ssField.getText());
			marks[4] = Integer.parseInt(malayalamField.getText());

			StringBuilder query = new StringBuilder("update marklist set ");

			for (int i = 0; i < sub.length; i++) {

				if (marks[i] != -1) {
					if (hasChanges)
						query.append(",");

					query.append(sub[i]).append("=?");
					hasChanges = true;
				}
			}

			if (hasChanges) {
				query.append(" where Sid=?");
				System.out.println(query);
				PreparedStatement modMark;
				try {
					modMark = c.prepareStatement(query.toString());
					int pi = 1; // parameter index
					for (int i = 0; i < sub.length; i++) {
						if (marks[i] != -1) {
							modMark.setInt(pi, marks[i]);
							pi++;
						}
					}
					modMark.setInt(pi, sid);
					int modMarksRes = modMark.executeUpdate();
					if (modMarksRes > 0) {
						JOptionPane.showMessageDialog(this, "Successfully updated marks for " + sid + " " + modName,
								"Success", JOptionPane.INFORMATION_MESSAGE);
						tchModStu.setVisible(true);
						dispose();
					}

				} catch (SQLException ex) {
					ex.printStackTrace();
				}

			} else {
				JOptionPane.showMessageDialog(this, "No changes made", "Info", JOptionPane.INFORMATION_MESSAGE);
				tchModStu.setVisible(true);
				dispose();
			}
		});

		setVisible(true);
	}
}
