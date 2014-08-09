package echo.monolithic;
import im.OperationName;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import trace.echo.modular.ListEditInput;
import trace.echo.modular.ListEditObserved;
import util.annotations.Tags;
import util.tags.ApplicationTags;
import util.tags.InteractiveTags;
@Tags({ApplicationTags.ECHOER})
public class MonolithicEchoer  {
	
	protected static List<String> history = new ArrayList();
	public static final String ECHO_INDICATOR = "[Echo]";
	public static final String QUIT = "quit";
	public static final String HISTORY = "history";	
	public static void main(String[] anArgs) {
		for (;;) {
			System.out.println("Please enter an input line or " +
				     QUIT + " or " + HISTORY);
			Scanner scanner = new Scanner(System.in);
			String nextInput = scanner.nextLine();
			if (nextInput.equals(QUIT)) {
				processQuit();
				break;
			} else if (nextInput.equals(HISTORY))
				printHistory();
			else
			    processInput(nextInput);
		}
	}
	/*
	 * Utlity function for tester not used in this program but should probably be
	 * in this class
	 */
	public static boolean isInput(String anInput) {
		return !anInput.equals(QUIT) && !anInput.equals(HISTORY) ;
		
	}
	public static final String echo(String anInput) {
		return anInput + ECHO_INDICATOR;
	}
	protected static void displayOutput(String newValue) {
		System.out.println(echo(newValue));
	}
	protected  static void addToHistory(String newValue) {
		history.add(history.size(), newValue);
	}
	protected static void processInput(String newValue) {
		addToHistory(newValue);
		displayOutput(newValue);

	}
	protected static void processQuit() {
		System.out.println("Quitting application");
	}
	public static String toString(List aHistory) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int index = 0; index < aHistory.size(); index++) {
			stringBuilder.append(aHistory.get(index));			
			stringBuilder.append((index == aHistory.size() - 1)? "\n":", ");
		}		
		return stringBuilder.toString();
	}
	public static  void printHistory() {
		System.out.println(toString(history));
	}
	

}
