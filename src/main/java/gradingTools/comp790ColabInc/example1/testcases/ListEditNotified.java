package gradingTools.comp790ColabInc.example1.testcases;


public class ListEditNotified extends ListEditInfo{

	public ListEditNotified(String aMessage, OperationName anOperationName, int anIndex,
			Object anElement, String aList, Object aFinder) {
		super(aMessage, anOperationName, anIndex, anElement, aList, aFinder);
	}
	public ListEditNotified(String aMessage, ListEditInfo aListEditInfo) {
		super(aMessage, aListEditInfo);
	}

	public static ListEditNotified toTraceable (String aMessage) {
		ListEditInfo aListEditInfo = ListEditInfo.toTraceable(aMessage);
		return new ListEditNotified(aMessage, aListEditInfo);
	}
	public static ListEditNotified newCase(
			OperationName anOperationName, int anIndex,
			Object anElement, String aList, Object aFinder) {			
		String aMessage = toString(anOperationName, anIndex, anElement, aList);
		ListEditNotified retVal = new ListEditNotified(aMessage, anOperationName, anIndex, anElement, aList, aFinder);
		retVal.announce();
		return retVal;
	}

}
