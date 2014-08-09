package replicatedWindow.jitter;

import replicatedWindow.BobReplicatedWindows;
import replicatedWindow.CathyReplicatedWindows;
import im.SessionManagerServerStarter;
import bus.uigen.models.MainClassLaunchingUtility;

public class DemoerOfJitteryReplicatedWindows {
	public static void main(String args[]) {
		demo();
	}	
	public static void demo() {	
		Class[] classes = {
				SessionManagerServerStarter.class,
				AliceJitteryReplicatedWindows.class,
				BobReplicatedWindows.class,
				CathyReplicatedWindows.class				
		};
		MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}
	
	

}
