package CDLShopping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInput {
	
	private BufferedReader reader;
	
	public ConsoleInput(){
		reader = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public int getPositiveInt(String questionText) throws IOException{
		System.out.println(questionText);
		String input = reader.readLine();
		while(!isPositiveInt(input)) {
			System.out.println("Invalid number, it must be an integer greater than 0. Please try again:");
			input = reader.readLine();
		}
		return Integer.parseInt(input);
	}
	
	public boolean isYes(String questionText) throws IOException {			
		System.out.println(questionText);
		String input = reader.readLine();
		while(!isYesNo(input)) {
			System.out.println("Invalid answer, please answer Y or N:");
			input = reader.readLine();
		}
		return (input.toUpperCase().equals("Y") || (input.toUpperCase().equals("Yes")));
	}
	
	public String getRawText(String questionText) throws IOException {
		System.out.println(questionText);
		return reader.readLine();
	}
	
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
