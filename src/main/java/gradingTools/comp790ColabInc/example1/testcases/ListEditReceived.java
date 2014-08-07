package gradingTools.comp790ColabInc.example1.testcases;


public class ListEditReceived extends CommunicatedListEditInfo{

	public ListEditReceived(String aMessage, String aProcessName, OperationName anOperationName, int anIndex,
			Object anElement, String aList,  String aDestinationOrSource, Object aFinder) {
		super(aMessage, aProcessName, anOperationName, anIndex, anElement, aList, aDestinationOrSource, aFinder);
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
			Object anElement, String aList, String aSourceOrDestination, Object aFinder) {
			
		String aMessage = toString(aProcessName, anOperationName, anIndex, anElement, aList, aSourceOrDestination);
		ListEditReceived retVal = new ListEditReceived(aMessage, aProcessName, anOperationName, anIndex, anElement, aList, aSourceOrDestination, aFinder);
		retVal.announce();
		return retVal;
	}

}
