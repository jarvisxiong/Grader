package sasa.echoerAndIM;

import java.util.ArrayList;
import java.util.List;

import echo.modular.AHistory;

public class AnObservableHistory<ElementType>  extends AHistory <ElementType>implements ObservableHistory<ElementType> {
//	static String QUIT = "quit";
//	static String HISTORY = "history";
//	List<ElementType> history = new ArrayList();
	List<HistoryObserver<ElementType>> observers = new ArrayList();
//	public void doInput() {
//		for (;;) {
//			System.out.println("Please enter an input line or " +
//				     QUIT + " or " + HISTORY);
//			String nextInput = Console.readString();
//			if (nextInput.equals(QUIT))
//				break;
//			else if (nextInput.equals(HISTORY))
//				printHistory();
//			else
//			    processInput(nextInput);
//		}
//	}
//	void processInput(String theNextInput) {
////		System.out.println("Input:" + theNextInput);
//		addToHistory(theNextInput);
//	}
	public synchronized void add(int index, ElementType input) {
		super.add(index, input);
		notifyObservers(input);
	}
//	public synchronized void printHistory() {
//		for (String input:history) {
//			System.out.print(input + ":");
//		}
//		System.out.println();
//	}
	@Override
	public void addObserver(HistoryObserver<ElementType> anObserver) {
		observers.add(anObserver);
	}
	
	public void notifyObservers(ElementType newValue) {
		for (HistoryObserver<ElementType> observer:observers)
			observer.elementAdded(newValue);
	}
}
