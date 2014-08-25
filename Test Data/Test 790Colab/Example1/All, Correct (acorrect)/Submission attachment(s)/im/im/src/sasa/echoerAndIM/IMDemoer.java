package sasa.echoerAndIM;

import im.AliceIM;
import im.BobIM;
import im.SessionManagerServerStarter;
import bus.uigen.models.MainClassLaunchingUtility;

public class IMDemoer {
	public static void main(String args[]) {
		demo();
	}
	
	public static void demo() {
		Class[] classes = {
				SessionManagerServerStarter.class,
				AliceIM.class,
				BobIM.class,
				

				
		};
		MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}
	
	

}
