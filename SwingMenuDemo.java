//package pricewatcher.base;
//
//import java.awt.*;
//import java.awt.event.*;
//
//import javax.swing.*;
//
//public class SwingMenuDemo {
//	private JFrame mainFrame;
//	private JLabel headerLabel;
//	private JLabel statusLabel;
//	private JPanel controlPanel;
//
//	public SwingMenuDemo() {
//		prepareGUI();
//	}
//
//	public static void main(String[] args) {
//		SwingMenuDemo swingMenuDemo = new SwingMenuDemo();
//		swingMenuDemo.showMenuDemo();
//	}
//
//	private void prepareGUI() {
//		mainFrame = new JFrame("Java SWING Examples");
//		mainFrame.setSize(400, 400);
//		mainFrame.setLayout(new GridLayout(3, 1));
//
//		headerLabel = new JLabel("", JLabel.CENTER);
//		statusLabel = new JLabel("", JLabel.CENTER);
//		statusLabel.setSize(350, 100);
//
//		mainFrame.addWindowListener(new WindowAdapter() {
//			public void windowClosing(WindowEvent windowEvent) {
//				System.exit(0);
//			}
//		});
//		controlPanel = new JPanel();
//		controlPanel.setLayout(new FlowLayout());
//
//		mainFrame.add(headerLabel);
//		mainFrame.add(controlPanel);
//		mainFrame.add(statusLabel);
//		mainFrame.setVisible(true);
//	}
//
//	private void showMenuDemo() {
//		// create a menu bar
//		final JMenuBar menuBar = new JMenuBar();
//
//		/**
//		 * 
//		 * Create Menus
//		 * 
//		 */
//		JMenu appMenu = new JMenu("App");
//		JMenu itemMenu = new JMenu("Item");
//		JMenu sortMenu = new JMenu("Sort");
//
//		// App
//		JMenuItem aboutMenuItem = new JMenuItem("About");
//		aboutMenuItem.setMnemonic(KeyEvent.VK_N);
//		aboutMenuItem.setActionCommand("About");
//
//		JMenuItem exitMenuItem = new JMenuItem("Exit");
//		exitMenuItem.setActionCommand("Exit");
//
//		// Item
//		JMenuItem checkMenuItem = new JMenuItem("Check Prices");
//		checkMenuItem.setActionCommand("Check Prices");
//
//		JMenuItem addMenuItem = new JMenuItem("Add Item");
//		addMenuItem.setActionCommand("Add Item");
//
//		JMenuItem searchMenuItem = new JMenuItem("Search");
//		searchMenuItem.setActionCommand("Search");
//
//		JMenuItem firstMenuItem = new JMenuItem("Search first");
//		firstMenuItem.setActionCommand("Search first");
//
//		JMenuItem lastMenuItem = new JMenuItem("Search last");
//		lastMenuItem.setActionCommand("Search last");
//
//		// Sort
//		JMenuItem addOldItem = new JMenuItem("Added Oldest");
//		addOldItem.setActionCommand("Added Oldest");
//
//		JMenuItem addNewItem = new JMenuItem("Added newest");
//		addNewItem.setActionCommand("Added Newest");
//
//		JMenuItem nameAscItem = new JMenuItem("Name ascending");
//		nameAscItem.setActionCommand("Name ascending");
//
//		JMenuItem nameDesItem = new JMenuItem("Name descending");
//		nameDesItem.setActionCommand("Name descending");
//
//		JMenuItem priceChangeItem = new JMenuItem("Price change (%)");
//		priceChangeItem.setActionCommand("Price change (%)");
//
//		JMenuItem priceLowItem = new JMenuItem("Price low ($)");
//		priceLowItem.setActionCommand("Price low ($)");
//
//		JMenuItem priceHighItem = new JMenuItem("Price high ($)");
//		priceHighItem.setActionCommand("Price high ($)");
//
//		MenuItemListener menuItemListener = new MenuItemListener();
//
//		/**
//		 * 
//		 * JMenuItem Action Listeners
//		 * 
//		 */
//
//		// app
//		aboutMenuItem.addActionListener(menuItemListener);
//		exitMenuItem.addActionListener(menuItemListener);
//
//		// Item
//		checkMenuItem.addActionListener(menuItemListener);
//		addMenuItem.addActionListener(menuItemListener);
//		searchMenuItem.addActionListener(menuItemListener);
//		firstMenuItem.addActionListener(menuItemListener);
//		lastMenuItem.addActionListener(menuItemListener);
//
//		// Sort
//		addOldItem.addActionListener(menuItemListener);
//		addNewItem.addActionListener(menuItemListener);
//		nameAscItem.addActionListener(menuItemListener);
//		nameDesItem.addActionListener(menuItemListener);
//		priceChangeItem.addActionListener(menuItemListener);
//		priceLowItem.addActionListener(menuItemListener);
//		priceHighItem.addActionListener(menuItemListener);
//
//		/**
//		 * 
//		 * JCheckBox
//		 * 
//		 */
//
//		// final JCheckBoxMenuItem checkBox = new JCheckBoxMenuItem("test",true);
//		// checkBox.addItemListener(new ItemListener());
//
//		/**
//		 * 
//		 * JMenuItem Add to JMenu DropBar
//		 * 
//		 */
//
//		// add menu items to menus
//		appMenu.add(aboutMenuItem);
//		appMenu.addSeparator();
//		appMenu.add(exitMenuItem);
//
//		itemMenu.add(checkMenuItem);
//		itemMenu.add(addMenuItem);
//		itemMenu.addSeparator();
//		itemMenu.add(searchMenuItem);
//		itemMenu.add(firstMenuItem);
//		itemMenu.add(lastMenuItem);
//		itemMenu.addSeparator();
//
//		sortMenu.add(addOldItem);
//		sortMenu.add(addNewItem);
//		sortMenu.addSeparator();
//		sortMenu.add(nameAscItem);
//		sortMenu.add(nameDesItem);
//		sortMenu.addSeparator();
//		sortMenu.add(priceChangeItem);
//		sortMenu.add(priceLowItem);
//		sortMenu.add(priceHighItem);
//
//		/**
//		 * 
//		 * JMenu Added to Menu Pane
//		 * 
//		 */
//
//		menuBar.add(appMenu);
//		menuBar.add(itemMenu);
//		menuBar.add(sortMenu);
//
//		// add menubar to the frame
//		mainFrame.setJMenuBar(menuBar);
//		mainFrame.setVisible(true);
//	}
//
//	class MenuItemListener implements ActionListener {
//		public void actionPerformed(ActionEvent e) {
//			statusLabel.setText(e.getActionCommand() + " JMenuItem clicked.");
//		}
//	}
//}

//removed and merged with main
