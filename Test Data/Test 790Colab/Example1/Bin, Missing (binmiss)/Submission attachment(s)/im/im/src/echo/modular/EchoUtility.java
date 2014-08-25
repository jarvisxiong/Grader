package echo.modular;

import java.beans.PropertyChangeEvent;

public class EchoUtility {
	public static boolean localEchoOf(PropertyChangeEvent aConsoleModelEvent, String anInput ) {
		String aText = aConsoleModelEvent.getNewValue().toString();
//		return aText.contains(anInput) && (aText.contains(EchoerInteractor.ECHO_INDICATOR));

//		return 	aText.contains(AnEchoerInteractor.echo(anInput));
		return localEchoOf(aText, anInput);
		
	}
	public static boolean localEchoOf(String aText, String anInput ) {
//		return aText.contains(anInput) && (aText.contains(EchoerInteractor.ECHO_INDICATOR));

		return 	aText.contains(AnEchoInteractor.echo(anInput));
		
	}


}
