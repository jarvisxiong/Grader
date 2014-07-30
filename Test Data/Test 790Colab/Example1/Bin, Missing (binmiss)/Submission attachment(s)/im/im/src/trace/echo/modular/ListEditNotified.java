package trace.echo.modular;

import im.OperationName;

public class ListEditNotified extends ListEditInfo{

	public ListEditNotified(String aMessage, OperationName anOperationName, int anIndex,
			Object anElement, Object aFinder) {
		super(aMessage, anOperationName, anIndex, anElement, aFinder);
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
			Object anElement, Object aFinder) {			
		String aMessage = toString(anOperationName, anIndex, anElement);
		ListEditNotified retVal = new ListEditNotified(aMessage, anOperationName, anIndex, anElement,aFinder);
		retVal.announce();
		return retVal;
	}

}
