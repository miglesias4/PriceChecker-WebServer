import java.awt.*; 
import javax.swing.*; 
import java.awt.event.*; 
import javax.swing.border.LineBorder;

public class SwingToolbarDemo extends JFrame {
	
	public SwingToolbarDemo() {
        ImageIcon openIcon = new ImageIcon(
        		SwingToolbarDemo.class.getResource("/image/blue ?.png"));
        ImageIcon saveIcon = new ImageIcon(
        		SwingToolbarDemo.class.getResource("/image/ebay.png"));
        ImageIcon newIcon = new ImageIcon(
        		SwingToolbarDemo.class.getResource("/image/blue settings.png"));

        Action openAction = new AbstractAction("About", openIcon) {
            @Override
            public void actionPerformed(ActionEvent e) {
            	JOptionPane.showMessageDialog(null, "PriceWatcher, version 13.1");
                System.out.println("Opening About");
            }
        };
        Action saveAction = new AbstractAction("Save", saveIcon) {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Save File");
            }
        };
        Action newAction = new AbstractAction("New", newIcon) {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("New File");
            }
        };

        JMenuItem openMenuItem = new JMenuItem(openAction);
        JMenuItem saveMenuItem = new JMenuItem(saveAction);
        JMenuItem newMenuItem = new JMenuItem(newAction);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(newMenuItem);
        menuBar.add(fileMenu);

        JToolBar toolBar = new JToolBar();
        toolBar.add(Box.createHorizontalGlue());
        toolBar.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        toolBar.add(newAction);
        toolBar.add(openAction);
        toolBar.add(saveAction);

        JFrame frame = new JFrame("Toolbar and Menu Test");
        frame.setJMenuBar(menuBar);
        frame.add(toolBar, BorderLayout.PAGE_START);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SwingToolbarDemo();
            }
        });
    }
	
	//remove the whole class//
