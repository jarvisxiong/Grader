package clean.windowApp;

import util.awt.ADelegateFrame;

public class GlobalPainterComposerAndLauncher {
	public static void main(String[] args) {
		createCharacterDrawer("frame 1");
		createCharacterDrawer("frame 2");
		createGlobalFrameAndPainter();
	}
	public static ADelegateFrame createCharacterDrawer(String theTitle) {
		ADelegateFrame frame = new ADelegateFrame(theTitle);
		new AListenableCharacterDrawer(frame);
//		frame.add(new TextField("I am a text field"));
		frame.setSize(300, 200);
		frame.setVisible(true);
		return frame;
	}
	public static ADelegateFrame createGlobalFrameAndPainter() {
		ADelegateFrame globalFrame = new ADelegateFrame("global frame");
		new AGlobalPainter(globalFrame);
		globalFrame.setSize(300, 200);
		globalFrame.setVisible(true);
		return globalFrame;
	}
}
