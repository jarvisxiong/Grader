package widgets;
import old.echoerAndIM.AnIMComposerAndLauncher;
import util.session.Communicator;
import util.session.PeerMessageListener;
public class AnColabTextFieldComposerAndLauncher {
	public static final String APPLICATION_NAME = "Widgets Editor";
	static Communicator communicator;
	public static void main (String[] args) {		
		Communicator communicator = AnIMComposerAndLauncher.createCommunicator(args, APPLICATION_NAME);
		createCollaborativeTextField("text 1", communicator);
		createCollaborativeTextField("text 2", communicator);
		communicator.join();		
	}
	public static TextField createCollaborativeTextField(String theTitle, Communicator communicator) {
		TextField textField = TextFieldLauncher.createTextField(theTitle);
		new ATextFieldOutCoupler(textField, communicator);
		PeerMessageListener inCoupler = new ATextFieldInCoupler(textField);
		communicator.addPeerMessageListener(inCoupler);	
		return textField;
	}
	
}
