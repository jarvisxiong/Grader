package replicatedWindow;

import trace.im.IMTracerSetter;
import util.annotations.Tags;
import util.session.Communicator;
import util.tags.DistributedTags;
import util.trace.Tracer;
import util.trace.session.ClientJoinFinished;
import util.trace.session.ServerClientJoined;
import util.trace.session.SessionTracerSetter;

@Tags({DistributedTags.CLIENT, DistributedTags.CLIENT_2})
public class BobReplicatedWindows implements ExampleSharedWindowsSession{
	public static final String USER_NAME = DistributedTags.CLIENT_2;
	public static void main (String[] args) {
		String[] launcherArgs = {SESSION_SERVER_HOST, SESSION_NAME, USER_NAME,  APPLICATION_NAME,  Communicator.DIRECT};
//		Tracer.showInfo(true);
//		IMTracerSetter.traceIM();
//		Tracer.setKeywordPrintStatus(ClientJoinFinished.class, true);
//		SessionTracerSetter.setSessionPrintStatus();

//		SessionTracerSetter.traceSession();
//		Tracer.setKeywordPrintStatus(ClientJoined.class, true);
		(new AReplicatedWindowsComposerAndLauncher()).composeAndLaunch(launcherArgs);
	}
	
}
