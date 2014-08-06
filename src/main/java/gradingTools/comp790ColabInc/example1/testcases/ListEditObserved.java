package gradingTools.comp790ColabInc.example1.testcases;


public class ListEditObserved extends ListEditInfo{

	public ListEditObserved(String aMessage, OperationName anOperationName, int anIndex,
			Object anElement, String aList, Object aFinder) {
		super(aMessage, anOperationName, anIndex, anElement, aList, aFinder);
	}
	public ListEditObserved(String aMessage, ListEditInfo aListEditInfo) {
		super(aMessage, aListEditInfo);
	}

	public static ListEditObserved toTraceable (String aMessage) {
		ListEditInfo aListEditInfo = ListEditInfo.toTraceable(aMessage);
		return new ListEditObserved(aMessage, aListEditInfo);
	}
	public static ListEditObserved newCase(
			OperationName anOperationName, int anIndex,
			Object anElement, String aList, Object aFinder) {			
		String aMessage = toString(anOperationName, anIndex, anElement, aList);
		ListEditObserved retVal = new ListEditObserved(aMessage, anOperationName, anIndex, anElement,aList, aFinder);
		retVal.announce();
		return retVal;
	}

}
