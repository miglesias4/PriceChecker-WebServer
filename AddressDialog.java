package pricewatcher.base;

import java.awt.*;

import javax.swing.*;



@SuppressWarnings("serial")
public class AddDialog extends JDialog {
	JLabel Name = new JLabel("Name: ");
	JLabel uRL = new JLabel("URL:");
	JLabel Price = new JLabel("Price:");



	JTextField nameField = new JTextField();
	JTextField urlField = new JTextField();
	JTextField priceField = new JTextField("0.00");
	JButton ok = new JButton("Ok");


	String[] list = new String[3];
      
	
	public AddDialog(AbstractAction abstractAction, boolean modal) {
		super();
		init();
	}

	private void init() {
		this.setTitle("Add");
		this.setLayout(new GridLayout(4, 2));
		this.add(Name);
		this.add(nameField);
		this.add(uRL);
		this.add(urlField);
		this.add(Price);
		this.add(priceField);
		this.add(ok);
	}

	public String[] getAddress() {
		list[0] = nameField.getText();
		list[1] = urlField.getText();
		list[2] = priceField.getText();
		return list;
	}
}

