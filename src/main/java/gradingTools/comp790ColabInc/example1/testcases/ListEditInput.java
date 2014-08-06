package gradingTools.comp790ColabInc.example1.testcases;


public class ListEditInput extends ListEditInfo{

	public ListEditInput(String aMessage, OperationName anOperationName, int anIndex,
			Object anElement, String aList, Object aFinder) {
		super(aMessage, anOperationName, anIndex, anElement, aList, aFinder);
	}
	
	public ListEditInput(String aMessage, ListEditInfo aListEditInfo) {
		super(aMessage, aListEditInfo);
	}

	public static ListEditInput toTraceable (String aMessage) {
		ListEditInfo aListEditInfo = ListEditInfo.toTraceable(aMessage);
		return new ListEditInput(aMessage, aListEditInfo);
	}
	public static ListEditInput newCase(
			OperationName anOperationName, int anIndex,
			Object anElement, String aList, Object aFinder) {			
		String aMessage = toString(anOperationName, anIndex, anElement, aList);
		ListEditInput retVal = new ListEditInput(aMessage, anOperationName, anIndex, anElement, aList, aFinder);
		retVal.announce();
		return retVal;
	}

}
