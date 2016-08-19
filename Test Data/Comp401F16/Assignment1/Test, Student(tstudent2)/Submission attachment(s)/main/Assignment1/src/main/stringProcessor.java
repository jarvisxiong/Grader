package main;

public class stringProcessor 
{
	private char delim = ' ';
	private int start = 0;
	private int end = 0;
	
	private int addTotal = 0;
	private int multiplyTotal=1; //anything times one is itself
	
	 /**
	  * the constructor will output the numbers entered but not the totals
	  * 
	  */
	
	public stringProcessor(String userInput)
	{
	System.out.print("Values Entered: ");

	for(int i = 0;i<userInput.length();i++)
	{
		if (userInput.charAt(i)==delim)
		{
			end = i;
			
			System.out.print(userInput.substring(start, end)+ " ");
			totaler(Integer.parseInt(userInput.substring(start, end)));
			
			start = i+1; // the start of the next token is one space after the delimiter
		}
		if (i+1 == userInput.length()) // condition that the index is the last of the string
		{
			
			System.out.print(userInput.substring(start)+ " ");
			totaler(Integer.parseInt(userInput.substring(start)));
		}
	}//end for
	
	
	
	}
	/**
	 * Adds and multiplies the tokens
	 */
	private void totaler(int value)
	{
		addTotal+=value;
		multiplyTotal*=value;
	}
	
	public void showTotals() // prints the totals on the same line
	{
		System.out.print("Sum: "+ addTotal + " ");
		System.out.print("Product: " + multiplyTotal);
		System.out.println();
	}
	
}