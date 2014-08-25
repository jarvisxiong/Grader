package im.access;

import java.io.Serializable;

public class AMessage<DataType> implements Serializable, Message<DataType> {
	DataType messageData;
	public AMessage(DataType theData) {
		messageData = theData;
	}
	public DataType getData() {
		return messageData;
	}
}
