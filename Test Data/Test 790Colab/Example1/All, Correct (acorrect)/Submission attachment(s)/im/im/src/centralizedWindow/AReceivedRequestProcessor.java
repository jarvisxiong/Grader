package centralizedWindow;

import java.util.Collection;
import java.util.List;

import util.awt.ADelegateFrame;
import util.awt.ASerializableGraphicsRequest;
import util.awt.SerializableFrameRequest;
import util.awt.SerializableGraphicsRequest;
import util.session.ReceivedMessageListener;
import util.trace.Tracer;

public class AReceivedRequestProcessor implements ReceivedMessageListener {
	LogPainter logPainter;	
	public AReceivedRequestProcessor(LogPainter theLogPainter) {
		logPainter = theLogPainter;
	}
	@Override
	public void objectReceived(Object message, String sourceName) {		
		processRequest(message);
		
	}
	void processRequest(Object message) {
		if (message instanceof SerializableGraphicsRequest)
			processGraphicsRequest((ASerializableGraphicsRequest) message);
		else if ( message instanceof SerializableFrameRequest) {
			processFrameRequest((SerializableFrameRequest) message);
		}
		else if (message instanceof List) {
			processLog((List) message);			
		}
		
	}
	
	void processLog (List log) {
		for (Object request: log)	
			processRequest(request);
	}
	
	void processFrameRequest (SerializableFrameRequest request) {
		if (request.getName().equals(request.CREATE_FRAME)) {
			int width = (Integer) request.getArgs()[0];
			int height = (Integer) request.getArgs()[1];
			String title = (String) request.getArgs()[2];
			if (request.getSource() != ADelegateFrame.getAllFrames().size()) {
				Tracer.error("Received master frame id: " + request.getSource() + " Expecting: " + ADelegateFrame.getAllFrames().size());
			}
			ADelegateFrame frame = new ADelegateFrame(title);
			frame.addPainter(logPainter);
			frame.setSize(width, height);
			frame.setVisible(true);
			
		} else if (request.getName().equals(request.SET_SIZE)) {
			ADelegateFrame frame = ADelegateFrame.getFrame(request.getSource());
			frame.processRequest(request);
//			int width = (Integer) request.getArgs()[0];
//			int height = (Integer) request.getArgs()[1];
//			
//			frame.setSize(width, height);			
		}
//	} else if (request.getName().equals(request.SET_SIZE)) {
//		int width = (Integer) request.getArgs()[0];
//		int height = (Integer) request.getArgs()[1];
//		ADelegateFrame frame = ADelegateFrame.getFrame(request.getSource());
//		frame.setSize(width, height);			
//	}
		
		
	}
	void processGraphicsRequest (SerializableGraphicsRequest request) {
		if (request.getName().equals(SerializableGraphicsRequest.PAINT_START)) {
			logPainter.clear();
		} else if (request.getName().equals(SerializableGraphicsRequest.PAINT_END)) {
			ADelegateFrame frame = ADelegateFrame.getFrame(request.getSource());
			frame.repaint();
		} else
			logPainter.add(request);
		
	}

	@Override
	public void clientJoined(String userName, String theApplicationName, String theSessionName, boolean newSession, boolean newApplication, Collection<String> allUsers) {
		
		
	}

	@Override
	public void clientLeft(String userName, String theApplicationName) {
		// TODO Auto-generated method stub
		
	}

}
