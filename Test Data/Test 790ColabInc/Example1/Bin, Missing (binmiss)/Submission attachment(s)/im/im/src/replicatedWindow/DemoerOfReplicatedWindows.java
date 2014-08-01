package replicatedWindow;

import im.SessionManagerServerStarter;
import bus.uigen.models.MainClassLaunchingUtility;

public class DemoerOfReplicatedWindows {
	public static void main(String args[]) {
		demo();
	}	
	public static void demo() {	
		Class[] classes = {
				SessionManagerServerStarter.class,
				AliceReplicatedWindows.class,
				BobReplicatedWindows.class,
				CathyReplicatedWindows.class				
		};
		MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}
	
	

}
