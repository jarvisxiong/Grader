package im.causal;

public class AMessageWithVectorTimeStamp implements MessageWithVectorTimeStamp {
	Object message;
	VectorTimeStamp vectorTimeStamp;
	public AMessageWithVectorTimeStamp (Object theMessage, VectorTimeStamp theVectorTimeStamp) {
		message = theMessage;
		vectorTimeStamp = theVectorTimeStamp;
		System.out.println("New Vector Time Stamp of size:" + vectorTimeStamp.size());
	}
	/* (non-Javadoc)
	 * @see causal.MessageWithVectorTimeStamp#getMessage()
	 */
	public Object getMessage() {
		return message;
	}
	/* (non-Javadoc)
	 * @see causal.MessageWithVectorTimeStamp#getVectorTimeStamp()
	 */
	public VectorTimeStamp getVectorTimeStamp() {
		return vectorTimeStamp;
	}
	public String toString() {
		return message.toString() + " " + vectorTimeStamp.toString();
	}

}
