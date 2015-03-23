package trace.causal;

import java.util.Map;

public class InitialVectorTimeStampCreated extends VectorTimeStampInfo{

	public InitialVectorTimeStampCreated(String aMessage, 
			String aUserName, 
			Map<String, Integer> aUserToCount,
			Object aFinder) {
		super(aMessage, aUserName, aLocalCount, aRemoteCount, isServer, aFinder);
	}
	public static InitialVectorTimeStampCreated newCase(String aUserName, 
			int aLocalCount, 
			int aRemoteCount,
			boolean anIsServer,
			Object aFinder) {
		String aMessage = "OT Time Stamp Created:" + toString(aUserName, aLocalCount, aRemoteCount, anIsServer);
		InitialVectorTimeStampCreated retVal = new InitialVectorTimeStampCreated(aMessage, aUserName, aLocalCount, aRemoteCount, anIsServer, aFinder);
		retVal.announce();
		return retVal;
	}

}
