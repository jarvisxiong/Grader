package trace.ot;

import trace.echo.modular.ListEditInfo;

public class NonConcurrentEdits extends OTListEditPairInfo{

	public NonConcurrentEdits(String aMessage, String aProcessName,
			OTListEditInfo aFirst, OTListEditInfo aSecond, Object aFinder) {
		super(aMessage, aProcessName, aFirst, aSecond, aFinder);
	}
	public NonConcurrentEdits(String aMessage, String aProcessName,
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
	public NonConcurrentEdits(String aMessage, OTListEditPairInfo aPairInfo) {
		super(aMessage, aPairInfo);
	}
	
	public static NonConcurrentEdits toTraceable (String aMessage) {
		OTListEditPairInfo aPairInfo = OTListEditPairInfo.toTraceable(aMessage);
		return new NonConcurrentEdits (aMessage, aPairInfo);
		}
	public static NonConcurrentEdits newCase(String aProcessName,
			OTListEditInfo aFirst, OTListEditInfo aSecond, Object aFinder) {
			String aMessage = toString(aProcessName, aFirst, aSecond);
			NonConcurrentEdits retVal = new NonConcurrentEdits(aMessage, aProcessName, aFirst, aSecond, aFinder);
			retVal.announce();
			return retVal;
		
	}
	public static NonConcurrentEdits newCase(String aProcessName,
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
