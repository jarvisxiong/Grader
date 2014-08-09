package trace.ot;

import trace.echo.modular.ListEditInfo;

public class TransformationOperands extends OTListEditPairInfo{

	public TransformationOperands(String aMessage, String aProcessName,
			OTListEditInfo aFirst, OTListEditInfo aSecond, Object aFinder) {
		super(aMessage, aProcessName, aFirst, aSecond, aFinder);
	}
	public TransformationOperands(String aMessage, String aProcessName,
			ListEditInfo aFirstEdit,
			OTTimeStampInfo aFistTimeStamp,
			String aFirstUser,
//			Boolean aFirstIsServer,
			ListEditInfo aSecondEdit,
			OTTimeStampInfo aSecondTimeStamp,
			String aSecondUser,
//			Boolean aSecondIsServer,
			Object aFinder) {
		super(aMessage, aProcessName, aFirstEdit, aFistTimeStamp, aFirstUser, null /*aFirstIsServer,*/, aSecondEdit, aSecondTimeStamp, aSecondUser, null/*aSecondIsServer,*/, aFinder);
	}
	public TransformationOperands(String aMessage, OTListEditPairInfo aPairInfo) {
		super(aMessage, aPairInfo);
	}
	
	public static TransformationOperands toTraceable (String aMessage) {
		OTListEditPairInfo aPairInfo = OTListEditPairInfo.toTraceable(aMessage);
		return new TransformationOperands (aMessage, aPairInfo);
		}
	public static TransformationOperands newCase(String aProcessName,
			OTListEditInfo aFirst, OTListEditInfo aSecond, Object aFinder) {
			String aMessage = toString(aProcessName, aFirst, aSecond);
			TransformationOperands retVal = new TransformationOperands(aMessage, aProcessName, aFirst, aSecond, aFinder);
			retVal.announce();
			return retVal;
		
	}
	public static TransformationOperands newCase(String aProcessName,
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
						new UserOTTimeStampInfo(aFirstUser, aFistTimeStamp, null /* aFirstIsServer*/, aFinder)),
						
				new OTListEditInfo(
						aSecondEdit, 
						new UserOTTimeStampInfo(aSecondUser, aSecondTimeStamp, null /* aSecondIsServer*/, aFinder)),	
				aFinder);
		
//		return newCase (aProcessName, aFirstEdit, aFistTimeStamp, aFirstUser, aFirstIsServer, aSecondEdit, aSecondTimeStamp, aSecondUser, aSecondIsServer, aFinder);
	}
	
		

}
