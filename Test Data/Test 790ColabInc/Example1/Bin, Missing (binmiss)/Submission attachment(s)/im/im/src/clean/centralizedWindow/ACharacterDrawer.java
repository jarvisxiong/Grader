package clean.centralizedWindow;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import util.awt.ADelegateFrame;
import util.awt.ListenableGraphics;
import util.awt.ListenablePainter;
import windowApp.ACursorTrackerOfDelegateFrame;

public class ACharacterDrawer extends ACursorTrackerOfDelegateFrame implements  ListenablePainter {
	final static int CARAT_LENGTH = 10;
	public ACharacterDrawer(ADelegateFrame theDelegateFrame) {
		super(theDelegateFrame);
		delegateFrame.addPainter(this);
	}
	public void paint (ADelegateFrame theDelegateFrame, ListenableGraphics g) {
		g.drawLine(charX, charY, charX, charY - CARAT_LENGTH);
		g.drawString("" + lastChar, charX, charY);
	}
	public void mousePressed(MouseEvent event) {
		super.mousePressed(event);
		delegateFrame.repaint();
	}
	public void keyTyped(KeyEvent event) {
		super.keyTyped(event);
		delegateFrame.repaint();
	}
}
