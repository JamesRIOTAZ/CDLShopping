package CDLShopping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		//set item values
		Item itemA = new Item("A");
		Item itemB = new Item("B");
		Item itemC = new Item("C");
		Item itemD = new Item("D");
		
		//set discounts
		itemA.setDiscount(130, 3);
		itemB.setDiscount(45, 2);
		
		ArrayList<Item> itemList = new ArrayList<>();
		itemList.add(itemA);
		itemList.add(itemB);
		itemList.add(itemC);
		itemList.add(itemD);
		
		System.out.println("Welcome to the CDL shop");
		System.out.println("Please set pricing rules");
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			
			for(Item tempItem : itemList) {
				System.out.println("Please enter a price (in pence) for item "+tempItem.getName()+":");
				String value = reader.readLine();
				while(!validValue(value)) {
					value = reader.readLine();
				}
				tempItem.setValue(Integer.parseInt(value));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static boolean validValue(String value) {
		boolean valid = false;
	    try {
	    	int num = Integer.parseInt(value);
	    	if(num <=0) {
	    		System.out.println("Value must be greater than 0, please try again:");
	    	}
	    	else {
	    		valid = true;
	    	}
	    } catch (NumberFormatException ex){
	        System.out.println("The value must be a integer greater than 0, please try again:");
	    }
	    return valid;
	 }

}
