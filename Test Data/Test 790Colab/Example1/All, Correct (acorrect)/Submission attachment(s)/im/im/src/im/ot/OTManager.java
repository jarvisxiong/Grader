package im.ot;

import im.ListEdit;
import util.session.SentMessage;
// Duplex connection between client and server
public interface OTManager {
	public void processTimeStampedEdit(ListEditWithOTTimeStamp receivedTSEdit, String aFromUser, boolean isServer);
	public ListEditWithOTTimeStamp processSentEdit(ListEdit edit );
	public void storeSentMessage(SentMessage message);
	String getUserName();
	boolean isServer();
	String getLocation();
}
