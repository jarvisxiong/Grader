package sasa.echoerAndIM;


public class ARemoteInput<DataType> extends AMessage<DataType> {
	public ARemoteInput (DataType theUserName) {
		super(theUserName);
	}
}
