package im.causal;
import im.AnIMComposerAndLauncher;
import util.session.ReceivedMessageFilterSelector;
import util.session.SentMessageFilterSelector;
import util.session.Communicator;
import util.session.MessageFilterCreator;
import util.session.ReceivedMessage;
import util.session.SentMessage;
public class ACausalIMComposerAndLauncher extends AnIMComposerAndLauncher {
	public static void main (String[] args) {

	}
	public Communicator createCommunicator(String args[]) {
		
		// set factories used to create communicator
//		ACommunicatorSelector.selectDirectCommunicator();
		CausalityManager causalityManager = new ACausalityManager(args[2]);
		MessageFilterCreator<ReceivedMessage> receivedMessageQueuerCreator = 
			new ACausalReceivedMessageFilterCreator(causalityManager);
		MessageFilterCreator<SentMessage> sentMessageQueuerCreator = 
			new ACausalSentMessageFilterCreator(causalityManager);
		ReceivedMessageFilterSelector.setMessageFilterFactory(receivedMessageQueuerCreator);
		SentMessageFilterSelector.setMessageFilterCreator(sentMessageQueuerCreator);
//		communicator = super.createCommunicator(args, applicationName);
		communicator = super.createCommunicator(args);

		communicator.addSessionMessageListener(causalityManager);
//		if (args[2].equalsIgnoreCase("alice"))
//			setDelaysAlice(communicator);
		return communicator;
//		MessageFilterCreator<ReceivedMessage> receivedMessageQueuerCreator = new ADeTimeStampingReceivedMessageFilterCreator();
//		MessageFilterCreator<SentMessage> sentMessageQueuerCreator = new ATimeStampingSentMessageFilterCreator();
//		AReceivedMessageFilterSelector
//				.setMessageFilterFactory(receivedMessageQueuerCreator);
//		ASentMessageFilterSelector
//				.setMessageFilterCreator(sentMessageQueuerCreator);
		
		
	}
	static void setDelaysAlice(Communicator communicator) {
		communicator.setMinimumDelayToPeer("Cathy", 20000);
	}
	
}
