package old.echoerAndIM;
import java.util.Collection;

import javax.swing.JOptionPane;

import util.session.Communicator;
import util.session.ReceivedMessageListener;
public class AnInputReceiver implements ReceivedMessageListener{
	Communicator communicator;
	public AnInputReceiver(Communicator theCommunicator) {
		communicator = theCommunicator;		
	}
	public void objectReceived(Object message, String userName) {
		if (message instanceof ARemoteInput)
			processRemoteInput((ARemoteInput) message, userName);				
	}
	void processRemoteInput (ARemoteInput remoteInput, String userName) {
		System.out.println("Remote Input:" + remoteInput.getData() + " from:" + userName);
	}
	public void clientJoined(String userName, String theApplicationName, String theSessionName, boolean newSession, boolean newApplication, Collection<String> allUsers) {
		String newOrOldSession = newSession?" new ":"";
		String newOrOldApplication = newApplication?" new ":"";
		String message = "User: " + userName + 
				" joined " + newOrOldApplication + " application: " + theApplicationName +
				" in " + newOrOldSession + " session: " + communicator.getSessionName() +
				getOtherUserNames();
		JOptionPane.showMessageDialog(null, message);
	}
	public void clientLeft(String userName, String theApplicationName) {
		String message = "User left session:" + userName;
		JOptionPane.showMessageDialog(null, message);
	}
	String getOtherUserNames() {
		String[] userNames = communicator.getUserNames();
		if (userNames.length == 0)
			return "";
		String retVal = " with other users:";		
		for (int i = 0; i < userNames.length; i++) {
				retVal += " " +  userNames[i];			
		}
		return retVal;	
	}
	
}
