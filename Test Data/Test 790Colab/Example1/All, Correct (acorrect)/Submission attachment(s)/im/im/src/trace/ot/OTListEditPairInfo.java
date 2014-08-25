package trace.ot;

import im.OperationName;
import trace.echo.modular.ListEditInfo;
import util.trace.Traceable;
import util.trace.TraceableInfo;
import util.trace.session.ProcessInfo;

public class OTListEditPairInfo extends ProcessInfo {
	OTListEditInfo firstEdit, secondEdit;
	public OTListEditPairInfo(String aMessage, String aProcessName,
			OTListEditInfo aFirstEdit,
			OTListEditInfo aSecondEdit,
			Object aFinder) {
		super(aMessage, aProcessName,  aFinder);
		firstEdit = aFirstEdit;
		secondEdit = aSecondEdit;
	}
	
	public OTListEditPairInfo(String aMessage, 
			OTListEditInfo aFirstEdit,
			OTListEditInfo aSecondEdit,
			ProcessInfo aProcessInfo) {
		super(aMessage, aProcessInfo);
		firstEdit = aFirstEdit;
		secondEdit = aSecondEdit;
	}	
	
	public OTListEditPairInfo(String aMessage, 
			OTListEditPairInfo aPairInfo) {
		this(aMessage, aPairInfo.getFirstEdit(), aPairInfo.getSecondEdit(), aPairInfo);
	
	}
	
	public OTListEditPairInfo(String aMessage, String aProcessName,
			ListEditInfo aFirstEdit,
			OTTimeStampInfo aFistTimeStamp,
			String aFirstUser,
			Boolean aFirstIsServer,
			ListEditInfo aSecondEdit,
			OTTimeStampInfo aSecondTimeStamp,
			String aSecondUser,
			Boolean aSecondIsServer,
			Object aFinder) {
		this (
				aMessage,
				aProcessName,
				new OTListEditInfo(
						aFirstEdit, 
						new UserOTTimeStampInfo(aFirstUser, aFistTimeStamp, aFirstIsServer, aFinder)),
						
				new OTListEditInfo(
						aSecondEdit, 
						new UserOTTimeStampInfo(aSecondUser, aSecondTimeStamp, aSecondIsServer, aFinder)),	
				aFinder);
	
		
	}
	
	public static String getFirstOTListEdit(String aMessage) {
		return getSuffixOfCompositeDescriptor(aMessage, FIRST);
	}
	
	public static String getSecondOTListEdit(String aMessage) {
		return getSuffixOfCompositeDescriptor(aMessage, SECOND);
	}
	
	
	
	
	public static OTListEditPairInfo toTraceable(String aMessage) {
		ProcessInfo aProcessInfo = ProcessInfo.toTraceable(aMessage);
		String aFirstEditText = getFirstOTListEdit(aMessage);
		String aSecondEditText = getSecondOTListEdit(aMessage);
		OTListEditInfo aFirstEdit = OTListEditInfo.toTraceable(aFirstEditText);
		OTListEditInfo aSecondEdit = OTListEditInfo.toTraceable(aSecondEditText);
		
		return new OTListEditPairInfo(aMessage, aFirstEdit, aSecondEdit, aProcessInfo);
	}
	
	public OTListEditInfo getFirstEdit() {
		return firstEdit;
	}	
	public OTListEditInfo getSecondEdit() {
		return firstEdit;
	}	
	public static final String FIRST = "First";
	public static final String SECOND = "Second";
	public static String toString(String aProcessName, 
			OTListEditInfo aFirstEdit,
			OTListEditInfo aSecondEdit) {
		return toString(aProcessName) + 
				
//				"[" + 
				FIRST + "_" + aFirstEdit.toLocalInfoToString() + " " + 
				SECOND + "_" +	aSecondEdit.toLocalInfoToString();
//		"[" + aFirstEdit. + "," + 
//		aSecondEdit.alternativeToString() + "]";
	}
	public String alternativeToString() {
		return toString(processName, firstEdit, secondEdit);
	}
	
	

}
