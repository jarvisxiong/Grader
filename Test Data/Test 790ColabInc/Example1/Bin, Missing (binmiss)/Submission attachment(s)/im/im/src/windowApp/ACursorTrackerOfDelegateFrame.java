package windowApp;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import util.awt.ADelegateFrame;

public class ACursorTrackerOfDelegateFrame implements MouseListener, KeyListener{
	protected ADelegateFrame delegateFrame;
	protected int charX, charY;
	protected char lastChar;		
	public ACursorTrackerOfDelegateFrame(ADelegateFrame theFrame) {
		delegateFrame = theFrame;
		delegateFrame.addMouseListener(this);
		delegateFrame.addKeyListener(this);
	}		
	public void mousePressed(MouseEvent event) {
		charX = event.getX();
		charY = event.getY();
	}
	public void keyTyped(KeyEvent event) {
		lastChar = event.getKeyChar();		
	}	
	public void mouseEntered(MouseEvent event) {	}	
	public void mouseExited(MouseEvent event) {}	
	public void mouseClicked(MouseEvent event) {}	
	public void mouseReleased(MouseEvent event) {}	
	public void keyPressed(KeyEvent event) {}	
	public void keyReleased(KeyEvent event) {} 	
}
