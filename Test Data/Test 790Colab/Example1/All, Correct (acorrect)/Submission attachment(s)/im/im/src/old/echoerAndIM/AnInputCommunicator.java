package old.echoerAndIM;
import util.session.Communicator;
public class AnInputCommunicator extends AnInputEchoer implements InputCommunicator {
	Communicator messageSender;
	public AnInputCommunicator(Communicator theMessageSender) {
		messageSender = theMessageSender;
	}	
	void processInput(String theNextInput) {
		super.processInput(theNextInput);	
		messageSender.toOthers(new ARemoteInput(theNextInput));
	}	
	public void doInput() {
		super.doInput();
		messageSender.leave();
	}	
}
