package cdl.shopping;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a shopping cart of items
 * @author James McDonald
 */

public class Cart {

	/**
	 * List to hold cartItems with a Initialization Blocks
	 */
	private List<CartItem> cartItems;
	{
		cartItems = new ArrayList<>();
	}
	
	/**
	 * Add Added item to the cart
	 * @param tempItem to be added
	 */
	public void addItem(Item tempItem){
		CartItem item = new CartItem(tempItem.getValue(), tempItem.getName());
		cartItems.add(item);
	}
	
	/**
	 * Take in an item and check if a discount should be added based on the
	 * number of items already in the cart
	 * 
	 * @param tempItem item to check
	 * @return true if discount should be added, false if not
	 */
	public boolean checkDiscount(Item tempItem){
		int totalItems = countItems(tempItem.getName());
		return (totalItems % tempItem.getDiscountQuantity()==0);
	}
	
	/**
	 * Take in an item and add a discount cart item with its name and values
	 * taken from the tempItem
	 * 
	 * @param tempItem item hold discount info
	 */
	public void addDiscount(Item tempItem){
		CartItem item = new CartItem(tempItem.getTotalDiscount(), "Discount for "+tempItem.getDiscountQuantity()+" x "+tempItem.getName());
		cartItems.add(item);
	}
	
	
	/**
	 * Count number of items in List with a match name
	 * @param name of items to count
	 * @return number of items in list as a int
	 */
	private int countItems(String name){
		int totalItems = 0;
		for(CartItem tempItem : cartItems) {
			if(name.equals(tempItem.getName())){
				totalItems++;
			}
		}
		return totalItems;
	}
	
	/**
	 * Get sum value of all cartItems in cart
	 * @return Total value of all CartItems in cart as a int
	 */
	public int getTotal(){
		int total = 0;
		for(CartItem tempItem : cartItems) {
			total += tempItem.getValue();
		}
		return total;
	}
	
	/**
	 * Print out the name and value of all cartItems in cart
	 */
	public void outputRecepit(){
		for(CartItem tempItem : cartItems) {
			System.out.println("Item: "+tempItem.getName()+" Price: "+tempItem.getValue());
		}
	}
	
}

/**
 * Small Class to represent a item stored in the cart
 * @author jamesm01
 */
class CartItem {
	
	
	//Instance variables to hold name and value of The Cart Item
	private int value;
	private String name;
	
	/**
	 * Constructor Methods to set the name and value 
	 * @param value
	 * @param name
	 */
	public CartItem(int value, String name){
		this.value = value;
		this.name = name;
	}

	/**
	 * Get value of a cartItem
	 * @return the value as a int
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Get the name of the cartItem
	 * @return the name as a String
	 */
	public String getName() {
		return name;
	}
}
