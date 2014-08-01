package im.timestamp;

import util.trace.Tracer;

public class AMessageWithTimeStamp implements MessageWithTimeStamp {
	Object message;
	long timeStamp;
	public AMessageWithTimeStamp (Object theMessage, long theTimeStamp) {
		message = theMessage;
		timeStamp = theTimeStamp;
		Tracer.info(this, "New  Time Stamp:" + theTimeStamp);
	}
	
	public Object getMessage() {
		return message;
	}
	
	public long geTimeStamp() {
		return timeStamp;
	}

	
	

}
