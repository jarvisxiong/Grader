package trace.ot;

import im.OperationName;
import trace.echo.modular.ListEditInfo;

public class TransformationResult extends OTListEditInfo{

	public TransformationResult(String aMessage, String aLocatable,
			ListEditInfo aListEdit, UserOTTimeStampInfo anOTTimeStamp,
			Object aFinder) {
		super(aMessage, aLocatable,  aListEdit, anOTTimeStamp, aFinder);
	}
	public TransformationResult(String aMessage, 
			OTListEditInfo aSuperClassInfo) {
		super(aMessage, aSuperClassInfo);
	}
	public static TransformationResult toTraceable(String aMessage) {
		OTListEditInfo aSuperClassInfo = OTListEditInfo.toTraceable(aMessage);
		return new TransformationResult(aMessage, 
				aSuperClassInfo);		
	}
//	public static String toString (String aLocatable, ListEditInfo aListEdit, UserOTTimeStampInfo anOTTimeStamp) {
//		return  "@" + aLocatable + UserOTTimeStampedListEditInfo.toString(aListEdit, anOTTimeStamp);
//	}
	public static TransformationResult newCase(String aLocatable,
			ListEditInfo aListEdit, UserOTTimeStampInfo anOTTimeStamp/*, String aSourceOrDestination*/,
			Object aFinder) {			
		String aMessage = toString(aLocatable, aListEdit, anOTTimeStamp);
		TransformationResult retVal = new TransformationResult(aMessage, aLocatable, aListEdit, anOTTimeStamp, aFinder);
		retVal.announce();
		return retVal;
	}
	public static TransformationResult newCase(String aLocatable,
			ListEditInfo aListEdit, OTTimeStampInfo anOTTimeStamp/*, String aSourceOrDestination*/,
			String aUserName, boolean anIsServer,
			Object aFinder) {
		UserOTTimeStampInfo userOTTimeStampInfo = new UserOTTimeStampInfo(aLocatable, aUserName, anOTTimeStamp, anIsServer);
		return newCase(aLocatable, aListEdit, userOTTimeStampInfo, aFinder);
	}
	
	public static TransformationResult newCase(String aLocatable,
			OperationName aName, int anIndex, Object anElement,
			int aLocalCount, int aRemoteCount, 
			String aUserName, boolean anIsServer,
			Object aFinder) {
		ListEditInfo aListEditInfo = new ListEditInfo(aName, anIndex, anElement);
		OTTimeStampInfo anOTTimeStampInfo = new OTTimeStampInfo(aLocalCount, aRemoteCount);
		return newCase(aLocatable, aListEditInfo, anOTTimeStampInfo,  aUserName, anIsServer, aFinder);
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
