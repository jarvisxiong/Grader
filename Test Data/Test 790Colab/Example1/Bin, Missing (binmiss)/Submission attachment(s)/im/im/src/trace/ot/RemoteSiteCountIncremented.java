package trace.ot;


public class RemoteSiteCountIncremented extends UserOTTimeStampInfo{
	
	public RemoteSiteCountIncremented(String aMessage, String aProcessName,
			String aUserName, 
			int aLocalCount, 
			int aRemoteCount,
			Boolean isServer,
			Object aFinder) {
		super(aMessage, aProcessName, aUserName, aLocalCount, aRemoteCount, isServer, aFinder);
	}
	public RemoteSiteCountIncremented(String aMessage, 
			UserOTTimeStampInfo aTimeStampInfo) {
		super(aMessage, aTimeStampInfo);
	}
	public static RemoteSiteCountIncremented toTraceable(String aMessage) {
		UserOTTimeStampInfo aTimeStampInfo = UserOTTimeStampInfo.toTraceable(aMessage);
		return new RemoteSiteCountIncremented(aMessage, 
				aTimeStampInfo);		
	}
	public static RemoteSiteCountIncremented newCase(
			String aProcessName,
			String aUserName, 
			int aLocalCount, 
			int aRemoteCount,
			/*boolean anInServer,*/
			Object aFinder) {
		String aMessage = toString(aProcessName, aUserName, aLocalCount, aRemoteCount, null /*anIsServer*/);
		RemoteSiteCountIncremented retVal = new RemoteSiteCountIncremented(aMessage, aProcessName, aUserName, aLocalCount, aRemoteCount, null, /*anInServer,*/ aFinder);
		retVal.announce();
		return retVal;
	}

}
