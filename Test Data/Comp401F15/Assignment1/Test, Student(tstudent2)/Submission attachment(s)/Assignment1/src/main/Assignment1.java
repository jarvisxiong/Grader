package main;
import java.util.Scanner;


import java.util.Scanner;

public class Assignment1 
{
	public static void main(String[] args) 
	{
		
		try 
		{
			Scanner in = new Scanner(System.in);
			String input = " ";
			
			System.out.println("Type your input or \".\" if you'd like to stop");
			input = in.nextLine();
			
			
			while (input.charAt(0)!= '.')
			{
			
			stringProcessor numProcessor = new stringProcessor(input);
			numProcessor.showTotals();
			
			
			System.out.println("Type your input or \".\" if you'd like to stop");
			input = in.nextLine();
			
			
			}
			
			System.out.println("Good bye!"); // End of program
			
			
			
		} catch (Exception e) {
			System.out.println("\nPlease only use integers");
			System.out.println("Please restart and try again");
		}
		
	}// end main
	

}
