package gradingTools.comp790ColabInc.example1.testcases;
import util.trace.session.AddressedMessageInfo;
import util.trace.session.ProcessInfo;
//public class CommunicatedListEditInfo extends ListEditInfo {
public class CommunicatedListEditInfo extends ListEditInfo {

	String destinationOrSource;
	String processName;
	
	public CommunicatedListEditInfo(String aMessage,
			String aProcessName,
			OperationName aName, 
			int anIndex, 
			Object anElement, String aList, 
			String aDestinationOrSource, 
			Object aFinder) {
		super(aMessage, aName, anIndex, anElement, aList, aFinder);
//		this.operationName = aName;
//		this.index = anIndex;
//		this.element = anElement;
		processName = aProcessName;
		destinationOrSource = aDestinationOrSource;
	}
	public CommunicatedListEditInfo(String aProcessName, OperationName aName, int anIndex, Object anElement, String aList, String aDestinationOrSource) {
		this("",  aProcessName, aName, anIndex, anElement, aList, aDestinationOrSource, null);		
	}
	public CommunicatedListEditInfo(String aMessage, String aProcessName, String aDestinationOrSource, ListEditInfo aListEditInfo) {
		super(aMessage, aListEditInfo);
		processName = aProcessName;
		destinationOrSource = aDestinationOrSource;
	}
	public CommunicatedListEditInfo(String aMessage, CommunicatedListEditInfo aCommunicatedListEditInfo) {
		this(aMessage, aCommunicatedListEditInfo.getProcessName(), aCommunicatedListEditInfo.getDestinationOrSource(), aCommunicatedListEditInfo);
	}

	public static CommunicatedListEditInfo toTraceable (String aMessage) {
		ListEditInfo aListEditInfo = ListEditInfo.toTraceable(aMessage);
		String aProcessName = ProcessInfo.getProcessName(aMessage);
		String anAddress = AddressedMessageInfo.getAddress(aMessage);		
		return new CommunicatedListEditInfo(aMessage, aProcessName, anAddress, aListEditInfo);
	}
	
	
	public String getDestinationOrSource() {
		return destinationOrSource;
	}
	
	public static String toString(String aProcessName, OperationName name, int anIndex, Object anElement, String aList, String aDestinationOrSource) {
		return ProcessInfo.toString(aProcessName)  + ListEditInfo.toLocalInfoToString(name, anIndex, anElement, aList) + 
//				" Address(" + 
				" " + AddressedMessageInfo.ADDRESS + "(" +
				aDestinationOrSource + ")";
	}
	public String alternativeToString() {
		return toString(processName, operationName, index, element, list, destinationOrSource);
	}
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	public void setDestinationOrSource(String destinationOrSource) {
		this.destinationOrSource = destinationOrSource;
	}
	
}
