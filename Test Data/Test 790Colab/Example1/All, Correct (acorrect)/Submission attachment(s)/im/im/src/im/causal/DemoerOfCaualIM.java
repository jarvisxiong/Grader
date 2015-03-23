package im.causal;

import im.BobIM;
import im.CathyIM;
import im.SessionManagerServerStarter;
import bus.uigen.models.MainClassLaunchingUtility;

public class DemoerOfCaualIM {
	public static void main(String args[]) {
		demo();
	}	
	public static void demo() {	
		Class[] classes = {
				SessionManagerServerStarter.class,
				AliceCausalIM.class,
				BobCausalIM.class,
				CathyCausalIM.class				
		};
		MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}
	
	

}
