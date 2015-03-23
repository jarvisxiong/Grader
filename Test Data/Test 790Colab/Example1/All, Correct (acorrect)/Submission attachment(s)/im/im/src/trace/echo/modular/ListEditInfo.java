package trace.echo.modular;
import java.util.Arrays;
import java.util.List;

import im.OperationName;
import util.trace.Traceable;
import util.trace.TraceableInfo;
public class ListEditInfo extends TraceableInfo {
	protected OperationName operationName;	
	protected int index;
	protected Object element;
	public static final String LIST_EDIT = "ListEdit";
	public static String[] listEditEqualPropertiesArray = {"OperationName", "Index", "Element"};
//	public static List<String> listEditEqualPropertiesList = Arrays.asList(listEditEqualPropertiesArray);

	public ListEditInfo(String aMessage, OperationName aName, int anIndex, Object anElement, Object aFinder) {
		super(aMessage,  aFinder);
		this.operationName = aName;
		this.index = anIndex;
		this.element = anElement;
	}
	protected void setEqualPropertiesList() {
		super.setEqualPropertiesList();
//		String[] listEditEqualPropertiesArray = {"OperationName", "Index", "Element"};

//		consoleEqualPropertiesArray = new String[]{"Output"};
		equalPropertiesList.addAll(Arrays.asList(listEditEqualPropertiesArray));
//		equalPropertiesList.addAll(listEditEqualPropertiesList);

		
//		equalPropertiesList = Arrays.asList(equalPropertiesArray);

	}
	
	
	public ListEditInfo(OperationName aName, int anIndex, Object anElement) {
		this("", aName, anIndex, anElement, (Object) null); 
		
	}
	public ListEditInfo(String aMessage, 
			OperationName aName, 
			int anIndex, 
			Object anElement,
			Traceable aTraceable
//			String aProcessName,			
//			Long aTimeStamp,
//			String aThreadName,
//			Object aFinder
			) {
		super(aMessage, aTraceable);
		this.operationName = aName;
		this.index = anIndex;
		this.element = anElement;
	}
	public ListEditInfo(String aMessage, 
			ListEditInfo aListEditInfo
//			String aProcessName,			
//			Long aTimeStamp,
//			String aThreadName,
//			Object aFinder
			) {
		this(aMessage, 
				aListEditInfo.getOperationName(),
				aListEditInfo.getIndex(),
				aListEditInfo.getElement(), 
//				getOperationName(aMessage), 
//				
//				getIndex(
//						getOperationArgs(aMessage, getOperationName(aMessage).toString() )),
//				getElement(
//						getOperationArgs(aMessage, getOperationName(aMessage).toString() )),

				aListEditInfo);
	}
	
	public static ListEditInfo toTraceable (String aMessage) {
		Traceable aTraceable = TraceableInfo.toTraceable(aMessage);
		OperationName anOperationName = getOperationName(aMessage);
		List<String> anOperationArgs = getOperationArgs(aMessage, anOperationName.toString());
		Integer anIndex = getIndex(anOperationArgs);
		String anElement = getElement(anOperationArgs);
		return new ListEditInfo(aMessage, anOperationName, anIndex, anElement, aTraceable);
				
	}
	
	public static String getListEdit(String aMessage) {
		return getArgOfCompositeDescriptor(aMessage, LIST_EDIT);
	}
	
	public static OperationName getOperationName(String aMessage) {
		String aListEdit = getListEdit(aMessage);
		aListEdit = aListEdit.trim();
		int aNameEndIndex = aListEdit.indexOf("(");
		String aName = aListEdit.substring(0, aNameEndIndex);
		return OperationName.fromString(aName);
	}
	
	public static List<String> getOperationArgs(String aMessage, String anOperationName) {
		return getArgs(aMessage, anOperationName);
	}
	
	public static Integer getIndex(List<String> anArgs) {
		return Integer.parseInt(anArgs.get(0));		
	}
	
	public static String getElement(List<String> anArgs) {
		return anArgs.get(1);
	}
	
	public OperationName getOperationName() {
		return operationName;
	}
	public int getIndex() {
		return index;
	}
	public Object getElement() {
		return element;
	}
	public static String toString(OperationName name, int anIndex, Object anElement) {
		return toString(System.currentTimeMillis()) +
				" " + LIST_EDIT + "_" +
				name + ("(") + anIndex + "," + anElement
			+ ")";
				
	}
	public static String toLocalInfoToString(OperationName name, int anIndex, Object anElement) {
		return 
				LIST_EDIT + "_" +
				name + ("(") + anIndex + ", " + anElement
			+ ")";
				
	}
	public String toLocalInfoToString() {
		return toLocalInfoToString(operationName, index, element); 
	}
	public String alternativeToString() {
//		return "ListEdit(" +  toString(operationName, index, element) + ")";
		return 
//				LIST_EDIT + "(" +
//		"ListEdit(" +  
		
		toString(operationName, index, element); 
//		")";
	}
	
	public boolean equalsEdit(ListEditInfo other) {
		return operationName.equals(other.getOperationName()) &&
				index == other.getIndex() &&
				element.equals(other.getElement());
	}
	
}
