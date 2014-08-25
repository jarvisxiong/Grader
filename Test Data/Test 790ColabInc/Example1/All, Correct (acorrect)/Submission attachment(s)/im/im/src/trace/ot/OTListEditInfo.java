package trace.ot;

import trace.echo.modular.ListEditInfo;
import util.trace.TraceableInfo;
import util.trace.session.ProcessInfo;

public class OTListEditInfo extends ProcessInfo {
	UserOTTimeStampInfo otTimeStamp;
	ListEditInfo listEdit;
//	String processName;
	public OTListEditInfo(String aMessage, String aProcessName,
			ListEditInfo aListEdit, UserOTTimeStampInfo anOTTimeStamp,
			Object aFinder) {
		super(aMessage, aProcessName,  aFinder);
//		processName = aProcessName;
		otTimeStamp = anOTTimeStamp;
		listEdit = aListEdit;
	}
	
	public OTListEditInfo(ListEditInfo aListEdit, 
			UserOTTimeStampInfo anOTTimeStamp) {
		this("", "", aListEdit, anOTTimeStamp, null);
	}

	public OTListEditInfo(String aMessage, ListEditInfo aListEdit, 
			UserOTTimeStampInfo anOTTimeStamp, ProcessInfo aProcessInfo) {
//		super(aMessage, "", aProcessInfo);
		super(aMessage, aProcessInfo);

		otTimeStamp = anOTTimeStamp;
		listEdit = aListEdit;
	}

	public OTListEditInfo(String aMessage, OTListEditInfo anOTListEdit) {
		this (aMessage, anOTListEdit.getListEdit(), anOTListEdit.getUserOTTimeStamp(), anOTListEdit);
		
	}
	public static String toLocalInfoToString(String aProcessName, ListEditInfo aListEdit,
			UserOTTimeStampInfo anOTTimeStamp) {
		return aListEdit.toLocalInfoToString() + " "
				+ anOTTimeStamp.alternativeToString() ;
		
	}
	public static String toString(String aProcessName, ListEditInfo aListEdit,
			UserOTTimeStampInfo anOTTimeStamp) {
		return toString(aProcessName) + " " + toLocalInfoToString(aProcessName, aListEdit, anOTTimeStamp);
//				"OTEdit(" + 
//				aListEdit.toLocalInfoToString() + " "
//				+ anOTTimeStamp.alternativeToString() ;
				
//				+ ")";

	}
	
	public static OTListEditInfo toTraceable(String aMessage) {
//		ProcessInfo aProcessInfo;
//		try {
		ProcessInfo aProcessInfo = ProcessInfo.toTraceable(aMessage);
//		} catch (Exception e) {
//			System.out.println(e);
//			aProcessInfo = null;
//		}
		ListEditInfo aListEditInfo = ListEditInfo.toTraceable(aMessage);
		UserOTTimeStampInfo aTimeStampInfo = UserOTTimeStampInfo.toTraceable(aMessage);
		return new OTListEditInfo(aMessage, aListEditInfo, aTimeStampInfo, aProcessInfo);
		
	}
	

	public String alternativeToString() {
		return toString(processName, listEdit, otTimeStamp);
	}
	public String toLocalInfoToString() {
		return toLocalInfoToString(processName, listEdit, otTimeStamp);
	}


	public UserOTTimeStampInfo getUserOTTimeStamp() {
		return otTimeStamp;
	}

	public ListEditInfo getListEdit() {
		return listEdit;
	}
	
	public String getProcessName() {
		return processName;
	}

}
