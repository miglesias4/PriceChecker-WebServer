package pricewatcher.base;

/*
 * Name:Javier Soon
 * Name: Matthew Iglesias
 * ID: 80436654
 * ID: 80591632
 * CS 3331
 * 
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import org.json.JSONObject;
//import org.json.simple.JSONTokener;


/**
 * A special panel to display the detail of an item.
 */
@SuppressWarnings("serial")
public class ItemView extends JPanel {

    public Item item;

    /**
     * Interface to notify a click on the view page icon.
     */
    public interface ClickListener {

        /**
         * Callback to be invoked when the view page icon is clicked.
         */
        void clicked();
    }

    /**
     * Directory for image files: src/image in Eclipse.
     */
    private final static String IMAGE_DIR = "/image/";

    /**
     * View-page clicking listener.
     */
    private ClickListener listener;

    /**
     * Create a new instance.
     *
     * @param item
     */
    public ItemView(Item item) {
        this.item = item;
        setPreferredSize(new Dimension(0, 160));
        setBackground(Color.WHITE);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (isViewPageClicked(e.getX(), e.getY()) && listener != null) {
                    listener.clicked();
                }
            }
        });
    }

    /**
     * Set the view-page click listener.
     *
     * @param listener
     */
    public void setClickListener(ClickListener listener) {
        this.listener = listener;
    }

    /**
     * Overridden here to display the details of the item.
     */
    @Override
    public void paintComponent(Graphics g) {

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = Calendar.getInstance().getTime();
        String dateAdd = dateFormat.format(date);

        item.setDateAdded(dateAdd);
        double priceChange = ((((item.getCurrentPrice()) * 100) / item.getInitialPrice()) - 100);
        if (priceChange == -100) {

            priceChange = 0;
        }
//        DecimalFormat df = new DecimalFormat("0.00");
//        item.setPriceChange(df.format(priceChange));

        super.paintComponent(g);

        int x = 20, y = 30;
        g.drawImage(getImage("blue monitor.png"), x, y - 20, null);
        y += 20;
        g.drawString("Name:", x, y);
        g.setFont(new Font("Arial", Font.BOLD, 13));
        g.drawString(item.getName(), x + 60, y);
        g.setFont(new Font("Arial", Font.PLAIN, 12));
        g.drawString("URL:", x, y + 20);
        g.drawString(item.getUrl(), x + 60, y + 20);
        g.drawString("Price:", x, y + 40);
        g.setColor(Color.BLUE);
        g.drawString("$" + item.getCurrentPrice(), x + 60, y + 40);
        g.setColor(Color.BLACK);
        if (item.getPriceChange() > 0) {
            g.drawString("Change:", x, y + 60);
            g.setColor(Color.RED);
            g.drawString(item.getPriceChange() + "%", x + 60, y + 60);
        }
        if (item.getPriceChange() < 0) {
            g.drawString("Change:", x, y + 60);
            g.setColor(Color.GREEN);
            g.drawString(item.getPriceChange() + "%", x + 60, y + 60);
        } else {
            g.drawString("Change:", x, y + 60);
            g.setColor(Color.BLACK);
            g.drawString(item.getPriceChange() + "%", x + 60, y + 60);
        }
        g.setColor(Color.BLACK);
        g.drawString("Added:", x, y + 80);
        g.drawString(item.getDateAdded() + " ($" + item.getInitialPrice() + ")", x + 60, y + 80);
    }

    public URL getCodeBase() {
        return getClass().getResource("/");
    }

    /**
     * Return true if the given screen coordinate is inside the viewPage icon.
     */
    private boolean isViewPageClicked(int x, int y) {
        return new Rectangle(20, 20, 30, 20).contains(x, y);
    }

    /**
     * Return the image stored in the given file.
     *
     * @param file
     * @return
     */
    public Image getImage(String file) {
        try {
            URL url = new URL(getClass().getResource(IMAGE_DIR), file);
            return ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
