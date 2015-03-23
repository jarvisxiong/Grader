package clean.windowApp;

import util.awt.ADelegateFrame;

public class CharacterDrawerLauncher {
	public static void main(String[] args) {
		createCharacterDrawer("frame 1");
		createCharacterDrawer("frame 2");
	}
	public static ADelegateFrame createCharacterDrawer(String theTitle) {
		ADelegateFrame frame = new ADelegateFrame(theTitle);
		new ACharacterDrawer(frame);
		frame.setSize(300, 200);
		frame.setVisible(true);
		return frame;
	}
}
