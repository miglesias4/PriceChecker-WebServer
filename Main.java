package pricewatcher.base;

import java.awt.*;

import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.*;

import org.json.JSONTokener;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * A dialog for tracking the price of an item.
 *
 * @author Javier Soon and Matthew Iglesias and Alejandro Villarreal
 * @ID 80436654 and 80591632 and 88759517
 */
@SuppressWarnings("serial")
public class Main extends JFrame {

	/** Default dimension of the dialog. */
	private final static Dimension DEFAULT_SIZE = new Dimension(400, 300);

	private static final ActionListener menuItemListener = null;

	/** Special panel to display the watched item. */
	private ItemView itemView;
	// private Toolbar toolBar;
	/** Message bar to display various messages. */
	private JLabel msgBar = new JLabel(" ");

	private JList<Item> itemList;
	private DefaultListModel<Item> listModel = new DefaultListModel<>();

	/**
	 * Create a new dialog.
	 * 
	 */
	public Main() {
		this(DEFAULT_SIZE);
	}

	/**
	 * Create a new dialog of the given screen dimension.
	 * 
	 */
	public Main(Dimension dim) {
		super("Price Watcher");
		setSize(dim);

		// create the model and adds elements
		listModel = new DefaultListModel<>();

		// // create the list
		itemList = new JList<>(listModel);
		loadItems();
		// itemList.setVisibleRowCount(2);

		configureUI();
		// JToolBarUI(); // commented out due to unneeded

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		// pack(); // makes it so the screen UI shows all the items
		setVisible(true);

	}

	public void loadItems() {
		String fromFile = "jsonItems.json";
		try {
			InputStream inpt = new FileInputStream(fromFile);
			if (inpt == null) {
				throw new NullPointerException("No file" + fromFile);
			}
			JSONTokener iToken = new JSONTokener(inpt);
			JSONArray arr = new JSONArray(iToken);
			for (int i = 0; i < arr.length(); i++) {
				JSONObject o = new JSONObject(arr.get(i).toString());
				System.out.println(o.toString());

				Item objectItem = Item.fromJSON(o);
				listModel.addElement(objectItem);
				System.out.println("recieved");
			}

		} catch (FileNotFoundException eee) {
			System.out.println("File not found");
		} catch (IOException eee) {
			System.out.println("File not found");
		}
	}

	/**
	 * Callback to be invoked when the refresh button is clicked. Find the current
	 * price of the watched item and display it along with a percentage price
	 * change.
	 */
	private void refreshButtonClicked(ActionEvent event) {

		// item.setCurrentPrice(PriceFinder.priceFinder2());
		itemView.repaint();
	}

	/**
	 * Callback to be invoked when the view-page icon is clicked. Launch a (default)
	 * web browser by supplying the URL of the item.
	 */
	private void viewPageClicked() {

		if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {

			String url = listModel.get(itemList.getSelectedIndex()).getUrl();
			try {
				Desktop.getDesktop().browse(java.net.URI.create(url));
			} catch (NullPointerException e) {
				System.out.println("Url does not exist.");

			} catch (UnsupportedOperationException ee) {
				// TODO Auto-generated catch block
				System.out.println("Current platform is not supported.");

			} catch (SecurityException eee) {
				System.out.println("Do not have security clearance.");

			} catch (IOException eeee) {
				System.out.println("Could not launch");
			}
		}
	}

	/** Configure UI. */
	private void configureUI() {
		setLayout(new BorderLayout());
		JScrollPane scroll = (new JScrollPane(itemList));

		JPanel control = makeControlPanel();

		control.setBorder(BorderFactory.createEmptyBorder(10, 16, 0, 16));
		add(control, BorderLayout.NORTH);

		JPanel board = new JPanel();
		board.setLayout(new BorderLayout());

		JMenuBarUI();
		add(JToolBarUI(), BorderLayout.NORTH);
		board.add(scroll, BorderLayout.CENTER);
		itemList.setCellRenderer(new ItemRenderer());
		add(board, BorderLayout.CENTER);
		msgBar.setBorder(BorderFactory.createEmptyBorder(10, 16, 10, 0));
		add(msgBar, BorderLayout.SOUTH);
	}

	ImageIcon checkIcon = new ImageIcon(Main.class.getResource("/image/blue check.png"));
	ImageIcon addIcon = new ImageIcon(Main.class.getResource("/image/blue +.png"));
	ImageIcon searchIcon = new ImageIcon(Main.class.getResource("/image/blue search.png"));
	ImageIcon firstIcon = new ImageIcon(Main.class.getResource("/image/blue back.png"));
	ImageIcon backIcon = new ImageIcon(Main.class.getResource("/image/blue forward.png"));
	ImageIcon selectedIcon = new ImageIcon(Main.class.getResource("/image/green check.png"));
	ImageIcon webIcon = new ImageIcon(Main.class.getResource("/image/green file.png"));
	ImageIcon editIcon = new ImageIcon(Main.class.getResource("/image/green pencil.png"));
	ImageIcon deleteIcon = new ImageIcon(Main.class.getResource("/image/green -.png"));
	ImageIcon questionIcon = new ImageIcon(Main.class.getResource("/image/blue question.png"));

