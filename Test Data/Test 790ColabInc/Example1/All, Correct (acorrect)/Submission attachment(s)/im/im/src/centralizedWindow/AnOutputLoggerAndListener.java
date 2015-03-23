package centralizedWindow;

import java.util.ArrayList;
import java.util.List;

import util.awt.SerializableFrameRequest;
import util.awt.SerializableGraphicsRequest;
import util.awt.SerializableRequest;
import util.session.Communicator;

public class AnOutputLoggerAndListener implements OutputLoggerAndListener{
	Communicator multicaster;
	List<SerializableRequest> outputLog = new ArrayList();
	public AnOutputLoggerAndListener(Communicator theMulticaster) {
		multicaster = theMulticaster;
	}
	@Override
	public void newFrameRequest(SerializableFrameRequest frameRequest) {
		outputLog.add(frameRequest);
		multicaster.toOthers(frameRequest);
		
		
	}
	@Override
	public void newGraphicsRequest(SerializableGraphicsRequest graphicsRequest) {
		outputLog.add(graphicsRequest);
		multicaster.toOthers(graphicsRequest);
		
	}
	@Override
	public List getOutputLog() {
		return outputLog;
	}
}
