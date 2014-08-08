package gradingTools.comp790ColabInc.example1.testcases;

import static util.pipe.ConsoleModelUtility.isOutputLine;

import java.beans.PropertyChangeEvent;
import java.util.List;

public class EchoUtilities {
	public static final String QUIT = "quit";
	public static final String HISTORY = "history";
	public static final String ECHO_INDICATOR = "[Echo]";

	/*
	 * Utlity function for tester not used in this program but should probably be
	 * in this class
	 */
	public static boolean isInput(String anInput) {
		return !anInput.equals(EchoUtilities.QUIT) && !anInput.equals(EchoUtilities.HISTORY) ;
		
	}

	public static boolean localEchoOf(PropertyChangeEvent aConsoleModelEvent, String anInput ) {
			String aText = aConsoleModelEvent.getNewValue().toString();
	//		return aText.contains(anInput) && (aText.contains(EchoerInteractor.ECHO_INDICATOR));
	
	//		return 	aText.contains(AnEchoerInteractor.echo(anInput));
			return EchoUtilities.localEchoOf(aText, anInput);
			
		}

	public static boolean localEchoOf(String aText, String anInput ) {
	//		return aText.contains(anInput) && (aText.contains(EchoerInteractor.ECHO_INDICATOR));
	
			return 	aText.contains(echo(anInput));
			
		}

	public static final String echo(String anInput) {
		return anInput + ECHO_INDICATOR;
	}

	public static String toString(List aList) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int index = 0; index < aList.size(); index++) {
			stringBuilder.append(aList.get(index));			
			stringBuilder.append((index == aList.size() - 1)? "\n":", ");
		}		
		return stringBuilder.toString();
	}

	public static String echoOf(String anInput) {
		return echo(anInput);
	}

	public static boolean echoOf(String aProcessName, String anOutputLine, String anInput) {
	
	//		return aText.contains(MonolithicEchoer.echo(anInput));
			return anOutputLine.contains(echoOf(anInput));
	
			
		}

	public static boolean echoOf(PropertyChangeEvent aConsoleModelEvent, String anInput) {
			if (!isOutputLine(aConsoleModelEvent)) return false;
			String aText = aConsoleModelEvent.getNewValue().toString();
	//		return aText.contains(MonolithicEchoer.echo(anInput));
			return aText.contains(echoOf(anInput));
	
			
		}

	public static final String PROMPT = "Please enter an input line or " +
	 QUIT + " or " + HISTORY;

	

}
