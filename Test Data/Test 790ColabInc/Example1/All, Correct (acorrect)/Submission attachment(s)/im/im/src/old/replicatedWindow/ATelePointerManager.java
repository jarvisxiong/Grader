package old.replicatedWindow;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

import util.awt.ADelegateFrame;
import util.awt.DelegateFramePainter;
public class ATelePointerManager implements DelegateFramePainter   {
	Map<String, TelePointer> telePointers = new HashMap();			
	public void paint (ADelegateFrame theDelegateFrame, Graphics g) {
		for (String clientName:telePointers.keySet()) {
			TelePointer telePointer = telePointers.get(clientName);
			if (telePointer.getDelegateFrame() == theDelegateFrame) {
				if (telePointer == null || clientName == null)
					break;
				g.drawString (clientName, telePointer.getX(), telePointer.getY());				
			}
		}
	}	
	public void put (String clientName, TelePointer telePointer) {
		if (clientName == null) {
			return;
		}
		TelePointer prevTelePointer = telePointers.get(clientName);
		telePointers.put(clientName, telePointer);
		telePointer.getDelegateFrame().repaint();
		if (prevTelePointer != null)
			prevTelePointer.getDelegateFrame().repaint();
	}
	public void remove (String clientName) {
		telePointers.remove(clientName);
	}
 
}
