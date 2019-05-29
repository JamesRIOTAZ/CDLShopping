package cdl.shopping;

import java.io.IOException;

/**
*  Program to represent a shop checkout using a command line interface
*
* @author  James McDonald
* @version 1.0
* @since   29-05-2019 
*/

public class Main {

	public static void main(String[] args) throws IOException{
		
		//create new shop object
		Shop shop = new Shop();
		
		try {
			shop.welomeMsg(); //output a welcome message
			shop.askPriceRule(); //set price rules
			shop.scanItems(); //add items to cart
			shop.checkout();//output items in cart and total cost
		
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
}
