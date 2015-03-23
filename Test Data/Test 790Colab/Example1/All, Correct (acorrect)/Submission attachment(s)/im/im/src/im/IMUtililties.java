package im;

import static util.models.ConsoleModelUtility.isOutputLine;

import java.beans.PropertyChangeEvent;

public class IMUtililties {
	public static boolean remoteEchoOf(PropertyChangeEvent aConsoleModelEvent, String anInput, String aUserName ) {
		if (!isOutputLine(aConsoleModelEvent)) return false;
		String aText = aConsoleModelEvent.getNewValue().toString();
		return aText.contains(AHistoryInCoupler.remoteEcho(anInput, aUserName));
		
	}
	
	
	
	

}
