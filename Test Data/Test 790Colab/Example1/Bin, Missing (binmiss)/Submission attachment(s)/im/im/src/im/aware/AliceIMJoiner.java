package im.aware;

import im.AliceIM;
import sasa.echoerAndIM.AnIMComposerAndLauncher;
import trace.im.aware.AwareIMTracerSetter;
import util.session.Communicator;
import util.trace.TraceableInfo;
import util.trace.Tracer;

public class AliceIMJoiner extends AliceIM{	
	public static void main (String[] args) {
//		Tracer.showInfo(true);
		TraceableInfo.setPrintSource(true);
		AwareIMTracerSetter.traceAwareIM();
		String[] launcherArgs = {SESSION_SERVER_HOST, SESSION_NAME, USER_NAME, null,  Communicator.DIRECT};
//		String[] launcherArgs = {SESSION_SERVER_HOST, "foo", USER_NAME, USER_NAME,  Communicator.DIRECT};

		(new AJoiningIMComposerAndLauncher()).compose(launcherArgs);
	}	
}
