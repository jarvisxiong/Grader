package sasa.echoerAndIM;

import im.AliceIM;
import sasa.echoerAndIM.AnAccessControlledIMComposerAndLauncher;
import sasa.echoerAndIM.AnIMComposerAndLauncher;
import trace.im.aware.AwareIMTracerSetter;
import util.trace.TraceableInfo;
import util.trace.Tracer;

public class AliceAccessControlledIM extends AliceIM{	
	public static void main (String[] args) {
//		Tracer.showInfo(true);
		TraceableInfo.setPrintSource(true);
		AwareIMTracerSetter.traceAwareIM();
		String[] launcherArgs = {SESSION_SERVER_HOST, SESSION_NAME, USER_NAME, AnIMComposerAndLauncher.DIRECT};
		(new AnAccessControlledIMComposerAndLauncher()).composeAndLaunch(launcherArgs);
	}	
}
