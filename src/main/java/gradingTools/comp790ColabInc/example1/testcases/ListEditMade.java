package gradingTools.comp790ColabInc.example1.testcases;


public class ListEditMade extends ListEditInfo{

	public ListEditMade(String aMessage, OperationName anOperationName, int anIndex,
			Object anElement, String aList, Object aFinder) {
		super(aMessage, anOperationName, anIndex, anElement, aList, aFinder);
	}
	public ListEditMade(String aMessage, ListEditInfo aListEditInfo) {
		super(aMessage, aListEditInfo);
	}

	public static ListEditMade toTraceable (String aMessage) {
		ListEditInfo aListEditInfo = ListEditInfo.toTraceable(aMessage);
		return new ListEditMade(aMessage, aListEditInfo);
	}
	public static ListEditMade newCase(
			OperationName anOperationName, int anIndex,
			Object anElement, String aList, Object aFinder) {			
		String aMessage = toString(anOperationName, anIndex, anElement, aList);
		ListEditMade retVal = new ListEditMade(aMessage, anOperationName, anIndex, anElement, aList, aFinder);
		retVal.announce();
		return retVal;
	}

}
