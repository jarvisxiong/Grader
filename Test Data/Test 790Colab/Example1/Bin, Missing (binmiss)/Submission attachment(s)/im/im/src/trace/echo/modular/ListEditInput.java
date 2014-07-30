package trace.echo.modular;

import im.OperationName;

public class ListEditInput extends ListEditInfo{

	public ListEditInput(String aMessage, OperationName anOperationName, int anIndex,
			Object anElement, Object aFinder) {
		super(aMessage, anOperationName, anIndex, anElement, aFinder);
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
			Object anElement, Object aFinder) {			
		String aMessage = toString(anOperationName, anIndex, anElement);
		ListEditInput retVal = new ListEditInput(aMessage, anOperationName, anIndex, anElement,aFinder);
		retVal.announce();
		return retVal;
	}

}
