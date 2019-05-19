/*
 * Name:Javier Soon
 * ID: 80436654
 * CS 3331
 * 
 */

package pricewatcher.base;
import java.text.*;


public class PriceFinder {   
    
    
public static void main(String[] args) {
	
	
}
	public static double priceFinder1() {
		double price = 0.00;
		double min = 19.00;
		double max = 35.00;
		
		price = (double) min + (Math.random()* (max - min));
		
		// converts double to decimal form
		DecimalFormat df = new DecimalFormat ("0.00");
		
		return Double.parseDouble(df.format(price));
	}
	

}
