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
	
	
	
}
