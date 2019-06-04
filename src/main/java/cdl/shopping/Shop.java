package cdl.shopping;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Shop object to control the process of buying items 
 * @author James McDonald
 */
public class Shop {
	
	//instance variables
	private ItemInventory inventory;
	private Cart cart;
	private ConsoleInput userInput;
	
	/**
	 * Instantiate the cart, inventory and userInput classes
	 */
	public Shop(){
		cart = new Cart();
		inventory = new ItemInventory();
		userInput = new ConsoleInput();
	}
	
	public Shop(ConsoleInput userInput) {
		cart = new Cart();
		inventory = new ItemInventory();
		this.userInput = userInput;
	}
	
	/**
	 * Output a welcome message to the user
	 */
	public void welomeMsg(){
		System.out.println("Welcome to the CDL shop");
		System.out.println("Please set pricing rules");
		System.out.println();
	}
	
	/**
	 * Ask if a new price rule is needed.
	 * If no use defaults already set
	 * If yes clear current price rules from Inventory and create new ones
	 * @throws IOException
	 */
	public void askPriceRule() throws IOException{
		if(userInput.isYes("Do you want to set new price rules? Y / N:")){
			inventory.clearItemValues();
			setNewPriceRules();
		}
		System.out.println("Price rules:");
		inventory.outputItemsToScreen();
		System.out.println();
	}
	
	/**
	 * output all items in the checkout and the total value of the items
	 */
	public void checkout(){
		cart.outputRecepit();
		System.out.println("Total cost is "+formatAsCurrency(cart.getTotal()));
	}
	
	/**
	 * Ask user for new price rules from the user for each item in the inventory 
	 * Set new values input to the items
	 * @throws IOException
	 */
	private void setNewPriceRules() throws IOException{
		for(int x=0; x < inventory.getNumberItem(); x++) {
			Item tempItem = inventory.getItem(x);
			tempItem.setValue(userInput.getPositiveInt("Please enter a price (in pence) for item "+tempItem.getName()+":"));
			if(tempItem.getValue()==1) {
				System.out.println("Price too low for discount");
			}
			else {
				addDiscount(tempItem);
			}
		}
	}
	
	/**
	 * Ask user if the item has a discount
	 * If yes, check discount is valid and store its details in the item
	 * @param tempItem to add the discount to a Item
	 * @throws IOException
	 */
	private void addDiscount(Item tempItem) throws IOException {
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
						System.out.println("Discount is not valid; it must be less than the value of the items");
					}
			}
		}
	}
	
	/**
	 * Ask user for item to add to cart.
	 * If valid item name is entered add to cart
	 * If invalid item name is entered re-ask the user for input
	 * If buy is entered exit the method
	 * Not case sensitive
	 * @throws IOException
	 */
	public void scanItems() throws IOException{
		boolean scanningItems = true;
		while(scanningItems){
			String userItem = userInput.getRawText("Enter a item to scan or type buy to pay:");
			if(userItem.equalsIgnoreCase("buy")){
				scanningItems = false;
			}
			else{
				addItemToCart(userItem);
			}
		}
	}
	
	/**
	 * Take in a string and find matching item in inventory
	 * If matching item is found, add item and discount if needed
	 * If not found ask for user to re-input 
	 * if buy is entered exit method
	 * @param userItem name of item to add as a String
	 * @throws IOException
	 */
	private void addItemToCart(String userItem) throws IOException {
		while(!inventory.canFindItem(userItem)){
			userItem = userInput.getRawText("Item not found, please enter another item:");
			if(userItem.equalsIgnoreCase("buy")){
				return;
			}
		}
		Item tempItem = inventory.getItem(userItem);
		cart.addItem(tempItem);
		System.out.println("added "+tempItem.getName()+" to cart at a price of "+formatAsCurrency(tempItem.getValue()));
		if(tempItem.hasDiscount()){
			if(cart.checkDiscount(tempItem)){
				cart.addDiscount(tempItem);
				System.out.println("added discount for "+tempItem.getDiscountQuantity()+" of item "+tempItem.getName()+" at "+formatAsCurrency(tempItem.getTotalDiscount()));
			}
		}
		System.out.println("Cart total is "+formatAsCurrency(cart.getTotal()));
	}
	
	/**
	 * Take in a string value representing a number in pence and return a string
	 * of it in GBP format with £ sign
	 * @param covertValue value in pence as a int
	 * @return String of value in GBP
	 */
	private String formatAsCurrency(int covertValue) {
		double amountPounds = (double)covertValue / 100;
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.UK);
        return currencyFormat.format(amountPounds);
	}
	
	protected ItemInventory getItemInventory() {
		return inventory;
	}
	
	protected Cart getCart() {
		return cart;
	}

}
