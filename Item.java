package pricewatcher.base;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

public class Item {
	/** Name of the item, use a good one, saved as a string */
	private String name;
	/** URL of the item, saved as a string */
	private String url;
	/** initial Price of the item, saved as a double */
	private double initialPrice;
	private double currentPrice;
	private double priceChange;
	private String dateAdded;

	public Item next;

	public Item(String name, String url, double currentPrice, double priceChange) throws IOException {
		// contains name, url, current price
		this.name = name;
		this.url = url;
		this.currentPrice = currentPrice;
		this.priceChange = priceChange;
	}
	
	public Item(String name, String url, double currentPrice) throws IOException {
		// contains name, url, current price
		this.name = name;
		this.url = url;
		this.currentPrice = currentPrice;
	}
	
	public Item() {
		// TODO Auto-generated constructor stub
	}

	public Item(Item item) {
		// TODO Auto-generated constructor stub
	}

	// Setter
	public void setName(String name) {
		this.name = name;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setInitialPrice(double initialPrice) {
		this.initialPrice = initialPrice;
	}

	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}

	public void setDateAdded(String dateAdded) {
		this.dateAdded = dateAdded;
	}

	public void setPriceChange(double priceChange) {
		this.priceChange = priceChange;
	}

	// Getters
	public String getName() {
		return name;
	}

	public String getUrl() {
		return url;
	}

	public double getInitialPrice() {
		return initialPrice;
	}

	public double getCurrentPrice() {
		return currentPrice;
	}

	public String getDateAdded() {
		return dateAdded;
	}

	public double getPriceChange() {
		return priceChange;
	}
	
	public JSONObject toJSON() {
		Map<String,Object> m = new HashMap<>();
		m.put("Product name",this.name );
		m.put("url", this.url);
		m.put("initial price", this.initialPrice);
		m.put("change", this.priceChange);
		m.put("date add", this.dateAdded);
		return new JSONObject(m);
	}
	
	public static Item fromJSON(JSONObject x) throws IOException {
    	Item item = new Item();
        String productName = x.getString("Product name");
        String url = x.getString("url");
        double initalPrice = (float)x.getDouble("initial price");
        double change = (float)x.getDouble("change");
        String dateAdded = x.getString("date add");
        
        return new Item(productName,url,initalPrice,change);

    }


}
