package clean.windowApp;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import util.awt.ADelegateFrame;
import util.awt.DelegateFramePainter;

public class ACharacterDrawer extends ACursorTrackerOfDelegateFrame implements DelegateFramePainter {
	final static int CARAT_LENGTH = 10;
	public ACharacterDrawer(ADelegateFrame theDelegateFrame) {
		super(theDelegateFrame);
		delegateFrame.addPainter(this);
	}
	public void paint(ADelegateFrame theDelegateFrame, Graphics g) {
		g.drawLine(charX, charY, charX, charY - CARAT_LENGTH);
		g.drawString("" + lastChar, charX, charY);
	}
	public void mouseClicked(MouseEvent event) {
		super.mouseClicked(event);
		delegateFrame.repaint();
	}
	public void keyTyped(KeyEvent event) {
		super.keyTyped(event);
		delegateFrame.repaint();
	}
}
