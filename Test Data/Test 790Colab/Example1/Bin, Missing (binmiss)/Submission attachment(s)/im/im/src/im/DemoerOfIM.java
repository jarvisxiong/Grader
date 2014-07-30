package im;

import bus.uigen.models.MainClassLaunchingUtility;

public class DemoerOfIM {
	public static void main(String args[]) {
		demo();
	}	
	public static void demo() {	
		Class[] classes = {
				SessionManagerServerStarter.class,
				AliceIM.class,
				BobIM.class,
				CathyIM.class				
		};
		MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}
	
	

}
