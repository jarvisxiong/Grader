package im.aware;

import im.BobIM;
import im.CathyIM;
import im.SessionManagerServerStarter;
import bus.uigen.models.MainClassLaunchingUtility;

public class DemoerOfAwareIM {
	public static void main(String args[]) {
		demo();
	}	
	public static void demo() {	
		Class[] classes = {
				SessionManagerServerStarter.class,
				AliceIMJoiner.class,
				BobIM.class,
				CathyIM.class				
		};
		MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}
	
	

}
