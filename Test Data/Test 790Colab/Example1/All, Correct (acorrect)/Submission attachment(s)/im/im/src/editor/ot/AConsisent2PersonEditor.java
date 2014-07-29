package editor.ot;
import old.echoerAndIM.ASessionAwarenessProvider;
import old.echoerAndIM.AnIMComposerAndLauncher;
import util.session.ReceivedMessageFilterSelector;
import util.session.SentMessageFilterSelector;
import util.session.Communicator;
import util.session.MessageFilterCreator;
import util.session.ReceivedMessage;
import util.session.SentMessage;
import util.trace.Tracer;
import widgets.AnColabTextFieldComposerAndLauncher;
import widgets.TextField;
public class AConsisent2PersonEditor {
	public static final String APPLICATION_NAME = "Widgets Editor";
	static Communicator communicator;
	public static void main (String[] args) {
		Tracer.showInfo(true);
		Communicator communicator = AnIMComposerAndLauncher.createCommunicator(args, APPLICATION_NAME);
		communicator.addSessionMessageListener(new ASessionAwarenessProvider());
		TextField textField = AnColabTextFieldComposerAndLauncher.createCollaborativeTextField("text 1", communicator);
		communicator.setMinimumDelayToServer(5000);
		addConsistencyModules(args[2], communicator);
		communicator.join();
		
	}
	public static void addConsistencyModules(String userName, Communicator communicator) {
		OTManager otManager;
		if (userName.equals("alice"))
			otManager = new AnOTManager(true);
		else
			otManager = new AnOTManager(false);			
		MessageFilterCreator<ReceivedMessage> receivedMessageQueuerCreator = 
			new AnOTReceivedMessageQueuerCreator(otManager);
		MessageFilterCreator<SentMessage> sentMessageQueuerCreator = 
			new AnOTSentMessageQueuerCreator(otManager);
		ReceivedMessageFilterSelector.setMessageFilterFactory(receivedMessageQueuerCreator);
		SentMessageFilterSelector.setMessageFilterCreator(sentMessageQueuerCreator);
	}
}
