package trace.ot;

import java.util.List;

import util.trace.session.ProcessInfo;


public class UserOTTimeStampInfo extends OTTimeStampInfo {
	String userName;
	// int localCount, remoteCount;
	Boolean isServer;

	public UserOTTimeStampInfo(String aMessage,  String aProcessName, String aUserName,
			int aLocalCount, int aRemoteCount, Boolean anIsServer,
			Object aFinder) {
		super(aMessage, aProcessName, aLocalCount, aRemoteCount, aFinder);
		userName = aUserName;
		// localCount = aLocalCount;
		// remoteCount = aRemoteCount;
		isServer = anIsServer;
	}

	public UserOTTimeStampInfo(String aProcessName, String aUserName, int aLocalCount,
			int aRemoteCount, Boolean anIsServer) {
		this("", aProcessName, aUserName, aLocalCount, aRemoteCount, anIsServer, null);
		// localCount = aLocalCount;
		// remoteCount = aRemoteCount;
	}
	public UserOTTimeStampInfo(String aProcessName, String aUserName,  OTTimeStampInfo anOTTimeStampInfo, Boolean anIsServer) {
		this("", "",  aUserName, anOTTimeStampInfo.getLocalCount(), anOTTimeStampInfo.getRemoteCount(), anIsServer, null);
		// localCount = aLocalCount;
		// remoteCount = aRemoteCount;
	}
	public UserOTTimeStampInfo(String aUserName, OTTimeStampInfo anOTTimeStamp, Boolean isServer, Object aFinder) {
		this("", "", aUserName, anOTTimeStamp.getLocalCount(), anOTTimeStamp.getRemoteCount(), isServer, aFinder);
		// localCount = aLocalCount;
		// remoteCount = aRemoteCount;
	}
	public UserOTTimeStampInfo(String aMessage, String aUserName, OTTimeStampInfo anOTTimeStampInfo) {
		super(aMessage, anOTTimeStampInfo);
		userName = aUserName;
		// localCount = aLocalCount;
		// remoteCount = aRemoteCount;
	}
	
	public UserOTTimeStampInfo(String aMessage, UserOTTimeStampInfo aUserOTTimeStampInfo) {
		this(aMessage, aUserOTTimeStampInfo.getMessage(), aUserOTTimeStampInfo);
		// localCount = aLocalCount;
		// remoteCount = aRemoteCount;
	}

	public String getUserName() {
		return userName;
	}

	// public int getLocalCount() {
	// return localCount;
	// }
	//
	// public int getRemoteCount() {
	// return remoteCount;
	// }

	// public boolean isServer() {
	// return isServer;
	// }
	
	public static String getUserName(String aMessage) {
		return getArgs(aMessage, USER_NAME).get(0);
	}
	public static UserOTTimeStampInfo toTraceable(String aMessage) {
		String aUserName = getUserName(aMessage);
		OTTimeStampInfo anOTTimeStampInfo = OTTimeStampInfo.toTraceable(aMessage);
		return new UserOTTimeStampInfo(aMessage, aUserName, anOTTimeStampInfo);		
	}
	public static final String USER_NAME = "User";
	public static String userNameToString (String aUserName) {
		return (aUserName == null|| !longMessage)?"":
//				+ " User(" + 
			    USER_NAME + "(" +
				aUserName + ")";
	}
	public static String isServerToString(Boolean isServer) {
		return (isServer == null || !longMessage)?"": 
				(", Actor(" + (isServer?"Server":"Client"));
	}
	public static String toString(String aProcessName, String aUserName, int aLocalCount,
			int aRemoteCount, Boolean anIsServer) {
		return toString(aProcessName) + " " + userNameToString(aUserName) + " " + OTTimeStampInfo.toString(aLocalCount, aRemoteCount)
		// "[local: " + aLocalCount + ", remote: " + aRemoteCount + "]" ;
//		 + "(" + (anIsServer?"Server": "Client") + ")";
				+ isServerToString(anIsServer);

	}

	public String alternativeToString() {
		return toString(processName, userName, localCount, remoteCount, isServer);
	}

}
