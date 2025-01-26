package gui_pack;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Mouse extends JFrame implements MouseListener {

	public JLabel label;

	public Mouse() {
		setTitle("Mouse Event Example");
		setSize(400, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setVisible(true);

		label = new JLabel("NOTHING");
		label.setBounds(100, 70, 100, 30);
		label.setOpaque(true);
		label.setBackground(Color.gray);

		label.addMouseListener(this);

		add(label);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		label.setBackground(Color.yellow);
		label.setText("Clicked");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		label.setBackground(Color.red);
		label.setText("Pressed");
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		label.setBackground(Color.cyan);
		label.setText("Released");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		label.setBackground(Color.green);
		label.setText("Hovering");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		label.setBackground(Color.gray);
		label.setText("Exited");
	}

	public static void main(String[] args) {
		new Mouse();
	}
}
