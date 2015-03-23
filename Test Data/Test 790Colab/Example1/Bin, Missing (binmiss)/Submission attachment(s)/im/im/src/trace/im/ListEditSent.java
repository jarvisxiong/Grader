package trace.im;

import im.OperationName;

public class ListEditSent extends CommunicatedListEditInfo{

	public ListEditSent(String aMessage, String aProcessName, OperationName anOperationName, int anIndex,
			Object anElement,  String aDestinationOrSource, Object aFinder) {
		super(aMessage, aProcessName, anOperationName, anIndex, anElement, aDestinationOrSource, aFinder);
	}
	public ListEditSent(String aMessage, CommunicatedListEditInfo aListEditInfo) {
		super(aMessage, aListEditInfo);
	}

	public static ListEditSent toTraceable (String aMessage) {
		CommunicatedListEditInfo aListEditInfo = CommunicatedListEditInfo.toTraceable(aMessage);
		return new ListEditSent(aMessage, aListEditInfo);
	}
	public static ListEditSent newCase(
			String aProcessName,
			OperationName anOperationName, int anIndex,
			Object anElement, String aSourceOrDestination, Object aFinder) {
			
		String aMessage = toString(aProcessName, anOperationName, anIndex, anElement, aSourceOrDestination);
		ListEditSent retVal = new ListEditSent(aMessage, aProcessName, anOperationName, anIndex, anElement, aSourceOrDestination, aFinder);
		retVal.announce();
		return retVal;
	}

}
