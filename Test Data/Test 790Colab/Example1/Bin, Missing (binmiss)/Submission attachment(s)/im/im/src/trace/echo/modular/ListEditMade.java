package trace.echo.modular;

import im.OperationName;

public class ListEditMade extends ListEditInfo{

	public ListEditMade(String aMessage, OperationName anOperationName, int anIndex,
			Object anElement, Object aFinder) {
		super(aMessage, anOperationName, anIndex, anElement, aFinder);
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
			Object anElement, Object aFinder) {			
		String aMessage = toString(anOperationName, anIndex, anElement);
		ListEditMade retVal = new ListEditMade(aMessage, anOperationName, anIndex, anElement,aFinder);
		retVal.announce();
		return retVal;
	}

}
