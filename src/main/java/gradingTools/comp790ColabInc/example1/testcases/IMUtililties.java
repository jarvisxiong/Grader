package gradingTools.comp790ColabInc.example1.testcases;

import static util.pipe.ConsoleModelUtility.isOutputLine;

import java.beans.PropertyChangeEvent;

public class IMUtililties {
	public static boolean remoteEchoOf(PropertyChangeEvent aConsoleModelEvent, String anInput, String aUserName ) {
		if (!isOutputLine(aConsoleModelEvent)) return false;
		String aText = aConsoleModelEvent.getNewValue().toString();
		return aText.contains(IMUtililties.remoteEcho(anInput, aUserName));
		
	}
	
	public static boolean remoteEchoOf(String aProcessName, String anOutputLine, String anInput, String aUserName ) {

		return anOutputLine.contains(IMUtililties.remoteEcho(anInput, aUserName));
		
	}

	public static String remoteEcho(String anInput, String aUserName) {
		return anInput + "[" + aUserName + "]";
	}
	
	

}
