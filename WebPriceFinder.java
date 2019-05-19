package pricewatcher.base;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

import javax.swing.JOptionPane;

import java.io.*;
import java.net.*;

public class WebPriceFinder extends PriceFinder{

	public static double PriceFinder(String url) {
		int check = 0;
		@SuppressWarnings("unused")
		double currentPrice = 0;
		if (url.contains("walmart")) {
			check = 1;
		}

		if (url.contains("bestbuy")) {
			check = 2;
		} else if (url.contains("newegg")) {
			check = 3;
		}

		switch (check) {
		case 1:
			return currentPrice = CheckWalmart(url);

		case 2:
			return currentPrice = CheckBestbuy(url);

		case 3:
			return currentPrice = CheckNewegg(url);
			
		default:
			System.out.println("invaild url");
			JOptionPane.showMessageDialog(null, "Invalid URL", "Invalid", JOptionPane.ERROR_MESSAGE);
			return 0;
		}
	}

	public static double CheckWalmart(String stringUrl) {
		HttpURLConnection con = null;
		double currentPrice_v_2 = 0;

		try {
			URL url = new URL(stringUrl);
			con = (HttpURLConnection) url.openConnection();
			// con.setRequestProperty("User-Agent", "...");
			String encoding = con.getContentEncoding();
			if (encoding == null) {
				encoding = "utf-8";
			}
			InputStreamReader reader = null;
			if ("gzip".equals(encoding)) { // gzipped document?
				reader = new InputStreamReader(new GZIPInputStream(con.getInputStream()));
			} else {
				reader = new InputStreamReader(con.getInputStream(), encoding);
			}
			BufferedReader in = new BufferedReader(reader);
			String line;
			Pattern pattern = Pattern.compile("\\$(\\d+\\.\\d{2})");

			while ((line = in.readLine()) != null) {
				if (line.contains("\"price-group\" role=\"text\" aria-label=")) {

					Matcher matcher = pattern.matcher(line);
					while (matcher.find()) {
						String price = matcher.group(1);
						double currentPrice = Double.parseDouble(price);
						currentPrice_v_2 = currentPrice;
						System.out.println(currentPrice);
						return currentPrice;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
		return currentPrice_v_2;
	}

	public static double CheckBestbuy(String stringUrl) {
		double currentPrice_v_2 = 0;
		HttpURLConnection con = null;

		try {
			URL url = new URL(stringUrl);
			con = (HttpURLConnection) url.openConnection();
			// con.setRequestProperty("User-Agent", "...");
			String encoding = con.getContentEncoding();
			if (encoding == null) {
				encoding = "utf-8";
			}
			InputStreamReader reader = null;
			if ("gzip".equals(encoding)) { // gzipped document?
				reader = new InputStreamReader(new GZIPInputStream(con.getInputStream()));
			} else {
				reader = new InputStreamReader(con.getInputStream(), encoding);
			}
			BufferedReader in = new BufferedReader(reader);
			String line;
			Pattern pattern = Pattern.compile("\\$(\\d+\\.\\d{2})");

			while ((line = in.readLine()) != null) {				
				if (line.contains("priceView-hero-price priceView-customer-price")) {

					Matcher matcher = pattern.matcher(line);
					while (matcher.find()) {
						String price = matcher.group(1);
						double currentPrice = Double.parseDouble(price);
						currentPrice_v_2 = currentPrice;
						System.out.println(currentPrice);
						return currentPrice;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
		return currentPrice_v_2;
	}

	public static double CheckNewegg(String stringUrl) {
		double currentPrice_v_2 = 0;
		HttpURLConnection con = null;

		try {
			URL url = new URL(stringUrl);
			con = (HttpURLConnection) url.openConnection();
			// con.setRequestProperty("User-Agent", "...");
			String encoding = con.getContentEncoding();
			if (encoding == null) {
				encoding = "utf-8";
			}
			InputStreamReader reader = null;
			if ("gzip".equals(encoding)) { // gzipped document?
				reader = new InputStreamReader(new GZIPInputStream(con.getInputStream()));
			} else {
				reader = new InputStreamReader(con.getInputStream(), encoding);
			}
			BufferedReader in = new BufferedReader(reader);
			Pattern pattern = Pattern.compile("\\$(\\d+\\.\\d{2})");
			String line;

			while ((line = in.readLine()) != null) {
				if ((line.contains("product_sale_price:"))){
					
					Matcher matcher = pattern.matcher(line);
					int indxNumber = line.indexOf("[");
					int indxNumber2 = line.indexOf("]");
					System.out.println(line.substring(indxNumber + 2, indxNumber2 -1));
					while (matcher.find()) {
						String price = matcher.group(1);
						double currentPrice = Double.parseDouble(price);
						currentPrice_v_2 = currentPrice;
						System.out.println(currentPrice);
						return currentPrice;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
		return currentPrice_v_2;
	}
}