	Action addAction = new AbstractAction("Add an item", addIcon) {
		@Override

		public void actionPerformed(ActionEvent e) {
			final AddDialog dialog = new AddDialog(this, false);
			dialog.setSize(250, 120);
			dialog.setVisible(true);
			dialog.ok.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String[] addList = dialog.getAddress();

					Item item = null;
					try {
						double p = WebPriceFinder.PriceFinder(addList[1]);
						System.out.println(p);

						item = new Item(addList[0], addList[1], p);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
					}

					listModel.addElement(item);

					dialog.dispose();
				}

			});
		}
	};

	Action checkAction = new AbstractAction("Check all Prices", checkIcon) {
		@Override
		public void actionPerformed(ActionEvent e) {

			for (int i = 0; i < listModel.size(); i++) {
				if (listModel.elementAt(i) != null) {
					try {
						Item temp = listModel.getElementAt(i);
						temp.setCurrentPrice(WebPriceFinder.PriceFinder(temp.getUrl()));
						listModel.setElementAt(temp, i);
					} catch (ArrayIndexOutOfBoundsException e1) {
						System.out.println("hey bitch you stupid");

					}
				}
			}
			System.out.println("Checking Prices");
		}
	};

	Action searchAction = new AbstractAction("Search", searchIcon) {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Search item");
		}
	};

	Action firstAction = new AbstractAction("New", firstIcon) {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (itemList.getSelectedIndex() > -1) {
				itemList.setSelectedIndex(listModel.getSize() - 1);

			}
		}
	};

	Action backAction = new AbstractAction("New", backIcon) {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("select File");
		}
	};

	Action selectedAction = new AbstractAction("New", selectedIcon) {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("select File");
		}
	};

	Action webAction = new AbstractAction("New", webIcon) {
		@Override
		public void actionPerformed(ActionEvent e) {
			// try{
			if (itemList.getSelectedIndex() > -1) {
				viewPageClicked();
				System.out.println("Open Webpage");
			} else {
				System.out.println("Select a item to go to the website");
			}
		}
	};

	Action editAction = new AbstractAction("New", editIcon) {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (itemList.getSelectedIndex() > -1) {
				final EditDialog dialog = new EditDialog(this, false);
				dialog.setSize(250, 120);
				dialog.setVisible(true);

				Item temp = listModel.getElementAt(itemList.getSelectedIndex());

				dialog.nameField.setText(listModel.getElementAt(itemList.getSelectedIndex()).getName());
				dialog.urlField.setText(listModel.getElementAt(itemList.getSelectedIndex()).getUrl());
				dialog.priceField
						.setText(String.valueOf(listModel.getElementAt(itemList.getSelectedIndex()).getCurrentPrice()));

				dialog.ok.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						temp.setName(dialog.nameField.getText());
						temp.setCurrentPrice(WebPriceFinder.PriceFinder(dialog.urlField.getText()));
						temp.setUrl(dialog.urlField.getText());

						listModel.setElementAt(temp, itemList.getSelectedIndex());

						System.out.println("Edit File");
						dialog.dispose();
					}
				});

			} else {
				JOptionPane.showMessageDialog(null, "Select an Item", "Invalid", JOptionPane.ERROR_MESSAGE);

			}
		}
	};

	Action deleteAction = new AbstractAction("New", deleteIcon) {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Delete File");
			listModel.removeElementAt(itemList.getSelectedIndex());
		}

	};

	Action aboutAction = new AbstractAction("About", questionIcon) {
		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "PriceWatcher, version 13.1");
		}
	};

	Action quitAction = new AbstractAction("Exit", deleteIcon) {
		public void actionPerformed(ActionEvent e) {
			// Initialize a JSON array
			JSONArray arr = new JSONArray();
			int listLen = listModel.size();
			for (int i = 0; i < listLen; i++) {
				if (listModel.elementAt(i) != null) {
					Item item = listModel.elementAt(i);
					arr.put(item.toJSON().toString());
				}
			}
			try {
				// Initialize and implement FileWriter in which shall store onto json file
				FileWriter file = new FileWriter("jsonItems.json", false);
				arr.write(file);
				file.close();
			} catch (Exception eee) {
				eee.getMessage();
			}
			System.exit(0);
		}
	};

	private JToolBar JToolBarUI() {
		JToolBar toolBar = new JToolBar(" Tool Bar");

		toolBar.add(checkAction);
		toolBar.add(addAction);
		toolBar.add(searchAction);
		toolBar.add(firstAction);
		toolBar.add(backAction);
		toolBar.addSeparator();
		toolBar.add(selectedAction);
		toolBar.add(webAction);
		toolBar.add(editAction);
		toolBar.add(deleteAction);
		toolBar.addSeparator();
		toolBar.add(aboutAction);

		return toolBar;
	}

	private void JMenuBarUI() {
		JMenuBar menuBar = new JMenuBar();

		/**
		 * 
		 * Create Menus
		 * 
		 */
		JMenu appMenu = new JMenu("App");
		JMenu itemMenu = new JMenu("Item");
		JMenu sortMenu = new JMenu("Sort");

		// App
		JMenuItem aboutMenuItem = new JMenuItem("About");

		aboutMenuItem.setMnemonic(KeyEvent.VK_N);
		aboutMenuItem.setAction(aboutAction);

		JMenuItem exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.addActionListener(quitAction);

		// Item
		JMenuItem checkMenuItem = new JMenuItem("Check Prices");
		checkMenuItem.setAction(checkAction);
		JMenuItem addMenuItem = new JMenuItem("Add Item");
		addMenuItem.setAction(addAction);
		JMenuItem searchMenuItem = new JMenuItem("Search");
		searchMenuItem.setAction(searchAction);
		JMenuItem firstMenuItem = new JMenuItem("Search first");
		firstMenuItem.setAction(firstAction);
		JMenuItem lastMenuItem = new JMenuItem("Search last");
		lastMenuItem.setAction(backAction);

		// Sort
		JMenuItem addOldItem = new JMenuItem("Added Oldest");
		addOldItem.setActionCommand("Added Oldest");
		JMenuItem addNewItem = new JMenuItem("Added newest");
		addNewItem.setActionCommand("Added Newest");
		JMenuItem nameAscItem = new JMenuItem("Name ascending");
		nameAscItem.setActionCommand("Name ascending");
		JMenuItem nameDesItem = new JMenuItem("Name descending");
		nameDesItem.setActionCommand("Name descending");
		JMenuItem priceChangeItem = new JMenuItem("Price change (%)");
		priceChangeItem.setActionCommand("Price change (%)");
		JMenuItem priceLowItem = new JMenuItem("Price low ($)");
		priceLowItem.setActionCommand("Price low ($)");
		JMenuItem priceHighItem = new JMenuItem("Price high ($)");
		priceHighItem.setActionCommand("Price high ($)");

		/**
		 * 
		 * JMenuItem Action Listeners
		 * 
		 */

		// app
		aboutMenuItem.addActionListener(menuItemListener);
		exitMenuItem.addActionListener(menuItemListener);

		// Item
		checkMenuItem.addActionListener(menuItemListener);
		addMenuItem.addActionListener(menuItemListener);
		searchMenuItem.addActionListener(menuItemListener);
		firstMenuItem.addActionListener(menuItemListener);
		lastMenuItem.addActionListener(menuItemListener);

		// Sort
		addOldItem.addActionListener(menuItemListener);
		addNewItem.addActionListener(menuItemListener);
		nameAscItem.addActionListener(menuItemListener);
		nameDesItem.addActionListener(menuItemListener);
		priceChangeItem.addActionListener(menuItemListener);
		priceLowItem.addActionListener(menuItemListener);
		priceHighItem.addActionListener(menuItemListener);

		menuBar.add(appMenu);
		menuBar.add(itemMenu);
		menuBar.add(sortMenu);

		appMenu.add(aboutMenuItem);
		appMenu.addSeparator();
		appMenu.add(exitMenuItem);

		itemMenu.add(checkMenuItem);
		itemMenu.add(addMenuItem);
		itemMenu.addSeparator();
		itemMenu.add(searchMenuItem);
		itemMenu.add(firstMenuItem);
		itemMenu.add(lastMenuItem);
		itemMenu.addSeparator();

		sortMenu.add(addOldItem);
		sortMenu.add(addNewItem);
		sortMenu.addSeparator();
		sortMenu.add(nameAscItem);
		sortMenu.add(nameDesItem);
		sortMenu.addSeparator();
		sortMenu.add(priceChangeItem);
		sortMenu.add(priceLowItem);
		sortMenu.add(priceHighItem);
		setJMenuBar(menuBar);

	}

	/** Create a control panel consisting of a refresh button. */
	private JPanel makeControlPanel() {

		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		JButton refreshButton = new JButton("Refresh Check prices");
		refreshButton.setFocusPainted(false);
		refreshButton.addActionListener(this::refreshButtonClicked);
		panel.add(refreshButton);
		return panel;
	}

	public static void main(String[] args) throws IOException {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Main();
			}
		});
	}
}
