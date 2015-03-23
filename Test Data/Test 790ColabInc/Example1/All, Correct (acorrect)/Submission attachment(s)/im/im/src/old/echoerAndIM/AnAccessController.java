package old.echoerAndIM;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import util.session.Communicator;
public class AnAccessController implements AccessController {	
	Communicator communicator;
	List<String> inputers = new ArrayList();
	List<String> administrators = new ArrayList();	
	String owner;
	public AnAccessController(Communicator theCommunicator) {
		communicator = theCommunicator;
	}
	public boolean hasOwnership(String theUser) {
		return theUser.equals(owner);
	}
	public void setOwner(String theOwner) {
		JOptionPane.showMessageDialog(null, theOwner + " is the owner");
		owner = theOwner;
	}
	public boolean canInput(String theUser) {
		return  inputers.contains(theUser);
	}
	public void addInputterLocal(String newInputer) {
		inputers.add(newInputer);	
		JOptionPane.showMessageDialog(null, newInputer + " can input");
	}
	public void addInputter(String newInputer) {
		if (!canAdminister(communicator.getClientName())) {			
				JOptionPane.showMessageDialog(null, communicator.getClientName() +
						" does not have authorization to administer");	
		return;
			
		}
		communicator.toAll(new AnInputAuthorization(newInputer));
	}
	public boolean canAdminister(String theUser) {
		return  administrators.contains(theUser);
	}
	public void addAdministratorLocal(String newAdminsitrator) {
		administrators.add(newAdminsitrator);
		JOptionPane.showMessageDialog(null, newAdminsitrator + " can administer");
	}	
	public void addAdministrator(String newAdminsitrator) {	
		if (!hasOwnership(communicator.getClientName())) {			
			JOptionPane.showMessageDialog(null, communicator.getClientName() +
					" is not owner");	
	        return;		
	    }
		communicator.toAll(new AnAdministratorAuthorization(newAdminsitrator));
	}	
	public boolean veto(Object theInput) {		
		boolean retVal = !canInput (communicator.getClientName());
		if (retVal) {
			JOptionPane.showMessageDialog(null, communicator.getClientName() +
					" does not have authorization to input");
		}
		return retVal;
	}	
}
