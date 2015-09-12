package main;

import java.util.Scanner;

/**
 * 
 * @author kosko
 *
 *This is for assignment1 from comp 401
 */

	public class ProcString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("please enter a string:");
		Scanner scan = new Scanner(System.in);
		String line="";
		while(line!=".")
		{
		 line = scan.nextLine();
		 String outLine="Numbers:";
		 int sum=0;
		 int product=1;
		 char c;
		 int beginInd=0;
		 int endInd=0;
		 int isDigit=0;
		 String num="";
		 for (int i=0;i<line.length();i++)
		 {
			 c=line.charAt(i);
			 if (Character.isDigit(c))
			 {
				 if (isDigit==0)
				 {
					 isDigit=1;
					 beginInd=i;
				 }
				 continue;
			 }else if (c==' ')
			 {
				 if(isDigit==1)
				 {
					 endInd=i;//the endInd is excluded
					 num=line.substring(beginInd,endInd);
					 int hh=Integer.parseInt(num);
					 sum+=hh;
					 product*=hh;
					 outLine+=" "+hh;
					 System.out.println("the num :"+num+" and the integer is: "+hh);
					 isDigit=0;					 
				 }
			 }else//other character
			 {
				 System.out.println("unexpected character:"+c);
				 if(isDigit==1)
				 {
					 endInd=i;//the endInd is excluded
					 num=line.substring(beginInd,endInd);
					 int hh=Integer.parseInt(num);
					 sum+=hh;
					 product*=hh;
					 outLine+=" "+hh;
					 System.out.println("the num :"+num+" and the integer is: "+hh);
					 isDigit=0;					 
				 }
			 }
		 }
		 if (isDigit==1)//for the last digit
		 {
			 endInd=line.length();
			 num=line.substring(beginInd,endInd);
			 int hh=Integer.parseInt(num);
			 sum+=hh;
			 product*=hh;
			 outLine+=" "+hh;
			 System.out.println("the num :"+num+" and the integer is: "+hh);
			 isDigit=0;
		 }
		 outLine+=" Sum: "+sum+" Product: "+product;
		 System.out.println(outLine); 
		}
	}

}
