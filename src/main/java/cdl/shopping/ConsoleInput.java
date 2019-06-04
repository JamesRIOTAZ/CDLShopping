package cdl.shopping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Class to get command line inputs from the user and validate them
 * @author James McDonald
 */
public class ConsoleInput {
	
	//Instance variable used to read input from user
	private BufferedReader reader;
	
	/**
	 * Constructor method to instantiate the reader
	 */
	public ConsoleInput(){
		reader = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public ConsoleInput(BufferedReader reader) {
		this.reader = reader;
	}
	
	/**
	 * Ask user for input and than loop until they enter a valued number
	 * 
	 * Valid answer is a positive number of type integer
	 * 
	 * @param questionText string representing the question to be ask
	 * @return a positive number of type int
	 * @throws IOException
	 */
	public int getPositiveInt(String questionText) throws IOException{
		System.out.println(questionText);
		String input = reader.readLine();
		while(!isPositiveInt(input)) {
			System.out.println("Invalid number, it must be an integer greater than 0. Please try again:");
			input = reader.readLine();
		}
		return Integer.parseInt(input);
	}
	
	/**
	 * Ask user for input then loop until they answer a valid yes, no string
	 * 
	 * Valid answers are y, n, no, yes. check is not case sensitive
	 * 
	 * @param questionText string representing the question to be asked 
	 * @return boolean, true if input is yes, false if input is no
	 * @throws IOException
	 */
	public boolean isYes(String questionText) throws IOException {			
		System.out.println(questionText);
		String input = reader.readLine();
		while(!isYesNo(input)) {
			System.out.println("Invalid answer, please answer Y or N:");
			input = reader.readLine();
		}
		return (input.toUpperCase().equals("Y") || (input.toUpperCase().equals("Yes")));
	}
	
	/**
	 * Ask user for input and return answer as a string
	 * 
	 * @param questionText string representing the question to be ask
	 * @return String input by the user
	 * @throws IOException
	 */
	public String getRawText(String questionText) throws IOException {
		System.out.println(questionText);
		return reader.readLine();
	}
	
	/**
	 * Check if a String is a positive (>0) integer
	 * @param value String to check
	 * @return true if string is a integer with a value greater than 0, false if not
	 */
	private boolean isPositiveInt(String value) {
		boolean valid = false;
	    try {
	    	int num = Integer.parseInt(value);
	    		if(num >0) {
	    			valid = true;
	    		}
	    } catch (NumberFormatException ex){
	    }
	    return valid;
	 }
	
	/**
	 * Take in string and check if its a valid yes no
	 * 
	 * Valid answers are y, n, no, yes. check is not case sensitive
	 * 
	 * @param input String to check
	 * @return true if valid, false if not.
	 */
	private boolean isYesNo(String input){
		input = input.toUpperCase();
		if(input.equals("Y")|| input.equals("YES")) {
			return true;
		}
		if(input.equals("N")|| input.equals("NO")) {
			return true;
		}
		return false;
	}

}
