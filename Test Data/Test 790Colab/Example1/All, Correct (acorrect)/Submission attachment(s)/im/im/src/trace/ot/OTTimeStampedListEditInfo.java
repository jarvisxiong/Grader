package trace.ot;

import trace.echo.modular.ListEditInfo;
import util.trace.TraceableInfo;
import util.trace.session.ProcessInfo;

public class OTTimeStampedListEditInfo extends ProcessInfo {
	OTTimeStampInfo otTimeStamp;
	ListEditInfo listEdit;

	// String userName;
	// int localCount, remoteCount;
	// boolean isServer;

	public OTTimeStampedListEditInfo(String aMessage, String aProcessName, ListEditInfo aListEdit,
			OTTimeStampInfo anOTTimeStamp, Object aFinder) {
		super(aMessage, aProcessName, aFinder);
		otTimeStamp = anOTTimeStamp;
		listEdit = aListEdit;

	}

	// public OTTimeStampedListEditInfo
	// (ListEditInfo aListEdit,
	// String aUser, boolean anIsServer,
	// OTTimeStampInfo anOTTimeStamp,
	// Object aFinder) {
	// this("", aListEdit, new UserOTTimeStampInfo(aUser, anOTTimeStamp,
	// anIsServer), null );
	//
	// }
	public OTTimeStampedListEditInfo(ListEditInfo aListEdit,

	OTTimeStampInfo anOTTimeStamp) {
		this("",  "", aListEdit, anOTTimeStamp, null);

	}

	public static String toString(String aProcessName, ListEditInfo aListEdit,
			OTTimeStampInfo anOTTimeStamp) {
		return toString(aProcessName) + "[" + aListEdit.alternativeToString() + ","
				+ anOTTimeStamp.alternativeToString() + "]";

	}

	public String alternativeToString() {
		return toString(processName, listEdit, otTimeStamp);
	}

	public OTTimeStampInfo getOTTimeStamp() {
		return otTimeStamp;
	}

	public ListEditInfo getListEdit() {
		return listEdit;
	}

}
