package cdl.shopping;

import java.util.ArrayList;
import java.util.List;

public class Cart {

	private List<CartItem> cartItems;
	{
		cartItems = new ArrayList<>();
	}
	
	public void addItem(Item tempItem){
		CartItem item = new CartItem(tempItem.getValue(), tempItem.getName());
		cartItems.add(item);
	}
	
	public boolean checkDiscount(Item tempItem){
		int totalItems = countItems(tempItem.getName());
		return (totalItems % tempItem.getDiscountQuanity()==0);
	}
	
	public void addDiscount(Item tempItem){
		CartItem item = new CartItem(tempItem.getValue(), "Discount for "+tempItem.getName());
		cartItems.add(item);
	}
	
	public List<CartItem> getCartItems() {
		return cartItems;
	}
	
	private int countItems(String name){
		int totalItems = 0;
		for(CartItem tempItem : cartItems) {
			if(name.equals(tempItem.getName())){
				totalItems++;
			}
		}
		return totalItems;
	}
	
	public int getTotal(){
		int total = 0;
		for(CartItem tempItem : cartItems) {
			total += tempItem.getValue();
		}
		return total;
	}
	
}

class CartItem {
	private int value;
	private String name;
	
	public CartItem(int value, String name){
		this.value = value;
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}
}
