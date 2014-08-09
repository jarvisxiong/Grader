package im.centralized;

import im.ExampleIMSession;
import im.AnIMComposerAndLauncher;
import trace.im.IMTracerSetter;
import util.session.Communicator;
import util.trace.Tracer;
import util.trace.session.ServerClientJoined;
import util.trace.session.SessionTracerSetter;


public class MasterIMModelLauncher implements ExampleIMSession{
	public static final String CLIENT_NAME = "Model";
	public static void main (String[] args) {
		String[] launcherArgs = {SESSION_SERVER_HOST, SESSION_NAME, CLIENT_NAME,  APPLICATION_NAME,  Communicator.DIRECT};
//		Tracer.showInfo(true);
		IMTracerSetter.traceIM();
		SessionTracerSetter.setSessionPrintStatus();

//		SessionTracerSetter.traceSession();
//		Tracer.setKeywordPrintStatus(ClientJoined.class, true);
		(new AMasterIMComposerAndLauncher()).composeAndLaunch(launcherArgs);
	}
	
}
