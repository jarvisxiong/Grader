package windowApp;

import util.awt.ADelegateFrame;
import util.awt.AnInputQueue;

public class LocalMirrorCharacterDrawer extends CharacterDrawerLauncher{
	public static void main(String[] args) {
		ADelegateFrame frame1 = createCharacterDrawer("frame 1");
		ADelegateFrame frame2 = createCharacterDrawer("frame 2");
		AnInputQueue.getEventQueue().clearEventQueuehandlers();
		AnInputQueue.getEventQueue().addEventQueueHandler(new ALocalEventMirrorer(frame1, frame2));
	}		
//	public static ADelegateFrame createCharacterDrawer(String theTitle) {
//		ADelegateFrame frame = new ADelegateFrame();
//		frame.setTitle(theTitle);
//		new ACharacterDrawer(frame);
//		frame.setSize(300, 200);
//		frame.setVisible(true);		
//		return frame;
//	}
}
