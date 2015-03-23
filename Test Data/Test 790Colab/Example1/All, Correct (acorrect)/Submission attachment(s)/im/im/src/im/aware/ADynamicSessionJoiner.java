package im.aware;

import im.AnIMComposerAndLauncher;

import java.util.Collection;

import javax.swing.JOptionPane;

import bus.uigen.misc.OEMisc;
import util.session.Communicator;
import util.session.CommunicatorSelector;
import util.session.SessionMessageListener;
import util.trace.session.ClientJoinNotificationReceived;
import util.trace.session.ClientLeaveNotificationReceived;

public class ADynamicSessionJoiner implements SessionMessageListener {
	String sessionManagerHost;
	String userName;
	public ADynamicSessionJoiner(String aSessionManagerHost, String aUserName) {
		sessionManagerHost = aSessionManagerHost;
		userName = aUserName;
	}
	public void clientJoined(
			String aUserName, String anApplicationName, String aSessionName,
			boolean aNewSession, boolean aNewApplication, Collection<String> anAllUsers) {
		ClientJoinNotificationReceived.newCase(CommunicatorSelector.getProcessName(), aUserName, (String) anApplicationName, aSessionName, this);
//		String aMessage = computeAwarenessMessage(aUserName, anApplicationName, aSessionName, aNewSession, aNewApplication, anAllUsers);
//		JOptionPane.showMessageDialog(null, aMessage);
		if (aNewApplication && anApplicationName != null && AnIMComposerAndLauncher.DEFAULT_APPLICATION_NAME.equals(anApplicationName))
			joinSession(anApplicationName, aSessionName);
		
	}
	public void joinSession(String anApplicationName, String aSessionName) {
		String[] launcherArgs = {sessionManagerHost, aSessionName, userName, anApplicationName, Communicator.DIRECT};
		OEMisc.runWithObjectEditorConsole(AnIMComposerAndLauncher.class, launcherArgs);
//		Communicator aCommunicator = CommunicatorSelector.
//				getCommunicator(sessionManagerHost, aSessionName, anApplicationName, Communicator.RELAYED);
		
		
	}
	public String computeAwarenessMessage(String aUserName, Object anApplicationName, String aSessionName,
			boolean aNewSession, boolean aNewApplication, Collection<String> anAllUsers) {
		String newOrOldSession = aNewSession?" new ":"";
		String newOrOldApplication = aNewApplication?" new ":"";
		String message = "User: " + aUserName +
				" joined " + newOrOldApplication + " application: " + anApplicationName +
				" in " + newOrOldSession + " session: " + aSessionName +
				getOtherUserNames(anAllUsers, aUserName);
		return message;
	}
	String getOtherUserNames(Collection<String> allUsers, String myName) {
		if (allUsers.size() == 1)
			return "";
		String retVal = " with other users:";
		for (String userName:allUsers) {
			if (userName.equals(myName)) continue;
			retVal += " " + userName;
		}
		return retVal;
	}
	public void clientLeft(String aUserName, String anApplicationName) {
		ClientLeaveNotificationReceived.newCase(CommunicatorSelector.getProcessName(), aUserName, (String) anApplicationName,  this);
		String message = "User left session:" + aUserName;
		JOptionPane.showMessageDialog(null, message);
	}
}
