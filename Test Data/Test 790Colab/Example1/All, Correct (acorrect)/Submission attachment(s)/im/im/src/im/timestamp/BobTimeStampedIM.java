package im.timestamp;

import im.BobIM;
import sasa.echoerAndIM.AnIMComposerAndLauncher;

public class BobTimeStampedIM extends BobIM {
//	public static final String SESSION_NAME = "FrostySession";
//	public static final String USER_NAME = "Bob";
//	public static final String SESSION_SERVER_HOST = "localhost";
	public static void main (String[] args) {
//		String[] launcherArgs = {SESSION_SERVER_HOST, SESSION_NAME, USER_NAME, AnIMComposerAndLauncher.DIRECT};
		String[] launcherArgs = {SESSION_SERVER_HOST, SESSION_NAME,  USER_NAME, APPLICATION_NAME};

		(new ATimeStampingIMComposerAndLauncher()).composeAndLaunch(launcherArgs);
	}
	
}
