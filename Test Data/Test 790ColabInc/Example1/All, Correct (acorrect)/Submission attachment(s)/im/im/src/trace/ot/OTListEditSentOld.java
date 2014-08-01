package trace.ot;

import trace.echo.modular.ListEditInfo;

public class OTListEditSentOld extends OTTimeStampedListEditInfo{

	public OTListEditSentOld(String aMessage, String aProcessName,
			ListEditInfo aListEdit, OTTimeStampInfo anOTTimeStamp,
			Object aFinder) {
		super(aMessage, aProcessName, aListEdit, anOTTimeStamp, aFinder);
	}
	public static String toString (ListEditInfo aListEdit, OTTimeStampInfo anOTTimeStamp) {
		return  "-->" + toString(aListEdit, anOTTimeStamp);
	}
	public static OTListEditSentOld newCase(String aProcessName,
			ListEditInfo aListEdit, OTTimeStampInfo anOTTimeStamp,
			Object aFinder) {			
		String aMessage = toString(aListEdit, anOTTimeStamp);
		OTListEditSentOld retVal = new OTListEditSentOld(aMessage, aProcessName, aListEdit, anOTTimeStamp, aFinder);
		retVal.announce();
		return retVal;
	}
//	public static OTTimeStampedListEditSent newCase(String aLocatable,
//			OTTimeStampedListEditInfo otTimeStampedListEditInfo, String aSourceOrDestination,
//			Object aFinder) {			
//		return newCase(aLocatable, otTimeStampedListEditInfo.getListEdit(), otTimeStampedListEditInfo.getOTTimeStamp(), aSourceOrDestination, aFinder);
//	}

}
