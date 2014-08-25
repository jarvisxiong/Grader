package trace.ot;

import im.OperationName;
import trace.echo.modular.ListEditInfo;

public class OTListEditSent extends OTListEditInfo{

	public OTListEditSent(String aMessage, String aLocatable,
			ListEditInfo aListEdit, UserOTTimeStampInfo anOTTimeStamp,
			Object aFinder) {
		super(aMessage, aLocatable,  aListEdit, anOTTimeStamp, aFinder);
	}
	public OTListEditSent(String aMessage, 
			OTListEditInfo aSuperClassInfo) {
		super(aMessage, aSuperClassInfo);
	}
	public static OTListEditSent toTraceable(String aMessage) {
		OTListEditInfo aSuperClassInfo = OTListEditInfo.toTraceable(aMessage);
		return new OTListEditSent(aMessage, 
				aSuperClassInfo);		
	}
	
//	public static String toString (String aLocatable, ListEditInfo aListEdit, UserOTTimeStampInfo anOTTimeStamp) {
//		return  "@" + aLocatable + UserOTTimeStampedListEditInfo.toString(aListEdit, anOTTimeStamp);
//	}
	public static OTListEditSent newCase(String aLocatable,
			ListEditInfo aListEdit, UserOTTimeStampInfo anOTTimeStamp/*, String aSourceOrDestination*/,
			Object aFinder) {			
		String aMessage = toString(aLocatable, aListEdit, anOTTimeStamp);
		OTListEditSent retVal = new OTListEditSent(aMessage, aLocatable, aListEdit, anOTTimeStamp, aFinder);
		retVal.announce();
		return retVal;
	}
	public static OTListEditSent newCase(String aLocatable,
			ListEditInfo aListEdit, OTTimeStampInfo anOTTimeStamp/*, String aSourceOrDestination*/,
			String aUserName, /*boolean anInServer,*/
			Object aFinder) {
		UserOTTimeStampInfo userOTTimeStampInfo = new UserOTTimeStampInfo(aLocatable, aUserName, anOTTimeStamp, null);
		return newCase(aLocatable, aListEdit, userOTTimeStampInfo, aFinder);
	}
	
	public static OTListEditSent newCase(String aLocatable,
			OperationName aName, int anIndex, Object anElement,
			int aLocalCount, int aRemoteCount, 
			String aUserName, /*boolean anInServer,*/
			Object aFinder) {
		ListEditInfo aListEditInfo = new ListEditInfo(aName, anIndex, anElement);
		OTTimeStampInfo anOTTimeStampInfo = new OTTimeStampInfo(aLocalCount, aRemoteCount);
		return newCase(aLocatable, aListEditInfo, anOTTimeStampInfo,  aUserName, /*anInServer,*/ aFinder);
	}
	
//	public static UserOTTimeStampedListEditSent newCase(/*String aLocatable,*/
//			ListEditInfo aListEdit, UserOTTimeStampInfo anOTTimeStamp, String aSourceOrDestination,
//			Object aFinder) {			
//		String aMessage = toString(aListEdit, anOTTimeStamp);
//		UserOTTimeStampedListEditSent retVal = new UserOTTimeStampedListEditSent(aMessage/*, aLocatable*/, aListEdit, anOTTimeStamp, aFinder);
//		retVal.announce();
//		return retVal;
//	}
//	public static UserOTTimeStampedListEditSent newCase(/*String aLocatable,*/
//			UserOTTimeStampedListEditInfo otTimeStampedListEditInfo, String aSourceOrDestination,
//			Object aFinder) {			
//		return newCase(/*aLocatable,*/ otTimeStampedListEditInfo.getListEdit(), otTimeStampedListEditInfo.getOTTimeStamp(), aSourceOrDestination, aFinder);
//	}

}
