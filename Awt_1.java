package gui_pack;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.Frame;
import java.awt.Label;
import java.awt.List;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.TextArea;
import java.awt.TextField;

public class Awt_1 extends Frame {
	Awt_1() {
		setSize(600, 600);
		setLayout(null);

		Button b1 = new Button("This is button");
		b1.setBounds(50, 50, 120, 30);
		add(b1);

		Label l1 = new Label("This is label");
		l1.setBounds(50, 100, 120, 30);
		add(l1);

		TextField tf1 = new TextField("This is TextField");
		tf1.setBounds(50, 150, 200, 30);
		add(tf1);

		TextArea ta1 = new TextArea("This is TextArea");
		ta1.setBounds(50, 200, 200, 60);
		add(ta1);

		Checkbox cb1 = new Checkbox("This is Checkbox 1");
		cb1.setBounds(50, 280, 200, 30);
		add(cb1);

		Checkbox cb2 = new Checkbox("This is Checkbox 2");
		cb2.setBounds(50, 320, 200, 30);
		add(cb2);

		Checkbox cb3 = new Checkbox("This is Checkbox 3");
		cb3.setBounds(50, 360, 200, 30);
		add(cb3);

		Checkbox cb4 = new Checkbox("This is Checkbox 4");
		cb4.setBounds(50, 400, 200, 30);
		add(cb4);

		Choice c1 = new Choice();
		c1.add("Choice 1");
		c1.add("Choice 2");
		c1.add("Choice 3");
		c1.add("Choice 4");
		c1.setBounds(50, 440, 150, 30);
		add(c1);

		List list = new List(3);
		list.add("List Item 1");
		list.add("List Item 2");
		list.add("List Item 3");
		list.setBounds(50, 480, 150, 60);
		add(list);

		MenuBar mb = new MenuBar();
		Menu m = new Menu("Menu1");
		Menu sm = new Menu("This is Submenu1");
		Menu m2 = new Menu("Menu2");
		MenuItem i1 = new MenuItem("Item 1");
		MenuItem i2 = new MenuItem("Item 2");
		MenuItem i3 = new MenuItem("Item 3");
		MenuItem i4 = new MenuItem("Item 4");
		MenuItem i5 = new MenuItem("Item 5");
		MenuItem i6 = new MenuItem("Item 6");
		MenuItem i7 = new MenuItem("Item 7");

		m.add(i1);
		m.add(i2);
		sm.add(i3);
		sm.add(i4);
		sm.add(i5);
		m.add(sm);
		m2.add(i6);
		m2.add(i7);
		mb.add(m);
		mb.add(m2);
		setMenuBar(mb);

		setVisible(true);
	}

// exit
	public static void main(String[] args) {
		new Awt_1();
		System.out.println("GUI created Successfully");
	}
}
