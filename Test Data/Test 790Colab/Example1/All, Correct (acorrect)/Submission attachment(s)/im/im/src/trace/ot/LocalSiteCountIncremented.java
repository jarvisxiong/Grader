package trace.ot;


public class LocalSiteCountIncremented extends UserOTTimeStampInfo{
	
	public LocalSiteCountIncremented(String aMessage, String aProcessName,
			String aUserName, 
			int aLocalCount, 
			int aRemoteCount,
			Boolean isServer,
			Object aFinder) {
		super(aMessage, aProcessName, aUserName, aLocalCount, aRemoteCount, isServer, aFinder);
	}
	public LocalSiteCountIncremented(String aMessage, 
			UserOTTimeStampInfo aTimeStampInfo) {
		super(aMessage, aTimeStampInfo);
	}
	public static LocalSiteCountIncremented toTraceable(String aMessage) {
		UserOTTimeStampInfo aTimeStampInfo = UserOTTimeStampInfo.toTraceable(aMessage);
		return new LocalSiteCountIncremented(aMessage, 
				aTimeStampInfo);		
	}
	public static LocalSiteCountIncremented newCase(
			String aProcessName,
			String aUserName, 
			int aLocalCount, 
			int aRemoteCount,
			/*boolean anInServer,*/
			Object aFinder) {
		String aMessage = toString(aProcessName, aUserName, aLocalCount, aRemoteCount, null);
		LocalSiteCountIncremented retVal = new LocalSiteCountIncremented(aMessage, aProcessName, aUserName, aLocalCount, aRemoteCount, null,/*anInServer,*/ aFinder);
		retVal.announce();
		return retVal;
	}

}
