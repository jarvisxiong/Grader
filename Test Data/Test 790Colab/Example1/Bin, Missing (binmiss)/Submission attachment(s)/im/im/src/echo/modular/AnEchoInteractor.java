package echo.modular;
import im.OperationName;

import java.util.Scanner;

import echo.monolithic.MonolithicEchoer;
import trace.echo.modular.ListEditInput;
import trace.echo.modular.ListEditObserved;
import util.annotations.Tags;
import util.tags.ApplicationTags;
import util.tags.InteractiveTags;
@Tags({ApplicationTags.ECHOER, InteractiveTags.INTERACTOR})
public class AnEchoInteractor implements EchoerInteractor {
	
	protected History<String> history;
	public static final String PROMPT = "Please enter an input line or " +
		     QUIT + " or " + HISTORY;
	public AnEchoInteractor(History<String> aHistory) {
		history = aHistory;
	}
	public AnEchoInteractor() {
	}
	public void doInput() {
		for (;;) {
//			System.out.println("Please enter an input line or " +
//				     QUIT + " or " + HISTORY);
//			System.out.println(Thread.currentThread());
			System.out.println(PROMPT);
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
	public static final String echo(String anInput) {
		return anInput + ECHO_INDICATOR;
	}
	protected void displayOutput(String newValue) {
		System.out.println(echo(newValue));
	}
	// overridden by IMInteractor
	protected void addToHistory(String newValue) {
//		int anIndex = history.size();
//		history.observableAdd(history.size(), newValue);
		history.observableAdd(newValue);

	}
	protected void processInput(String newValue) {
//		displayOutput(newValue);
		ListEditInput.newCase(OperationName.ADD, history.size(), newValue, this);
		addToHistory(newValue);
	}
	protected void processQuit() {
		System.out.println("Quitting application");
	}
	public static String toString(History aHistory) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int index = 0; index < aHistory.size(); index++) {
			stringBuilder.append(aHistory.get(index));			
			stringBuilder.append((index == aHistory.size() - 1)? "\n":", ");
		}		
		return stringBuilder.toString();
	}
	public synchronized void printHistory() {
//		System.out.println(Thread.currentThread());

		System.out.println(toString(history));
//		for (int index = 0; index < history.size(); index++) {
//			System.out.print(history.get(index));
//		}
//		System.out.println();
	}
	@Override
	public void elementAdded(int anIndex, Object aNewValue) {
		ListEditObserved.newCase(OperationName.ADD, anIndex, aNewValue, this);
		displayOutput(history.get(anIndex));
	}

}
