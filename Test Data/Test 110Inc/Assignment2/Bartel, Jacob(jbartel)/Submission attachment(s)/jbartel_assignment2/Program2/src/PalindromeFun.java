import java.util.*;
/******************************************************************
 * Program or Assignment #: Assignment 2
 *
 * Programmer: Kyle Miller
 *
 * Due Date: N/A
 *
 * COMP110-002, Spring 2014       Instructor: Prof. Jay Aikat
 *
 * Pledge: I have neither given nor received unauthorized aid
 *         on this program. 
 *
 * Description: Takes a string and tests to see whether or not it's a palindrome
 *
 * Input: A string
 *
 * Output: Error message if the string isn't the desired length otherwise it informs the user
 * 	whether the string is a palindrome or not
 *
 ******************************************************************/
public class PalindromeFun {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// Prompt user for input
		System.out.print("Please, enter a string with a length between one and eight characters:");
		String inputString = sc.nextLine();
		
		// Error if the string isn't the desired length
		if(inputString.length() > 6 || inputString.length() < 1)
		{
			System.out.print("There was an Error. Please, enter a string with a length between one and eight characters instead of " + inputString.length() + " characters");
			return;
		}

		// Test to see if the string is a palindrome
		for(int i=0; i < inputString.length() / 2; i++)
			if(inputString.charAt(i) != inputString.charAt(inputString.length() - 1 - i))
			{
				System.out.print("The string you entered is not a palindrome.");
				return;
			}

		// The string is a palindrome, inform the user
		System.out.print("The string you entered is a palindrome.");
	}

}
