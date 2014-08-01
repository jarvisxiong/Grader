package clean.windowApp;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import util.awt.ADelegateFrame;
import util.awt.AnInputQueue;
import util.awt.EventQueueHandler;
import util.awt.DelegateFramePainter;
import util.awt.SerializableEvent;

public class AGlobalInputTracker implements EventQueueHandler, MouseListener, KeyListener, DelegateFramePainter {
	String lastFrameTitle;
	ADelegateFrame globalFrame;
	int lastX, lastY;
	char lastChar = ' ';
	public AGlobalInputTracker(ADelegateFrame theGlobalFrame) {
		globalFrame = theGlobalFrame;
		globalFrame.addMouseListener(this);
		globalFrame.addKeyListener(this);
		globalFrame.addPainter(this);
		AnInputQueue.getEventQueue().addEventQueueHandler(this);
	}
	public void newEvent(SerializableEvent event) {
		if (event.getSource() == globalFrame.getID() ||
		    !event.isKeyEvent() && !event.isMousePressedEvent())
			return;
		int frameId = event.getSource();
		ADelegateFrame frame =  ADelegateFrame.getFrame(frameId);
		lastFrameTitle = frame.getTitle();
		globalFrame.processEvent(event);
	}
	public void paint(ADelegateFrame theFrame, Graphics g) {
		g.drawString(lastFrameTitle+':'+lastChar, lastX, lastY);
	}
	public void mousePressed(MouseEvent event) {
		lastX = event.getX();
		lastY = event.getY();
		globalFrame.repaint();
	}
    public void keyPressed(KeyEvent event) {
		lastChar = event.getKeyChar();
		globalFrame.repaint();
	}
	public void mouseClicked(MouseEvent arg0) {}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
	public void keyReleased(KeyEvent arg0) {}
	public void keyTyped(KeyEvent arg0) {}
}
