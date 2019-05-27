package CDLShopping;

public class Item {

	private String name;
	private int value;
	
	private boolean discount;
	private int discountPrice;
	private int discountQuanity;
	
	public Item(String name){
		this.name = name;
	}
	
	public void setDiscount(int discountPrice, int discountQuanity) {
		this.discountPrice = discountPrice;
		this.discountQuanity = discountQuanity;
		this.discount = true;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}

	public boolean isDiscount() {
		return discount;
	}

	public int getDiscountPrice() {
		return discountPrice;
	}

	public int getDiscountQuanity() {
		return discountQuanity;
	}
	
}
