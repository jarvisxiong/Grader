package main;

import grail.*;

import java.util.Scanner;
/**
 * 
 * @author Dong Nie
 * dongnie@cs.unc.edu
 *
 *This is for assignment2 from comp 401
 */
public class Assignment2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("please enter a string:");
		Scanner scan = new Scanner(System.in);
		String line="";
		BeanStringScanner bss=new BeanStringScanner();
		while(true)
		{
		 line = scan.nextLine();
		 if(line.equals(""))
			 continue;
		 if (line.charAt(0)=='.')
		 {
			 break;
		 }
		 //System.out.println("Processed by ordinary class");
		 //StringScanner.myScanner(line); 
		 System.out.println("Processed by bean class");
		 //test bean scanner
		 bss.setScannedString(line);
		}
	}

}
