package sasa.echoerAndIM;

import java.util.ArrayList;
import java.util.List;

import util.misc.Console;

public class AnEchoer implements Echoer {
	static String QUIT = "quit";
	static String HISTORY = "history";
	List<String> history = new ArrayList();
	public void doInput() {
		for (;;) {
			System.out.println("Please enter an input line or " +
				     QUIT + " or " + HISTORY);
			String nextInput = Console.readString();
			if (nextInput.equals(QUIT))
				break;
			else if (nextInput.equals(HISTORY))
				printHistory();
			else
			    processInput(nextInput);
		}
	}
	void processInput(String theNextInput) {
		System.out.println("Input:" + theNextInput);
		addToHistory(theNextInput);
	}
	public synchronized void addToHistory(String input) {
		history.add(input);
	}
	public synchronized void printHistory() {
		for (String input:history) {
			System.out.print(input + ":");
		}
		System.out.println();
	}
}
