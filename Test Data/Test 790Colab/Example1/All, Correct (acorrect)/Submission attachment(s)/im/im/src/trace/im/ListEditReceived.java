package trace.im;

import trace.echo.modular.ListEditInfo;
import trace.echo.modular.ListEditInput;
import im.OperationName;

public class ListEditReceived extends CommunicatedListEditInfo{

	public ListEditReceived(String aMessage, String aProcessName, OperationName anOperationName, int anIndex,
			Object anElement,  String aDestinationOrSource, Object aFinder) {
		super(aMessage, aProcessName, anOperationName, anIndex, anElement, aDestinationOrSource, aFinder);
	}
	public ListEditReceived(String aMessage, CommunicatedListEditInfo aListEditInfo) {
		super(aMessage, aListEditInfo);
	}

	public static ListEditReceived toTraceable (String aMessage) {
		CommunicatedListEditInfo aListEditInfo = CommunicatedListEditInfo.toTraceable(aMessage);
		return new ListEditReceived(aMessage, aListEditInfo);
	}
	public static ListEditReceived newCase(
			String aProcessName,
			OperationName anOperationName, int anIndex,
			Object anElement, String aSourceOrDestination, Object aFinder) {
			
		String aMessage = toString(aProcessName, anOperationName, anIndex, anElement, aSourceOrDestination);
		ListEditReceived retVal = new ListEditReceived(aMessage, aProcessName, anOperationName, anIndex, anElement, aSourceOrDestination, aFinder);
		retVal.announce();
		return retVal;
	}

}
