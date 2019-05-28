package CDLShopping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
		
		Cart cart = new Cart();
		
		System.out.println("Welcome to the CDL shop");
		System.out.println("Please set pricing rules");
		
		try {
			ConsoleInput userInput = new ConsoleInput();
			for(Item tempItem : itemList) {
			
				tempItem.setValue(userInput.getPositiveInt("Please enter a price (in pence) for item "+tempItem.getName()+":"));
				boolean isDiscount = userInput.isYes("Is there a special offer on this item? Y / N:");
				
				if(isDiscount){
					int discountQuanity = userInput.getPositiveInt("How many items required for a discount:");
					int discountPrice = userInput.getPositiveInt("What is the discount price?");
					tempItem.setDiscount(discountPrice, discountQuanity);
				}
			}
			System.out.println("All values set");
			System.out.println();
			
			while(true){
				String userItem = userInput.getRawText("Enter a item to buy:");
				while(checkForItem(userItem, itemList)==null){
					userItem = userInput.getRawText("Item not found, please iten anohter item:");
				}
				Item tempItem = checkForItem(userItem, itemList);
				cart.addItem(tempItem.getName(), tempItem.getValue());
				if(tempItem.isDiscount()){
					cart.checkDiscount(tempItem.getName(), tempItem.getDiscountQuanity(), tempItem.getTotalDiscount());
				}
				System.out.println(cart.getTotal());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static Item checkForItem(String findThis, List<Item> itemList){
		for(Item tempItem : itemList){
			if(tempItem.getName().equals(findThis)){
				return tempItem;
			}
		}
		return null;
	}
	
}
