package main;
import java.util.ArrayList;
import java.util.Scanner;
public class Scan 
{
	public static void main(String[] args)
	{
		Scanner keyboard = new Scanner(System.in);
		String inputString = keyboard.nextLine();
		char period = '.';
		char firstChar = inputString.charAt(0);
		//terminate if a period is entered as the first character of the line
		//loop through input lines if period is not entered
		while (firstChar != period)
		{
			scan (inputString);
			System.out.println();
			inputString = keyboard.nextLine();
			firstChar = inputString.charAt(0);
		}
		System.exit(-1);
		keyboard.close();
	}
	public static void scan(String inputString)
	{
		//create Array List to store numbers
		ArrayList<String> numberList = new ArrayList<String>();
		System.out.print("Numbers: ");
		int sum = 0;
		int product = 1;
		String numberString = "";
		for (int index=0;index<inputString.length();index++)
		{
			char currentNumber = inputString.charAt(index);
			char space = ' ';
			//if at a space, add the number to the Array List
			if (currentNumber == space) {
				numberList.add(numberString);
				numberString = "";
			//if last number in string, add number to Array and print array, sum and product
			} else if(index == inputString.length() - 1) {
				numberString += currentNumber;
				numberList.add(numberString);
				for(int i=0;i<numberList.size();i++){
					System.out.print(numberList.get(i) + " ");
					int num = Integer.parseInt(numberList.get(i));
					sum += num;
					product *= num;
				}
				numberString = "";
				System.out.print("Sum: " + sum + " Product: " + product);
			//for all other numbers, add to the temporary string to later be added to the Array List once a space is found
			} else if (currentNumber != space) {
				numberString += currentNumber;
			}
		}	
	}
}

