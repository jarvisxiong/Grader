package trace.ot;


public class InitialOTTimeStampCreated extends UserOTTimeStampInfo{
	
	public InitialOTTimeStampCreated(String aMessage, String aProcessName,
			String aUserName, 
			int aLocalCount, 
			int aRemoteCount,
			boolean isServer,
			Object aFinder) {
		super(aMessage, aProcessName, aUserName, aLocalCount, aRemoteCount, isServer, aFinder);
	}
	public InitialOTTimeStampCreated(String aMessage, 
			UserOTTimeStampInfo aTimeStampInfo) {
		super(aMessage, aTimeStampInfo);
	}
	public static InitialOTTimeStampCreated toTraceable(String aMessage) {
		UserOTTimeStampInfo aTimeStampInfo = UserOTTimeStampInfo.toTraceable(aMessage);
		return new InitialOTTimeStampCreated(aMessage, 
				aTimeStampInfo);		
	}
	
	public static InitialOTTimeStampCreated newCase(
			String aProcessName,
			String aUserName, 
			int aLocalCount, 
			int aRemoteCount,
			boolean anIsServer,
			Object aFinder) {
		String aMessage = toString(aProcessName, aUserName, aLocalCount, aRemoteCount, anIsServer);
		InitialOTTimeStampCreated retVal = new InitialOTTimeStampCreated(aMessage, aProcessName, aUserName, aLocalCount, aRemoteCount, anIsServer, aFinder);
		retVal.announce();
		return retVal;
	}

}
