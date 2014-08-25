package trace.ot;

import trace.echo.modular.ListEditInfo;

public class ConcurrentEdits extends OTListEditPairInfo{

	public ConcurrentEdits(String aMessage, String aProcessName,
			OTListEditInfo aFirst, OTListEditInfo aSecond, Object aFinder) {
		super(aMessage, aProcessName, aFirst, aSecond, aFinder);
	}
	public ConcurrentEdits(String aMessage, String aProcessName,
			ListEditInfo aFirstEdit,
			OTTimeStampInfo aFistTimeStamp,
			String aFirstUser,
			boolean aFirstIsServer,
			ListEditInfo aSecondEdit,
			OTTimeStampInfo aSecondTimeStamp,
			String aSecondUser,
			boolean aSecondIsServer,
			Object aFinder) {
		super(aMessage, aProcessName, aFirstEdit, aFistTimeStamp, aFirstUser, aFirstIsServer, aSecondEdit, aSecondTimeStamp, aSecondUser, aSecondIsServer, aFinder);
	}
	public ConcurrentEdits(String aMessage, OTListEditPairInfo aPairInfo) {
		super(aMessage, aPairInfo);
	}
	
	public static ConcurrentEdits toTraceable (String aMessage) {
		OTListEditPairInfo aPairInfo = OTListEditPairInfo.toTraceable(aMessage);
		return new ConcurrentEdits (aMessage, aPairInfo);
		}
	public static ConcurrentEdits newCase(String aProcessName,
			OTListEditInfo aFirst, OTListEditInfo aSecond, Object aFinder) {
			String aMessage = toString(aProcessName, aFirst, aSecond);
			ConcurrentEdits retVal = new ConcurrentEdits(aMessage, aProcessName, aFirst, aSecond, aFinder);
			retVal.announce();
			return retVal;
		
	}
	public static ConcurrentEdits newCase(String aProcessName,
			ListEditInfo aFirstEdit,
			OTTimeStampInfo aFistTimeStamp,
			String aFirstUser,
//			boolean aFirstIsServer,
			ListEditInfo aSecondEdit,
			OTTimeStampInfo aSecondTimeStamp,
			String aSecondUser,
//			boolean aSecondIsServer,
			Object aFinder) {
		
		return newCase(aProcessName, 
				new OTListEditInfo(
						aFirstEdit, 
						new UserOTTimeStampInfo(aFirstUser, aFistTimeStamp, null,/*aFirstIsServer,*/ aFinder)),
						
				new OTListEditInfo(
						aSecondEdit, 
						new UserOTTimeStampInfo(aSecondUser, aSecondTimeStamp, null, /*aSecondIsServer,*/ aFinder)),	
				aFinder);
		
//		return newCase (aProcessName, aFirstEdit, aFistTimeStamp, aFirstUser, aFirstIsServer, aSecondEdit, aSecondTimeStamp, aSecondUser, aSecondIsServer, aFinder);
	}
	
		

}
