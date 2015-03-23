#import java.util.Scanner;
#/******************************************************************
# * Program or Assignment #: Assignment1
# *
# * Programmer: Jacob
# *
# * Due Date: Tuesday, Jan. 28
# *
# * COMP110-002, Spring 2014       Instructor: Prof. Jay Aikat
# *
# * Pledge: I have neither given nor received unauthorized aid
# *         on this program. 
# *
# * Description: Insert a brief paragraph describing the program
# *
# * Input: Insert a brief description of user inputs, or "None" if
# *        there is no user input
# *
# * Output: Insert a brief description of the program output
# *
# ******************************************************************/
#
#public class InputAndOutput {
#
#	public static void main(String[] args) {
#		Scanner scan = new Scanner(System.in);
#		System.out.println("Please input an integer");
#		int num1 = scan.nextInt();
#		System.out.println("Please input a decimal");
#		double num2 = scan.nextDouble();
#		System.out.println("The int addition:"+ (num1 + (int) num2));
#		System.out.println("The double addition:"+ ( (double) num1 + num2));
#		System.out.println("The int multiplication:"+ (num1 * (int) num2));
#		System.out.println("The double multiplication:"+ ( (double) num1 * num2));
#		
#	}
#
#}
##
num1 = int(raw_input("Please input an integer "));
num2 =  float(raw_input("Please input a decimal "));
print "The int addition:", num1 + int(num2);
print "The double addition:", float(num1) + num2;
print "The int multiplication:", num1 * int(num2);
print "The double multiplication:", float(num1)*num2;
  		