package im;

import trace.im.IMTracerSetter;
import util.annotations.Tags;
import util.session.Communicator;
import util.tags.ApplicationTags;
import util.tags.DistributedTags;
import util.trace.Tracer;
import util.trace.session.ClientJoinFinished;
import util.trace.session.SessionTracerSetter;


@Tags({DistributedTags.CLIENT, DistributedTags.CLIENT_2, ApplicationTags.IM})

public class BobIM implements ExampleIMSession{
	public static final String USER_NAME = DistributedTags.CLIENT_2;
	public static void main (String[] args) {
		String[] launcherArgs = {SESSION_SERVER_HOST, SESSION_NAME, USER_NAME,  APPLICATION_NAME,  Communicator.RELAYED};
//		Tracer.showInfo(true);
		IMTracerSetter.traceIM();
//		Tracer.setKeywordPrintStatus(ClientJoinFinished.class, true);

//		SessionTracerSetter.setSessionPrintStatus();

		(new AnIMComposerAndLauncher()).composeAndLaunch(launcherArgs);
	}
	
}
