package main;

import java.util.Scanner;

public class Assignment2 {

	public static void main(String[] args) {
		BeanA2 scannerBean = new BeanA2();
		while (true) { //loops program to ask for input until terminating character is entered
			System.out.println("Please enter a line of text: ");
			Scanner input = new Scanner(System.in);		
			String inputLine = input.nextLine();
			if (inputLine.charAt(0) != '.') { //runs program while terminating character is not entered
				scannerBean.setScannedString(inputLine);
			}
			else { //terminates the program
				System.out.println("Terminating character entered - terminating program.");
				System.exit(0);
			}		
		}
	}	
}

