package cdl.shopping;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to control and hold the items available to be purchased in the shop
 * @author James McDonald
 */
public class ItemInventory {
	
	//List to hold the items
	List<Item> itemList;
	
	/**
	 * constructor methods to add items to list with default values
	 */
	public ItemInventory(){
		itemList = new ArrayList<>();
		
		Item itemA = new Item("A", 50, 130, 3);
		Item itemB = new Item("B", 30, 45, 2);
		Item itemC = new Item("C", 20);
		Item itemD = new Item("D", 15);
		
		addItem(itemA);
		addItem(itemB);
		addItem(itemC);
		addItem(itemD);
	}
	
	/**
	 * Set all values but name to 0/false in the inventory
	 */
	public void clearItemValues(){
		for(Item tempItem : itemList){
			tempItem.resetValues();
		}
	}
		
	/**
	 * Add a item to the inventory list
	 * @param item to be added of type Item
	 */
	public void addItem(Item item){
		itemList.add(item);
	}
	
	/**
	 * Return the item store in the list at the given location
	 * @param id location in list as a int
	 * @return item found as Item
	 */
	public Item getItem(int id){
		return itemList.get(id);
	}
	
	/**
	 * Return first item found in list with a matching name
	 * @param name of item to find as String
	 * @return item found as item. Null if no item found
	 */
	public Item getItem(String name){
		for(Item tempItem : itemList){
			if(tempItem.getName().equalsIgnoreCase(name)){
				return tempItem;
			}
		}
		return null;
	}
	
	/**
	 * Size of list, representing number of items in the inventory
	 * @return size of list as a int
	 */
	public int getNumberItem(){
		return itemList.size();
	}
	
	
	/**
	 * Take in a name and return if it match the name parameter of a item stored in the list
	 * @param name of item to find as a String
	 * @return boolean true if found, false if not
	 */
	public boolean canFindItem(String name){
		for(Item tempItem : itemList){
			if(tempItem.getName().equalsIgnoreCase(name)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Output all item to console with details of any discount
	 */
	public void outputItemsToScreen(){
		for(Item tempItem : itemList){
			String discountText = "";
			if(tempItem.hasDiscount()){
				discountText = " Special offer: "+tempItem.getDiscountQuantity()+" for "+tempItem.getDiscountPrice();
			}
			System.out.println("Item: "+tempItem.getName()+" Price "+tempItem.getValue()+discountText);
		}
	}

}
