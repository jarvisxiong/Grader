package im.ot;

import im.AnIMComposerAndLauncher;
import util.session.ReceivedMessageFilterSelector;
import util.session.SentMessageFilterSelector;
import util.session.Communicator;
import util.session.MessageFilterCreator;
import util.session.ReceivedMessage;
import util.session.SentMessage;
public class AnOTIMComposerAndLauncher extends AnIMComposerAndLauncher {
	public static void main (String[] args) {
//		Tracer.showInfo(true);
//		ACommunicatorSelector.selectDirectCommunicator();
//		CausalityManager causalityManager = new ACausalityManager(args[2]);
//		MessageFilterCreator<ReceivedMessage> receivedMessageQueuerCreator = 
//			new ACausalReceivedMessageFilterCreator(causalityManager);
//		MessageFilterCreator<SentMessage> sentMessageQueuerCreator = 
//			new ACausalSentMessageFilterCreator(causalityManager);
//		AReceivedMessageFilterSelector.setMessageFilterFactory(receivedMessageQueuerCreator);
//		ASentMessageFilterSelector.setMessageFilterCreator(sentMessageQueuerCreator);
//		Communicator communicator = createCommunicator(args, APPLICATION_NAME);
//		communicator.addSessionMessageListener(causalityManager);		
//		if (args[2].equalsIgnoreCase("alice"))
//			setDelaysAlice(communicator);
//		Echoer outCouplerAndEchoer =  new AnOutCoupledEchoer(communicator);
//		addAwarenessAndInCoupler(communicator, outCouplerAndEchoer);
//		//communicator.join();
//		communicator.join();
//		outCouplerAndEchoer.doInput();	
	}
	public Communicator createCommunicator(String args[]) {
		
		// set factories used to create communicator
//		ACommunicatorSelector.selectDirectCommunicator();

		OTManager otManager = new AnOTManager(args[1], args[2], false);
		MessageFilterCreator<ReceivedMessage> receivedMessageFilterCreator = 
				new AnOTReceivedMessageFilterCreator(otManager);
			MessageFilterCreator<SentMessage> sentMessageFilterCreator = 
				new AnOTSentMessageFilterCreator(otManager);
			ReceivedMessageFilterSelector.setMessageFilterFactory(receivedMessageFilterCreator);
			SentMessageFilterSelector.setMessageFilterCreator(sentMessageFilterCreator);
		
		communicator = super.createCommunicator(args);
//		communicator.addSessionMessageListener(new ASessionAwarenessProvider());

//		if (args[2].equalsIgnoreCase("alice"))
//			setDelaysAlice(communicator);
		communicator.setMinimumDelayToServer(4000);
		return communicator;
//		MessageFilterCreator<ReceivedMessage> receivedMessageQueuerCreator = new ADeTimeStampingReceivedMessageFilterCreator();
//		MessageFilterCreator<SentMessage> sentMessageQueuerCreator = new ATimeStampingSentMessageFilterCreator();
//		AReceivedMessageFilterSelector
//				.setMessageFilterFactory(receivedMessageQueuerCreator);
//		ASentMessageFilterSelector
//				.setMessageFilterCreator(sentMessageQueuerCreator);
		
		
	}
	
	
}
