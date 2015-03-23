package old.echoerAndIM;
import util.session.Communicator;
public class AnOutCoupledEchoer extends AnEchoer  {
	Communicator communicator;
	public AnOutCoupledEchoer(Communicator theCommunicator) {
		communicator = theCommunicator;
	}	
	void processInput(String theNextInput) {
		super.processInput(theNextInput);	
		communicator.toOthers(new ARemoteInput(theNextInput));
	}	
	public void doInput() {
		super.doInput();
		communicator.leave();
	}	
}
