package im.causal;
import old.echoerAndIM.AnIMComposerAndLauncher;
import old.echoerAndIM.AnOutCoupledEchoer;
import old.echoerAndIM.Echoer;
import util.session.Communicator;
public class ANonCausalIMComposerAndLauncher extends AnIMComposerAndLauncher {
	static String userName;
	public static void main (String[] args) {
		Communicator communicator = createCommunicator(args, APPLICATION_NAME);
		setDelaysAlice(communicator);
		Echoer outCouplerAndEchoer =  new AnOutCoupledEchoer(communicator);
		addAwarenessAndInCoupler(communicator, outCouplerAndEchoer);
		communicator.join();
		outCouplerAndEchoer.doInput();	
	}
	static void setDelaysAlice(Communicator communicator) {
		communicator.setMinimumDelayToPeer("cathy", 20000);
	}
	
}
