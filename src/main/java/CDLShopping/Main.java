package CDLShopping;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Main {

	public static void main(String[] args) throws IOException {
		
		//set item values
		Item itemA = new Item("A");
		Item itemB = new Item("B");
		Item itemC = new Item("C");
		Item itemD = new Item("D");
		
		List<Item> itemList = new ArrayList<>();
		itemList.add(itemA);
		itemList.add(itemB);
		itemList.add(itemC);
		itemList.add(itemD);
		
		Cart cart = new Cart();
		
		System.out.println("Welcome to the CDL shop");
		System.out.println("Please set pricing rules");
		
		try {
			ConsoleInput userInput = new ConsoleInput();
			
			if(userInput.isYes("Do you want to set new price rules? Y / N:")){
				setNewPriceRules(itemList, userInput);
			}
			else{
				setDefaultPriceRules(itemList);
			}
			
			scanItems(userInput, itemList, cart);
			System.out.println("Total cost is "+"Cart total is "+formatAsCurrency(cart.getTotal()));
			
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}

	}
	
	private static void setNewPriceRules(List<Item> itemList, ConsoleInput userInput) throws IOException{
		for(Item tempItem : itemList) {
			tempItem.setValue(userInput.getPositiveInt("Please enter a price (in pence) for item "+tempItem.getName()+":"));
			if(tempItem.getValue()==1) {
				System.out.println("Price too low for discount");
			}
			else {
				addDiscount(userInput, tempItem);
			}
		}
		System.out.println("All values set");
		System.out.println();
	}
	
	private static void setDefaultPriceRules(List<Item> itemList) {
		System.out.println("Setting default values");
		for(Item tempItem : itemList) {
			String name = tempItem.getName();
			switch (name){
			case "A": tempItem.setValue(50);
					  tempItem.setDiscount(130, 3);
					  break;
			case "B": tempItem.setValue(30);
					  tempItem.setDiscount(45, 2);
					  break;
			case "C": tempItem.setValue(20);
				  	  break;
			case "D": tempItem.setValue(15);
		  		      break;
			default:
			}
		}
	}
	
	private static void scanItems(ConsoleInput userInput, List<Item> itemList, Cart cart) throws IOException{
		boolean scanningItems = true;
		while(scanningItems){
			String userItem = userInput.getRawText("Enter a item to scan or type buy to pay:");
			if(userItem.equalsIgnoreCase("buy")){
				scanningItems = false;
			}
			else{
				addItemToCart(userInput, itemList, cart, userItem);
			}
		}
	}
	
	private static Item checkForItem(String findThis, List<Item> itemList){
		for(Item tempItem : itemList){
			if(tempItem.getName().equalsIgnoreCase(findThis)){
				return tempItem;
			}
		}
		return null;
	}
	
	private static String formatAsCurrency(int covertValue) {
		double amountPounds = (double)covertValue / 100;
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.UK);
        return currencyFormat.format(amountPounds);
	}
	
	private static void addDiscount(ConsoleInput userInput, Item tempItem) throws IOException {
		boolean isDiscount = userInput.isYes("Is there a special offer on this item? Y / N:");
		if(isDiscount){
			boolean validDiscount = false;
			while(!validDiscount) {
				int discountQuanity = userInput.getPositiveInt("How many items required for a discount:");
				int discountPrice = userInput.getPositiveInt("What is the discount price?");
				validDiscount = tempItem.validDiscount(discountPrice, discountQuanity);
					if(validDiscount) {
						tempItem.setDiscount(discountPrice, discountQuanity);
					}else {
						System.out.println("Discount is not valid, it must be less than the value of the items");
					}
			}
		}
	}
	
	private static void addItemToCart(ConsoleInput userInput, List<Item> itemList, Cart cart, String userItem) throws IOException {
		while(checkForItem(userItem, itemList)==null){
			userItem = userInput.getRawText("Item not found, please iten anohter item:");
		}
		Item tempItem = checkForItem(userItem, itemList);
		cart.addItem(tempItem);
		System.out.println("added "+tempItem.getName()+" to cart at a price of "+formatAsCurrency(tempItem.getValue()));
		if(tempItem.isDiscount()){
			if(cart.checkDiscount(tempItem)){
				cart.addDiscount(tempItem);
				System.out.println("added discount for "+tempItem.getDiscountQuanity()+" of item "+tempItem.getName()+" at "+formatAsCurrency(tempItem.getTotalDiscount()));
			}
		}
		System.out.println("Cart total is "+formatAsCurrency(cart.getTotal()));
	}
	
}
