package trace.causal;

import trace.echo.modular.ListEditInfo;
import util.trace.TraceableInfo;
import util.trace.session.ProcessInfo;

public class CausalListEditInfo extends ProcessInfo {
	UserOTTimeStampInfo otTimeStamp;
	ListEditInfo listEdit;
//	String processName;
	public CausalListEditInfo(String aMessage, String aProcessName,
			ListEditInfo aListEdit, UserOTTimeStampInfo anOTTimeStamp,
			Object aFinder) {
		super(aMessage, aProcessName,  aFinder);
//		processName = aProcessName;
		otTimeStamp = anOTTimeStamp;
		listEdit = aListEdit;
	}
	
	public CausalListEditInfo(ListEditInfo aListEdit, 
			UserOTTimeStampInfo anOTTimeStamp) {
		this("", "", aListEdit, anOTTimeStamp, null);
	}

	public CausalListEditInfo(String aMessage, ListEditInfo aListEdit, 
			UserOTTimeStampInfo anOTTimeStamp, ProcessInfo aProcessInfo) {
//		super(aMessage, "", aProcessInfo);
		super(aMessage, aProcessInfo);

		otTimeStamp = anOTTimeStamp;
		listEdit = aListEdit;
	}

	public CausalListEditInfo(String aMessage, CausalListEditInfo anOTListEdit) {
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
	
	public static CausalListEditInfo toTraceable(String aMessage) {
//		ProcessInfo aProcessInfo;
//		try {
		ProcessInfo aProcessInfo = ProcessInfo.toTraceable(aMessage);
//		} catch (Exception e) {
//			System.out.println(e);
//			aProcessInfo = null;
//		}
		ListEditInfo aListEditInfo = ListEditInfo.toTraceable(aMessage);
		UserOTTimeStampInfo aTimeStampInfo = UserOTTimeStampInfo.toTraceable(aMessage);
		return new CausalListEditInfo(aMessage, aListEditInfo, aTimeStampInfo, aProcessInfo);
		
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
