package main;

import bus.uigen.ObjectEditor;
import util.misc.ThreadSupport;
import bus.uigen.OEFrame;
import grail.graphics.BridgeScene;
import grail.graphics.ABridgeScene;

public class Assignment6 {
	public static final int DEFAULT_SCREEN_SIZE_X = 1600;
	public static final int DEFAULT_SCREEN_SIZE_Y = 1000;
	public static final int DEFAULT_SLEEP_TIME = 2500;
	
	public static void main(String[] args) {
		BridgeScene test = new ABridgeScene();
		OEFrame editor = ObjectEditor.edit(test);
		editor.showDrawPanel();
		editor.hideMainPanel();
		editor.setSize(DEFAULT_SCREEN_SIZE_X, DEFAULT_SCREEN_SIZE_Y);
		editor.setLocation(0, 0);
		ThreadSupport.sleep(DEFAULT_SLEEP_TIME);
		test.approach(test.getRobin());
		editor.refresh();
		ThreadSupport.sleep(DEFAULT_SLEEP_TIME);
		test.failed();
		editor.refresh();
		ThreadSupport.sleep(DEFAULT_SLEEP_TIME);
		test.passed();
		editor.refresh();
		ThreadSupport.sleep(DEFAULT_SLEEP_TIME);
	}
}