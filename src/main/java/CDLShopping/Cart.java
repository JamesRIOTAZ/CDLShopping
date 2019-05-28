package CDLShopping;

import java.util.ArrayList;

public class Cart {

	private ArrayList<CartItem> cartItems;
	{
		cartItems = new ArrayList<>();
	}
	
	public void addItem(String name, int value){
		CartItem item = new CartItem(value, name);
		cartItems.add(item);
	}
	
	public void checkDiscount(String name, int quantity, int value){
		int totalItems = countItems(name);

		if(totalItems % quantity==0){
			CartItem item = new CartItem(value, "Discount for "+name);
			cartItems.add(item);
		}
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
