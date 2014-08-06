package gradingTools.comp790ColabInc.example1.testcases;

import java.beans.PropertyChangeEvent;

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

	

}
