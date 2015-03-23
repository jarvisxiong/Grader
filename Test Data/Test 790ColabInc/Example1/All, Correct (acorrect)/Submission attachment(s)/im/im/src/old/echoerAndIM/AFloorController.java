package old.echoerAndIM;

import javax.swing.JOptionPane;

import util.session.Communicator;

public class AFloorController implements FloorController {	
	Communicator messageSender;
	public AFloorController(Communicator theMessageSender) {
		messageSender = theMessageSender;
	}
	String floorHolder;	
	public boolean veto(Object theInput) {		
		boolean retVal = !messageSender.getClientName().equals(floorHolder);
		if (retVal) {
			JOptionPane.showMessageDialog(null, "Must have floor to input.");
		}
		return retVal;
	}	
	public void newFloorHolder(String newVal) {
		floorHolder = newVal;
		if (newVal != null)
			JOptionPane.showMessageDialog(null, floorHolder + " has the floor");
	}	
	public boolean hasFloor() {
		return  messageSender.getClientName().equals(floorHolder);
	}	
	public void getFloor() {
		if (hasFloor()) return;
		messageSender.toAll(new AGetFloorMessage(messageSender.getClientName()));		
	}	
//	public void releaseFloor() {
//		if (hasFloor())
//			messageSender.toAll(new AReleaseFloorMessage(messageSender.getClientName()));		
//	}
}
