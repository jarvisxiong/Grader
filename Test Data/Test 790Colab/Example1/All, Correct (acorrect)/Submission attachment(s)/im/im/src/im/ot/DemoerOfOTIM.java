package im.ot;

import bus.uigen.models.MainClassLaunchingUtility;

public class DemoerOfOTIM {
	public static void main(String args[]) {
		demo();
	}	
	public static void demo() {	
		Class[] classes = {
				OTSessionManagerServerStarter.class,
				AliceOTIM.class,
				BobOTIM.class,
				CathyOTIM.class				
		};
		MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}
	
	

}
