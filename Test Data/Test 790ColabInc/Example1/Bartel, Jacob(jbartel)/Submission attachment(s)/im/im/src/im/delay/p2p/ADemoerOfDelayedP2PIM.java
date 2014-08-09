package im.delay.p2p;

import im.AnIMDemoerAndTester;
import im.AliceIM;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import util.misc.ThreadSupport;
import util.models.ConsoleModel;
import util.remote.ProcessExecer;
import bus.uigen.ObjectEditor;
import bus.uigen.models.DemoerAndTester;
import bus.uigen.models.MainClassLaunchingUtility;
import bus.uigen.models.MainClassListLauncher;

public class ADemoerOfDelayedP2PIM extends AnIMDemoerAndTester implements DemoerAndTester{
	
	public static void main(String args[]) {
		DemoerAndTester demoer = createDemoer();
		MainClassListLauncher aLauncher = demoer.createAndDisplayLauncher();
		demoer.executeAll();
	}
	public static DemoerAndTester createDemoer() {
		return new ADemoerOfDelayedP2PIM();
	}
	protected Class aliceClass() {
		return 	AliceP2PDelayingIM.class;
	}
	protected Class bobClass() {
		return 	BobP2PIM.class;
	}
	protected Class cathyClass() {
		return 	CathyP2PIM.class;
	}


	
	
	
	
	
	
	
	
		
	
	
	

}
