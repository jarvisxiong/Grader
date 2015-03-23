package trace.echo.modular;

import im.OperationName;

public class ListEditObserved extends ListEditInfo{

	public ListEditObserved(String aMessage, OperationName anOperationName, int anIndex,
			Object anElement, Object aFinder) {
		super(aMessage, anOperationName, anIndex, anElement, aFinder);
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
			Object anElement, Object aFinder) {			
		String aMessage = toString(anOperationName, anIndex, anElement);
		ListEditObserved retVal = new ListEditObserved(aMessage, anOperationName, anIndex, anElement,aFinder);
		retVal.announce();
		return retVal;
	}

}
