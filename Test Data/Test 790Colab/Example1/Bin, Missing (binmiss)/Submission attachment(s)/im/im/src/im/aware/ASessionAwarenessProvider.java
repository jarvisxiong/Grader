package im.aware;

import java.util.Collection;

import javax.swing.JOptionPane;

import util.session.CommunicatorSelector;
import util.session.SessionMessageListener;
import util.trace.session.ClientJoinNotificationReceived;
import util.trace.session.ClientLeaveNotificationReceived;

public class ASessionAwarenessProvider implements SessionMessageListener {
	public void clientJoined(
			String aUserName, String anApplicationName, String aSessionName,
			boolean aNewSession, boolean aNewApplication, Collection<String> anAllUsers) {
		ClientJoinNotificationReceived.newCase(CommunicatorSelector.getProcessName(), aUserName, (String) anApplicationName, aSessionName, this);
		String aMessage = computeAwarenessMessage(aUserName, anApplicationName, aSessionName, aNewSession, aNewApplication, anAllUsers);
		JOptionPane.showMessageDialog(null, aMessage);
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
