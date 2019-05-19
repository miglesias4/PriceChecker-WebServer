package pricewatcher.base;

import java.io.*;

import org.json.JSONArray;
import org.json.JSONTokener;



public class FileItemManager extends Item {
	private static FileItemManager theinstance;
	
	private FileItemManager() {
		
	}
	
	public static FileItemManager getInstance() {
		if(theinstance == null) {
			theinstance = new FileItemManager();
		}
		return theinstance;
	}
	
	public void start() {
		restore();
	}
	
	public Item add(String name, String url, double price, double dateAdd) {
		Item item = super.add(name,url,price,dateAdd);
		if(item != null) {
			save();
		}
		return item;
	}
	
	public Item remove(String url) {
		Item item = super.remove(url);
	}
	
	@Override
	public Item updatePrice(String url,float price) {
		Item item = super.updatePrice(url,price);
		if(item != null) {
			save();
		}
		return item;
	}
	
	public Item change(Item item){
		item = super.change(item);
		if(item != null){
			save();
		}
	}
	
	protected void save() {
		try(BufferedWriter write = nre BufferedWriter(new FileWriter(Constants.DATA_FILE))){
			JSONArray arr = new JSONArray();
			for(Item e: items()) {
				arr.put(e.toJson());
			}
			write.write(arr.toString());
		}
		catch(IOException e) {
			
		}
	}
	
	protected void restore() {
		try(BufferedReader read = new BufferedReader(new FileReader(Constants.DATA_FILE))){
			JSONTokener token = new JSONArray(read);
			JSONArray arr = new JSONArray(token);
			for(int i = 0; i < arr.length(); i++) {
				Item item = Item.fromJSON(arr.getJSONObject(i));
				super.add(item);
			}
		}
		catch(IOException e) {
			
		}
	}
	
}
