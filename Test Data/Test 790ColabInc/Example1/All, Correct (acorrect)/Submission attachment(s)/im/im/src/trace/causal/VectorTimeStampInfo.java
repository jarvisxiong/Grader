package trace.causal;

import java.util.HashMap;
import java.util.Map;

import util.trace.TraceableInfo;
import util.trace.session.ProcessInfo;

public class VectorTimeStampInfo extends ProcessInfo {
	
	Map<String, Integer> userToCount = new HashMap();


	public VectorTimeStampInfo
			(String aMessage, 
			String aProcessName,
			Map<String, Integer> aUserToCount,
			Object aFinder) {
		super(aMessage, aProcessName,  aFinder);
		userToCount = aUserToCount;
	}
	
	public Map<String, Integer> getUserToCount() {
		return userToCount;
	}
	
	public static String to

//	public boolean isServer() {
//		return isServer;
//	}
	public static String toString(
			String aUserName, 
			int aLocalCount,
			int aRemoteCount, 
			boolean anIsServer
			) {
		return aUserName  + 
				 "[local: " + aLocalCount + ", remote: " + aRemoteCount + "]" ;
//				+ "(" +	(anIsServer?"Server": "Client") + ")";
		
	}

	public String alternativeToString() {
		getArgOfCompositeDescriptor(aTraceLine, aDescriptor)
		return toString(userName, localCount, remoteCount, isServer);
	}

}
