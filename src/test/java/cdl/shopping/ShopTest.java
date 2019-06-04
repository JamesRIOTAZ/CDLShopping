package cdl.shopping;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import org.junit.Test;

public class ShopTest {

	@Test
	public void testDefaultPrices() throws IOException {
		ConsoleInput input = new ConsoleInput(new BufferedReader(new StringReader("N")));
		Shop testShop = new Shop(input);
		testShop.askPriceRule();
		ItemInventory testResults = testShop.getItemInventory();
		
		assertEquals(4, testResults.getNumberItem());
		
		Item itemA = testResults.getItem(0);
		checkItemValues(itemA, "A", 50, 130, 3, -20);
		assertTrue(itemA.hasDiscount());
		
		Item itemB = testResults.getItem(1);
		checkItemValues(itemB, "B", 30, 45, 2, -15);
		assertTrue(itemB.hasDiscount());
		
		Item itemC = testResults.getItem(2);
		checkItemValues(itemC, "C", 20, 0, 0, 0);
		assertFalse(itemC.hasDiscount());
		
		Item itemD = testResults.getItem(3);
		checkItemValues(itemD, "D", 15, 0, 0, 0);
		assertFalse(itemD.hasDiscount());
	}
	
	@Test
	public void testPriceChangeYes() throws IOException {
		ConsoleInput input = new ConsoleInput(new BufferedReader(new StringReader("Y\n50\nn\n60\nn\n70\nn\n1\nn")));
		Shop testShop = new Shop(input);
		testShop.askPriceRule();
		ItemInventory testResults = testShop.getItemInventory();
		
		assertEquals(4, testResults.getNumberItem());
		
		Item itemA = testResults.getItem(0);
		checkItemValues(itemA, "A", 50, 0, 0, 0);
		assertFalse(itemA.hasDiscount());
		
		Item itemB = testResults.getItem(1);
		checkItemValues(itemB, "B", 60, 0, 0, 0);
		assertFalse(itemB.hasDiscount());
		
		Item itemC = testResults.getItem(2);
		checkItemValues(itemC, "C", 70, 0, 0, 0);
		assertFalse(itemC.hasDiscount());
		
		Item itemD = testResults.getItem(3);
		checkItemValues(itemD, "D", 1, 0, 0, 0);
		assertFalse(itemD.hasDiscount());
	}
	
	@Test
	public void testPriceChangeWithDiscount() throws IOException {
		ConsoleInput input = new ConsoleInput(new BufferedReader(new StringReader("Y\n50\nn\n60\nn\n70\ny\n5\n200\n\n80\ny\n1\n40\n")));
		Shop testShop = new Shop(input);
		testShop.askPriceRule();
		ItemInventory testResults = testShop.getItemInventory();
		
		assertEquals(4, testResults.getNumberItem());
		
		Item itemA = testResults.getItem(0);
		checkItemValues(itemA, "A", 50, 0, 0, 0);
		assertFalse(itemA.hasDiscount());
		
		Item itemB = testResults.getItem(1);
		checkItemValues(itemB, "B", 60, 0, 0, 0);
		assertFalse(itemB.hasDiscount());
		
		Item itemC = testResults.getItem(2);
		checkItemValues(itemC, "C", 70, 200, 5, -150);
		assertTrue(itemC.hasDiscount());
		
		Item itemD = testResults.getItem(3);
		checkItemValues(itemD, "D", 80, 40, 1, -40);
		assertTrue(itemD.hasDiscount());
	}
	
	@Test
	public void testPriceChangeWithInvalidInput() throws IOException {
		ConsoleInput input = new ConsoleInput(new BufferedReader(new StringReader("Y\n50\nApple\nn\n60\nn\n70\ny\n5\n200\n\n80\ny\n1\n80\n1\n40\n")));
		Shop testShop = new Shop(input);
		testShop.askPriceRule();
		ItemInventory testResults = testShop.getItemInventory();
		
		assertEquals(4, testResults.getNumberItem());
		
		Item itemA = testResults.getItem(0);
		checkItemValues(itemA, "A", 50, 0, 0, 0);
		assertFalse(itemA.hasDiscount());
		
		Item itemB = testResults.getItem(1);
		checkItemValues(itemB, "B", 60, 0, 0, 0);
		assertFalse(itemB.hasDiscount());
		
		Item itemC = testResults.getItem(2);
		checkItemValues(itemC, "C", 70, 200, 5, -150);
		assertTrue(itemC.hasDiscount());
		
		Item itemD = testResults.getItem(3);
		checkItemValues(itemD, "D", 80, 40, 1, -40);
		assertTrue(itemD.hasDiscount());
	}
	
	private void checkItemValues(Item item, String name, int value, int discountPrice, int discountQuantity, int totalDiscount) {
		assertEquals(name, item.getName());
		assertEquals(value, item.getValue());
		assertEquals(discountPrice, item.getDiscountPrice());
		assertEquals(discountQuantity, item.getDiscountQuantity());
		assertEquals(totalDiscount, item.getTotalDiscount());
	}
	
	@Test
	public void testScanItems() throws IOException {
		ConsoleInput input = new ConsoleInput(new BufferedReader(new StringReader("a\nb\nc\nd\nbuy")));
		Shop testShop = new Shop(input);
		testShop.scanItems();
		Cart testCart = testShop.getCart();
		assertEquals(115, testCart.getTotal());
	}
	
	@Test
	public void testScanItemsWithInvlidItem() throws IOException {
		ConsoleInput input = new ConsoleInput(new BufferedReader(new StringReader("a\nh\nb\nc\nd\nh\nbuy\nbuy")));
		Shop testShop = new Shop(input);
		testShop.scanItems();
		Cart testCart = testShop.getCart();
		assertEquals(115, testCart.getTotal());
	}
	
	@Test
	public void testScanItemsWithDiscount() throws IOException {
		ConsoleInput input = new ConsoleInput(new BufferedReader(new StringReader("a\na\na\nb\nc\nd\nbuy")));
		Shop testShop = new Shop(input);
		testShop.scanItems();
		Cart testCart = testShop.getCart();
		assertEquals(195, testCart.getTotal());
	}
	
}
