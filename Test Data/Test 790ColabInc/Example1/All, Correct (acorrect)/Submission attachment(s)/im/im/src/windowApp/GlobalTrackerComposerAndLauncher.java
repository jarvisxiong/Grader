package windowApp;

import util.awt.ADelegateFrame;

public class GlobalTrackerComposerAndLauncher extends CharacterDrawerLauncher {
	public static void main(String[] args) {
		createCharacterDrawer("frame 1");
		createCharacterDrawer("frame 2");
		createGlobalFrameAndPainter();
	}
	public static ADelegateFrame createGlobalFrameAndPainter() {
		ADelegateFrame globalFrame = new ADelegateFrame("global frame");
		new AGlobalPainter(globalFrame);
		globalFrame.setSize(300, 200);
		globalFrame.setVisible(true);
		return globalFrame;		
	}
}
