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
		//itemA.setDiscount(130, 3);
		//itemB.setDiscount(45, 2);
		
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
				String input = reader.readLine();
				while(!validValue(input)) {
					System.out.println("Invalid number, it must be an integer greater than 0. Please try again:");
					input = reader.readLine();
				}
				tempItem.setValue(Integer.parseInt(input));
				
				System.out.println("Is there a special offer on this item? Y / N:");
				input = reader.readLine();
				while(validYN(input)<0) {
					System.out.println("Invalid answer, please answer Y or N:");
					input = reader.readLine();
				}
				
				System.out.println("How many items required for a discount?");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static boolean validValue(String value) {
		boolean valid = false;
	    try {
	    	int num = Integer.parseInt(value);
	    		if(num >0) {
	    			valid = true;
	    		}
	    } catch (NumberFormatException ex){
	    }
	    return valid;
	 }
	
	public static int validYN(String input) {
		input = input.toUpperCase();
		if(input.equals("Y")|| input.equals("YES")) {
			return 2;
		}
		if(input.equals("N")|| input.equals("NO")) {
			return 1;
		}
		return -1;
	}

}
