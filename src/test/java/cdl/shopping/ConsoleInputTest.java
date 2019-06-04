package cdl.shopping;

import static org.junit.Assert.*;

import org.junit.Test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;


public class ConsoleInputTest {
	

	@Test
	public void testGetPositiveIntOnFirstInput() throws IOException {
		ConsoleInput testSubject = new ConsoleInput(new BufferedReader(new StringReader("2")));
		int result = testSubject.getPositiveInt("Testing?");
		assertEquals(2, result);
	}
	
	@Test
	public void testGetPositiveIntOnSecondInput() throws IOException {
		ConsoleInput testSubject = new ConsoleInput(new BufferedReader(new StringReader("0\n4")));
		int result = testSubject.getPositiveInt("Testing?");
		assertEquals(4, result);
	}
	
	@Test
	public void testGetPositiveIntOnThirdInput() throws IOException {
		ConsoleInput testSubject = new ConsoleInput(new BufferedReader(new StringReader("0\n0\n4")));
		int result = testSubject.getPositiveInt("Testing?");
		assertEquals(4, result);
	}
	
	@Test
	public void testGetPositiveIntWithEmptyStringFirst() throws IOException {
		ConsoleInput testSubject = new ConsoleInput(new BufferedReader(new StringReader("\n2")));
		int result = testSubject.getPositiveInt("Testing?");
		assertEquals(2, result);
	}
	
	@Test
	public void testGetPositiveIntWithSpecialCharactersFirst() throws IOException {
		ConsoleInput testSubject = new ConsoleInput(new BufferedReader(new StringReader("%\n$\n*\n'\n2")));
		int result = testSubject.getPositiveInt("Testing?");
		assertEquals(2, result);
	}
	
	@Test
	public void testisYesTrue() throws IOException {
		ConsoleInput testSubject = new ConsoleInput(new BufferedReader(new StringReader("Yes")));
		assertTrue(testSubject.isYes("Testing Yes"));
	}
	
	@Test
	public void testisNofalse() throws IOException {
		ConsoleInput testSubject = new ConsoleInput(new BufferedReader(new StringReader("no")));
		assertFalse(testSubject.isYes("Testing no"));
	}	
	@Test
	public void testisYTrue() throws IOException {
		ConsoleInput testSubject = new ConsoleInput(new BufferedReader(new StringReader("y")));
		assertTrue(testSubject.isYes("Testing y"));
	}
	
	@Test
	public void testisNfalse() throws IOException {
		ConsoleInput testSubject = new ConsoleInput(new BufferedReader(new StringReader("n")));
		assertFalse(testSubject.isYes("Testing n"));
	}
	
	@Test
	public void testisYesInvalid() throws IOException {
		ConsoleInput testSubject = new ConsoleInput(new BufferedReader(new StringReader("Pie\nyes")));
		assertTrue(testSubject.isYes("Testing yep Yes"));
	}
	
	@Test
	public void testRawText() throws IOException {
		ConsoleInput testSubject = new ConsoleInput(new BufferedReader(new StringReader("hello")));
		assertEquals("hello", testSubject.getRawText("Test raw text"));
	}
	
	@Test
	public void testRawTextNothing() throws IOException {
		ConsoleInput testSubject = new ConsoleInput(new BufferedReader(new StringReader("")));
		assertNull(testSubject.getRawText("Test no text"));
	}
	
	
	
}
