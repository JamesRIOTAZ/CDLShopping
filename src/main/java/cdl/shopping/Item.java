package cdl.shopping;

/**
 * Class represents a item that can be bought by a user
 * Hold name, value and discount details
 * 
 * @author James McDonald
 */
public class Item {

	//instance variables
	private String name;
	private int value;
	
	private boolean discount;
	private int discountPrice;
	private int discountQuantity;
	private int totalDiscount;
	
	/**
	 * Create a new item with name set
	 * @param name as string
	 */
	public Item(String name){
		this.name = name;
	}
	
	/**
	 * Create a new item with name and value set
	 * 
	 * @param name as String
	 * @param value as int
	 */
	public Item(String name, int value){
		this.name = name;
		this.value = value;
	}
	
	/**
	 * Create a item with name, value and discount
	 * @param name as String
	 * @param value as int
	 * @param discountPrice as int
	 * @param discountQuanity as int
	 */
	public Item(String name, int value, int discountPrice, int discountQuanity){
		this.name = name;
		this.value = value;
		setDiscount(discountPrice, discountQuanity);
	}
	
	/**
	 * Reset all values to 0 or false;
	 */
	public void resetValues(){
		discount = false;
		value = 0;
		discountPrice = 0;
		discountQuantity  = 0;
		totalDiscount = 0;
	}
	
	/**
	 * if discount is valid (less than standard cost) set discount variables
	 * 
	 * @param discountPrice as int
	 * @param discountQuanity as int
	 */
	public void setDiscount(int discountPrice, int discountQuanity) {
		if(validDiscount(discountPrice, discountQuanity)) {
			this.discountPrice = discountPrice;
			this.discountQuantity = discountQuanity;
			this.totalDiscount = discountPrice - (value * discountQuanity);
			this.discount = true;
		}
	}
	
	/**
	 * Check if a discount is less than the cost of the items at the standard price
	 * 
	 * @param discountPrice as int
	 * @param discountQuanity as int
	 * @return boolean. True if total discount is yes, false if same or more
	 */
	public boolean validDiscount(int discountPrice, int discountQuanity) {
		return ((value * discountQuanity)>discountPrice);
	}
	
	/**
	 * return the name of the item
	 * @return name as a string
	 */
	public String getName() {
		return name;
	}
	/**
	 * Set name of the item
	 * @param name of item as a String
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * return the value of the item
	 * @return value as a int
	 */
	public int getValue() {
		return value;
	}
	/**
	 * Set the value of the item
	 * @param value as int
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * If the item has a discount
	 * @return boolean, ture if yes. false if no
	 */
	public boolean hasDiscount() {
		return discount;
	}

	/**
	 * Discount price for buying multiple items
	 * @return discount price as a int
	 */
	public int getDiscountPrice() {
		return discountPrice;
	}

	/**
	 * Number of items required for a discount
	 * @return discount quantity as a int
	 */
	public int getDiscountQuantity() {
		return discountQuantity;
	}

	/**
	 * Total discount is difference in cost 
	 * between the discount and non discount totals
	 * @return total discount as int
	 */
	public int getTotalDiscount() {
		return totalDiscount;
	}
}
