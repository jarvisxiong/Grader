package trace.ot;

import java.util.List;

import util.trace.TraceableInfo;
import util.trace.session.ProcessInfo;

public class OTTimeStampInfo extends ProcessInfo {
//	String userName;
	int localCount, remoteCount;
//	boolean isServer;

	public OTTimeStampInfo
			(String aMessage, 
			String aProcessName,
			int aLocalCount,
			int aRemoteCount, 
//			boolean anIsServer, 
			Object aFinder) {
		super(aMessage, aProcessName,  aFinder);
//		userName = aUserName;
		localCount = aLocalCount;
		remoteCount = aRemoteCount;
//		isServer = anIsServer;
	}
	public OTTimeStampInfo
	( 
	String aMessage,
	Integer aLocalCount,
	Integer aRemoteCount,
	ProcessInfo aProcessInfo
	
//	boolean anIsServer, 
//	Object aFinder
	) {
		super(aMessage, aProcessInfo);
		localCount = aLocalCount;
		remoteCount = aRemoteCount;
		
//userName = aUserName;
//localCount = aLocalCount;
//remoteCount = aRemoteCount;
//isServer = anIsServer;
}
	public OTTimeStampInfo
	( 
	String aMessage,
	OTTimeStampInfo anOTTimeStampInfo
	
	

	) {
		this(aMessage, anOTTimeStampInfo.getLocalCount(), anOTTimeStampInfo.getRemoteCount(), anOTTimeStampInfo);
		
//userName = aUserName;
//localCount = aLocalCount;
//remoteCount = aRemoteCount;
//isServer = anIsServer;
}
	
	public OTTimeStampInfo
	( 
	int aLocalCount,
	int aRemoteCount 
//	boolean anIsServer, 
//	Object aFinder
	) {
		this("", "", aLocalCount, aRemoteCount, null);
		
//userName = aUserName;
//localCount = aLocalCount;
//remoteCount = aRemoteCount;
//isServer = anIsServer;
}

//	public String getUserName() {
//		return userName;
//	}

	public int getLocalCount() {
		return localCount;
	}

	public int getRemoteCount() {
		return remoteCount;
	}

//	public boolean isServer() {
//		return isServer;
//	}
	public static final String COUNTS = "Counts";
	public static Integer getLocalCount(String aMessage){	
		List<String> args = getArgs(aMessage, COUNTS);
		return Integer.parseInt(args.get(0));
		
	}
	public static Integer getRemoteCount(String aMessage){	
		List<String> args = getArgs(aMessage, COUNTS);
		return Integer.parseInt(args.get(1));
		
	}
	public static OTTimeStampInfo toTraceable(String aMessage) {
		List<String> args = getArgs(aMessage, COUNTS);
		int aLocalCount = Integer.parseInt(args.get(0));
		int aRemoteCount = Integer.parseInt(args.get(1));
		ProcessInfo aProcessInfo = ProcessInfo.toTraceable(aMessage);
		return new OTTimeStampInfo(aMessage, aLocalCount, aRemoteCount, aProcessInfo);
	}
	public static String toString(
//			String aUserName, 
			int aLocalCount,
			int aRemoteCount 
//			boolean anIsServer
			) {
//		return "Counts(" + "L(" + aLocalCount + "), " + "R(" + aRemoteCount + "))";
		return 
				COUNTS + "(" +
//				"Counts(" + 
				aLocalCount + ", " + aRemoteCount + ")";

//				+ "(" +	(anIsServer?"Server": "Client") + ")";
		
	}

	public String alternativeToString() {
		return toString(localCount, remoteCount);
	}

}
