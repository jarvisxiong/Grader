package im.ot;

import util.session.ServerMessageFilter;
import util.session.ServerMessageFilterCreator;

public class AnOTServerMessageFilterCreator  implements ServerMessageFilterCreator{
	ServerMessageFilter  serverMessageQueuer = new AnOTReceivedServerMessageFilter();
	@Override
	public ServerMessageFilter getMessageQueuer() {
		return new AnOTReceivedServerMessageFilter();
	}

}
