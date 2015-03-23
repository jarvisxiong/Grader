package editor.ot;

import util.session.SentMessage;
import widgets.Edit;
// Duplex connection between client and server
public interface OTManager {
	public void processTimeStampedEdit(EditWithOTTimeStamp receivedTSEdit);
	public EditWithOTTimeStamp processSentEdit(Edit edit );
	public void storeSentMessage(SentMessage message);
}
