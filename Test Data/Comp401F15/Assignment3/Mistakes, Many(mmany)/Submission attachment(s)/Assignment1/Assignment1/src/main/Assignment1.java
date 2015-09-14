package main;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * 
 * @author kosko
 *
 *This is for assignment1 from comp 401
 */
public class Assignment1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("please enter a string:");
		Scanner scan = new Scanner(System.in);
		String line="";
		scan.delimiter();
		"hello world".split(" ");
		
		while(true)
		{
		 line = scan.nextLine();
		 if(line.equals(""))
			 continue;
		 if (line.charAt(0)=='.')
		 {
			 break;
		 }
		 
		 System.out.println(StringScanner.myScanner(line)); 
		}
	}

}
