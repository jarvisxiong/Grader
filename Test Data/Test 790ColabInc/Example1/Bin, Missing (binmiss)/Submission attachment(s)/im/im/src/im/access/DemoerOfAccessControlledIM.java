package im.access;

import im.SessionManagerServerStarter;
import bus.uigen.models.MainClassLaunchingUtility;

public class DemoerOfAccessControlledIM {
	public static void main(String args[]) {
		demo();
	}	
	public static void demo() {	
		Class[] classes = {
				SessionManagerServerStarter.class,
				AliceAccessControlledIM.class,
				BobAccessControlledIM.class,
				CathyAccessControlledIM.class				
		};
		MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}
	
	

}
