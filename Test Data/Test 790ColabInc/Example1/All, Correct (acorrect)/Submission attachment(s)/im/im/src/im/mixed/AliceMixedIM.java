package im.mixed;

import im.AliceIM;
import im.aware.AnAwareIMComposerAndLauncher;
import sasa.echoerAndIM.AnIMComposerAndLauncher;
import trace.im.IMTracerSetter;
import trace.im.aware.AwareIMTracerSetter;
import util.session.Communicator;
import util.session.CommunicatorSelector;
import util.trace.TraceableInfo;
import util.trace.Tracer;
import util.trace.session.SessionTracerSetter;
/*
 * This does not relaly work, cannot do both direct and p2p
 */
public class AliceMixedIM extends AliceIM{	
	public static void main (String[] args) {
//		Tracer.showInfo(true);
		TraceableInfo.setPrintSource(true);
//		AwareIMTracerSetter.traceAwareIM();
		IMTracerSetter.traceIM();
		SessionTracerSetter.setSessionPrintStatus();
		SessionTracerSetter.setSessionPrintStatus();
//		String[] launcherArgs = {SESSION_SERVER_HOST, SESSION_NAME, USER_NAME, AnIMComposerAndLauncher.DIRECT};
		String[] launcherArgs = {SESSION_SERVER_HOST, SESSION_NAME,  USER_NAME, APPLICATION_NAME};

		(new AP2PAndRelayedIMComposerAndLauncher()).composeAndLaunch(launcherArgs);

	}	
}
