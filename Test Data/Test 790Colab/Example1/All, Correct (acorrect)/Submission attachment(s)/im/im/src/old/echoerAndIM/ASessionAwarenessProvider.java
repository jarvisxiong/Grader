package old.echoerAndIM;
import java.util.Collection;

import javax.swing.JOptionPane;

import util.session.SessionMessageListener;
public class ASessionAwarenessProvider implements SessionMessageListener{	
	public void clientJoined(String userName, String theApplicationName, String theSessionName, boolean newSession, boolean newApplication, Collection<String> allUsers) {
		String newOrOldSession = newSession?" new ":"";
		String newOrOldApplication = newApplication?" new ":"";
		String message = "User: " + userName + 
				" joined " + newOrOldApplication + " application: " + theApplicationName +
				" in " + newOrOldSession + " session: " + theSessionName +
				getOtherUserNames(allUsers, userName);
		JOptionPane.showMessageDialog(null, message);
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
	public void clientLeft(String userName, String theApplicationName) {
		String message = "User left session:" + userName;
		JOptionPane.showMessageDialog(null, message);
	}
//	public void objectReceived(Object message, String userName) {					
//	}
}
